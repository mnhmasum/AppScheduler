package com.meldcx.appscheduler.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.meldcx.appscheduler.BR;

public class Item extends BaseObservable {
    private int selectedItemPosition;

    @Bindable
    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        this.selectedItemPosition = selectedItemPosition;
        notifyPropertyChanged(BR.selectedItemPosition);
    }
}