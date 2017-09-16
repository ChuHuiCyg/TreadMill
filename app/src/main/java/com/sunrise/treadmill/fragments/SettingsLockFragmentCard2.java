package com.sunrise.treadmill.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsLockFragmentCard2 extends BaseFragment {
    @BindView(R.id.settings_card4_2_customer_psw_value)
    TextView psw;

    @BindViews({R.id.settings_card4_2_customer_psw, R.id.settings_card4_2_srs_psw, R.id.settings_card4_2_srs_psw_value})
    List<TextView> txtList;


    @BindView(R.id.settings_card4_2_reset)
    ImageView reSet;


    @Override
    public int getLayoutId() {
        return R.layout.settings_fragment_card_4_2;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
            TextUtils.setTextTypeFace(psw, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
            TextUtils.setTextTypeFace(psw, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.settings_card4_2_reset)
    public void reSet(View view) {
        TextUtils.changeTextColor(psw, R.color.settings_tabs_on);
        ImageUtils.changeImageView(reSet, R.mipmap.btn_factory_reset_2);

    }

}
