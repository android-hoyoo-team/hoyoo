package com.huyoo.global;


import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.huyoo.entity.EInvitation;
import com.huyoo.utils.GsonUtil;

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
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,exp INTEGER,type TEXT,description TEXT,icon TEXT,addition TEXT,"
					+ "totalProgress INTEGER,expensionPack TEXT)");
			//EArticle
			db.execSQL("CREATE TABLE IF NOT EXISTS EARTICLE"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,time INTEGER,title TEXT,content TEXT,icon TEXT,hits INTEGER,type TEXT)");
			//EComment
			db.execSQL("CREATE TABLE IF NOT EXISTS ECOMMENT"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,time INTEGER,content TEXT,invitationId INTEGER,commentIdTo INTEGER)");
			//EInvitation
			db.execSQL("CREATE TABLE IF NOT EXISTS EINVITATION"+
					"(id INTEGER PRIMARY KEY AUTOINCREMENT,personId INTEGER,activityTime INTEGER,title TEXT,content TEXT,address TEXT,"
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
		a1.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/5CB33506984A4ABCAD0A0D898148F3F8");
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
		a2.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/6B41D92E68E144CA8B10F2120D971D62");
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
		a3.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/2F53E297598847A6A6366405984111E8");
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
		a4.put("icon", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/B2787DA6ADD842C1A30606C7AB21CA33");
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
		Random random = new Random();
		ContentValues a1 = new ContentValues();
		a1.put("id", 1);
		a1.put("hits", 111);
		a1.put("content","<p style=\"text-indent: 2em;\">2015年全国两会，习近平下团成了另一种形式的“走基层”。上海团、江西团、广西团、吉林团，面对几个具有鲜明地域特征的省份，他除了有针对性地谈感受，也同时说到一些共性的发展问题。对于反腐话题，习近平讲道：“可以说所有腐败问题，起初往往都是从作风失范开始的，从不讲规矩开始的，从违反纪律开始的。几顿饭、几杯酒、几张卡，‘温水煮青蛙’，不知不觉，一失足成千古恨”。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">2015年全国两会，习近平下团成了另一种形式的“走基层”。上海团、江西团、广西团、吉林团，面对几个具有鲜明地域特征的省份，他除了有针对性地谈感受，也同时说到一些共性的发展问题。这次两会，人民日报全媒体平台（中央厨房）小厨，有机会现场聆听总书记的一系列谈话。经过小厨这几天认真梳理，发现有四个高频词非同一般，诸位可以从中看出习近平的牵挂和关切。</p><p style=\"text-indent: 2em;\"><br/></p><p><br/></p>");
		a1.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D534209A93414716B5693C0148302734");
		a1.put("personId", 1);
		a1.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a1.put("title", "习近平：腐败往往始于几杯酒几张卡");
		a1.put("type", "公会新闻");
		this.db.insert("EArticle", null, a1);

		ContentValues a2 = new ContentValues();
		a2.put("id", 2);
		a2.put("hits", 222);
		a2.put("content","<p style=\"text-indent: 2em;\">司法是实现公平正义的重要保障。无论是纠正冤假错案，还是“打虎拍蝇”，维护的皆是守法者的合法权益。细读这两份总长上万字的报告，“公正”一词共出现了19次。即便听起来“高大上”的报告内容，其实也跟每一个普通人有关，皆是“努力让人民群众在每一个司法案件中都感受到公平正义”，为了维护你我公平分享改革发展成果。（3月12日人民网）</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">公平正直,没有偏私是社会主义核心价值观之一。在“两高”的报告中，19次的“公正” 让人们看到了“两高”力促司法公正的努力，让人们感受到了中国提升司法公正水平的决心。同时，也给了人们信心，让人们更加相信公平正义将不会是水中月、镜中花。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">公正是法治的生命线。司法公正，就会引领社会公正；司法不公，就会对社会公正造成致命破坏。2015年是我国全面深化改革的关键之年，是全面依法治国的开局之年，司法改革则是全面推进依法治国的重要环节，足以可见司法公正之于司法改革的重要性，而这19次“公正”正是为司法公正这一司法改革的关键点按下了“启动键”、挂上了“快进档”。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">“法者,治之端也。”要确保司法改革能跨过深水区，顺利上岸，就要确保执法者能以身作则，坚决执行党的决定，坚决拥护党纪国法，能够依法行事、依法办事，绝不会跨越纪律红线、打破法律底线，绝不会出现司法为权、司法为钱、司法为情的情况。正所谓“奉法者强则国强，奉法者弱则国弱”，只有做到了公正廉洁、明洁奉公，才能保证司法改革的前行不会出现方向性错误，才能让司法改革深得民心，才能政清人和、政通人和。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">公开透明是保证司法公正最有效的举措。执法者要时刻坚守司法公正底线, 确保法律面前人人平等，就需要加大司法公开力度，让权力在阳光下运行，接受广大人民群众的监督，让阳光防腐为权力瘦身，确保权是为民所用、利是为民所谋，努力让人民群众在每个司法案件中感受到公平正义，让司法改革顺利前行。</p><p><br/></p>");
		a2.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/866DBA36585445A68C69F454D37DC46E");
		a2.put("personId", 1);
		a2.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a2.put("title", "“两高”报告出现19次“公正”为司法改革提速");
		a2.put("type", "公会新闻");
		this.db.insert("EArticle", null, a2);

		ContentValues a3 = new ContentValues();
		a3.put("id", 3);
		a3.put("hits", 333);
		a3.put("content","<p style=\"text-indent: 2em;\">我们党在上世纪90年代时，就明确提出要“从严治党”。在党的十五大报告中首次指出：“从严治党，是保持党的先进性和纯洁性，增强党的凝聚力和战斗力的保证。”习近平总书记提出的“全面从严治党”，是在原有的“从严治党”前面加上了“全面”二字，更加突出了管党治党的艰巨性、复杂性和深刻性。它以高屋建瓴的理论视野，深化和拓展了我们对党要管党、从严治党的认识。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">全面从严治党“全在哪里”？首先是全覆盖。中国共产党上上下下的所有组织，没有任何的特殊和例外，都要贯彻实施全面从严治党，实现全覆盖。党的各级组织必须把全面从严治党列入党建工作议程。全面从严治党的全覆盖，目前的重点和难点在于基层组织，主要是农村的乡镇、村（居），城市的街道、社区里的党组织；还有，非公企业和新社会组织中的党组织。为了不使这些党组织被遗漏，一些地方党委成立了非公经济组织和新社会组织党建工作协调领导小组，建立了非公经济组织和新社会组织党建工作联席会议制度，明确了相关部门的职责和工作任务，形成了在党委统一领导下，全面从严治党各负其责、各尽其力、密切配合、齐抓共管的工作格局。这样的格局要继续推广和发展。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">全面从严治党“全在哪里”？其次是全方位。党的十八大报告对党的建设作出了总体部署，即：全面加强党的思想建设、组织建设、作风建设、反腐倡廉建设、制度建设，由此形成了“五位一体”的全方位的党建格局。全面从严治党，要求我们切实把握好五个方面的全方位建设。实现五个方面的全方位建设，要求我们不能只注意某一方面而忽视某一方面，只强调某一方面而舍弃某一方面。各级党组织全面从严治党，应从实际出发，针对自身情况，突出其重点，着力解决现实存在的问题。但在整体上，则必须坚持全面从严治党的全方位推进。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">全面从严治党“全在哪里”？再次是全过程。全面从严治党的每一项工作，都要重在细节、重在过程，必须防止前热后冷、前紧后松，出现虎头蛇尾、半途而废的现象。我们要严格按照全面从严治党的工作部署，扎扎实实地走完每一个步骤、每一个程序。全过程要求全面从严治党的每一项工作，一定要善始善终，不图形式，不走过场。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">全面从严治党“全在哪里”？最后是全周期。全面从严治党这项党建重点工作，将是长期的，无止境的。全面从严治党只有进行时，没有完成时。一些党组织和党员、干部，总以为全面从严治党也不过是一个短时期的活动，搞个三年五载就差不多了。必须认识到，全面从严治党的全周期，固然也会表现为一个三年五载的短时期、阶段性的发展，但一个阶段的发展和结束，只是标志着全面从严治党的拓展和深化，并不意味着全面从严治党整个工作的结束。全面从严治党是由众多的发展阶段组成的全周期，全面从严治党必须持之以恒，搞好阶段与阶段之间的衔接，坚持长期发展、持续推进、扎实推进。</p><p><br/></p>");
		a3.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/771AAA117D2549B8A6A45A03EDE07DF7");
		a3.put("personId", 1);
		a3.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a3.put("title", "带你理解“全面从严治党”");
		a3.put("type", "公会新闻");
		this.db.insert("EArticle", null, a3);

		ContentValues a4 = new ContentValues();
		a4.put("id", 4);
		a4.put("hits", 444);
		a4.put("content","<p style=\"text-indent: 2em;\">本报讯（记者 李天际）上海日前发生交警被肇事司机拖行10米致死事件，引发社会广泛关注。针对袭警案件的多发，全国人大代表、北京市保安服务总公司海淀分公司副经理朱良玉提出建议，尽快推动设立“袭警罪”，同时推动公安民警参加工伤保险制度。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">朱良玉通过调研发现，2010年以来，全国已经查处的袭警侵警案件年均递增1000起以上，受侵害民警人数更是跳跃式猛增，2010年受侵害民警7268人，到2013年增至12327人，上升70%。2013年，全国查处的侵害民警执法权益案（事）件达9835件，受轻微伤以上民警7956人，牺牲23人。仅北京市2013年就有498名民警在执法中被打伤，比上一年增加65%。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">朱良玉表示，仅公安部所调查的7个省份，三年来一次导致3名以上民警遭袭受伤的就有367起，特别是在接处警、处置群体性事件、纠违、盘查嫌疑人等过程中，民警遭受侵害的案（事）件经常发生。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">他认为，袭警行为不能等同于普通的妨害公务罪，造成人民警察伤亡的更不能等同于普通的伤害罪和杀人罪。他建议，相关部门应及时修订法律法规，设立“袭警罪”，依法严厉打击暴力袭警的行为。</p><p><br/></p>");
		a4.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/85BBA4100B7447CFAA70628F6AD0B443");
		a4.put("personId", 1);
		a4.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a4.put("title", "人大代表：严打暴力袭警应设“袭警罪”");
		a4.put("type", "公会新闻");
		this.db.insert("EArticle", null, a4);

		ContentValues a5 = new ContentValues();
		a5.put("id", 5);
		a5.put("hits", 555);
		a5.put("content","<p style=\"text-indent: 2em;\">3月15日上午，国务院总理李克强在人民大会堂三楼金色大厅会见采访十二届全国人大三次会议的中外记者，并回答记者提出的问题。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">台湾TVBS记者：去年一年在台湾发生一些事情，影响到两岸经济合作的进程。在大陆的经济结构、经济增速放缓的趋势下，在大陆台商也遇到一些经营和发展上的困难。请问大陆在继续促进两岸经济合作的过程中有什么策略，可以让台湾的企业家和台湾一般民众更优先而切实地感受到大陆发展的机遇？</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">李克强：两岸是一家人，是骨肉同胞。坚持“一个中国”、“九二共识”、反对“台独”，维护两岸关系和平发展，就会给两岸经济合作创造基础，扩大空间。对推动两岸经济合作来说，需要两个轮子一起转。一个轮子就是要加强两岸经贸合作的制度化建设，比如说像ECFA后续协商。另一个轮子就是扩大相互开放。对大陆来说，尤其是要重视在大陆投资的台湾企业。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">李克强：这里请你传递一个带有“定心丸”的消息，就是大陆将会继续维护台资企业和台商的合法权益，保持对他们的合理优惠政策。而且在对外开放中，我们会先一步对台湾开放，或者说对台湾开放的力度和深度会更大一些。我们欢迎台商包括年轻人到大陆来创业，并且愿意推动两岸人员交流，拉近两岸民众的心理距离。谢谢！(根据网络文字直播整理)</p><p><br/></p>");
		a5.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/0BFAE766F5DA4B709785FAC39D20E9E1");
		a5.put("personId", 1);
		a5.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a5.put("title", "李克强：在对外开放中大陆会先一步对台湾开放");
		a5.put("type", "公会新闻");
		this.db.insert("EArticle", null, a5);

		ContentValues a7 = new ContentValues();
		a7.put("id", 7);
		a7.put("hits", 777);
		a7.put("content","<p style=\"text-indent: 2em;\">新华社北京8月3日电 北京时间8月3日16时30分许，云南省昭通市鲁甸县境内（北纬27.1度，东经103.3度）发生6.5级地震，震源深度12公里。据初步统计，截至3日20时45分，已造成150多人死亡，1300多人受伤。鲁甸县城通往震中龙头山镇的道路因塌方中断，部分地区出现房屋倒塌情况。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">地震发生后，中共中央总书记、国家主席、中央军委主席习近平高度重视，立即作出重要指示，要求当前把救人放在第一位，努力减少人员伤亡，妥善做好群众安置工作。有关方面要抓紧了解灾情，组织群众避险，全力投入抗震救灾。要加强余震监测预报，密切防范次生灾害发生。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">中共中央政治局常委、国务院总理李克强作出批示，要求有关部门千方百计抢救被掩埋和受伤人员。要确保群众有饭吃、有衣穿、有干净水喝、有临时住处、有病能得到及时治疗。要保障救灾物资、人员运输和通讯畅通，维护灾区社会秩序。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">根据习近平和李克强指示，国家减灾委秘书长、民政部副部长姜力率有关部门组成的国务院工作组赶赴地震灾区代表党中央、国务院慰问灾区群众，指导救灾工作。国家减灾委、民政部已紧急启动国家Ⅲ级救灾应急响应，云南省委、省政府负责同志已率领工作组赶赴灾区，各项抗震救灾工作正在抓紧进行。</p><p><br/></p>");
		a7.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/942D3E555D4844CD8EF7DBA95F5AB567");
		a7.put("personId", 1);
		a7.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a7.put("title", "习近平对云南昭通鲁甸县地震作出重要指示批示");
		a7.put("type", "公会新闻");
		this.db.insert("EArticle", null, a7);

		ContentValues a8 = new ContentValues();
		a8.put("id", 8);
		a8.put("hits", 888);
		a8.put("content","<p style=\"text-indent: 2em;\">本报讯（记者 杨琳 实习记者 孔令晗 刘洪静）近日，有市民拨打本报热线电话反映，朝阳区雅宝路附近有商家贴出“中国人不得进入”的告示。对此，该商家店员称，之所以张贴这份告示是因为自家只做外贸生意，不想中国同行进入店铺造成走款。法律专家则认为，商家此举涉嫌歧视消费者。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">昨天下午，北青报记者来到雅宝路附近。在一家服装店门口，记者看到，店门的玻璃上贴着一张写有“中国人不得进入（员工除外）”的告示。北青报记者随后以普通消费者名义进入这家服装店，刚进去便有该店的店员告诉记者他们不进行内销业务。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">记者随即表示，自己并不是来购买服饰，只是看到门口贴有“中国人不得进入”的告示，所以特来询问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">该名店员解释称，他们之所以张贴这张告示是因为自家只做外贸生意，不愿意中国同行进入店铺造成走款。另一位服装店的工作人员解释说，防止同行抄袭并不是主要原因，之所以贴这张告示是因为“有些中国客户实在是太过分了”。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">这名员工告说，门上这张告示张贴了已经有一周左右的时间，原因是之前店里发生了外国顾客在店内选购期间被店里的中国客户偷走钱包的事情。虽然店家积极配合失主调查，也调取了店内监控视频，但外国顾客始终认为店家与扒手是同伙，并要求店家赔偿自己5000美元的损失。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">此外，平日里也常常有很多中国女性客户试穿一大堆衣服，最后却并不购买，返还衣物时还对店员恶言相向。店家认为自己人手有限，实在难以分出精力去顾及零售业务，所以才贴出了“中国人不得进入”的告示。这位员工再三向记者表示：“我们也不想贴这个告示，让别人觉得自己都看不起自己，但有些中国顾客实在太过分了。”</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">北青报记者查询发现，这已经不是雅宝路第一次对国人说“不”。早在2003年，就有媒体报道称雅宝路的部分商户拒绝国人进入，商户挂帘上中文就写“谢绝参观”，外文就写“欢迎光临”。当时商户给出的解释说，此举并非是因为歧视国人，而是为了防止同行获取商业情报。其间甚至发生过因为店员拒绝中国消费者入内而与顾客的斗殴事件。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">中国政法大学教授李显东认为，如果雅宝路这些商家以不零售、怕同行进入造成走款为由拒绝消费者入内，这种行为可以理解，因为商户有权选择自己的经营模式，也能以做外贸经营不零售为理由，不向消费者出售商品，但在商铺贴上“中国人禁止入内”的标语却有些激进，涉嫌歧视国人。“如果禁止中国人入内，那么这些店员是不是中国人，他们为什么就可以进入？虽然从法律上来说这些商家的行为可能不违法，但从文化上来看，这种做法并不妥当。”</p><p><br/></p>");
		a8.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C5B49D22BA1442C7A404085227425417");
		a8.put("personId", 1);
		a8.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a8.put("title", "商家贴“中国人不得进入”被指歧视国人");
		a8.put("type", "公会新闻");
		this.db.insert("EArticle", null, a8);

		ContentValues a9 = new ContentValues();
		a9.put("id", 9);
		a9.put("hits", 999);
		a9.put("content","<p style=\"text-indent: 2em;\">人民网东京3月18日电（赵松）据《读卖新闻》报道，被称为准航母的日本海上自卫队最大的护卫舰“日向号”17日进入位于日本海沿岸的海上自卫队舞鹤基地，替换即将退役的“白根号”护卫舰。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">“日向号”全长197米，标准排水量为1万3950吨，可搭载约10架直升飞机，舰上人员约380人，是日本海上自卫队最大的护卫舰。“日向号”从2009年开始服役，此前部署在位于太平洋沿岸的神奈川县横须贺港。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">以舞鹤基地为母港的“白根号”从1980年开始服役，是日本现役的47艘护卫舰中服役时间最长的。“白根号”即将于本月25日正式退役，“日向号”成为后继舰只。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">报道称，舞鹤基地所在的地方政府此前就“白根号”退役对当地造成的影响进行了讨论，称舰上人员及其家属的外迁将对当地经济打来打击，同时也会影响灾害救助，因此要求在舞鹤基地部署后继舰只。</p><p><br/></p>");
		a9.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A344CA200B3146DDAF9B4B7E49780928");
		a9.put("personId", 1);
		a9.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a9.put("title", "日本准航母部署日本海沿岸 替换老旧舰只");
		a9.put("type", "公会新闻");
		this.db.insert("EArticle", null, a9);

		ContentValues a10 = new ContentValues();
		a10.put("id", 10);
		a10.put("hits", 1000);
		a10.put("content","<p style=\"text-indent: 2em;\">中新网3月18日电  据日本媒体报道，当地时间本月17日晚，日本首相安倍晋三在首相官邸与美国前总统比尔·克林顿共进晚餐。此外，克林顿在任时的日本前首相森喜朗及现美国驻日本大使卡罗琳·肯尼迪也一起陪同出席。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">据报道，晚宴当天，克林顿在首相官邸玄关处受到了安倍的亲自迎接。在合影留念后，克林顿笑着表示“谢谢”。双方在日式房间内进行的晚餐会持续了约1个半小时。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">据悉，围绕计划于今年4月底进行的访美等事宜，安倍与克林顿共同探讨了深化日美同盟及推进文化交流等议题，并就当前的东亚局势深入交换了意见。分别时克林顿还与安倍等人依依不舍地握了手。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">据悉，安倍和克林顿还将于本月18日出席在早稻田大学举行的一场研讨会。</p><p><br/></p>");
		a10.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/DF433D093A0A40CDB539F63AB72BDAB2");
		a10.put("personId", 1);
		a10.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a10.put("title", "安倍与美国前总统克林顿共进晚餐 探讨访美事宜");
		a10.put("type", "公会新闻");
		this.db.insert("EArticle", null, a10);

		ContentValues a11 = new ContentValues();
		a11.put("id", 11);
		a11.put("hits", 234);
		a11.put("content","<p style=\"text-indent: 2em;\">20多岁时，酉阳县王祥贵在一次意外中炸断双手和一条臂膀。出院后，他学回了用脚代替手的功能。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">1988年，村里的护林员老了。由于工资低，工作辛苦，没人愿意当护林员。在村里推荐下，王祥贵接任了护林员工作。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">最初，每月工资只有60元，但王祥贵工作中从不敷衍。除了下雨，每天天刚亮，王祥贵就去巡山，傍晚才回家，一天只吃早晚两顿饭。责任林包括10座山头，他每天要走30多公里山路。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">没有双手，王祥贵就用残缺的左臂夹住电筒。一次，王祥贵摔进一个坑，半小时才爬出来。多年来，他身上留着一道道伤疤。夏天山上蚊子多，他身上留下一个个红疙瘩。没手抓不到痒，他只能在树木或石头上来回磨蹭。经常有人在草丛设下套子套猎物，一次，一个细铁丝做的夹子将他绊倒，他花了1个多小时，嘴脚并用将铁丝解开。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">当大家在采购年货准备过年的时候，王祥贵还在山上砍伐防火带；当大家吃完年夜饭准备燃放烟花爆竹的时候，王祥贵还在冒着寒风在县城环城路上巡逻；当大家上山上坟的时候，王祥贵却守住上山路口，宣传防火知识。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">26年一晃而过，王祥贵已经55岁，在他呵护下，原来树木稀疏的10座山头，成了国家级森林公园。</p><p><br/></p>");
		a11.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/864FF3F9D6B441518C17900666CD7D08");
		a11.put("personId", 1);
		a11.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a11.put("title", "独臂护林员巡山26年 稀疏山头变国家森林公园");
		a11.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a11);

		ContentValues a12 = new ContentValues();
		a12.put("id", 12);
		a12.put("hits", 123);
		a12.put("content","<p style=\"text-indent: 2em;\">宋思源打小就是胖姑娘，有句话叫做，“喝水都能胖”说的就是她。说不自卑是假的，每当朋友聚会时，都是呆在角落里，寡言少语，朋友不多，也时常遭遇别人的冷嘲热讽。不过，乐观的宋思源没有在意过，只是逛街购物时，每件衣服连试穿的机会都被老板拒绝，还是让她烦恼不已。那是所有女人的最爱啊，她从小到大的梦想就是穿上一条美丽的裙子，却从小到大都没实现过。</p>");
		a12.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C8DB5B32DF2C4227B00B2D5DBD39C106");
		a12.put("personId", 1);
		a12.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a12.put("title", "190斤胖妞减重90斤变身蛮腰长腿女神");
		a12.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a12);

		ContentValues a13 = new ContentValues();
		a13.put("id", 13);
		a13.put("hits", 345);
		a13.put("content","<p style=\"text-indent: 2em;\">北京时间3月26日，中国男足国家队继续在长沙体育中心集训备战与海地国家队的比赛。从训练的情况以及主帅佩兰的态度来看，参加亚洲杯比赛的球员将成为明天与海地队比赛的主要球员。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">连续两天的训练课，主帅佩兰都在进行球队的主力阵容调试，根据记者的观察，亚洲杯时期的主力球员依然是佩兰十分倚重的力量，佩兰在发布会上也公开表示，我们会以亚洲杯球员为班底准备这场比赛。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">门将位置上，王大雷将继续首发。后防线上，冯潇霆和梅方搭档中后卫，左后卫任航，右边后卫是吉翔。尽管冯潇霆没有参加亚洲杯，但这名恒大中卫在佩兰国家队之前的比赛中一直坐稳主力，这次重新回归国足也是一次再度证明自己的机会。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">中场方面，左前卫于海，右前卫于汉超，防守型后腰位置是蔡慧康，两个攻击型前卫是武磊和吴曦。前锋线上郜林顶在最前面。这套阵容也是昨天和今天两堂训练课上，佩兰主要演练的阵容。如果没有伤病的原因，明天国足的首发阵容将不会发生变化。</p><p><br/></p>");
		a13.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/227822F27A3B41ACAB1D05E1367CCE60");
		a13.put("personId", 1);
		a13.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a13.put("title", "国足预计首发：冯潇霆回归领防线 王大雷守龙门");
		a13.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a13);

		ContentValues a14 = new ContentValues();
		a14.put("id", 14);
		a14.put("hits", 456);
		a14.put("content","<p style=\"text-indent: 2em;\">红安一干部中餐刚喝完一顿酒，满脸通红来开会，结果当场被酒精测试“揪住尾巴”，会上被纪委就地宣布停职。昨日，红安“政治纪律和政治规矩”集中教育动员大会上，一批干部被“小事动真格”处理，给全县干部上了一堂生动的教育课。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">13时50分，该县县直机关、镇、村的近千名党员干部陆续走进县影剧院。按常规，与会者签到、领材料。令人意外的是，会场门口有4名工作人员手持酒精测试仪，逐一测试与会者是否中餐饮酒。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">此时，一名满脸通红的中年男子发现势头不对，扭头就走，被县纪委工作人员当场拦住。现场酒精测试显示，该男子中餐饮酒。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">大会第一项议程就让会场气氛凝重。县纪委书记万小勇当场通报违反会议纪律和中餐饮酒规定情况：县政府机关事务管理办公室副主任吴怀泰中餐饮酒被停职；会议迟到者7人；未经请假擅自请人代会1人；3个单位未参加会议。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">按规定，红安县委从严治党办公室将对相关未履行主体责任的单位党组织主要负责人追究责任，并在红安电视台、红安网上公开通报曝光。“讲政治纪律和政治规矩是从严治党的首要任务。”黄冈市委常委、红安县委书记余学武说，严惩开会迟到、中餐饮酒等问题，看似“小题大做”，实则关系从严治党，不能半点马虎。</p><p><br/></p>");
		a14.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C53CBE739EDC4788948811D1FBB78CDD");
		a14.put("personId", 1);
		a14.put("time",new Date().getTime()-(int)(60*1000*60*4*random.nextFloat()));
		a14.put("title", "湖北1官员中餐喝酒 满脸通红去开会当场被停职");
		a14.put("type", "成功秘笈");
		this.db.insert("EArticle", null, a14);
	}

	public void initComment(){
		ContentValues coin2 = new ContentValues();
		coin2.put("id", 1);
		coin2.put("personId", 1);
		coin2.put("time",1426583209941l);
		coin2.put("content", "这个乐队的歌很不错，我听过");
		coin2.put("invitationId", 1);
		coin2.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin2);

		ContentValues coin3 = new ContentValues();
		coin3.put("id", 2);
		coin3.put("personId", 2);
		coin3.put("time",1426583209941l);
		coin3.put("content", "很有意义的活动！");
		coin3.put("invitationId", 2);
		coin3.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin3);

		ContentValues coin4 = new ContentValues();
		coin4.put("id", 3);
		coin4.put("personId", 3);
		coin4.put("time",1426583209941l);
		coin4.put("content", "想去的地方。。。嗯~");
		coin4.put("invitationId", 3);
		coin4.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin4);

		ContentValues coin5 = new ContentValues();
		coin5.put("id", 4);
		coin5.put("personId", 4);
		coin5.put("time",1426583209941l);
		coin5.put("content", "不见不散");
		coin5.put("invitationId", 4);
		coin5.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin5);

		ContentValues coin6 = new ContentValues();
		coin6.put("id", 5);
		coin6.put("personId", 5);
		coin6.put("time",1426583209941l);
		coin6.put("content", "哇塞~很酷的说，我要参加！");
		coin6.put("invitationId", 5);
		coin6.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin6);
		
		ContentValues coin7 = new ContentValues();
		coin7.put("id", 6);
		coin7.put("personId", 1);
		coin7.put("time",1426583209941l);
		coin7.put("content", "来吧来吧来吧");
		coin7.put("invitationId", 6);
		coin7.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin7);

		ContentValues coin8 = new ContentValues();
		coin8.put("id", 7);
		coin8.put("personId", 2);
		coin8.put("time",1426583209941l);
		coin8.put("content", "棒棒哒");
		coin8.put("invitationId", 7);
		coin8.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin8);

		ContentValues coin9 = new ContentValues();
		coin9.put("id", 8);
		coin9.put("personId", 3);
		coin9.put("time",1426583209941l);
		coin9.put("content", "让我们都献出自己的一份力量，很有意义的活动，大家多多报名");
		coin9.put("invitationId", 8);
		coin9.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin9);

		ContentValues coin10 = new ContentValues();
		coin10.put("id", 9);
		coin10.put("personId", 4);
		coin10.put("time",1426583209941l);
		coin10.put("content", "不见不散");
		coin10.put("invitationId", 1);
		coin10.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin10);

		ContentValues coin11 = new ContentValues();
		coin11.put("id", 10);
		coin11.put("personId", 5);
		coin11.put("time",1426583209941l);
		coin11.put("content", "给你32个赞");
		coin11.put("invitationId", 2);
		coin11.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin11);
		
		ContentValues coin12 = new ContentValues();
		coin12.put("id", 11);
		coin12.put("personId", 6);
		coin12.put("time",1426583209941l);
		coin12.put("content", "很期待");
		coin12.put("invitationId", 2);
		coin12.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin12);
		
		ContentValues coin13 = new ContentValues();
		coin13.put("id", 12);
		coin13.put("personId", 3);
		coin13.put("time",1426583209941l);
		coin13.put("content", "可以通过这种活动多认识些朋友，很好！");
		coin13.put("invitationId", 4);
		coin13.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin13);
		
		ContentValues coin14 = new ContentValues();
		coin14.put("id", 13);
		coin14.put("personId", 1);
		coin14.put("time",1426583209941l);
		coin14.put("content", "宅男宅女一起来玩，哈哈哈");
		coin14.put("invitationId", 4);
		coin14.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin14);
		
		ContentValues coin15 = new ContentValues();	
		coin15.put("id", 14);
		coin15.put("personId", 5);
		coin15.put("time",1426583209941l);
		coin15.put("content", "春天来了，一起去踏青吧~");
		coin15.put("invitationId", 7);
		coin15.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin15);
		
		ContentValues coin16 = new ContentValues();	
		coin16.put("id", 15);
		coin16.put("personId", 7);
		coin16.put("time",1426583209941l);
		coin16.put("content", "多出去玩玩也是极好的~");
		coin16.put("invitationId", 7);
		coin16.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin16);
		
		ContentValues coin17 = new ContentValues();	
		coin17.put("id", 16);
		coin17.put("personId", 7);
		coin17.put("time",1426583209941l);
		coin17.put("content", "如果能组织好的话，以后应该多有类似的活动！");
		coin17.put("invitationId", 8);
		coin17.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin17);
		
		ContentValues coin18 = new ContentValues();	
		coin18.put("id", 17);
		coin18.put("personId", 2);
		coin18.put("time",1426583209941l);
		coin18.put("content", "塞塞塞，一定很热闹。。。");
		coin18.put("invitationId", 1);
		coin18.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin18);
		
		ContentValues coin19 = new ContentValues();	
		coin19.put("id", 18);
		coin19.put("personId", 3);
		coin19.put("time",1426583209941l);
		coin19.put("content", "演唱会，没去过，去玩玩");
		coin19.put("invitationId", 1);
		coin19.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin19);
		
		ContentValues coin20 = new ContentValues();	
		coin20.put("id", 19);
		coin20.put("personId", 8);
		coin20.put("time",1426583209941l);
		coin20.put("content", "有么有一起去的，私信啊~");
		coin20.put("invitationId", 1);
		coin20.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin20);
		
		ContentValues coin21 = new ContentValues();	
		coin21.put("id", 20);
		coin21.put("personId", 8);
		coin21.put("time",1426583209941l);
		coin21.put("content", "不会的能去不，有没有教练的啊");
		coin21.put("invitationId", 5);
		coin21.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin21);
		
		ContentValues coin22 = new ContentValues();	
		coin22.put("id", 21);
		coin22.put("personId", 1);
		coin22.put("time",1426583209941l);
		coin22.put("content", "看起来很棒的样子。");
		coin22.put("invitationId", 5);
		coin22.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin22);
		
		ContentValues coin23 = new ContentValues();	
		coin23.put("id", 22);
		coin23.put("personId", 3);
		coin23.put("time",1426583209941l);
		coin23.put("content", "求同行");
		coin23.put("invitationId", 6);
		coin23.put("commentIdTo", 0);
		this.db.insert("EComment", null, coin23);
	}
	public long insertInvitation(EInvitation in)
	{
		try{
			 
			ContentValues in8 = new ContentValues();
			//		in8.put("id", 8);
			in8.put("personId", in.getPersonId());
			in8.put("activityTime", in.getActivityTime());
			in8.put("title", in.getTitle());
			in8.put("content", in.getContent());
			in8.put("address", in.getAddress());
			in8.put("maxNum", in.getMaxNum());
			in8.put("currentNum", in.getCurrentNum());
			in8.put("forwardIdFrom", in.getForwardIdFrom());
			in8.put("originalId", in.getOriginalId());
			in8.put("status",in.getStatus());
			in8.put("issueTime", in.getIssueTime());
			in8.put("hits", in.getHits());
			in8.put("icons", in.getIcons());
			long insert = this.getReadableDatabase().insert("EInvitation", null, in8);
			return insert;
		}catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
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
		in1.put("hits", 120);
		in1.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/EF1E6B7DDB694CCFB133EE7CA37E0A0D\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B252839B13484797AE1D0B7FA617DFC2\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D836B186E1DC4BD5A4E25FF62ED14C06\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/F50F722D3E404CC89A6694B8D1C88F46\"]");
		ContentValues in2 = new ContentValues();
		in2.put("id", 2); 
		in2.put("personId", 4); 
		in2.put("title", "");
		in2.put("issueTime", 1425545650006l); 
		in2.put("activityTime", 1425546750006l); 
		in2.put("content", "2015“我的手艺”首届中华手作创意嘉年华暨全国手工艺品创意品大型巡回展销会"); 
		in2.put("address", "江苏省无锡市清明桥古运河景区南长街"); 
		in2.put("currentNum", 15); 
		in2.put("maxNum", 30); 
		in2.put("hits", 234); 
		in2.put("forwardIdFrom", 0);
		in2.put("originalId", 0);
		in2.put("status","");
		in2.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/01478C5D5E414C809175674096369ADF\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/F1279CAB4E11468D900CC54B29FF7D0F\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D9198AAEE66A443BA5A6D0B8E74B2138\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8DEFE79EBF2848A3AADD161F94A0AAC5\"]");

		ContentValues in3 = new ContentValues();
		
		in3.put("id", 3); 
		in3.put("personId", 3); 
		in3.put("title", "");
		in3.put("issueTime", 1425545550006l); 
		in3.put("activityTime", 1425546750006l); 
		in3.put("content", "周一晚上过来玩"); 
		in3.put("address", "你想到的地方1"); 
		in3.put("currentNum", 15); 
		in3.put("maxNum", 15); 
		in3.put("hits", 34); 
		in3.put("forwardIdFrom", 0);
		in3.put("originalId", 0);
		in3.put("status","");
		
		in3.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B137AD4E9E884ED58E5F6F4B6B327E24\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/5CD269E52C2F4E1C8EE1C044E46CACE5\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7403CA06348A405E9055F6CE05A3F13F\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/67490C28CA58403C822D092FFB96CE14\"]");

		ContentValues in4 = new ContentValues();
		in4.put("id", 4);
		in4.put("personId", 4);
		in4.put("activityTime", 1425546750006l);
		in4.put("title", "");
		in4.put("content", "三国杀、狼人杀。。。桌游任你选，既能丰富了课余时间，又可以体会到上网玩网络游戏体验不到的乐趣，你还在等什么");
		in4.put("address", "大学生活动中心二楼222室");
		in4.put("maxNum", 100);
		in4.put("currentNum", 10);
		in4.put("forwardIdFrom", 0);
		in4.put("originalId", 0);
		in4.put("status","");
		in4.put("issueTime", 1425545450006l);
		in4.put("hits", 1105);
		in4.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/FA2D09FB0EFD4A7F875164771100DA97\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D960A186BCBB440E90A14C841AE32834\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/E96F573FF18A42B6A89CA58B430F8CD7\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/24C9CF5972074986BACA3290DD039467\"]");
		ContentValues in5 = new ContentValues();
		in5.put("id", 5);
		in5.put("personId", 5);
		in5.put("activityTime", 1425546750006l);
		in5.put("title", "");
		in5.put("content", "自由者轮滑爱好者联盟刷街活动");
		in5.put("address", "江南大学南门口");
		in5.put("maxNum", 50);
		in5.put("currentNum", 15);
		in5.put("forwardIdFrom", 0);
		in5.put("originalId", 0);
		in5.put("status","");
		in5.put("issueTime", 1425545350006l);
		in5.put("hits", 33);
		in5.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/59248EE77C8444D3AE302C3A6B9EF061\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D32F5186CEDF4A1088F215885AA21890\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/21A66F75A8C64EA1A619170848776112\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/629C2313A94045579C181321BBE99921\"]");
		ContentValues in6 = new ContentValues();
		in6.put("id", 6);
		in6.put("personId", 6);
		in6.put("activityTime", 1425546750006l);
		in6.put("title", "");
		in6.put("content", "忧郁的慈禧 ——逃亡的路上可有星光照耀 .幸福大街2015年春季巡演无锡专场 ");
		in6.put("address", "活塞 LIVE HOUSE");
		in6.put("maxNum", 200);
		in6.put("currentNum", 4);
		in6.put("forwardIdFrom", 0);
		in6.put("originalId", 0);
		in6.put("status","");
		in6.put("issueTime", 1425545250006l);
		in6.put("hits", 71);
		in6.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A70CED51CA0B4764AA93913B2F69B789\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/90234A7F9F6845D4A79E4D06E6E85381\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/E4BD481D3FEC4051AC074D16E3A2FC5A\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4018750C98324206B25A38CB7715FC82\"]");
		ContentValues in7 = new ContentValues();
		in7.put("id", 7);
		in7.put("personId", 7);
		in7.put("activityTime", 1425546750006l);
		in7.put("title", "");
		in7.put("content", "旅行是件经事儿，背上行囊走在青春与梦想的路上。 不要问我来自哪里， 不要问我为何孤身一人。 相遇，分开，把玩孤独，享受寂寞， 更明白自己是谁。 在一个人的旅途上， 我并不害怕");
		in7.put("address", "无锡 滨湖区 雕刻时光 无锡");
		in7.put("maxNum", 50);
		in7.put("currentNum", 49);
		in7.put("forwardIdFrom", 0);
		in7.put("originalId", 0);
		in7.put("status","");
		in7.put("issueTime", 1425545150006l);
		in7.put("hits", 459);
		in7.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/33F201265CD04D648844E100C52A7219\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8D61EFD7FAC74757B40382F29AEDC64F\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/40DEC653FC9A40F4B5140A341F2776D2\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/2F67D3B1D96B467094A25202A1ABC2BF\"]");
		ContentValues in8 = new ContentValues();
		in8.put("id", 8);
		in8.put("personId", 8);
		in8.put("activityTime", 1425546750006l);
		in8.put("title", "");
		in8.put("content", "无锡孤独症康复十周年“爱心券”义卖，帮助来自星星的孩子们");
		in8.put("address", "无锡 无锡大剧院");
		in8.put("maxNum", 30);
		in8.put("currentNum", 30);
		in8.put("forwardIdFrom", 0);
		in8.put("originalId", 0);
		in8.put("status","");
		in8.put("issueTime", 1425545050006l);
		in8.put("hits", 21);
		in8.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7CBBABD0E0FE4529BE7149E9A5DC58C4\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8C7A6E9EF7564477A071E89AD9444C47\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/DFECC2460489423BBD7A9E11CAF5CE15\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/EF46280D7B8E4485A71A77FE7DC317A7\"]");
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
		lvl1.put("name", "1级");
		lvl1.put("upgradeExp", 3000);
		lvl1.put("type", "person");
		this.db.insert("ELevel", null, lvl1);

		ContentValues lvl2 = new ContentValues();
		lvl2.put("id", 2);
		lvl2.put("name", "6级公会");
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
		p1.put("school", "蚌埠幼儿园");
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
		ContentValues p4 = new ContentValues();
		

		ContentValues p3 = new ContentValues();
		p3.put("id", 3);
		p3.put("name", "测试小张");
		p3.put("levelId", 1);
		p3.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/04B2893268CD4D9290BA494C6C08B65A");
		p3.put("sex", "男");
		p3.put("school", "斯坦福小学");
		p3.put("department", "计算机");
		p3.put("birthday", 10000000000l);
		p3.put("phoneNum", "10002");
		p3.put("password", "1");
		p3.put("position", "学生会主席");
		p3.put("vp", 80);
		p3.put("currentExp", 1001);
		this.db.insert("EPerson", null, p3);
		
		p4.put("id", 4);
		p4.put("name", "许小飞");
		p4.put("levelId", 1);
		p4.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D7239B770A164485B56175BF5698D2AB");
		p4.put("sex", "男");
		p4.put("school", "东南附小");
		p4.put("department", "计算机");
		p4.put("birthday", 10000000000l);
		p4.put("phoneNum", "10003");
		p4.put("password", "1");
		p4.put("vp", 80);
		p4.put("currentExp", 100);
		this.db.insert("EPerson", null, p4);
		
		for(int i=0;i<20;i++){
			ContentValues cv = new ContentValues();
			cv.put("id", i+5);
			cv.put("name", "王昆"+i);
			cv.put("levelId", 1);
			cv.put("icon", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B5F4A17929EF4A9A86C0EB697FC0F1EE");
			cv.put("sex", "男");
			cv.put("school", "蚌埠中学");
			cv.put("department", "计算机");
			cv.put("birthday", 10000000000l);
			cv.put("phoneNum", 10004+i+"");
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
