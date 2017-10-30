package com.sunrise.treadmill.dialog.factory;

import android.view.View;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.base.BaseDialogFragment;
import com.sunrise.treadmill.interfaces.factory.OnFactory2Fragment2Card2DialogReturn;

import butterknife.OnClick;

/**
 * Created by ChuHui on 2017/9/21.
 */

public class Factory2FragmentCard2Dialog extends BaseDialogFragment {
    public static final String TAG = "Factory2FragmentCard2Dialog";
    private OnFactory2Fragment2Card2DialogReturn dialogReturn;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_factory2_card2;
    }

    @Override
    public void recycleObject() {
        dialogReturn = null;
    }

    @OnClick({R.id.dialog_factory2_card2_yes, R.id.dialog_factory2_card2_no})
    public void conformReset(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.dialog_factory2_card2_yes:
                dialogReturn.onYes();
                dismiss();
                break;
            case R.id.dialog_factory2_card2_no:
                dismiss();
                break;
        }
    }

    public void setDialogReturn(OnFactory2Fragment2Card2DialogReturn dialogReturn) {
        this.dialogReturn = dialogReturn;
    }

}
