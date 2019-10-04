package com.gmail.bananacode.billsplitter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.gmail.bananacode.billsplitter.adapters.PersonRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Map<String, Person> personList;
    BillAssistant ba;
    EditText taxtb,tiptb;

    protected void initApp(){
        Log.d(TAG, "initApp: Started.");
        
        if (personList == null){
            personList = new HashMap<>();

            ba = new BillAssistant();

            personList.put("You",new Person("You",ba));

            taxtb = findViewById(R.id.taxtb);
            tiptb = findViewById(R.id.tiptb);

            taxtb.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    ba_refresh();
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });


            tiptb.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    ba_refresh();
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
    }
    
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerview = findViewById(R.id.recyclerv_view);
        PersonRecyclerAdapter adapter = new PersonRecyclerAdapter(personList, this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this ));
    }

    public void ba_refresh(){
        Log.d(TAG, "ba_refresh: Refreshing Tip and Tax");
        ba.refresh(Double.parseDouble( taxtb.getText().toString().isEmpty()? "0" : taxtb.getText().toString()),
                Double.parseDouble( tiptb.getText().toString().isEmpty()? "0" : tiptb.getText().toString())
        );
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
        initRecyclerView();
    }


}
