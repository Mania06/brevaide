package com.example.brevaide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import xml.brevaide.QuestionsXMLHandler;

import com.example.brevaide.QCM;
import com.example.test.R;

import data.brevaide.Question;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;

public class QCM extends Activity {
	
	ArrayList<Question> Questions;
	Question currentQuestion;
	
	public int questionCounter = 0;
	public int correctQuestionCounter = 0;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qcm);
		
		
		try {
			InputStream in = getApplicationContext().getAssets().open("questions.xml");
			QuestionsXMLHandler XMLHandler = new QuestionsXMLHandler();	
			Questions = XMLHandler.parse(in);

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
		
		currentQuestion = Questions.get(questionCounter);		
		
		
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
					userAnswerID = 1;
				else if(answer2.isChecked())
					userAnswerID = 2;
				else if(answer3.isChecked())
					userAnswerID = 3;
				else
					return;
					
				if(userAnswerID == answerID)
					correctQuestionCounter++;
				
				
				if(questionCounter >= 10)
				{	
					setContentView(R.layout.activity_qcm_resultat);
					TextView resultat = (TextView) findViewById(R.id.resultat);
					resultat.setText("Bonnes réponses : " + correctQuestionCounter + "/10");
				}
				else
				{	
					currentQuestion = Questions.get(questionCounter);
					questionCounter++;
					
					question.setText(currentQuestion.getText());
					answer1.setText(currentQuestion.getAnswer(0));
					answer2.setText(currentQuestion.getAnswer(1));
					answer3.setText(currentQuestion.getAnswer(2));	
				}
			}
			
			
		});
	}

}
