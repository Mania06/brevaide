package com.example.brevaide;

import java.util.ArrayList;

import data.brevaide.QCM;
import db.brevaide.Db_QCM;
import android.os.Bundle;
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
		
		ArrayList<QCM> mathsQCMs = dbQcm.getQCMsbyMatiere("maths");
		ArrayList<QCM> historyQCMs = dbQcm.getQCMsbyMatiere("history");
		ArrayList<QCM> frenchQCMs = dbQcm.getQCMsbyMatiere("french");
		
		
		int mathsCounterQcms = mathsQCMs.size();
		int historyCounterQcms = historyQCMs.size();
		int frenchCounterQcms = frenchQCMs.size();
		int globalCounterQcms = frenchCounterQcms + historyCounterQcms + mathsCounterQcms;
		
		int mathsAverage = 0;
		int historyAverage = 0;
		int frenchAverage = 0;
		int globalAverage = 0;
		
		final TextView globalMean = (TextView) findViewById(R.id.globalMean);
		final TextView globalCounter = (TextView) findViewById(R.id.globalCounter);
		
		final TextView frenchMean = (TextView) findViewById(R.id.frenchMean);
		final TextView frenchCounter = (TextView) findViewById(R.id.frenchCounter);

		final TextView mathsMean = (TextView) findViewById(R.id.mathsMean);
		final TextView mathsCounter = (TextView) findViewById(R.id.mathsCounter);
		
		final TextView historyMean = (TextView) findViewById(R.id.historyMean);
		final TextView historyCounter = (TextView) findViewById(R.id.historyCounter);
		
		for(int i=0;i<mathsCounterQcms;i++)
		{	
			mathsAverage += mathsQCMs.get(i).getScore();
		}
		for(int i=0;i<frenchCounterQcms;i++)
		{	
			frenchAverage += frenchQCMs.get(i).getScore();
		}
		for(int i=0;i<mathsCounterQcms;i++)
		{	
			historyAverage += historyQCMs.get(i).getScore();
		}
		globalAverage = frenchAverage + mathsAverage + historyAverage;
		
		if(frenchCounterQcms != 0)
			frenchAverage = frenchAverage/frenchCounterQcms;
		
		if(mathsCounterQcms != 0)
			mathsAverage = mathsAverage/mathsCounterQcms;
		
		if(historyCounterQcms != 0)
			historyAverage = historyAverage/historyCounterQcms;
		
		if(globalCounterQcms != 0)
			globalAverage = globalAverage/globalCounterQcms;
		
		historyCounter.setText("Nombre de test en histoire : " + Integer.toString(historyCounterQcms));
		historyMean.setText("Moyenne en histore : " + Integer.toString(historyAverage) + "%");

		frenchCounter.setText("Nombre de test en frencais : " + Integer.toString(frenchCounterQcms));
		frenchMean.setText("Moyenne en frencais : " + Integer.toString(frenchAverage) + "%");
		
		mathsCounter.setText("Nombre de test en maths : " + Integer.toString(mathsCounterQcms));
		mathsMean.setText("Moyenne en maths : " + Integer.toString(mathsAverage) + "%");
		
		globalCounter.setText("Nombre de tests : " + Integer.toString(globalCounterQcms));
		globalMean.setText("Moyenne des tests : " + Integer.toString(globalAverage) + "%");
		
		dbQcm.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
