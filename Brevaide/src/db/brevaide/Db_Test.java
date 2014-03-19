package db.brevaide;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import data.brevaide.Test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Db_Test {
	
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "stats.db";
 
	private final static String TABLE_NAME = "stats_Test";
	private final static String COL_ID = "id";
	private final static String COL_SCORE = "score";
	private final static String COL_DATE = "date";
	
	private final static int NUM_COL_ID = 0;
	private final static int NUM_COL_DATE = 1;
	private final static int NUM_COL_SCORE = 2;

	private SQLiteDatabase db;
	private DbHandler DbHandler;
	
	public Db_Test(Context context) {
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
	
	public void add(Test Test) {
		ContentValues value = new ContentValues();
		DateFormat dateFormat = DateFormat.getInstance();
		
		value.put(COL_DATE, dateFormat.format(new Date()));
		value.put(COL_SCORE, Test.getScore());
		
		db.insert(TABLE_NAME, null, value);
	}
	
	public void reset()
	{
		db.delete(TABLE_NAME,null,null);
	}
	
	public int delete(Test Test){
		return db.delete(TABLE_NAME, COL_ID + " = " + Test.getId(), null);
	}
 
	public Test getTestbyId(int id){
		db = DbHandler.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, new String[] {COL_ID, COL_DATE, COL_SCORE}, COL_ID + " = " + id, null, null, null, null);
		return cursorToTest(c);
	}
	
	public ArrayList<Test> getAllTests(){

		db = DbHandler.getReadableDatabase();
		Cursor c= db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		return cursorToTests(c);

	}
	
	private ArrayList<Test> cursorToTests(Cursor c) {
		
		ArrayList<Test> Tests = new ArrayList<Test>();
		if (c.getCount() == 0)
			return Tests;
		
		if(c != null)
			c.moveToFirst();
		
		
		while (!c.isAfterLast())
		{
			Test Test = new Test();
			Test.setId(c.getInt(NUM_COL_ID));
			String DateTime = c.getString(NUM_COL_DATE);
			DateFormat dateFormat = DateFormat.getInstance();
			try {
				Test.setDate(dateFormat.parse(DateTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Test.setScore(c.getInt(NUM_COL_SCORE));
			
			Tests.add(Test);
			c.moveToNext();
		}
		
		c.close();
		return Tests;
	}

	
	
	private Test cursorToTest(Cursor c) {
		Test Test = new Test();
		
		if (c.getCount() == 0)
			return Test;
 
		if(c != null)
			c.moveToFirst();
		
	
		Test.setId(c.getInt(NUM_COL_ID));
		String DateTime = c.getString(NUM_COL_DATE);
		DateFormat dateFormat = DateFormat.getInstance();
		try {
			Test.setDate(dateFormat.parse(DateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Test.setScore(c.getInt(NUM_COL_SCORE));
		 
		c.close();

		return Test;
		
		
	}


}
