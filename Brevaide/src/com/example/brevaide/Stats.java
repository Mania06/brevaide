package com.example.brevaide;

import java.util.ArrayList;

import data.brevaide.QCM;
import db.brevaide.Db_QCM;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		final Button reset = (Button) findViewById(R.id.button1);
		
		for(int i=0;i<counterQcms;i++)
		{	
			average += qcms.get(i).getScore();
			Log.i("Mania",qcms.get(i).toString());
		}
		if(counterQcms != 0)
			average = average/counterQcms;
		
		counter.setText("Nombre de tests : " + Integer.toString(counterQcms));
		mean.setText("Moyenne : " + Integer.toString(average) + "%");
		
		reset.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				dbQcm.reset();
				finish();
				startActivity(getIntent());
			}
			
		});
	}
	
	

}
