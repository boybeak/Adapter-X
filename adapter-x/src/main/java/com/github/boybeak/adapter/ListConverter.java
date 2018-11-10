package com.github.boybeak.adapter;

import androidx.annotation.NonNull;

import java.util.Collection;

public interface ListConverter<Data> {
    Collection<LayoutImpl> convert(Data data, @NonNull DataBindingAdapter adapter);
}
