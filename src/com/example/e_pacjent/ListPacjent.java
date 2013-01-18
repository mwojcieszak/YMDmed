package com.example.e_pacjent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.view.View;
import android.app.ListActivity;


public class ListPacjent extends Activity {
	
	public static final String ROW_ID = "row_id";
	private SQLiteAdapter mySQLiteAdapter;
	
	private CursorAdapter cursorAdapter;
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
