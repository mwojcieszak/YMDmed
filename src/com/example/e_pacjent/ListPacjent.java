package com.example.e_pacjent;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListPacjent extends Activity {
	private SQLiteAdapter mySQLiteAdapter;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list);

		ListView listContent = (ListView) findViewById(R.id.contentlist);

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
		
//		listContent.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//			long arg3) {
//			 ListView lv = (ListView) arg0;
//			 TextView tv = (TextView) lv.getChildAt(arg2);
//			 String s = tv.getText().toString();
//			 Toast.makeText(ListPacjent.this, "Clicked item is"+s, Toast.LENGTH_LONG).show();
//			} });

		mySQLiteAdapter.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
    public void onBackPressed()
    {
		ListPacjent.this.finish();
       
    }
}
