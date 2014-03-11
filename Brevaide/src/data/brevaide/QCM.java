package data.brevaide;

import java.util.Date;



public class QCM {
	public long id;
	public Date date;
	public String matiere;
	public int score;
	
	public QCM(String matiere, int score) {
		super();
		this.matiere = matiere;
		this.score = score;
	}

	


	public QCM() {
		super();
	}




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "QCM [id=" + id + ", date=" + date + ", matiere=" + matiere
				+ ", score=" + score + "]";
	}
}
