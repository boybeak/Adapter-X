package com.github.boybeak.adapter;

import androidx.databinding.ViewDataBinding;

public interface HolderFactory {
    AbsDataBindingHolder getHolder(int viewType, ViewDataBinding binding);
}
