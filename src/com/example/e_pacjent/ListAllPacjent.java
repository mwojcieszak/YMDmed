package com.example.e_pacjent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListAllPacjent extends Activity {
	private SQLiteAdapter mySQLiteAdapter;
	Cursor cursor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list);

		ListView listContent = (ListView) findViewById(R.id.contentlist);
		try {
			/*
			 * Open the same SQLite database and read all it's content.
			 */
			mySQLiteAdapter = new SQLiteAdapter(this);
			mySQLiteAdapter.openToRead();
	
			Cursor cursor = mySQLiteAdapter.queueAll();
			startManagingCursor(cursor);
	
			String[] from = new String[] { SQLiteAdapter.KEY_CONTENT1,
					SQLiteAdapter.KEY_CONTENT2 };
			int[] to = new int[] { R.id.text1, R.id.text2 };
	
			SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
					R.layout.row, cursor, from, to);
	
			listContent.setAdapter(cursorAdapter);
	
			
		        listContent.setOnItemClickListener(new OnItemClickListener() {
		          public void onItemClick(AdapterView<?> parent, View view,
		              int position, long id) {
		 
		              // selected item

		 
		              // Launching new Activity on selecting single List Item
		              Intent i = new Intent(getApplicationContext(), ViewPacjent.class);
		             // // sending data to new activity
		              //Context context = getApplicationContext();
		             // CharSequence text = patient;
		             // int duration = Toast.LENGTH_SHORT;
	
		              
		              
		              TextView v =(TextView) view.findViewById(R.id.text1);
		              Toast.makeText(getApplicationContext(), "selected Item Name is "+v.getText(), Toast.LENGTH_LONG).show();

		             // startActivity(i);
		 
		          }
		        });
		} catch (Exception e) {
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
		}
		mySQLiteAdapter.close();
    }

}
