package com.example.e_pacjent;

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


public class ListAllPacjent extends ListActivity {
	
	 public static final String ROW_ID = "row_id";
	 private ListView conListView;
	 private CursorAdapter cursorAdapter;
	 
	 Cursor cursor;
	   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        conListView=getListView();
        conListView.setOnItemClickListener(viewConListener);
        
      
        String[] from = new String[] { SQLiteAdapter.KEY_CONTENT1,
				SQLiteAdapter.KEY_CONTENT2 };
		int[] to = new int[] { R.id.text1, R.id.text2};

		cursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
               
        setListAdapter(cursorAdapter); // set adapter
    }
	
	
	
	
    @Override
    protected void onResume() 
    {
       super.onResume();  
       new GetContacts().execute((Object[]) null);
     } 
	
	
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) 
	    {
	       super.onCreateOptionsMenu(menu);
	       MenuInflater inflater = getMenuInflater();
	       inflater.inflate(R.menu.activity_main, menu);
	       return true;
	    }   
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) 
	    {
	       Intent addContact = new Intent(ListAllPacjent.this, AddPacjent.class);
	       startActivity(addContact);
	       return super.onOptionsItemSelected(item);
	    }
	
	
	@Override
    public void onBackPressed()
    {
		ListAllPacjent.this.finish();
       
    }
	
	private class GetContacts extends AsyncTask<Object, Object, Cursor> 
    {
       SQLiteAdapter dbConnector = new SQLiteAdapter(ListAllPacjent.this);

       @Override
       protected Cursor doInBackground(Object... params)
       {
          dbConnector.openToRead();
          return dbConnector.queueAll(); 
       } 
       
       @Override
       protected void onPostExecute(Cursor result)
       {
          cursorAdapter.changeCursor(result); // set the adapter's Cursor
          dbConnector.close();
       } 
    } 
	
	OnItemClickListener viewConListener = new OnItemClickListener() 
    {
       public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
       {         
          Intent viewCon = new Intent(ListAllPacjent.this, ViewPacjent.class);
          viewCon.putExtra(ROW_ID, arg3);
          startActivity(viewCon);
       }
    };  

}
