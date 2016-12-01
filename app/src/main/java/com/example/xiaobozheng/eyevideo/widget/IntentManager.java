package com.example.xiaobozheng.eyevideo.widget;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.activity.MovieDetailActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment.EXTRA_ITEM;


/**
 * Created by zsj on 2016/10/11.
 */
public class IntentManager {

    public static void flyToMovieDetail(final Activity context,
                                        final ItemList item, final View view) {
        Picasso.with(context).load(item.data.cover.detail)
                .fetch(new Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(EXTRA_ITEM, item);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                context, view, context.getString(R.string.transition_shot));
                        context.startActivity(intent, options.toBundle());
                    }

                    @Override
                    public void onError() {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(EXTRA_ITEM, item);
                        context.startActivity(intent);
                    }
                });
    }
}
