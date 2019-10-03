package com.gmail.bananacode.billsplitter;

import com.gmail.bananacode.billsplitter.ui.BillAssistant;

import java.util.HashMap;
import java.util.Map;

public class Person {
    double total;
    String name;
    BillAssistant ba;
    Map<String, Double> LItem;

    protected Person(String name, BillAssistant ba){
        this.total = 0.0;
        this.name = name;
        this.ba = ba;
        LItem = new HashMap<>();
    }

    protected void add_item(String itemname, double amoount){
        LItem.put(itemname, amoount);
        total += amoount;
    }

    protected void remove_item(String itemname){
        double sub_amount = LItem.get(itemname);
        LItem.remove(itemname);
        total -= sub_amount;
    }

    protected double get_TotalFinal(){
        return (total * ba.tax) + (total * ba.tip);
    }

    protected double get_totaltip(){
        return (total * ba.tip);
    }

    protected double get_totaltax(){
        return (total * ba.tax);
    }

    protected double get_total(){
        return total;
    }
}
