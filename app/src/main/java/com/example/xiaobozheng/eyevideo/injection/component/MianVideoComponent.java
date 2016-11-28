package com.example.xiaobozheng.eyevideo.injection.component;

import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;

import dagger.Component;

/**
 * Created by xiaobozheng on 11/28/2016.
 */
@Component(dependencies = AppComponent.class)
public interface MianVideoComponent {
    ChoiceFragment inject(ChoiceFragment choiceFragment);
}
