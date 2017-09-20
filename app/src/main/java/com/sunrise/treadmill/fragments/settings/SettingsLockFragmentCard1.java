package com.sunrise.treadmill.fragments.settings;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseFragment;
import com.sunrise.treadmill.interfaces.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;
import com.sunrise.treadmill.views.MyKeyBoardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/14.
 */

public class SettingsLockFragmentCard1 extends BaseFragment implements OnKeyBoardReturn {
    @BindView(R.id.settings_lock_fragment_card_1_1)
    LinearLayout leftLayout;

    @BindView(R.id.settings_card4_1_1_reset)
    ImageView leftReset;

    @BindView(R.id.settings_card4_1_1_time_value)
    TextView timeValue;

    @BindView(R.id.settings_card4_1_1_remaining_time_value)
    TextView remainingTimeValue;


    @BindView(R.id.settings_lock_fragment_card_1_2)
    LinearLayout rightLayout;

    @BindView(R.id.settings_card4_1_2_reset)
    ImageView rightReset;


    @BindView(R.id.settings_card4_1_2_distance_value)
    TextView distanceValue;

    @BindView(R.id.settings_card4_1_2_remaining_distance_value)
    TextView remainingDistanceValue;


    @BindView(R.id.settings_lock_fragment_card_1_2_keyboard)
    MyKeyBoardView rightKeyBoard;

    @BindView(R.id.settings_lock_fragment_card_1_1_keyboard)
    MyKeyBoardView leftKeyBoard;

    private static int reSetTG = -1;
    private static final int reSetTime = 1001;
    private static final int reSetDistance = 1002;

    @Override
    public int getLayoutId() {
        return R.layout.settings_lock_fragment_card_1;
    }

    @Override
    protected void setTextStyle() {
        List<TextView> txtList = new ArrayList<>();

        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_time));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_time_unit));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_remaining_time));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_1_remaining_time_unit));

        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_distance));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_distance_unit));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_remaining_distance));
        txtList.add((TextView) getParentView().findViewById(R.id.settings_card4_1_2_remaining_distance_unit));

        txtList.add(timeValue);
        txtList.add(remainingTimeValue);
        txtList.add(distanceValue);
        txtList.add(remainingDistanceValue);

        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(txtList, TextUtils.MicrosoftBold(getContext()));
        } else {
            TextUtils.setTextTypeFace(txtList, TextUtils.ArialBold(getContext()));
        }
    }

    @Override
    protected void init() {
        leftKeyBoard.setTitleImage(R.mipmap.tv_keybord_time);
        rightKeyBoard.setTitleImage(R.mipmap.tv_keybord_distance);

        leftKeyBoard.setKeyBoardReturn(this);
        rightKeyBoard.setKeyBoardReturn(this);
    }

    @OnClick({R.id.settings_card4_1_1_reset, R.id.settings_card4_1_2_reset})
    public void reSet(View view) {
        switch (view.getId()) {
            case R.id.settings_card4_1_1_reset:
                reSetTG = reSetTime;
                rightLayout.setVisibility(View.GONE);
                leftKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.settings_tabs_on));
                break;
            case R.id.settings_card4_1_2_reset:
                reSetTG = reSetDistance;
                leftLayout.setVisibility(View.GONE);
                rightKeyBoard.setVisibility(View.VISIBLE);
                TextUtils.changeTextColor(distanceValue, getResources().getColor(R.color.settings_tabs_on));
                break;
            default:
                break;
        }
    }

    @Override
    public void onEnter(String result) {
        switch (reSetTG) {
            case reSetTime:
                timeValue.setText(result);
                break;
            case reSetDistance:
                distanceValue.setText(result);
                break;
            default:
                break;
        }
    }

    @Override
    public void onKeyBoardHide() {
        switch (reSetTG) {
            case reSetTime:
                TextUtils.changeTextColor(timeValue, getResources().getColor(R.color.settings_white));
                rightLayout.setVisibility(View.VISIBLE);
                leftKeyBoard.setVisibility(View.GONE);
                break;
            case reSetDistance:
                TextUtils.changeTextColor(distanceValue, getResources().getColor(R.color.settings_white));
                leftLayout.setVisibility(View.VISIBLE);
                rightKeyBoard.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
