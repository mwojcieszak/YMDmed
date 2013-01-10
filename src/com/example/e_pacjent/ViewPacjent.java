package com.example.e_pacjent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ViewPacjent extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.row);
 
        TextView txtProduct = (TextView) findViewById(R.id.text2);
 
        Intent i = new Intent();
        // getting attached intent data
        String product = i.getStringExtra("pacjent");
        // displaying selected product name
        txtProduct.setText(product);
 
    }
}
