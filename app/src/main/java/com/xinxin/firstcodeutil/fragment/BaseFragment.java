package com.xinxin.firstcodeutil.fragment;

import android.app.Fragment;

import rx.Subscription;

/**
 * Created by xinxin on 2016/5/6.
 */
public abstract class BaseFragment extends Fragment {

    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract int getDialogRes();
    protected abstract int getTitleRes();
}
