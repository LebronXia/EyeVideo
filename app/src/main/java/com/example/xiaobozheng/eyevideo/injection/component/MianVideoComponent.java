package com.example.xiaobozheng.eyevideo.injection.component;

import com.example.xiaobozheng.eyevideo.ui.activity.AuthorActivity;
import com.example.xiaobozheng.eyevideo.ui.activity.MainContentActivity;
import com.example.xiaobozheng.eyevideo.ui.activity.MovieDetailActivity;
import com.example.xiaobozheng.eyevideo.ui.activity.SearchActivity;
import com.example.xiaobozheng.eyevideo.ui.activity.SpecialDetailActivity;
import com.example.xiaobozheng.eyevideo.ui.fragment.AuthorTypeFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.SearchFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.SpecialFragment;

import dagger.Component;

/**
 * Created by xiaobozheng on 11/28/2016.
 */
@Component(dependencies = AppComponent.class)
public interface MianVideoComponent {

    ChoiceFragment inject(ChoiceFragment choiceFragment);

    SpecialFragment inject(SpecialFragment specialFragment);

    MovieDetailActivity inject(MovieDetailActivity movieDetailActivity);

    SpecialDetailActivity inject(SpecialDetailActivity specialActivity);

    MainContentActivity inject(MainContentActivity mainContentActivity);

    SearchActivity inject(SearchActivity searchActivity);

    AuthorActivity inject(AuthorActivity authorActivity);

    AuthorTypeFragment inject(AuthorTypeFragment authorTypeFragment);
}
