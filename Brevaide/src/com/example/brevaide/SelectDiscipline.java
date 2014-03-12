
package com.example.brevaide;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class SelectDiscipline extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_discipline);
	
		final Button btFrench = (Button) findViewById(R.id.buttonFrench);
		final Button btMaths = (Button) findViewById(R.id.buttonMaths);
		final Button btHistory = (Button) findViewById(R.id.buttonHistory);
		final Intent intent = new Intent(SelectDiscipline.this,Act_QCM.class);
		
		
		btFrench.setOnClickListener(new Button.OnClickListener(){
	
			@Override
			public void onClick(View arg0) {
				intent.putExtra("discipline","french");
				startActivity(intent);
			}
	
		});
	
		btMaths.setOnClickListener(new Button.OnClickListener(){
	
			@Override
			public void onClick(View arg0) {
				intent.putExtra("discipline","maths");
				startActivity(intent);
			}
	
		});
	
		btHistory.setOnClickListener(new Button.OnClickListener(){
	
			@Override
			public void onClick(View arg0) {
				intent.putExtra("discipline","history");
				startActivity(intent);
			}
	
		});

	
	}


}

