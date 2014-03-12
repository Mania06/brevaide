package db.brevaide;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import data.brevaide.QCM;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Db_QCM {
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "qcm.db";
 
	private final static String TABLE_NAME = "stats_QCM";
	private final static String COL_ID = "id";
	private final static String COL_SCORE = "score";
	private final static String COL_DATE = "date";
	private final static String COL_MATIERE = "matiere";
	
	private final static int NUM_COL_ID = 0;
	private final static int NUM_COL_DATE = 1;
	private final static int NUM_COL_MATIERE = 2;
	private final static int NUM_COL_SCORE = 3;

	private SQLiteDatabase db;
	private DbHandler DbHandler;
	
	public Db_QCM(Context context) {
		DbHandler = new DbHandler(context, DB_NAME, null, DB_VERSION);
	}
	
	public void open(){
		db = DbHandler.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}

	public SQLiteDatabase getDb() {
		return db;
	}
	
	public void add(QCM qcm) {
		ContentValues value = new ContentValues();
		DateFormat dateFormat = DateFormat.getInstance();
		
		value.put(COL_DATE, dateFormat.format(new Date()));
		value.put(COL_MATIERE, qcm.getMatiere());
		value.put(COL_SCORE, qcm.getScore());
		
		db.insert(TABLE_NAME, null, value);
	}
	
	public void reset()
	{
		db.delete(TABLE_NAME,null,null);
	}
	
	public int delete(QCM qcm){
		return db.delete(TABLE_NAME, COL_ID + " = " + qcm.getId(), null);
	}
 
	public QCM getQCMbyId(int id){
		db = DbHandler.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, new String[] {COL_ID, COL_DATE, COL_MATIERE, COL_SCORE}, COL_ID + " = " + id, null, null, null, null);
		return cursorToQCM(c);
	}
	
	public ArrayList<QCM> getAllQCM(){
		ArrayList<QCM> QCMs = new ArrayList<QCM>();
		db = DbHandler.getReadableDatabase();
		Cursor c= db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		
		if (c.getCount() == 0)
			return QCMs;
		
		if(c != null)
			c.moveToFirst();
		
		
		while (!c.isAfterLast())
		{
			QCM qcm = new QCM();
			qcm.setId(c.getInt(NUM_COL_ID));
			String DateTime = c.getString(NUM_COL_DATE);
			DateFormat dateFormat = DateFormat.getInstance();
			try {
				qcm.setDate(dateFormat.parse(DateTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			qcm.setMatiere(c.getString(NUM_COL_MATIERE));
			qcm.setScore(c.getInt(NUM_COL_SCORE));
			
			QCMs.add(qcm);
			c.moveToNext();
		}
		
		c.close();
		return QCMs;
	}
	

	private QCM cursorToQCM(Cursor c) {
		QCM qcm = new QCM();
		
		if (c.getCount() == 0)
			return qcm;
 
		if(c != null)
			c.moveToFirst();
		
	
		qcm.setId(c.getInt(NUM_COL_ID));
		String DateTime = c.getString(NUM_COL_DATE);
		DateFormat dateFormat = DateFormat.getInstance();
		try {
			qcm.setDate(dateFormat.parse(DateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		qcm.setMatiere(c.getString(NUM_COL_MATIERE));
		qcm.setScore(c.getInt(NUM_COL_SCORE));
		 
		c.close();

		return qcm;
		
		
	}


}
