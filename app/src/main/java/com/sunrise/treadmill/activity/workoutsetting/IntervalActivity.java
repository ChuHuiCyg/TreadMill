package com.sunrise.treadmill.activity.workoutsetting;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.workoutsetting.OnGenderReturn;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyGenderView;
import com.sunrise.treadmill.views.MyKeyBoardView;
import com.sunrise.treadmill.views.MyWorkOutHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/22.
 */

public class IntervalActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {
    @BindView(R.id.workout_mode_head)
    MyWorkOutHead headView;

    @BindView(R.id.workout_mode_gender_select)
    MyGenderView genderView;

    @BindView(R.id.workout_mode_keyboard)
    MyKeyBoardView keyBoardView;

    @BindView(R.id.workout_edit_age_value)
    TextView ageValue;
    @BindView(R.id.workout_edit_weight_value)
    TextView weightValue;
    @BindView(R.id.workout_edit_time_value)
    TextView timeValue;

    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_interval;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<TextView>();
        txtList.add((TextView) headView.findViewById(R.id.workout_head_mode));
        txtList.add((TextView) headView.findViewById(R.id.workout_head_hint));

        txtList.add((TextView) findViewById(R.id.workout_edit_age));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight));
        txtList.add((TextView) findViewById(R.id.workout_edit_weight_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_time));
        txtList.add((TextView) findViewById(R.id.workout_edit_time_unit));

        txtList.add((TextView) findViewById(R.id.workout_edit_start_hint_1));

        txtList.add(ageValue);
        txtList.add(weightValue);
        txtList.add(timeValue);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.Microsoft(this));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.Arial(this));
        }
    }

    @Override
    public void genderReturn(int gender) {

    }

    @Override
    public void onKeyBoardEnter(String result) {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_AGE:
                ageValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_WEIGHT:
                weightValue.setText(result);
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                timeValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardClose() {
        switch (WorkOutSettingCommon.changeTg) {
            default:
                break;
            case WorkOutSettingCommon.CHANGE_AGE:
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_white));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_WEIGHT:
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_white));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_1);
                break;
            case WorkOutSettingCommon.CHANGE_TIME:
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_white));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_1);
                break;
        }
        WorkOutSettingCommon.changeTg = WorkOutSettingCommon.RE_SET;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_interval), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_interval_icon);
        genderView.setOnGenderReturn(this);
        keyBoardView.setKeyBoardReturn(this);
    }


    private boolean isShowingKeyBoard = false;

    @OnClick({R.id.workout_edit_age_value, R.id.workout_edit_weight_value, R.id.workout_edit_time_value})
    public void changValue(View view) {
        if (isShowingKeyBoard) {
            return;
        }
        isShowingKeyBoard = true;
        keyBoardView.setVisibility(View.VISIBLE);
        genderView.setVisibility(View.GONE);
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_edit_age_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_AGE;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_age);
                TextUtils.changeTextColor(ageValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_tabs_on));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_weight_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_WEIGHT;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_weight);
                TextUtils.changeTextColor(weightValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_tabs_on));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_time_value:
                WorkOutSettingCommon.changeTg = WorkOutSettingCommon.CHANGE_TIME;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(timeValue, ContextCompat.getColor(getApplicationContext(), R.color.factory_tabs_on));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_2);
                break;
        }
    }

    @OnClick({R.id.workout_mode_start_1})
    public void beginWorkOut() {

    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }

}
