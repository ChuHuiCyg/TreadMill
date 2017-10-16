package com.sunrise.treadmill.dialog.workoutrunning;

import android.widget.ImageView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.utils.AnimationsContainer;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by ChuHui on 2017/10/9.
 */

public class CountDownDialog extends BaseDialogFragment {
    public static final String TAG = "CountDownDialog";

    @BindView(R.id.workout_running_dialog_count_down_img)
    ImageView img;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_count_down;
    }

    @Override
    protected void init() {
        final AnimationsContainer.FramesSequenceAnimation animation = AnimationsContainer.getInstance(R.array.count_down, 1000).createProgressDialogAnim(img);
        animation.start();
        animation.setOnAnimStopListener((AnimationsContainer.OnAnimationStoppedListener) getActivity());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                animation.stop();
                dismiss();
            }
        }, 4000);
    }
}
