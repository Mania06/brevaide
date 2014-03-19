package com.example.brevaide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import org.xmlpull.v1.XmlPullParserException;


import xml.brevaide.QuestionsXMLHandler;

import data.brevaide.Question;
import data.brevaide.Test;
import db.brevaide.Db_Test;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Act_Test extends Activity {
	
	ArrayList<Question> falseQuestions = new ArrayList<Question>();
	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<Question> questionsMaths;
	ArrayList<Question> questionsFrench;
	ArrayList<Question> questionsHistory;
	
	Question currentQuestion;
	
	public int questionCounter = 0;
	public int[] questionCounterArray = new int[30];
	public static int nombreQuestions = 30;
	public int correctQuestionCounter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		for(int i=0;i<nombreQuestions;i++)
			questionCounterArray[i] = i;
		Util.shuffle(questionCounterArray);
		
		try {
			InputStream inMaths = getApplicationContext().getAssets().open("maths.xml");
			InputStream inFrench = getApplicationContext().getAssets().open("french.xml");
			InputStream inHistory = getApplicationContext().getAssets().open("history.xml");
			QuestionsXMLHandler XMLHandler = new QuestionsXMLHandler();	
			questionsMaths = XMLHandler.parse(inMaths);
			questionsFrench = XMLHandler.parse(inFrench);
			questionsHistory = XMLHandler.parse(inHistory);
			
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
		
		
		for (int i=0;i<nombreQuestions/3;i++)
			questions.add(questionsMaths.get(i));
		
		for (int i=0;i<nombreQuestions/3;i++)
			questions.add(questionsFrench.get(i));
		
		for (int i=0;i<nombreQuestions/3;i++)
			questions.add(questionsHistory.get(i));
		
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
				
				currentQuestion.setUserAnswerId(userAnswerID);
				
				if(userAnswerID == answerID)
					correctQuestionCounter++;
				else
					falseQuestions.add(currentQuestion);
				
				if(questionCounter >= nombreQuestions)
				{	
					
					Test test = new Test(correctQuestionCounter*100/nombreQuestions);
					Db_Test dbTest = new Db_Test(getApplicationContext());
					
					dbTest.open();
					dbTest.add(test);
					dbTest.close();
					
					setContentView(R.layout.activity_resultat);
					TextView resultat = (TextView) findViewById(R.id.resultat);
					Button seeErrors = (Button) findViewById(R.id.buttonSeeErrors);
					
					seeErrors.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(Act_Test.this,SeeErrors.class);
							intent.putExtra("falseQuestions",falseQuestions);
							startActivity(intent);

						}});
					
					resultat.setText("Bonnes réponses : " + correctQuestionCounter + "/" + nombreQuestions);
					
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
