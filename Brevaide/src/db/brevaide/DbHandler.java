package db.brevaide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper{
	public final static String STATS_QCM_ID = "id";
	public final static String STATS_QCM_SCORE = "score";
	public final static String STATS_QCM_DATE = "date";
	public final static String STATS_QCM_MATIERE = "matiere";
	
	public final static String STATS_QCM_TABLE_NAME = "stats_QCM";
	public final static String STATS_QCM_TABLE_CREATE = 
			"CREATE TABLE " + STATS_QCM_TABLE_NAME + " (" + 
				STATS_QCM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				STATS_QCM_DATE + " DATETIME DEFAULT CURRENT_DATE, " +
				STATS_QCM_MATIERE + " TEXT NOT NULL, " +
				STATS_QCM_SCORE + " INTEGER NOT NULL);";
	private static final String STATS_QCM_TABLE_DROP = "DROP TABLE IF EXISTS " + STATS_QCM_TABLE_NAME + ";";
	
	
	public DbHandler(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(STATS_QCM_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}

	public void reset_qcm_stats(SQLiteDatabase db){
		  db.execSQL(STATS_QCM_TABLE_DROP);
		  onCreate(db);
	} 
	
}
