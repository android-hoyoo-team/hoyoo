package com.huyoo.global;


import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static int VERSION = 1;
	SQLiteDatabase db = null;

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
		this.db = db;
		if(db!=null){
			//dropTable();
			//EAchievement
			db.execSQL("CREATE TABLE IF NOT EXISTS EACHIEVEMENT"+
					"(id INTEGER,name TEXT,exp INTEGER,type TEXT,description TEXT,icon TEXT,addition TEXT,"
					+ "totalProgress INTEGER,expensionPack TEXT)");
			//EArticle
			db.execSQL("CREATE TABLE IF NOT EXISTS EARTICLE"+
					"(id INTEGER,personId INTEGER,time INTEGER,title TEXT,content TEXT,icon TEXT,hits INTEGER,type TEXT)");
			//EComment
			db.execSQL("CREATE TABLE IF NOT EXISTS ECOMMENT"+
					"(id INTEGER,personId INTEGER,time INTEGER,content TEXT,invitationId INTEGER,commentIdTo INTEGER)");
			//EInvitation
			db.execSQL("CREATE TABLE IF NOT EXISTS EINVITATION"+
					"(id INTEGER,personId INTEGER,activityTime INTEGER,title TEXT,content TEXT,address TEXT,"
					+ "maxNum INTEGER,currentNum INTEGER,forwardIdFrom INTEGER,originalId INTEGER,status TEXT,issueTime INTEGER,"
					+ "icons TEXT,hits TEXT)");
			//ELetter
			db.execSQL("CREATE TABLE IF NOT EXISTS ELETTER"+
					"(id INTEGER,personIdTo INTEGER,title TEXT,content TEXT,time INTEGER,statusFrom TEXT"
					+ "statusTo TEXT)");
			//ELevel
			db.execSQL("CREATE TABLE IF NOT EXISTS ELEVEL"+
					"(id INTEGER,name TEXT,upgradeExp INTEGER,type TEXT)");
			//EPerson
			db.execSQL("CREATE TABLE IF NOT EXISTS EPERSON"+
					"(id INTEGER,name TEXT,sex TEXT,school TEXT,department TEXT,birthday INTEGER,"
					+ "phoneNum TEXT,position TEXT,password TEXT,levelId INTEGER, vp INTEGER,icon TEXT,currentExp INTEGER)");
			//EUnion
			db.execSQL("CREATE TABLE IF NOT EXISTS EUNION"+
					"(id INTEGER,name TEXT,chairmanId INTEGER,levelId INTEGER,type TEXT,totalNum INTEGER,"
					+ "icon TEXT,time INTEGER,currentExp INTEGER,activityNum INTEGER,status TEXT)");

			//RAttention
			db.execSQL("CREATE TABLE IF NOT EXISTS RATTENTION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personIdFrom INTEGER,personIdTo INTEGER,time INTEGER)");
			//RInvitationLike
			db.execSQL("CREATE TABLE IF NOT EXISTS RINVITATIONLIKE"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,invitationId INTEGER,personId INTEGER,time INTEGER)");

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
			initData();	
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		this.db = db;
	}

	public void initData(){

		//初始化EAchievement
		initAchievement();
		initArticle();
		initComment();
		initInvitation();
		initLetter();
		initLevel();
		initPerson();
		initUnion();
		initUnionPerson();
	}

	public void dropTable(){
		db.execSQL("drop table eachievement");
		db.execSQL("drop table EArticle");
		db.execSQL("drop table EComment");
		db.execSQL("drop table EInvitation");
		db.execSQL("drop table ELetter");
		db.execSQL("drop table ELevel");
		db.execSQL("drop table EPerson");
		db.execSQL("drop table EUnion");
		db.execSQL("drop table RAttention");
		db.execSQL("drop table RInvitationPerson");
		db.execSQL("drop table RPersonAchievement");
		db.execSQL("drop table RUnionApplication");
		db.execSQL("drop table RUnionPerson");
	}

	public void deleteData(){
		db.execSQL("delete from eachievement");
		db.execSQL("delete from EArticle");
		db.execSQL("delete from EComment");
		db.execSQL("delete from EInvitation");
		db.execSQL("delete from ELetter");
		db.execSQL("delete from ELevel");
		db.execSQL("delete from EPerson");
		db.execSQL("delete from EUnion");
		db.execSQL("delete from RAttention");
		db.execSQL("delete from RInvitationPerson");
		db.execSQL("delete from RPersonAchievement");
		db.execSQL("delete from RUnionApplication");
		db.execSQL("delete from RUnionPerson");
	}

	public void initAchievement(){
		ContentValues a1 = new ContentValues();
		a1.put("id", 1);
		a1.put("name", "你可来了");
		a1.put("addition", "首次登陆HoYoo");
		a1.put("description", "千呼万唤终于等到了你");
		a1.put("exp", 20);
		a1.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A7CD0734EBCC440BA9DFF1CE45B988DD");
		a1.put("totalProgress", 1);
		a1.put("type", "综合类");
		a1.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a1);
		ContentValues a2 = new ContentValues();
		a2.put("id", 2);
		a2.put("name", "有头有脸");
		a2.put("addition", "上传自己的头像");
		a2.put("description", "此处应该有自拍");
		a2.put("exp", 10);
		a2.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B36B19036A345F7AA165614D9D8FE10");
		a2.put("totalProgress", 1);
		a2.put("type", "综合类");
		a2.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a2);
		ContentValues a3 = new ContentValues();
		a3.put("id", 3);
		a3.put("name", "好玩吧，没玩过吧");
		a3.put("addition", "第一次发布或者参与一次邀请");
		a3.put("description", "");
		a3.put("exp", 15);
		a3.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/AC4B59CE5F194B23A4444FF26C12CC10");
		a3.put("totalProgress", 1);
		a3.put("type", "综合类");
		a3.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a3);
		ContentValues a4 = new ContentValues();
		a4.put("id", 4);
		a4.put("name", "漫漫长路");
		a4.put("addition", "第一次进入成就界面");
		a4.put("description", "有无数的成就等着你完成");
		a4.put("exp", 5);
		a4.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A7CD0734EBCC440BA9DFF1CE45B988DD");
		a4.put("totalProgress", 1);
		a4.put("type", "综合类");
		a4.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a4);

		ContentValues a5 = new ContentValues();
		a5.put("id", 5);
		a5.put("name", "我宣你");
		a5.put("addition", "第一次关注别人");
		a5.put("description", "");
		a5.put("exp", 5);
		a5.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B36B19036A345F7AA165614D9D8FE10");
		a5.put("totalProgress", 1);
		a5.put("type", "综合类");
		a5.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a5);

		ContentValues a6 = new ContentValues();
		a6.put("id", 6);
		a6.put("name", "友人");
		a6.put("addition", "获得一个互相关注");
		a6.put("description", "");
		a6.put("exp", 5);
		a6.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		a6.put("totalProgress", 1);
		a6.put("type", "综合类");
		a6.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a6);
		ContentValues a7 = new ContentValues();
		a7.put("id", 7);
		a7.put("name", "初出茅庐");
		a7.put("addition", "成就等级达到\"新兵\"");
		a7.put("description", "");
		a7.put("exp", 10);
		a7.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		a7.put("totalProgress", 1);
		a7.put("type", "综合类");
		a7.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a7);
		ContentValues a8 = new ContentValues();
		a8.put("id", 8);
		a8.put("name", "你也喜欢这个？");
		a8.put("addition", "发布的邀请得到了别人的回复");
		a8.put("description", "");
		a8.put("exp", 10);
		a8.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B09EEE5C385D4C46B1A7F48B4EE6619A");
		a8.put("totalProgress", 1);
		a8.put("type", "综合类");
		a8.put("expensionPack", "病毒入侵");
		this.db.insert("EAchievement", null, a8);
	}

	public void initArticle(){
		ContentValues a1 = new ContentValues();
		a1.put("id", 1);
		a1.put("hits", 111);
		a1.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a1.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a1.put("personId", 1);
		a1.put("time",1425545750006l);
		a1.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a1.put("type", "公会新闻");
		this.db.insert("EArticle", null, a1);

		ContentValues a2 = new ContentValues();
		a2.put("id", 2);
		a2.put("hits", 222);
		a2.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a2.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a2.put("personId", 1);
		a2.put("time",1425547561063l);
		a2.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a2.put("type", "公会新闻");
		this.db.insert("EArticle", null, a2);

		ContentValues a3 = new ContentValues();
		a3.put("id", 3);
		a3.put("hits", 333);
		a3.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a3.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a3.put("personId", 1);
		a3.put("time",1425547628282l);
		a3.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a3.put("type", "公会新闻");
		this.db.insert("EArticle", null, a3);

		ContentValues a4 = new ContentValues();
		a4.put("id", 4);
		a4.put("hits", 444);
		a4.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a4.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a4.put("personId", 1);
		a4.put("time",1425545750006l);
		a4.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a4.put("type", "公会新闻");
		this.db.insert("EArticle", null, a4);

		ContentValues a5 = new ContentValues();
		a5.put("id", 5);
		a5.put("hits", 555);
		a5.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a5.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a5.put("personId", 1);
		a5.put("time",1425545750006l);
		a5.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a5.put("type", "公会新闻");
		this.db.insert("EArticle", null, a5);

		ContentValues a7 = new ContentValues();
		a7.put("id", 7);
		a7.put("hits", 777);
		a7.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a7.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a7.put("personId", 1);
		a7.put("time",1425545750006l);
		a7.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a7.put("type", "公会新闻");
		this.db.insert("EArticle", null, a7);

		ContentValues a8 = new ContentValues();
		a8.put("id", 8);
		a8.put("hits", 888);
		a8.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a8.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a8.put("personId", 1);
		a8.put("time",1425545750006l);
		a8.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a8.put("type", "公会新闻");
		this.db.insert("EArticle", null, a8);

		ContentValues a9 = new ContentValues();
		a9.put("id", 9);
		a9.put("hits", 999);
		a9.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a9.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a9.put("personId", 1);
		a9.put("time",1425545750006l);
		a9.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a9.put("type", "公会新闻");
		this.db.insert("EArticle", null, a9);

		ContentValues a10 = new ContentValues();
		a10.put("id", 10);
		a10.put("hits", 1000);
		a10.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a10.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a10.put("personId", 1);
		a10.put("time",1425545750006l);
		a10.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a10.put("type", "公会新闻");
		this.db.insert("EArticle", null, a10);

		ContentValues a11 = new ContentValues();
		a11.put("id", 11);
		a11.put("hits", 234);
		a11.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a11.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a11.put("personId", 1);
		a11.put("time",1425545750006l);
		a11.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a11.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a11);

		ContentValues a12 = new ContentValues();
		a12.put("id", 12);
		a12.put("hits", 123);
		a12.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a12.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a12.put("personId", 1);
		a12.put("time",1425545750006l);
		a12.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a12.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a12);

		ContentValues a13 = new ContentValues();
		a13.put("id", 13);
		a13.put("hits", 345);
		a13.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a13.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a13.put("personId", 1);
		a13.put("time",1425545750006l);
		a13.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a13.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a13);

		ContentValues a14 = new ContentValues();
		a14.put("id", 14);
		a14.put("hits", 456);
		a14.put("content","<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		a14.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		a14.put("personId", 1);
		a14.put("time",1425545750006l);
		a14.put("title", "徐绍史：加强粤港澳地区基础设施的互联互通");
		a14.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a14);
	}

	public void initComment(){
		ContentValues coin2 = new ContentValues();
		coin2.put("id", 1);
		coin2.put("personId", 1);
		coin2.put("time",1426583209941l);
		coin2.put("content", "来吧来吧来吧");
		coin2.put("invitationId", 1);
		coin2.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin2);

		ContentValues coin3 = new ContentValues();
		coin3.put("id", 2);
		coin3.put("personId", 1);
		coin3.put("time",1426583209941l);
		coin3.put("content", "来吧来吧来吧");
		coin3.put("invitationId", 1);
		coin3.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin3);

		ContentValues coin4 = new ContentValues();
		coin4.put("id", 3);
		coin4.put("personId", 1);
		coin4.put("time",1426583209941l);
		coin4.put("content", "来吧来吧来吧");
		coin4.put("invitationId", 1);
		coin4.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin4);

		ContentValues coin5 = new ContentValues();
		coin5.put("id", 4);
		coin5.put("personId", 1);
		coin5.put("time",1426583209941l);
		coin5.put("content", "来吧来吧来吧");
		coin5.put("invitationId", 1);
		coin5.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin5);

		ContentValues coin6 = new ContentValues();
		coin6.put("id", 5);
		coin6.put("personId", 1);
		coin6.put("time",1426583209941l);
		coin6.put("content", "来吧来吧来吧");
		coin6.put("invitationId", 1);
		coin6.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin6);
	}

	public void initInvitation(){
		ContentValues in1 = new ContentValues();
		in1.put("id", 1);
		in1.put("personId", 1);
		in1.put("activityTime", 1425546750006l);
		in1.put("title", "");
		in1.put("content", "2015 痛仰乐队《愿爱无忧》全国巡演 无锡站");
		in1.put("address", "无锡 南长区 N1955南下塘文化创意园9号楼");
		in1.put("maxNum", 50);
		in1.put("currentNum", 15);
		in1.put("forwardIdFrom", 0);
		in1.put("originalId", 0);
		in1.put("status","");
		in1.put("issueTime", 1425545750006l);
		in1.put("hits", 123);
		in1.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		ContentValues in2 = new ContentValues();
		in2.put("id", 2); 
		in2.put("personId", 2); 
		in2.put("title", "");
		in2.put("issueTime", 1425545750006l); 
		in2.put("activityTime", 1425546750006l); 
		in2.put("content", "2015“我的手艺”首届中华手作创意嘉年华暨全国手工艺品创意品大型巡回展销会"); 
		in2.put("address", "江苏省无锡市清明桥古运河景区南长街"); 
		in2.put("currentNum", 15); 
		in2.put("maxNum", 30); 
		in2.put("hits", 234); 
		in2.put("forwardIdFrom", 0);
		in2.put("originalId", 0);
		in2.put("status","");
		in2.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");

		ContentValues in3 = new ContentValues();
		
		in3.put("id", 3); 
		in3.put("personId", 2); 
		in3.put("title", "");
		in3.put("issueTime", 1425545750006l); 
		in3.put("activityTime", 1425546750006l); 
		in3.put("content", "周一晚上过来玩"); 
		in3.put("address", "你想到的地方1"); 
		in3.put("currentNum", 15); 
		in3.put("maxNum", 15); 
		in3.put("hits", 345); 
		in3.put("forwardIdFrom", 0);
		in3.put("originalId", 0);
		in3.put("status","");
		
		in3.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");

		ContentValues in4 = new ContentValues();
		in4.put("id", 4);
		in4.put("personId", 2);
		in4.put("activityTime", 1425546750006l);
		in4.put("title", "");
		in4.put("content", "周六晚上过来玩");
		in4.put("address", "你想不到的地方");
		in4.put("maxNum", 100);
		in4.put("currentNum", 10);
		in4.put("forwardIdFrom", 0);
		in4.put("originalId", 0);
		in4.put("status","");
		in4.put("issueTime", 1425545750006l);
		in4.put("hits", 1);
		in4.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		ContentValues in5 = new ContentValues();
		in5.put("id", 5);
		in5.put("personId", 4);
		in5.put("activityTime", 1425546750006l);
		in5.put("title", "");
		in5.put("content", "2015 痛仰乐队《愿爱无忧》全国巡演 无锡站");
		in5.put("address", "无锡 南长区 N1955南下塘文化创意园9号楼");
		in5.put("maxNum", 50);
		in5.put("currentNum", 15);
		in5.put("forwardIdFrom", 0);
		in5.put("originalId", 0);
		in5.put("status","");
		in5.put("issueTime", 1425545750006l);
		in5.put("hits", 123);
		in5.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		ContentValues in6 = new ContentValues();
		in6.put("id", 6);
		in6.put("personId", 5);
		in6.put("activityTime", 1425546750006l);
		in6.put("title", "");
		in6.put("content", "忧郁的慈禧 ——逃亡的路上可有星光照耀 .幸福大街2015年春季巡演无锡专场 ");
		in6.put("address", "活塞 LIVE HOUSE");
		in6.put("maxNum", 200);
		in6.put("currentNum", 4);
		in6.put("forwardIdFrom", 0);
		in6.put("originalId", 0);
		in6.put("status","");
		in6.put("issueTime", 1425545750006l);
		in6.put("hits", 123);
		in6.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		ContentValues in7 = new ContentValues();
		in7.put("id", 7);
		in7.put("personId", 6);
		in7.put("activityTime", 1425546750006l);
		in7.put("title", "");
		in7.put("content", "旅行是件经事儿，背上行囊走在青春与梦想的路上。 不要问我来自哪里， 不要问我为何孤身一人。 相遇，分开，把玩孤独，享受寂寞， 更明白自己是谁。 在一个人的旅途上， 我并不害怕");
		in7.put("address", "无锡 滨湖区 雕刻时光 无锡");
		in7.put("maxNum", 50);
		in7.put("currentNum", 49);
		in7.put("forwardIdFrom", 0);
		in7.put("originalId", 0);
		in7.put("status","");
		in7.put("issueTime", 1425545750006l);
		in7.put("hits", 123);
		in7.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		ContentValues in8 = new ContentValues();
		in8.put("id", 8);
		in8.put("personId", 7);
		in8.put("activityTime", 1425546750006l);
		in8.put("title", "");
		in8.put("content", "无锡孤独症康复十周年“爱心券”义卖，帮助来自星星的孩子们");
		in8.put("address", "无锡 无锡大剧院");
		in8.put("maxNum", 30);
		in8.put("currentNum", 30);
		in8.put("forwardIdFrom", 0);
		in8.put("originalId", 0);
		in8.put("status","");
		in8.put("issueTime", 1425545750006l);
		in8.put("hits", 123);
		in8.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		this.db.insert("EInvitation", null, in1);
		this.db.insert("EInvitation", null, in2);
		this.db.insert("EInvitation", null, in3);
		this.db.insert("EInvitation", null, in4);
		this.db.insert("EInvitation", null, in5);
		this.db.insert("EInvitation", null, in6);
		this.db.insert("EInvitation", null, in7);
		this.db.insert("EInvitation", null, in8);
	}

	public void initLetter(){

	}

	public void initLevel(){
		ContentValues lvl1 = new ContentValues();
		lvl1.put("id", 1);
		lvl1.put("name", "成就魔导师");
		lvl1.put("upgradeExp", 3000);
		lvl1.put("type", "person");
		this.db.insert("ELevel", null, lvl1);

		ContentValues lvl2 = new ContentValues();
		lvl2.put("id", 2);
		lvl2.put("name", "超级工会");
		lvl2.put("upgradeExp", 13000);
		lvl2.put("type", "union");
		this.db.insert("ELevel", null, lvl2);
	}

	public void initPerson(){
		System.out.println("进来了");
		ContentValues p1 = new ContentValues();
		p1.put("id", 1);
		p1.put("name", "王昆");
		p1.put("levelId", 1);
		p1.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
		p1.put("sex", "男");
		p1.put("school", "蚌埠学院");
		p1.put("department", "计算机");
		p1.put("birthday", 10000000000l);
		p1.put("phoneNum", "10000");
		p1.put("password", "1");
		p1.put("position", "学生会主席");
		p1.put("vp", 80);
		p1.put("currentExp", 2730);
		this.db.insert("EPerson", null, p1);

		ContentValues p2 = new ContentValues();
		p2.put("id", 2);
		p2.put("name", "吕高昂");
		p2.put("levelId", 1);
		p2.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/8AF5E330B6E245BAAAFC8078BC8A9D91");
		p2.put("sex", "男");
		p2.put("school", "江南大学");
		p2.put("department", "计算机");
		p2.put("birthday", 10000000000l);
		p2.put("phoneNum", "10001");
		p2.put("password", "1");
		p2.put("vp", 80);
		p2.put("currentExp", 2730);
		this.db.insert("EPerson", null, p2);

		ContentValues p3 = new ContentValues();
		p3.put("id", 3);
		p3.put("name", "测试小张");
		p3.put("levelId", 1);
		p3.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
		p3.put("sex", "男");
		p3.put("school", "斯坦福小学");
		p3.put("department", "计算机");
		p3.put("birthday", 10000000000l);
		p3.put("phoneNum", "10000");
		p3.put("password", "1");
		p3.put("position", "学生会主席");
		p3.put("vp", 80);
		p3.put("currentExp", 1001);
		this.db.insert("EPerson", null, p3);
		for(int i=0;i<20;i++){
			ContentValues cv = new ContentValues();
			cv.put("id", i+4);
			cv.put("name", "王昆"+i);
			cv.put("levelId", 1);
			cv.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/51DD76FE9A8349DFA47BEAD61C91E523");
			cv.put("sex", "男");
			cv.put("school", "蚌埠学院");
			cv.put("department", "计算机");
			cv.put("birthday", 10000000000l);
			cv.put("phoneNum", 10003+i+"");
			cv.put("password", "1");
			cv.put("vp", 80);
			cv.put("currentExp", 2730);
			this.db.insert("EPerson", null, cv);
		}
	}

	public void initUnion(){
		ContentValues u1 = new ContentValues();
		u1.put("id", 1);
		u1.put("name", "STARS");
		u1.put("chairmanId", 1);
		u1.put("currentExp", 12100);
		u1.put("icon", "http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
		u1.put("levelId", 2);
		u1.put("time", 1000000000l);
		u1.put("totalNum", 570);
		u1.put("status", "normal");
		u1.put("type", "综合类");
		u1.put("activityNum", 5);
		this.db.insert("EUnion", null, u1);

		ContentValues u2 = new ContentValues();
		u2.put("id", 2);
		u2.put("name", "BlueSky");
		u2.put("currentExp", 0);
		u1.put("chairmanId", 2);
		u2.put("icon", "http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
		u2.put("levelId", 2);
		u2.put("time", 1000000000l);
		u2.put("totalNum", 6);
		u2.put("status", "applying");
		u2.put("type", "综合类");
		u2.put("activityNum", 5);
		this.db.insert("EUnion", null, u2);
	}

	public void initAttention(){

	}

	public void initInvitationPerson(){

	}
	public void initPersonAchievement(){
		ContentValues pa1 = new ContentValues();
		pa1.put("personId",1);
		pa1.put("achievementId",1);
		pa1.put("time",new Date().getTime());
		pa1.put("currentProgress", 1);
		this.db.insert("RPersonAchievement", null, pa1);

		ContentValues pa2 = new ContentValues();
		pa2.put("personId",1);
		pa2.put("achievementId",2);
		pa2.put("time",new Date().getTime());
		pa2.put("currentProgress", 1);
		this.db.insert("RPersonAchievement", null, pa2);
		ContentValues pa3 = new ContentValues();
		pa3.put("personId",1);
		pa3.put("achievementId",3);
		pa3.put("time",new Date().getTime());
		pa3.put("currentProgress", 1);
		this.db.insert("RPersonAchievement", null, pa3);
		ContentValues pa4 = new ContentValues();
		pa4.put("personId",1);
		pa4.put("achievementId",4);
		pa4.put("time",new Date().getTime());
		pa4.put("currentProgress", 1);
		this.db.insert("RPersonAchievement", null, pa4);

	}
	public void initUnionApplication(){

	}
	public void initUnionPerson(){
		ContentValues up1 = new ContentValues();
		up1.put("unionId", 1);
		up1.put("personId", 1);
		up1.put("status", "in");
		this.db.insert("RUnionPerson", null, up1);

		ContentValues up2 = new ContentValues();
		up2.put("unionId", 2);
		up2.put("personId", 2);
		up2.put("status", "in");
		this.db.insert("RUnionPerson", null, up2);

		for(int i = 0;i<20;i++){
			ContentValues up= new ContentValues();
			up.put("unionId", 1);
			up.put("personId",i+3);
			up.put("status", "in");
			this.db.insert("RUnionPerson", null, up);
		}
	}
}
