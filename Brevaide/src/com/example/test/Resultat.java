package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Resultat extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm_resultat);
		
		final TextView resultat = (TextView) findViewById(R.id.resultat);
		
		resultat.setText("Bonnes réponses : " + 6 + "/10");
		
	
	
	}



}
