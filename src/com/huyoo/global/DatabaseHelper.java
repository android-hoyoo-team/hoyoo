package com.huyoo.global;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static int VERSION = 1;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper(Context context,String name,int version){
		this(context,name,null,version);
	}
	public DatabaseHelper(Context context,String name){
		this(context,name,VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if(db!=null){
			//EAchievement
			db.execSQL("CREATE TABLE IF NOT EXISTS EACHIEVEMENT"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,name　TEXT,exp INTEGER,type　TEXT,discription TEXT,icon TEXT,addition TEXT,"
					+ "totalProgress INTEGER)");
			//EArticle
			db.execSQL("CREATE TABLE IF NOT EXISTS EARTICLE"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,time INTEGER,title TEXT,content TEXT,icon TEXT,hits INTEGER,type TEXT)");
			//EComment
			db.execSQL("CREATE TABLE IF NOT EXISTS ECOMMENT"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,time INTEGER,content TEXT,invitationId INTEGER,coomentIdTo INTEGER)");
			//EInvitation
			db.execSQL("CREATE TABLE IF NOT EXISTS EINVITATION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,activityTime INTEGER,title TEXT,content TEXT,address TEXT"
					+ "maxNum INTEGER,currentNum INTEGER,forwardIdFrom INTEGER,originalId INTEGER,status TEXT,issueTime INTEGER,"
					+ "icons TEXT,hits TEXT)");
			//ELetter
			db.execSQL("CREATE TABLE IF NOT EXISTS ELETTER"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personIdTo INTEGER,title TEXT,content TEXT,time INTEGER,statusFrom TEXT"
					+ "statusTo TEXT)");
			//ELevel
			db.execSQL("CREATE TABLE IF NOT EXISTS ELEVEL"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,upgradeExp INTEGER,type TEXT)");
			//EPerson
			db.execSQL("CREATE TABLE IF NOT EXISTS EPERSON"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,sex TEXT,school TEXT,department TEXT,birthday INTEGER,"
					+ "phoneNum TEXT,position TEXT,password TEXT,levelId INTEGER, vp INTEGER,icon TEXT,currentExp INTEGER)");
			//EUnion
			db.execSQL("CREATE TABLE IF NOT EXISTS EUNION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,chairmanId INTEGER,levelId INTEGER,type TEXT,totalNum INTEGER,"
					+ "icon TEXT,time INTEGER,currentExp INTEGER,activityNum INTEGER,status TEXT)");

			//RAttention
			db.execSQL("CREATE TABLE IF NOT EXISTS RATTENTION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personIdFrom INTEGER,personIdTo INTEGER,time INTEGER)");
			//RInvitationPerson
			db.execSQL("CREATE TABLE IF NOT EXISTS RINVITATIONPERSON"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,time INTEGER,content TEXT,invitationId INTEGER,coomentIdTo INTEGER)");
			//RPersonAchievement
			db.execSQL("CREATE TABLE IF NOT EXISTS RPERSONACHIEVEMENT"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,achievementId INTEGER,time INTEGER,currentProgress INTEGER)");
			//RUnionApplication
			db.execSQL("CREATE TABLE IF NOT EXISTS RUNIONAPPLICATION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,unionId INTEGER,attachInfo TEXT,time INTEGER,status TEXT)");
			//RUnionPerson
			db.execSQL("CREATE TABLE IF NOT EXISTS RUNIONPERSON"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,unionId INTEGER,personId INTEGER,time INTEGER,status TEXT)");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
