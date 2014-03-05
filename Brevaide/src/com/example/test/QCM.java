package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;

public class QCM extends Activity {
	
	
	public int questionCounter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm);
		
		final TextView question = (TextView) findViewById(R.id.question);
		final RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
		final RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
		final RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
		Button validate = (Button) findViewById(R.id.validate);
		

		question.setText("Bonsouère ! Es tu un battarre?" + questionCounter);
		answer1.setText("Oui");
		answer2.setText("Non");
		answer3.setText("Hahaha !!!!");
			
		validate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				questionCounter++;
				question.setText("Bonsouère ! Es tu un battarre? (" + questionCounter + "/10)");
				answer1.setText("Oui");
				answer2.setText("Non");
				answer3.setText("Hahaha !!!!");			
			}
			
			
		});
	}

}
