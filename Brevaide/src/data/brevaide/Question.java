package data.brevaide;

public class Question {
	
	public String text;
	public String[] answer = new String[3];	
	public int answerId;
	
	public String getText(){
		return text;
	}
	public void setText(String text){
		this.text = text;
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
}
