package com.example.e_pacjent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewPacjent extends Activity {
	
	   private long rowID;
	   private TextView imieTv;
	   private TextView nazwiskoTv;
	   private TextView plecTv; 
	   private TextView krewTv;
	
	   
	   @Override
	   public void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.layout_view_pacjent);
	      
	      setUp();
	      Bundle extras = getIntent().getExtras();
	      rowID = extras.getLong(ListPacjent.ROW_ID);
	      
	   }
	     
	   private void setUp(){
		   imieTv = (TextView) findViewById(R.id.name);
		   nazwiskoTv = (TextView) findViewById(R.id.surname);
		   plecTv = (TextView) findViewById(R.id.plec);
		   krewTv = (TextView) findViewById(R.id.krew);
	   }
	   
	   @Override
	   protected void onResume()
	   {
	      super.onResume();
	      new LoadContacts().execute(rowID);
	   }
	   
	   private class LoadContacts extends AsyncTask<Long, Object, Cursor> 
	   {
	      SQLiteAdapter dbConnector = new SQLiteAdapter(ViewPacjent.this);
	      
	      @Override
	      protected Cursor doInBackground(Long... params)
	      {
	         dbConnector.openToRead();
	         return dbConnector.getOneContact(params[0]);
	      } 

	      @Override
	      protected void onPostExecute(Cursor result)
	      {
	         super.onPostExecute(result);
	   
	         result.moveToFirst();
	         // get the column index for each data item
	         int imieIndex = result.getColumnIndex("Imie");
	         int nazwiskoIndex = result.getColumnIndex("Nazwisko");
	         int peselIndex = result.getColumnIndex("Plec");
	         int krewIndex = result.getColumnIndex("Krew");
	   
	         imieTv.setText(result.getString(imieIndex));
	         nazwiskoTv.setText(result.getString(nazwiskoIndex));
	         plecTv.setText(result.getString(peselIndex));
	         krewTv.setText(result.getString(krewIndex));
	   
	         result.close();
	         dbConnector.close();
	      }
	   } 
	   
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) 
	   {
	      super.onCreateOptionsMenu(menu);
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.menu_view_pacjent, menu);
	      return true;
	   }
	   
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) 
	   {
	      switch (item.getItemId())
	      {
	         case R.id.editItem:
	            Intent addEditContact =
	               new Intent(this, AddPacjent.class);
	           // tutaj trzeba zrobic edycje
	            startActivity(addEditContact); 
	            return true;
	            
	         case R.id.deleteItem:
	            deleteContact();
	            return true;
	            
	         default:
	            return super.onOptionsItemSelected(item);
	      } 
	   }
	   
	   private void deleteContact()
	   {
	     
	      AlertDialog.Builder alert = new AlertDialog.Builder(ViewPacjent.this);

	      alert.setTitle(R.string.confirmTitle); 
	      alert.setMessage(R.string.confirmMessage); 

	      alert.setPositiveButton(R.string.delete_btn,
	         new DialogInterface.OnClickListener()
	         {
	            public void onClick(DialogInterface dialog, int button)
	            {
	               final SQLiteAdapter dbConnector = 
	                  new SQLiteAdapter(ViewPacjent.this);

	               AsyncTask<Long, Object, Object> deleteTask =
	                  new AsyncTask<Long, Object, Object>()
	                  {
	                     @Override
	                     protected Object doInBackground(Long... params)
	                     {
	                        dbConnector.deleteContact(params[0]); 
	                        return null;
	                     } 
	                     
	                     @Override
	                     protected void onPostExecute(Object result)
	                     {
	                        finish(); 
	                     }
	                  };

	               deleteTask.execute(new Long[] { rowID });               
	            }
	         }
	      );
	      
	      alert.setNegativeButton(R.string.cancel_btn, null).show();
	   }
	   
	   
}
