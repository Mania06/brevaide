package com.example.test;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;

public class QCM extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm);
		
		TextView question = (TextView) findViewById(R.id.question);
		RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
		RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
		RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
		
		question.setText("Bonsouère ! Es tu un battarre?");
		answer1.setText("Oui");
		answer2.setText("Non");
		answer3.setText("Hahaha !!!!");
	}

}
