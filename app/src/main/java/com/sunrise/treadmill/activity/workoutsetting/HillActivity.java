package com.sunrise.treadmill.activity.workoutsetting;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.activity.workoutrunning.HillRunningActivity;
import com.sunrise.treadmill.base.BaseActivity;
import com.sunrise.treadmill.interfaces.OnGenderReturn;
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
 * Created by ChuHui on 2017/9/20.
 */

public class HillActivity extends BaseActivity implements OnGenderReturn, OnKeyBoardReturn {
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

    @BindView(R.id.workout_mode_start_1)
    ImageView startBtn;


    @Override
    public int getLayoutId() {
        return R.layout.activity_workout_setting_hill;
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
    protected void init() {
        headView.setHeadMsg(getResources().getString(R.string.workout_mode_hill), getResources().getString(R.string.workout_mode_hint_f), R.mipmap.img_program_hill_icon);
        genderView.setOnGenderReturn(this);
        keyBoardView.setKeyBoardReturn(this);
    }

    @Override
    public void genderReturn(int gender) {

    }


    @Override
    public void onEnter(String result) {
        switch (reSetTG) {
            default:
                break;
            case reSetAge:
                ageValue.setText(result);
                break;
            case reSetWeight:
                weightValue.setText(result);
                break;
            case reSetTime:
                timeValue.setText(result);
                break;
        }
    }

    @Override
    public void onKeyBoardHide() {
        switch (reSetTG) {
            default:
                break;
            case reSetAge:
                TextUtils.changeTextColor(ageValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_1);
                break;
            case reSetWeight:
                TextUtils.changeTextColor(weightValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_1);
                break;
            case reSetTime:
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.factory_white));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_1);
                break;
        }
        reSetTG = reSetTG;
        isShowingKeyBoard = false;
        keyBoardView.setVisibility(View.GONE);
        genderView.setVisibility(View.VISIBLE);
        startBtn.setEnabled(true);
    }

    private boolean isShowingKeyBoard = false;
    private static int reSetTG = -1;
    private static final int reSetAge = 1001;
    private static final int reSetWeight = 1002;
    private static final int reSetTime = 1003;

    @OnClick({R.id.workout_edit_age_value, R.id.workout_edit_weight_value, R.id.workout_edit_time_value})
    public void changValue(View view) {
        if (isShowingKeyBoard) {
            return;
        }
        isShowingKeyBoard = true;
        keyBoardView.setVisibility(View.VISIBLE);
        genderView.setVisibility(View.GONE);
        startBtn.setEnabled(false);
        switch (view.getId()) {
            default:
                break;
            case R.id.workout_edit_age_value:
                reSetTG = reSetAge;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_age);
                TextUtils.changeTextColor(ageValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(ageValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_weight_value:
                reSetTG = reSetWeight;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_weight);
                TextUtils.changeTextColor(weightValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(weightValue, R.mipmap.img_number_frame_2);
                break;
            case R.id.workout_edit_time_value:
                reSetTG = reSetTime;
                keyBoardView.setTitleImage(R.mipmap.tv_keybord_time);
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.factory_tabs_on));
                TextUtils.changeTextBackground(timeValue, R.mipmap.img_number_frame_2);
                break;
        }
    }

    @OnClick({R.id.workout_mode_start_1})
    public void beginWorkOut() {
        Intent intent = new Intent(HillActivity.this, HillRunningActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bottom_logo_tab_home)
    public void onBackHome() {
        finishActivity();
    }
}