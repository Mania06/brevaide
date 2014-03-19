package data.brevaide;

import java.util.Date;



public class Test {
	public int id;
	public Date date;
	public int score;
	
	public Test(int id, Date date, int score) {
		super();
		this.id = id;
		this.date = date;
		this.score = score;
	}

	public Test(int score) {
		super();
		this.score = score;
	}

	


	public Test() {
		super();
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "QCM [id=" + id + ", date=" + date + ", score=" + score + "]";
	}
}
