package com.example.brevaide;

import java.util.ArrayList;

import data.brevaide.QCM;
import db.brevaide.Db_QCM;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Stats extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		Db_QCM dbQcm = new Db_QCM(getApplicationContext());
		
		ArrayList<QCM> qcms = dbQcm.getAllQCM();
		int counterQcms = qcms.size();
		int average = 0;
		
		final TextView mean = (TextView) findViewById(R.id.textView1);
		final TextView counter = (TextView) findViewById(R.id.textView2);
		
		for(int i=0;i<counterQcms;i++)
		{	
			average += qcms.get(i).getScore();
			Log.i("Mania",qcms.get(i).toString());
		}
		if(counterQcms != 0)
			average = average/counterQcms;
		
		counter.setText("Nombre de tests : " + Integer.toString(counterQcms));
		mean.setText("Moyenne des tests : " + Integer.toString(average) + "%");
		
		dbQcm.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats_reset, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Db_QCM dbQcm = new Db_QCM(getApplicationContext());
		
		switch (item.getItemId()) {
			case R.id.reset_qcm_stats:
				dbQcm.open();
 				dbQcm.reset();
 				dbQcm.close();
 				// Refreshing
 				
 				Intent intent = getIntent();
 				overridePendingTransition(0, 0);
 				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
 				finish();
 				overridePendingTransition(0, 0);
 				startActivity(intent);
 				
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
 

}
