package data.brevaide;

import java.sql.Date;

public class QCM {
	public long id;
	public Date date;
	public String matiere;
	public int score;
	
	public QCM(long id, Date date, String matiere, int score) {
		super();
		this.id = id;
		this.date = date;
		this.matiere = matiere;
		this.score = score;
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
}
