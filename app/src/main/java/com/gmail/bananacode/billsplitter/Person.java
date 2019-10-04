package com.gmail.bananacode.billsplitter;

import java.util.HashMap;
import java.util.Map;

public class Person {

    double total;
    String name;
    BillAssistant ba;
    Map<String, Double> LItem;

    public Person(String name, BillAssistant ba){
        this.total = 0.0;
        this.name = name;
        this.ba = ba;
        LItem = new HashMap<>();
    }

    public void add_item(String itemname, double amoount){
        LItem.put(itemname, amoount);
        total += amoount;
    }

    public void remove_item(String itemname){
        double sub_amount = LItem.get(itemname);
        LItem.remove(itemname);
        total -= sub_amount;
    }

    public String getName()  {
        return name;
    }

    public double get_TotalFinal(){
        return total + (total * ba.tax) + (total * ba.tip);
    }

    public double get_totaltip(){
        return (total * ba.tip);
    }

    public double get_totaltax(){
        return (total * ba.tax);
    }

    public double get_total(){
        return total;
    }
}
