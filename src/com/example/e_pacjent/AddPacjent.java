package com.example.e_pacjent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddPacjent extends Activity implements OnClickListener {
	private long id;
	private EditText inputContent1, inputContent2, inputContent3;
	Button buttonAdd, buttonBack;
	private Spinner SpinnerList;
	private SQLiteAdapter mySQLiteAdapter;
	ListView listContent;
	RadioGroup rgGender;
	SimpleCursorAdapter cursorAdapter;
	Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_add);
		hideKeyboard();

		inputContent1 = (EditText) findViewById(R.id.content1);
		inputContent2 = (EditText) findViewById(R.id.content2);
		//inputContent3 = (EditText) findViewById(R.id.content3);

		// buttonAdd = (Button) findViewById(R.id.Btnadd);
		View Add = findViewById(R.id.BtnAdd);
		Add.setOnClickListener(this);

		// buttonBack = (Button) findViewById(R.id.Btnback);
		View Back = findViewById(R.id.BtnBack);
		Back.setOnClickListener(this);

		// create a dropdown for users to select various continents
		SpinnerList = (Spinner) findViewById(R.id.SpinnerList);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.krew_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerList.setAdapter(adapter);

		

	}

	public void onClick(View v) {

		if (v.getId() == R.id.BtnAdd) {
			mySQLiteAdapter = new SQLiteAdapter(this);
			mySQLiteAdapter.openToWrite();
			
			rgGender = (RadioGroup) findViewById(R.id.gender);
			int selected = rgGender.getCheckedRadioButtonId();
			RadioButton rbSelected = (RadioButton) findViewById(selected);

			String data1 = inputContent1.getText().toString();
			String data2 = inputContent2.getText().toString();
			//String data3 = inputContent3.getText().toString();
			String data3 = rbSelected.getText().toString();
			String SpinnerData = SpinnerList.getSelectedItem().toString();

			if (data1.trim().equalsIgnoreCase("")) {
				Toast.makeText(getBaseContext(), "Proszê wpisaæ IMIÊ pacjenta",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (data2.trim().equalsIgnoreCase("")) {
				Toast.makeText(getBaseContext(),
						"Proszê wpisaæ NAZWISKO pacjenta", Toast.LENGTH_LONG)
						.show();
				return;
			}

			mySQLiteAdapter.insert(data1, data2, data3, SpinnerData);
			AddPacjent.this.finish();
			// Intent i = new Intent(this, MainActivity.class);
			// startActivity(i);
		}

		if (v.getId() == R.id.BtnBack) {
			AddPacjent.this.finish();

		}

	}

	private void hideKeyboard() {
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
}
