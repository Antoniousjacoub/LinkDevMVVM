package com.example.linkdevmvvm.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment<D extends ViewDataBinding, M extends BaseViewModel> extends Fragment {
    private D mViewDataBinding;
    private M mViewModel;

    protected abstract int getBindingVariable();
    protected abstract @LayoutRes int getLayoutId();
    protected abstract M getViewModel();
    public D getViewDataBinding() {
        return mViewDataBinding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        performDataBinding(inflater, container);
        return getViewDataBinding().getRoot();

    }

    private void performDataBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (getActivity() == null) return;
        mViewDataBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), viewGroup, false);
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


}

