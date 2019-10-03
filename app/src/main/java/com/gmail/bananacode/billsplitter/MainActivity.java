package com.gmail.bananacode.billsplitter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.gmail.bananacode.billsplitter.ui.BillAssistant;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

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

    ArrayList<Person> personList;
    BillAssistant ba;

    protected void initApp(){
        if (personList == null){
            personList = new ArrayList<Person>();
            ba = new BillAssistant();
            yourEditText = (EditText) findViewById(R.id.yourEditTextId);

            yourEditText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {

                    // you can call or do what you want with your EditText here

                    // yourEditText...
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
    }

    protected void add_Person(String personname){
        PersonList.add(new Person(personname))
    }

    protected void remove_Person(String itemname){
        double sub_amount = LItem.get(itemname);
        LItem.remove(itemname);
        total -= sub_amount;
    }
}
