package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Brevaide extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brevaide);
		
		/*
		 * Buttons actions
		 */
		
		
		Button btQCM = (Button) findViewById(R.id.buttonQCM);
		Button btTest = (Button) findViewById(R.id.buttonTest);
		Button btStats = (Button) findViewById(R.id.buttonStats);
		// QCM
		
		btQCM.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Brevaide.this,QCM.class));
			}

		});
		
		// Test
		
		btTest.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Brevaide.this,Test.class));
			}

		});

		
		// Stats
		
		btStats.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Brevaide.this,Stats.class));
			}

		});
		
	}


}
