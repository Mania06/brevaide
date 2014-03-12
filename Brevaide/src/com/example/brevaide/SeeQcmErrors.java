package com.example.brevaide;

import java.util.ArrayList;

import data.brevaide.Question;
import data.brevaide.QuestionAdapter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.app.Activity;

public class SeeQcmErrors extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_qcm_errors);
		
		ArrayList<Question> falseQuestions = getIntent().getExtras().getParcelableArrayList("falseQuestions");
		ListView lvErrors = (ListView) findViewById(R.id.listViewErrors);
		QuestionAdapter questionAdapter = new QuestionAdapter(this,falseQuestions);
		lvErrors.setAdapter(questionAdapter);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
