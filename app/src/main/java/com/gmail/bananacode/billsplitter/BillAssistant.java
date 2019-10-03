package com.gmail.bananacode.billsplitter;

public class BillAssistant {

    public double tip;
    public double tax;

    public void refresh(double tax, double tip) {
        this.tax = tax/100;
        this.tip = tip/100;
        System.out.println("Tax:" + this.tax);
        System.out.println("Tip:" + this.tip);
    }
}
