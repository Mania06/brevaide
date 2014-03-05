package com.example.test;

import com.example.test.QCM;
import com.example.test.Resultat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class QCM extends Activity {
	
	
	public int questionCounter = 0;
	public int correctQuestionCounter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm);
		
		final TextView question = (TextView) findViewById(R.id.question);
		final RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
		final RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
		final RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
		final Button validate = (Button) findViewById(R.id.validate);
		

		question.setText("Bonsou�re ! Es tu un battarre? (" + questionCounter + "/10)");
		answer1.setText("Oui");
		answer2.setText("Non");
		answer3.setText("Hahaha !!!!");
			
		validate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				if(questionCounter >= 10)
				{
					startActivity(new Intent(QCM.this,Resultat.class));
				}
				else
				{
					questionCounter++;
					question.setText("Bonsou�re ! Es tu un battarre? (" + questionCounter + "/10)");
					answer1.setText("Oui");
					answer2.setText("Non");
					answer3.setText("Hahaha !!!!");			
				}
			}
			
			
		});
	}

}
