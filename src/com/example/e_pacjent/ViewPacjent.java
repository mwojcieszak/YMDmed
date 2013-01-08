package com.example.e_pacjent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ViewPacjent extends Activity {
	
	   private long rowID;
	   private TextView nameTv;
	   private TextView surnameTv;
	   private TextView plecTv; 
	   private TextView krewTv;
	   
	   @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	     // setContentView(R.layout.view_country);
	      
	      setUpViews();
	      Bundle extras = getIntent().getExtras();
	      //rowID = extras.getLong(CountryList.ROW_ID); 
	   }
	   
	   private void setUpViews() {
		  // nameTv = (TextView) findViewById(R.id.name);
		  // surnameTv = (TextView) findViewById(R.id.surname);
		  // peselTv = (TextView) findViewById(R.id.pesel);
		  // krewTv = (TextView) findViewById(R.id.krew);
	   }
}
