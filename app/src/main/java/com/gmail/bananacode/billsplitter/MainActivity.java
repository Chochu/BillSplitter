package com.gmail.bananacode.billsplitter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        initApp();
    }

    Map<String,Person> personList;
    BillAssistant ba;
    EditText taxtb,tiptb;

    protected void initApp(){
        if (personList == null){
            personList = new HashMap<>();

            ba = new BillAssistant();

            personList.put("You",new Person("You",ba));

            taxtb = findViewById(R.id.taxtb);
            tiptb = findViewById(R.id.tiptb);

            taxtb.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                  ba.refresh(Double.parseDouble(taxtb.getText().toString()),Double.parseDouble(tiptb.getText().toString()));
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });


            tiptb.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    ba.refresh(Double.parseDouble(taxtb.getText().toString()),Double.parseDouble(tiptb.getText().toString()));
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
    }

    public void add_Person(String personName){
        personList.put(personName,new Person(personName, ba));
    }

    public void remove_Person(String personName){
        personList.remove(personName);
    }

    public double get_total(){
        double finalTotal = 0.0;
        for (Map.Entry<String,Person> entry : personList.entrySet()){
            finalTotal += entry.getValue().get_TotalFinal();
        }
        return finalTotal;
    }
}
