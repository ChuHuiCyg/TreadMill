package com.sunrise.treadmill.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.factory.FactoriesActivity;
import com.sunrise.treadmill.activity.settings.SettingsActivity;
import com.sunrise.treadmill.adapter.HomeFragmentAdapter;
import com.sunrise.treadmill.base.BaseFragmentActivity;
import com.sunrise.treadmill.dialog.Home_InitialiteDialog;
import com.sunrise.treadmill.dialog.Home_LanguageDialog;
import com.sunrise.treadmill.fragments.HomeFragmentPage1;
import com.sunrise.treadmill.fragments.HomeFragmentPage2;
import com.sunrise.treadmill.fragments.HomeFragmentPage3;
import com.sunrise.treadmill.interfaces.HomeLanguageDialogReturn;
import com.sunrise.treadmill.utils.ImageUtils;
import com.sunrise.treadmill.views.LogoImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/6.
 */

public class HomeActivity extends BaseFragmentActivity implements HomeLanguageDialogReturn, ViewPager.OnPageChangeListener {

    @BindView(R.id.home_btn_language)
    ImageView btn_language;

    @BindView(R.id.home_btn_setting)
    ImageView btn_setting;

    @BindView(R.id.home_img_vp)
    ImageView selectTg;

    @BindView(R.id.bottom_logo)
    LogoImageView logo;

    @BindView(R.id.home_viewPage)
    ViewPager viewPager;

    private HomeFragmentAdapter fragmentAdapter;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageUtils.changeLanguageIconState(btn_language, false);
        initFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onLanguageReturn(boolean isChange) {
        if (isChange) {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            finishActivity();
            startActivity(intent);
            return;
        } else {
            ImageUtils.changeLanguageIconState(btn_language, isChange);
        }
    }

    @OnClick(R.id.home_btn_language)
    public void changeLanguage() {
        ImageUtils.changeLanguageIconState(btn_language, true);
        Home_LanguageDialog languageDialog = new Home_LanguageDialog();
        languageDialog.show(fragmentManager, Home_LanguageDialog.Home_Language_Dialog);
    }

    @OnClick(R.id.home_btn_setting)
    public void toSettings() {
        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }


    private void initFragment() {
        Home_InitialiteDialog initialiteDialog = new Home_InitialiteDialog();
        initialiteDialog.show(fragmentManager, Home_InitialiteDialog.Home_Initialite_Dialog);

        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new HomeFragmentPage1());
        list.add(new HomeFragmentPage2());
        list.add(new HomeFragmentPage3());
        fragmentAdapter = new HomeFragmentAdapter(fragmentManager, list);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);

        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(HomeActivity.this, FactoriesActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }


    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_1);
                break;
            case 1:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_2);
                break;
            case 2:
                ImageUtils.changeImageView(selectTg, R.mipmap.img_virtual_reality_page_3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
