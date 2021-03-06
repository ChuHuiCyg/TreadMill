package com.sunrise.treadmill.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.GlobalSetting;
import com.sunrise.treadmill.R;
import com.sunrise.treadmill.utils.LanguageUtils;
import com.sunrise.treadmill.utils.TextUtils;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class WorkOutSettingHead extends LinearLayout {
    private TextView workOutMode;

    private TextView workOutHint;
    private ImageView workOutIcon;

    public WorkOutSettingHead(Context context) {
        this(context, null);
    }

    public WorkOutSettingHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WorkOutSettingHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.workout_setting_head, this, true);
        workOutMode = findViewById(R.id.workout_head_mode);
        workOutHint = findViewById(R.id.workout_head_hint);
        workOutIcon = findViewById(R.id.workout_head_icon);
        if (GlobalSetting.AppLanguage.equals(LanguageUtils.zh_CN)) {
            TextUtils.setTextTypeFace(workOutHint, TextUtils.Microsoft());
            TextUtils.setTextTypeFace(workOutHint, TextUtils.Microsoft());
        } else {
            TextUtils.setTextTypeFace(workOutMode, TextUtils.Arial());
            TextUtils.setTextTypeFace(workOutMode, TextUtils.Arial());
        }
    }

    public void setHeadMsg(String title, String hint, int imgResource) {
        workOutMode.setText(title);
        workOutHint.setText(hint);
        workOutIcon.setImageResource(imgResource);
    }

    public void setWorkOutModeName(String title) {
        workOutMode.setText(title);
    }

    public void setWorkOutHint(String hint) {
        workOutHint.setText(hint);
    }

    public void setWorkOutIcon(int imgResource) {
        workOutIcon.setImageResource(imgResource);
    }

    public void recycle() {
        workOutMode = null;
        workOutHint = null;
        workOutIcon = null;
    }

}
