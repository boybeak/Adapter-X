package com.github.boybeak.adapter;

import androidx.annotation.NonNull;

public interface Converter<Data, Layout extends LayoutImpl> {
    Layout convert (Data data, @NonNull DataBindingAdapter adapter);
}
