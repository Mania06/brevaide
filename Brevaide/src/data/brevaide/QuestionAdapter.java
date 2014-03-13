package data.brevaide;

import java.util.ArrayList;

import com.example.brevaide.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionAdapter extends BaseAdapter {
	ArrayList<Question> Questions;
	LayoutInflater inflater;

	public QuestionAdapter(Context context,ArrayList<Question> Questions) {
		inflater = LayoutInflater.from(context);
		this.Questions = Questions;
	}

	@Override
	public int getCount() {
		return Questions.size();
	}

	@Override
	public Object getItem(int index) {
		return Questions.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    ViewHolder holder;

	    if(convertView == null) {
	        holder = new ViewHolder();
	        convertView = inflater.inflate(R.layout.item_question, null);

	        holder.question_text = (TextView) convertView.findViewById(R.id.question_text);
	        holder.question_answer = (TextView) convertView.findViewById(R.id.question_answer);
	        holder.question_user_answer = (TextView) convertView.findViewById(R.id.question_user_answer);
	        
	        convertView.setTag(holder);
	    } else {
	        holder = (ViewHolder) convertView.getTag();
	    }
		
	    holder.question_text.setText(Questions.get(position).getText());
	    holder.question_answer.setText(Questions.get(position).getAnswer(Questions.get(position).getAnswerId()-1));
	    holder.question_user_answer.setText(Questions.get(position).getAnswer(Questions.get(position).getUserAnswerId()-1));

	    return convertView;

	}
	
}
