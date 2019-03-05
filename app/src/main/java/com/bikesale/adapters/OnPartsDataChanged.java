package com.bikesale.adapters;

public interface OnPartsDataChanged {
    void totalSoldChanged(double sold);
    void totalCalculatedProfitsChanged(double totalCalculated, double minTotalCalculated);
}
