package com.gmail.bananacode.billsplitter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CustomDialog extends AppCompatDialogFragment {

    private EditText uio_valueText;
    private CustomDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_customdialog, null);

        uio_valueText = view.findViewById(R.id.uio_value);

        builder.setView(view).setTitle(getArguments().getString("Title")).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton(getArguments().getString("ConfirmText"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!uio_valueText.getText().toString().isEmpty()) {
                    String inputValue = uio_valueText.getText().toString();
                    listener.getDialogValue(inputValue);
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CustomDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement CustomDialogListener");
        }
    }

    public interface  CustomDialogListener{
        void getDialogValue(String pValue);
    }
}
