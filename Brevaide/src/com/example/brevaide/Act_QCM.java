package com.example.brevaide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import org.xmlpull.v1.XmlPullParserException;

import xml.brevaide.QuestionsXMLHandler;

import com.example.brevaide.Act_QCM;

import data.brevaide.QCM;
import data.brevaide.Question;
import db.brevaide.Db_QCM;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.app.Activity;

public class Act_QCM extends Activity {
	
	ArrayList<Question> falseQuestions = new ArrayList<Question>();
	ArrayList<Question> questions;
	Question currentQuestion;

    
	public int questionCounter = 0;
	public int[] questionCounterArray = {0,1,2,3,4,5,6,7,8,9};
	public int correctQuestionCounter = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm);
		
		final String discipline = getIntent().getExtras().getString("discipline");
		try {
			InputStream in = getApplicationContext().getAssets().open(discipline + ".xml");
			QuestionsXMLHandler XMLHandler = new QuestionsXMLHandler();	
			questions = XMLHandler.parse(in);

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		final TextView question = (TextView) findViewById(R.id.question);
		final RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
		final RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
		final RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
		final Button validate = (Button) findViewById(R.id.validate);
		
		Util.shuffle(questionCounterArray);
		currentQuestion = questions.get(questionCounterArray[questionCounter]);		
		

		question.setText(currentQuestion.getText());
		answer1.setText(currentQuestion.getAnswer(0));
		answer2.setText(currentQuestion.getAnswer(1));
		answer3.setText(currentQuestion.getAnswer(2));
		questionCounter++;
			
		validate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				int answerID = currentQuestion.getAnswerId();
				int userAnswerID;
				
				RadioButton answer1 =  (RadioButton) findViewById(R.id.answer1);
				RadioButton answer2 =  (RadioButton) findViewById(R.id.answer2);
				RadioButton answer3 =  (RadioButton) findViewById(R.id.answer3);
				
				if(answer1.isChecked())
				{
					userAnswerID = 1;
				}
				else if(answer2.isChecked())
				{
					userAnswerID = 2;
				}
				else if(answer3.isChecked())
				{
					userAnswerID = 3;
				}
				else
				{
					Toast.makeText(getApplicationContext(), R.string.error_have_to_check, Toast.LENGTH_SHORT ).show();
					return;
				}
				if(userAnswerID == answerID)
					correctQuestionCounter++;
				else
					falseQuestions.add(currentQuestion);
				
				if(questionCounter >= 10)
				{	
					
					QCM qcm = new QCM(discipline, correctQuestionCounter*10);
					Db_QCM dbQcm = new Db_QCM(getApplicationContext());
					
					dbQcm.open();
					dbQcm.add(qcm);
					dbQcm.close();
					
					setContentView(R.layout.activity_qcm_resultat);
					TextView resultat = (TextView) findViewById(R.id.resultat);
					Button seeErrors = (Button) findViewById(R.id.buttonSeeErrors);
					
					seeErrors.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							
							
						}});
					
					resultat.setText("Bonnes réponses : " + correctQuestionCounter + "/10");
					
					for(int i=0;i<falseQuestions.size();i++)
						Log.i("Mania",falseQuestions.get(i).getText());
				}
				else
				{	
					currentQuestion = questions.get(questionCounterArray[questionCounter]);
					questionCounter++;
					
					question.setText(currentQuestion.getText());
					answer1.setText(currentQuestion.getAnswer(0));
					answer2.setText(currentQuestion.getAnswer(1));
					answer3.setText(currentQuestion.getAnswer(2));	
				}
			}
			
			
		});
		
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static class Util {

	    private static Random random;

	    /**
	     * Code from method java.util.Collections.shuffle();
	     */
	    public static void shuffle(int[] array) {
	        if (random == null) random = new Random();
	        int count = array.length;
	        for (int i = count; i > 1; i--) {
	            swap(array, i - 1, random.nextInt(i));
	        }
	    }

	    private static void swap(int[] array, int i, int j) {
	        int temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	    }
	}
}
