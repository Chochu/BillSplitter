package com.gmail.bananacode.billsplitter.ui;

public class BillAssistant {

    double tip;
    double tax;

    protected void refresh() {
        this.tax = 1 + tax;
        this.tip = 1 + tip;
    }
}
