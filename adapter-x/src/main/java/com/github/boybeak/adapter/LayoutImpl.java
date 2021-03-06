package com.github.boybeak.adapter;

import androidx.annotation.LayoutRes;

/**
 * Created by gaoyunfei on 2018/3/8.
 */

public interface LayoutImpl<Data> {
    String id();
    void setId(String id);
    Data getSource();
    void setSource(Data data);
    <T> T getSourceUnSafe();
    Class<? extends AbsDataBindingHolder> getHolderClass();
    @LayoutRes int getLayout();

    void setSelected(boolean selected);
    boolean isSelected();

    void setSelectable(boolean selectable);
    boolean isSelectable();

    boolean supportSelect();

}