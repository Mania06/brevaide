package com.example.brevaide;

import java.util.ArrayList;

import data.brevaide.QCM;
import db.brevaide.Db_QCM;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Activity;

public class Stats extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		
		final Db_QCM dbQcm = new Db_QCM(getApplicationContext());
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
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stats_reset, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final Db_QCM dbQcm = new Db_QCM(getApplicationContext());
		Log.i("Mania", ""+item.getItemId());
		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			
			case R.id.reponse :
  				dbQcm.reset();
				finish();
				startActivity(getIntent());		
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
