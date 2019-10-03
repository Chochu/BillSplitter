package com.gmail.bananacode.billsplitter;

public class BillAssistant {

    public double tip;
    public double tax;

    public void refresh(double tax, double tip) {
        this.tax = 1 + tax;
        this.tip = 1 + tip;
        System.out.println("Tax:" + this.tax);
        System.out.println("Tip:" + this.tip);
    }
}
