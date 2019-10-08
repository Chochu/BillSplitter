package com.gmail.bananacode.billsplitter;

import android.app.Dialog;
import android.view.View;
import android.widget.Toast;

public class CustomListener implements View.OnClickListener {
    private final Dialog dialog;
    public CustomListener(Dialog dialog) {
        this.dialog = dialog;
    }
    @Override
    public void onClick(View v) {
        // put your code here
        String mValue = mEdtText.getText().toString();
        if(validate(mValue)){
            dialog.dismiss();
        }else{
            Toast.makeText(YourActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
        }
    }
}
