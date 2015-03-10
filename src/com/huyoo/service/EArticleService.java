package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.newhoyoo.R;
import com.example.newhoyoo.util.UrlToDrawable;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EArticle;
import com.huyoo.global.Application;

/**
 * 
 * @author HJL 20150302
 * 
 */
public class EArticleService {
	private static List<EArticle> newsList = new ArrayList<EArticle>();
	private static List<EArticle> tipList=new ArrayList<EArticle>();

	public EArticle getNewsById(int id) {
		for (EArticle eArticle : newsList) {
			if (eArticle.getId()==id) {
				return eArticle;
			}
		}
		return null;
	}
	public EArticle getTipById(int id){
		for (EArticle eArticle : tipList) {
			if(eArticle.getId()==id)
				return eArticle;
		}
		return null;
	}

	// 获取最新的公会新闻
	public List<EArticle> getTopUnionNews(int num,int unionId){
		String type="公会新闻";
		List<EArticle> resultList=new ArrayList<EArticle>();
		Collections.sort(newsList, new Comparator<EArticle>() {

			@Override
			public int compare(EArticle lhs, EArticle rhs) {
				if(lhs.getTime()<rhs.getTime())
					return 1;
				else if(lhs.getTime()>rhs.getTime())
					return -1;
				return 0;
			}
		});
		for(int i=0;i<num;i++){
			EArticle eArticle=new EArticle();
			eArticle=newsList.get(i);
			resultList.add(eArticle);
		}
//		resultList=getUnionNewsFromTo(0, num, newsList);	
		return resultList;
	}

	// 获取最受欢迎的公会新闻
	public List<EArticle> getHotUnionNews(int num, int unionId) {
		String type = "公会新闻";
		List<EArticle> resultList = new ArrayList<EArticle>();
		return resultList;
	}

	// 获取最新的成功秘笈
	public List<EArticle> getTopTips(int num, int unionId) {
		String type = "成功秘笈";
		List<EArticle> resultList = new ArrayList<EArticle>();
		Collections.sort(tipList, new Comparator<EArticle>() {

			@Override
			public int compare(EArticle lhs, EArticle rhs) {
				// TODO Auto-generated method stub
				if(lhs.getTime()<rhs.getTime())
					return 1;
				else if(lhs.getTime()>rhs.getTime())
					return -1;
				return 0;
			}	
		});
		for(int i=0;i<num;i++){
			EArticle eArticle=new EArticle();
			eArticle=tipList.get(i);
			resultList.add(eArticle);
		}
//		resultList=getTipFromTo(0, num, tipList);
		return resultList;
	}

	// 获取最受欢迎的成功秘笈
	public List<EArticle> getHotTips(int num, int unionId) {
		String type = "成功秘笈";
		List<EArticle> resultList = new ArrayList<EArticle>();
		return resultList;
	}

	//获取newsList中第fromNum到toNum的数据（不包含toNum）
//	public List<EArticle> getUnionNewsFromTo(int fromNum, int toNum,List<EArticle> list) {
//		List<EArticle> resultList = new ArrayList<EArticle>();
//		try {
//			for(;fromNum<toNum;fromNum++){
//				EArticle eArticle=list.get(fromNum);
//				resultList.add(eArticle);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			//超出界限
//		}
//		return resultList;
//	}
	
//	//获取tipList中第fromNum到toNum的数据（不包含toNum）
//	public List<EArticle> getTipFromTo(int fromNum,int toNum,List<EArticle> list){
//		List<EArticle> resultList=new ArrayList<EArticle>();
//		try {
//			for(;fromNum<toNum;fromNum++){
//				EArticle eArticle=list.get(fromNum);
//				resultList.add(eArticle);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			//超出界限
//		}
//		return resultList;
//	}
	public List<EArticle> getAllNews(){
		return newsList;
	}

	public List<EArticle> getAllEArticle() {
		return null;
	}
	public List<Map<String, Object>> convertFromList(List<EArticle> list){
		List<Map<String, Object>> resultList=new ArrayList<Map<String,Object>>();
		for (EArticle eArticle : list) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", eArticle.getId());
			map.put("picture", eArticle.getIcon());
			map.put("title", eArticle.getTitle());
			EPerson ePerson=new EPerson();
			
			ePerson=Application.getPersonService().getEPersonById(eArticle.getPersonId());
			map.put("name", ePerson.getName());
			map.put("time", eArticle.getTime());
			map.put("readers", eArticle.getHits());
			resultList.add(map);
		}
		return resultList;
	}
	static {
		EArticle art1 = new EArticle();
		art1.setId(1);
		art1.setHits(111);
		art1.setContent("<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		art1.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art1.setPersonId(1);
		art1.setTime(1425545750006l);
		art1.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art1.setType("公会新闻");
		EArticle art2 = new EArticle();
		art2.setId(2);
		art2.setHits(222);
		art2.setContent("<p><strong><span style=\"font-size:12px;\"></span></strong></p><p dir=\"ltr\" style=\"text-indent: 2em;\">“这个‘duang’是怎么来的，我自己都晕了！”这是自网络热门现象“duang”流传开来后，成龙本人在《我看你有戏》的录制中，首次面对采访时做出的独家回应。近日，成龙因之前的广告代言被网友恶搞，其中的一句“Duang”更在一夜之间成了热门词语。不过作为“创始人”，成龙却有些“云里雾里”，不知道自己怎么会突然“爆红”。</p><p dir=\"ltr\" style=\"text-indent: 2em;\">&quot;早在2004年，成龙在代言某洗发水的广告中，一句“拍这洗头水广告的时候，其实我是拒绝的”就曾引发一场热议。而近日，这则被“打假”的广告再被网友挖出来恶搞。而这次恶搞更显“高大上”，与当前热门的庞麦郎的《我的滑板鞋》神同步，其中的一句“Duang”更是在短时间内迅速“蹿红”，成为了网络热门词语。而成龙本人在接受采访时则颇显“困扰”，丝毫不明白为何“大家都发信息给我”，“唧唧喳喳的，我都不知道在讲什么。”而对于“duang”，成龙首先理解为“英文的大哥”，后来发现也说不通。随后他继续“吐槽”道：“今天早上一来到这里（《我看你有戏》录制现场），每个人都说‘duang、duang’，我自己都晕了！”而一向以调侃成龙“为乐”的张国立和冯小刚，自然也不会放过这次“绝佳”的机会，连连用“duang”来“攻击”成龙。</p><p><span style=\"font-size:14px;\" class=\"ue_t\"></span><br/></p><p><br/></p><p><br/></p>");
		art2.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art2.setPersonId(1);
		art2.setTime(1425547561063l);
		art2.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art2.setType("公会新闻");
		EArticle art3 = new EArticle();
		art3.setId(3);
		art3.setHits(333);
		art3.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心于3月5日（星期四）15时在梅地亚中心多功能厅举行记者会，邀请国家发展和改革委员会主任徐绍史就“经济社会发展与宏观调控”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">广东和深圳一直是我们改革的先行区，最近我也去过广东和深圳，我到了前海，也到了南沙，与广东一些同事们也探讨过这个问题。现在深圳在考虑的粤港澳湾区应该说是一个很有发展前景的地区，它有很好的基础。这个基础首先是泛珠合作，“9+2”已经搞了10年了，机制也越来越成熟，成效也越来越明显。泛珠合作的机制对于推动粤港澳和沿珠江这些省份的发展都发挥了重要的作用。第二，现在也面临着一些重大的机遇。大家都知道广东自贸区的建立，我想对促进整个广东、珠三角甚至包括港澳地区的发展都是非常有益的。</p><p><br/></p>");
		art3.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art3.setPersonId(1);
		art3.setTime(1425547628282l);
		art3.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art3.setType("公会新闻");
		EArticle art4 = new EArticle();
		art4.setId(4);
		art4.setHits(444);
		art4.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心将于3月6日（星期五）上午10时在梅地亚中心多功能厅举行记者会，邀请财政部部长楼继伟、副部长刘昆就“财政工作和财税改革”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">财政部部长楼继伟今天说，中央财政去年三公经费是71亿元多，今年只会更少，估算一下，全国的三公经费也就400多亿元，“有说3000多亿，那是胡扯。”</p><p style=\"text-indent: 2em;\">河南日报报业集团全媒体大河报全媒体记者：<br/></p><p style=\"text-indent: 2em;\">今年许多省份的政府工作报告中又一次提出了压缩“三公”经费，个别省份的削减幅度甚至达到30%以上。现在问题来了，削减掉也就是说节省出这部分的钱都去哪儿了？是不是也应该公开具体的去向，接受人民的监督？谢谢。</p><p style=\"text-indent: 2em;\">楼继伟：</p><p style=\"text-indent: 2em;\">全国的情况我们不是很清楚，因为中央是代编地方预算，地方预算由他们自己编，我们代编是汇总他们的预算，因此细节不在汇总范围内</p><p style=\"text-indent: 2em;\">我给你说一下中央财政。中央财政去年“三公”经费是71亿多，比前年减了8亿，今年的预算是按照不多于去年来安排的。至于中央减下来的8个亿，收回总预算平衡，这是一个财政上的常识，没有说收回的钱用到什么地方，收回总预算，平衡在各个方面。<br/></p><p><br/></p>");
		art4.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art4.setPersonId(1);
		art4.setTime(1425547653392l);
		art4.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art4.setType("公会新闻");
		EArticle art5 = new EArticle();
		art5.setId(5);
		art5.setHits(555);
		art5.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心于3月5日（星期四）15时在梅地亚中心多功能厅举行记者会，邀请国家发展和改革委员会主任徐绍史就“经济社会发展与宏观调控”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">广东和深圳一直是我们改革的先行区，最近我也去过广东和深圳，我到了前海，也到了南沙，与广东一些同事们也探讨过这个问题。现在深圳在考虑的粤港澳湾区应该说是一个很有发展前景的地区，它有很好的基础。这个基础首先是泛珠合作，“9+2”已经搞了10年了，机制也越来越成熟，成效也越来越明显。泛珠合作的机制对于推动粤港澳和沿珠江这些省份的发展都发挥了重要的作用。第二，现在也面临着一些重大的机遇。大家都知道广东自贸区的建立，我想对促进整个广东、珠三角甚至包括港澳地区的发展都是非常有益的。</p><p><br/></p>");
		art5.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art5.setPersonId(1);
		art5.setTime(1425547671788l);
		art5.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art5.setType("公会新闻");
		EArticle art6 = new EArticle();
		art6.setId(6);
		art6.setHits(666);
		art6.setContent("<p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">中新网3月4日电据外媒报道，多国科学家研究发现，艾滋病毒已知的4种病株，均来自喀麦隆的黑猩猩及大猩猩，是人类首次完全确定艾滋病毒毒株的所有源头。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">目前已知艾滋病毒毒株共有4种，分别是M、N、O、P，每种各有不同源头，其中传播最广的M和N早已证实来自黑猩猩，但较罕见的O和P则一直未能证实源头。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">研究员透过分析喀麦隆及邻近地区的黑猩猩及大猩猩基因资料，终于证实O和P均是来自喀麦隆西南部的大猩猩。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">全球至今只有两宗P型病例，O型亦只有10万人，主要集中在中西非。</span><br/></p><p style=\"text-align: center;\"><img width=\"205\" height=\"115\" src=\"http://api.map.baidu.com/staticimage?center=120.260033,31.532735&zoom=18&width=530&height=340&markers=120.260105,31.533181\" style=\"width: 205px; height: 115px;\"/></p><p style=\"display:none;\" data-background=\"background-repeat:no-repeat; background-position:center center; background-color:#D99694;\"><br/></p>");
		art6.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/C74704B6F35F4F69ADAC375DC0CFA1DA");
		art6.setPersonId(1);
		art6.setTime(1425547688273l);
		art6.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art6.setType("公会新闻");
		EArticle art7 = new EArticle();
		art7.setId(7);
		art7.setHits(777);
		art7.setContent("<p style=\"text-indent: 2em;\">新华网北京3月4日电(记者侯雪静、徐博、赵叶苹)人力资源和社会保障部副部长胡晓义近日透露，2014年职工养老征缴收入增长明显低于支出增长，养老基金收支缺口愈加明显，一些省份当期已经收不抵支。</p><p style=\"text-indent: 2em;\">然而，更令人担忧的是，由于种种限制，急需开源、增值的中国养老金事实上每年都在“缩水”。这份担忧，也是今年两会上不少代表委员关心的话题，他们建议国家尽快推动养老金投资保值的相关改革。</p><p style=\"text-align: center;\"><iframe src=\"http://www.ghostchina.com/\" width=\"250\" height=\"300\" scrolling=\"no\" frameborder=\"0\" align=\"\"></iframe></p>");
		art7.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/18E665C877ED46EFA61684FC0F061543");
		art7.setPersonId(1);
		art7.setTime(1425547700959l);
		art7.setTitle("媒体：养老金20年贬值近千亿元 改革等到花儿都谢了");
		art7.setType("公会新闻");
		EArticle art8 = new EArticle();
		art8.setId(8);
		art8.setHits(888);
		art8.setContent("<p style=\"text-indent: 2em;\">昨天，有媒体报道称，知名歌手韩红日前申请辞去空军政治部文工团副团长一职欲转业，并且已获相关部门批准。面对传闻，韩红独家回应本报：不属实。</p><p style=\"text-indent: 2em;\">昨天下午，一则《知名歌手韩红申请辞去空军政治部文工团副团长一职欲转业》的消息，在网络上被迅速传开。有媒体报道称，全国政协委员、知名歌手韩红日前申请辞去空军政治部文工团副团长一职欲转业，并且已获相关部门批准。经过京华时报记者多方求证，韩红回复了本报记者的求证短信，短信中言简意赅地称：“不属实。”</p><p><br/></p>");
		art8.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D11C38B900434C61BFCCB6A0FFE8DF13");
		art8.setPersonId(1);
		art8.setTime(1425547716309l);
		art8.setTitle("韩红回应请辞空军政治部文工团副团长：不属实");
		art8.setType("公会新闻");
		EArticle art9 = new EArticle();
		art9.setId(9);
		art9.setHits(999);
		art9.setContent("<p style=\"text-indent: 2em;\">3月3日下午，台湾大学综合体育馆一楼座无虚席。3点15分，在三位校长的介绍下，阿里巴巴集团董事局主席马云以“从梦想到成功创业”为题，登台演讲，并与台湾大学，台湾师大，台科大三所大学的学生对谈交流。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">马云向台湾学子分享了自己十五年创业的经验，同时也阐述了他心目中的创业者三大素质：乐观，自省，坚持。在随后的对谈提问环节，台湾学生热情高涨，仅半小时300多个问题通过短信现场提出，他们就未来创业机会，三地青年差异，阿里全球战略，个人生涯选择等问题与马云交流。原定一小时的交流不断延长，台下掌声不断&quot;</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">而昨日马云演讲的报名消息一出，入场门票即被秒杀，甚至因报名太过踊跃，一度造成网络报名系统瘫痪，最后四小时内迅数千个座位被一抢而空，在台湾年轻人心中比明星演唱会更为爆。台下观众更包括郭台铭等诸位台湾知名企业家。</p><p><br/></p>");
		art9.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/575DFACF8430417F9A61A90434CE16EF");
		art9.setPersonId(1);
		art9.setTime(1425626569100l);
		art9.setTitle("马云台湾大学演讲：永远相信年轻人才是未来");
		art9.setType("公会新闻");
		EArticle art10 = new EArticle();
		art10.setId(10);
		art10.setHits(1000);
		art10.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心于3月5日（星期四）15时在梅地亚中心多功能厅举行记者会，邀请国家发展和改革委员会主任徐绍史就“经济社会发展与宏观调控”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">广东和深圳一直是我们改革的先行区，最近我也去过广东和深圳，我到了前海，也到了南沙，与广东一些同事们也探讨过这个问题。现在深圳在考虑的粤港澳湾区应该说是一个很有发展前景的地区，它有很好的基础。这个基础首先是泛珠合作，“9+2”已经搞了10年了，机制也越来越成熟，成效也越来越明显。泛珠合作的机制对于推动粤港澳和沿珠江这些省份的发展都发挥了重要的作用。第二，现在也面临着一些重大的机遇。大家都知道广东自贸区的建立，我想对促进整个广东、珠三角甚至包括港澳地区的发展都是非常有益的。</p><p><br/></p>");
		art10.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/6588727ADDF0441DAA681C3E395E584A");
		art10.setPersonId(1);
		art10.setTime(1425627606873l);
		art10.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art10.setType("公会新闻");
		EArticle art11 = new EArticle();
		art11.setId(11);
		art11.setHits(123);
		art11.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心将于3月6日（星期五）上午10时在梅地亚中心多功能厅举行记者会，邀请财政部部长楼继伟、副部长刘昆就“财政工作和财税改革”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\"><br/></p><p style=\"text-indent: 2em;\">财政部部长楼继伟今天说，中央财政去年三公经费是71亿元多，今年只会更少，估算一下，全国的三公经费也就400多亿元，“有说3000多亿，那是胡扯。”</p><p style=\"text-indent: 2em;\">河南日报报业集团全媒体大河报全媒体记者：<br/></p><p style=\"text-indent: 2em;\">今年许多省份的政府工作报告中又一次提出了压缩“三公”经费，个别省份的削减幅度甚至达到30%以上。现在问题来了，削减掉也就是说节省出这部分的钱都去哪儿了？是不是也应该公开具体的去向，接受人民的监督？谢谢。</p><p style=\"text-indent: 2em;\">楼继伟：</p><p style=\"text-indent: 2em;\">全国的情况我们不是很清楚，因为中央是代编地方预算，地方预算由他们自己编，我们代编是汇总他们的预算，因此细节不在汇总范围内</p><p style=\"text-indent: 2em;\">我给你说一下中央财政。中央财政去年“三公”经费是71亿多，比前年减了8亿，今年的预算是按照不多于去年来安排的。至于中央减下来的8个亿，收回总预算平衡，这是一个财政上的常识，没有说收回的钱用到什么地方，收回总预算，平衡在各个方面。<br/></p><p><br/></p>");
		art11.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/6588727ADDF0441DAA681C3E395E584A");
		art11.setPersonId(1);
		art11.setTime(1425547754892l);
		art11.setTitle("楼继伟：全国三公经费就400亿 说3000亿那是胡扯");
		art11.setType("成功秘笈");
		EArticle art12 = new EArticle();
		art12.setId(12);
		art12.setHits(234);
		art12.setContent("<p style=\"text-indent: 2em;\">十二届全国人大三次会议新闻中心于3月5日（星期四）15时在梅地亚中心多功能厅举行记者会，邀请国家发展和改革委员会主任徐绍史就“经济社会发展与宏观调控”的相关问题回答中外记者的提问。</p><p style=\"text-indent: 2em;\">&quot;广东和深圳一直是我们改革的先行区，最近我也去过广东和深圳，我到了前海，也到了南沙，与广东一些同事们也探讨过这个问题。现在深圳在考虑的粤港澳湾区应该说是一个很有发展前景的地区，它有很好的基础。这个基础首先是泛珠合作，“9+2”已经搞了10年了，机制也越来越成熟，成效也越来越明显。泛珠合作的机制对于推动粤港澳和沿珠江这些省份的发展都发挥了重要的作用。第二，现在也面临着一些重大的机遇。大家都知道广东自贸区的建立，我想对促进整个广东、珠三角甚至包括港澳地区的发展都是非常有益的。</p><p><br/></p>");
		art12.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/575DFACF8430417F9A61A90434CE16EF");
		art12.setPersonId(1);
		art12.setTime(1425547769170l);
		art12.setTitle("徐绍史：加强粤港澳地区基础设施的互联互通");
		art12.setType("成功秘笈");

		EArticle art13 = new EArticle();
		art13.setId(13);
		art13.setHits(345);
		art13.setContent("<p><strong><span style=\"font-size:12px;\"></span></strong></p><p dir=\"ltr\" style=\"text-indent: 2em;\">“这个‘duang’是怎么来的，我自己都晕了！”这是自网络热门现象“duang”流传开来后，成龙本人在《我看你有戏》的录制中，首次面对采访时做出的独家回应。近日，成龙因之前的广告代言被网友恶搞，其中的一句“Duang”更在一夜之间成了热门词语。不过作为“创始人”，成龙却有些“云里雾里”，不知道自己怎么会突然“爆红”。</p><p dir=\"ltr\" style=\"text-indent: 2em;\">&quot;早在2004年，成龙在代言某洗发水的广告中，一句“拍这洗头水广告的时候，其实我是拒绝的”就曾引发一场热议。而近日，这则被“打假”的广告再被网友挖出来恶搞。而这次恶搞更显“高大上”，与当前热门的庞麦郎的《我的滑板鞋》神同步，其中的一句“Duang”更是在短时间内迅速“蹿红”，成为了网络热门词语。而成龙本人在接受采访时则颇显“困扰”，丝毫不明白为何“大家都发信息给我”，“唧唧喳喳的，我都不知道在讲什么。”而对于“duang”，成龙首先理解为“英文的大哥”，后来发现也说不通。随后他继续“吐槽”道：“今天早上一来到这里（《我看你有戏》录制现场），每个人都说‘duang、duang’，我自己都晕了！”而一向以调侃成龙“为乐”的张国立和冯小刚，自然也不会放过这次“绝佳”的机会，连连用“duang”来“攻击”成龙。</p><p><span style=\"font-size:14px;\" class=\"ue_t\"></span><br/></p><p><br/></p><p><br/></p>");
		art13.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D11C38B900434C61BFCCB6A0FFE8DF13");
		art13.setPersonId(1);
		art13.setTime(1425547790521l);
		art13.setTitle("成龙回应霸王洗发水事件：我第一次听到都晕了");
		art13.setType("成功秘笈");
		EArticle art14 = new EArticle();
		art14.setId(14);
		art14.setHits(456);
		art14.setContent("<p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">中新网3月4日电据外媒报道，多国科学家研究发现，艾滋病毒已知的4种病株，均来自喀麦隆的黑猩猩及大猩猩，是人类首次完全确定艾滋病毒毒株的所有源头。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">目前已知艾滋病毒毒株共有4种，分别是M、N、O、P，每种各有不同源头，其中传播最广的M和N早已证实来自黑猩猩，但较罕见的O和P则一直未能证实源头。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">研究员透过分析喀麦隆及邻近地区的黑猩猩及大猩猩基因资料，终于证实O和P均是来自喀麦隆西南部的大猩猩。</span></p><p style=\"text-indent: 2em;\"><span style=\"font-size: 10px;\">全球至今只有两宗P型病例，O型亦只有10万人，主要集中在中西非。</span><br/></p><p style=\"text-align: center;\"><img width=\"205\" height=\"115\" src=\"http://api.map.baidu.com/staticimage?center=120.260033,31.532735&zoom=18&width=530&height=340&markers=120.260105,31.533181\" style=\"width: 205px; height: 115px;\"/></p><p style=\"display:none;\" data-background=\"background-repeat:no-repeat; background-position:center center; background-color:#D99694;\"><br/></p>");
		art14.setIcon("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/18E665C877ED46EFA61684FC0F061543");
		art14.setPersonId(1);
		art14.setTime(1425547802074l);
		art14.setTitle("艾滋病病源首次被确认");
		art14.setType("成功秘笈");
		newsList.add(art1);
		newsList.add(art2
				
				);
		newsList.add(art3);
		newsList.add(art4);
		newsList.add(art5);
		newsList.add(art6);
		newsList.add(art7);
		newsList.add(art8);
		newsList.add(art9);
		newsList.add(art10);
		tipList.add(art11);
		tipList.add(art12);
		tipList.add(art13);
		tipList.add(art14);

	}
}
