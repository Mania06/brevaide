package data.brevaide;

import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
	
	public String text;
	public String[] answer = new String[3];	
	public int answerId;
	
	public int userAnswerId;
	
	public Question(String text, String[] answer, int answerId) {
		super();
		this.text = text;
		this.answer = answer;
		this.answerId = answerId;
	}
	
	public Question(String text, String[] answer, int answerId, int userAnswer) {
		super();
		this.text = text;
		this.answer = answer;
		this.answerId = answerId;
		this.userAnswerId = userAnswer;
	}
	
	public Question(Parcel in) {
		super();
		this.text = in.readString();
		in.readStringArray(this.answer);
		this.answerId = in.readInt();
		this.userAnswerId = in.readInt();
	}
	

	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeStringArray(answer);
		dest.writeInt(answerId);
		dest.writeInt(userAnswerId);
	}
	
	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>()
	{
		@Override
		public Question createFromParcel(Parcel source)
		{
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size)
		{
			return new Question[size];
		}
	};
	
	public static Parcelable.Creator<Question> getCreator()
	{
		return CREATOR;
	}
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
	}
	
	public int getUserAnswerId() {
		return userAnswerId;
	}
	public void setUserAnswerId(int userAnswerId) {
		this.userAnswerId = userAnswerId;
	}
	
	public String getAnswer(int index){
		return answer[index];
	}
	public void setAnswer(int index, String answer){
		this.answer[index] = answer;
	}
	
	public int getAnswerId(){
		return answerId;
	}
	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}
	
	@Override
	public String toString() {
		return "Question [text=" + text + ", answer=" + Arrays.toString(answer)
				+ ", answerId=" + answerId + "]";
	}

}
