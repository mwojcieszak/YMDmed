package com.example.e_pacjent;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button buttonAdd, buttonList;
	private SQLiteAdapter mySQLiteAdapter;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View Add = findViewById(R.id.menu_add);
		Add.setOnClickListener(this);

		View List = findViewById(R.id.menu_list);
		List.setOnClickListener(this);

		View Exit = findViewById(R.id.menu_exit);
		Exit.setOnClickListener(this);
		
		View DeleteAll = findViewById(R.id.menu_delete);
		DeleteAll.setOnClickListener(this);

	}

	public void onClick(View v) {
		if (v.getId() == R.id.menu_add) {

			Intent i = new Intent(MainActivity.this, AddPacjent.class);
			startActivity(i);
			//startActivityForResult(i,2);
		}
		if (v.getId() == R.id.menu_list) {
			Intent a = new Intent(MainActivity.this, ListPacjent.class);
			// start the second Activity
			this.startActivity(a);
			//startActivityForResult(a,3);
		}
		if (v.getId() == R.id.menu_delete){
			mySQLiteAdapter = new SQLiteAdapter(this);
			mySQLiteAdapter.openToWrite();
			mySQLiteAdapter.deleteAll();
			//Wstawiæ komunikat z potwierdzeniem usuniêcia
			Intent b = new Intent(this, MainActivity.class);
			this.startActivity(b);
		}
		if (v.getId() == R.id.menu_exit) {
			MainActivity.this.finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add:
			Intent dodaj = new Intent(this, AddPacjent.class);
			this.startActivity(dodaj);
			break;

		/*
		 * case R.id.menu_deleteAll: Intent usun = new Intent(this,
		 * Delete.class); this.startActivity(usun); break;
		 */
		case R.id.menu_list:
			Intent lista = new Intent(this, ListPacjent.class);
			this.startActivity(lista);
		case R.id.menu_quit:
			MainActivity.this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
		return true;
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Ask the user if they want to quit
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(R.string.quit)
					.setMessage(R.string.really_quit)
					.setPositiveButton(R.string.yes,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									// Stop the activity
									MainActivity.this.finish();
								}

							}).setNegativeButton(R.string.no, null).show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

}
