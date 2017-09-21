package com.sunrise.treadmill.activity.factory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.fragments.factory.Factory2FragmentCard1;
import com.sunrise.treadmill.fragments.factory.Factory2FragmentCard2;
import com.sunrise.treadmill.fragments.factory.Factory2FragmentCard3;
import com.sunrise.treadmill.fragments.factory.Factory2FragmentCard4;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/13.
 */

public class Factory2Activity extends BaseFragmentActivity {

    @BindView(R.id.factory_2_view_bg)
    LinearLayout bgView;

    @BindViews({R.id.factory_2_card_setting, R.id.factory_2_card_info, R.id.factory_2_card_update, R.id.factory_2_card_logo, R.id.factory_2_title})
    List<TextView> txtList;

    @BindView(R.id.bottom_logo_tab_home)
    ImageButton backHome;


    private FragmentManager fragmentManager;
    private Fragment nowFragment;
    private Factory2FragmentCard1 card1;
    private Factory2FragmentCard2 card2;
    private Factory2FragmentCard3 card3;
    private Factory2FragmentCard4 card4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_factory2;
    }

    @Override
    protected void setTextStyle() {
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(this));
        }
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

    @OnClick({R.id.factory_2_card_setting, R.id.factory_2_card_info, R.id.factory_2_card_update, R.id.factory_2_card_logo})
    public void onSelectCardClick(View view) {
        int bgResource = -1;
        int tgCard = -1;
        Fragment tgFragment = null;
        switch (view.getId()) {
            default:
                bgResource = -1;
                tgCard = -1;
                tgFragment = null;
                break;
            case R.id.factory_2_card_setting:
                bgResource = R.mipmap.img_factory_3_1;
                tgCard = 0;
                if (card1 == null) {
                    card1 = new Factory2FragmentCard1();
                }
                tgFragment = card1;
                break;
            case R.id.factory_2_card_info:
                bgResource = R.mipmap.img_factory_3_2;
                tgCard = 1;
                if (card2 == null) {
                    card2 = new Factory2FragmentCard2();
                }
                tgFragment = card2;
                break;
            case R.id.factory_2_card_update:
                bgResource = R.mipmap.img_factory_3_3;
                tgCard = 2;
                if (card3 == null) {
                    card3 = new Factory2FragmentCard3();
                }
                tgFragment = card3;
                break;
            case R.id.factory_2_card_logo:
                bgResource = R.mipmap.img_factory_3_4;
                tgCard = 3;
                if (card4 == null) {
                    card4 = new Factory2FragmentCard4();
                }
                tgFragment = card4;
                break;
        }
        bgView.setBackgroundResource(bgResource);
        if (bgResource != -1 && tgFragment != null) {
            for (int i = 0; i < txtList.size() - 1; i++) {
                if (i == tgCard) {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_on));
                    TextUtils.changeTextSize(txtList.get(i), 30f);
                } else {
                    TextUtils.changeTextColor(txtList.get(i), getColor(R.color.settings_tabs_off));
                    TextUtils.changeTextSize(txtList.get(i), 25f);
                }
            }
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (!tgFragment.isAdded()) {
                ft.hide(nowFragment).add(R.id.factory_2_layout, tgFragment).commit();
            } else {
                ft.hide(nowFragment).show(tgFragment).commit();
            }
            nowFragment = tgFragment;
        }
    }
    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
        card1 = new Factory2FragmentCard1();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.factory_2_layout, card1).commit();
        nowFragment = card1;
    }
}
