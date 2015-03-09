package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EInvitation;

public class EInvitationService {

	public List<EInvitation> getInvitations(Map<String,Object> params)
	{
		List<EInvitation> list=new ArrayList<>();
		EInvitation i = new EInvitation();
		i.setActivityTime(1425546750006l);
		i.setAddress("无锡 南长区 N1955南下塘文化创意园9号楼");
		i.setContent("2015 痛仰乐队《愿爱无忧》全国巡演 无锡站");
		i.setCurrentNum(15);
//		i.setForwardIdFrom();
		i.setIcons("");
		i.setId(1);
		i.setIssueTime(1425545750006l);
		i.setMaxNum(50);
//		i.setOriginalId();
//		i.setOriginalId();
		i.setPersonId(1);
//		i.setStatus();
//		i.setTitle();
		list.add(i);
		return list;
	}
	public EInvitation getInvitationById(int id)
	{
		return getInvitations(null).get(0);
	}
	public List<Map<String,Object>> getInvitationsMapByUnionId(int UnionId,int from ,int size)
	{
		/*
		personName
		personLevel
		issueTime
		activityTime
		content
		address
		currentNum
		maxNum
		isJoin 0:1(join)
		 * */
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		Map<String ,Object> m=new HashMap<String, Object>();
		m.put("id", 1);
		m.put("personName", "张三");
		m.put("personUrl", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
		m.put("personLevel", "塔基大师");
		m.put("issueTime", 1425545750006l);
		m.put("activityTime", 1425546750006l);
		m.put("content", "2015 痛仰乐队《愿爱无忧》全国巡演 无锡站");
		m.put("address", "无锡 南长区 N1955南下塘文化创意园9号楼");
		m.put("currentNum", 15);
		m.put("maxNum", 50);
		m.put("isJoin", 1);
		m.put("hits", 123);
		m.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		Map<String ,Object> m1=new HashMap<String, Object>();
		m1.put("id", 2);
		m1.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/67B095895879445986C90C1A6FB74136");
		m1.put("personName", "李四");
		m1.put("personLevel", "塔基大师");
		m1.put("issueTime", 1425545750006l);
		m1.put("activityTime", 1425546750006l);
		m1.put("content", "2015“我的手艺”首届中华手作创意嘉年华暨全国手工艺品创意品大型巡回展销会");
		m1.put("address", "江苏省无锡市清明桥古运河景区南长街");
		m1.put("currentNum", 14);
		m1.put("maxNum", 30);
		m1.put("isJoin", 0);
		m1.put("hits", 234);
		m1.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\"]"
				);
		Map<String ,Object> m2=new HashMap<String, Object>();
		m2.put("id", 3);
		m2.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B5F4A17929EF4A9A86C0EB697FC0F1EE");
		m2.put("personName", "王二");
		m2.put("personLevel", "塔基大师1");
		m2.put("issueTime", 1425545750006l);
		m2.put("activityTime", 1425546750006l);
		m2.put("content", "周一晚上过来玩");
		m2.put("address", "你想到的地方1");
		m2.put("currentNum", 15);
		m2.put("maxNum", 15);
		m2.put("isJoin", 0);
		m2.put("hits", 345);
		m2.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		Map<String ,Object> m3=new HashMap<String, Object>();
		m3.put("id", 4);
		m3.put("personName", "李木木的世界");
		m3.put("personLevel", "塔基塔基");
		m3.put("issueTime", 1425545750006l);
		m3.put("activityTime", 1425546750006l);
		m3.put("content", "狼人杀的过来玩，一餐");
		m3.put("address", "一餐桌上");
		m3.put("currentNum", 10);
		m3.put("maxNum", 10);
		m3.put("isJoin", 1);
		m3.put("hits", 456);
		m3.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+		
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		Map<String ,Object> m4=new HashMap<String, Object>();
		m4.put("id", 5);
		m4.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D0AC385AAC114722A5AD76FC92DB82F4");
		m4.put("personName", "虾米虾米");
		m4.put("personLevel", "果奶拼盘");
		m4.put("issueTime", 1425545750006l);
		m4.put("activityTime", 1425546750006l);
		m4.put("content", "周六晚上过来玩");
		m4.put("address", "你想不到的地方");
		m4.put("currentNum", 15);
		m4.put("maxNum", 50);
		m4.put("isJoin", 1);
		m4.put("hits", 567);
		m4.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		Map<String ,Object> m5=new HashMap<String, Object>();
		m5.put("id", 6);
		m5.put("personName", "张三");
		m5.put("personLevel", "塔基大师");
		m5.put("issueTime", 1425545750006l);
		m5.put("activityTime", 1425546750006l);
		m5.put("content", "周六晚上过来玩");
		m5.put("address", "你想不到的地方");
		m5.put("currentNum", 15);
		m5.put("maxNum", 50);
		m5.put("isJoin", 1);
		m5.put("hits", 678);
		m5.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\"]");
		Map<String ,Object> m6=new HashMap<String, Object>();
		m6.put("id", 7);
		m6.put("personName", "张三");
		m6.put("personLevel", "塔基大师");
		m6.put("issueTime", 1425545750006l);
		m6.put("activityTime", 1425546750006l);
		m6.put("content", "周六晚上过来玩");
		m6.put("address", "你想不到的地方");
		m6.put("currentNum", 15);
		m6.put("maxNum", 50);
		m6.put("isJoin", 1);
		m6.put("hits", 789);
		m6.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		Map<String ,Object> m7=new HashMap<String, Object>();
		m7.put("id", 1);
		m7.put("personName", "张三");
		m7.put("personLevel", "塔基大师");
		m7.put("issueTime", 1425545750006l);
		m7.put("activityTime", 1425546750006l);
		m7.put("content", "周六晚上过来玩");
		m7.put("address", "你想不到的地方");
		m7.put("currentNum", 15);
		m7.put("maxNum", 50);
		m7.put("isJoin", 1);
		m7.put("hits", 890);
		m7.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+ 
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+		
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
		list.add(m);
		list.add(m1);
		list.add(m2);
		list.add(m3);
		list.add(m4);
//		list.add(m5);
//		list.add(m6);
//		list.add(m7);
		return list;
	}
	/**
	 * @author clu
	 * 获取最新的邀请
	 * @param num
	 * @param unionId
	 * @return
	 */
	public List<Map<String,Object>> getTopInvitation(int num,int unionId){
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list=getInvitationsMapByUnionId(unionId,0,num);
		Collections.sort(list,new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> lhs, Map<String, Object> rhs) {
				// TODO Auto-generated method stub
				Long lhsTime=Long.parseLong(lhs.get("issueTime").toString());
				Long rhsTime=Long.parseLong(rhs.get("issueTime").toString());
				if(lhsTime<rhsTime)
					return 1;
				else if(lhsTime>rhsTime)
					return -1;
				return 0;
			}
			
		});
		for(int i=0;i<num;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map=list.get(i);
			resultList.add(map);
		}		
		return resultList;
	}
//	/**
//	 * @author clu
//	 * 获取list中第fromNum到toNum的数据（不包含toNum）
//	 * @param fromNum
//	 * @param toNum
//	 * @param list
//	 * @return
//	 */
//	public List<Map<String,Object>> getInvitationFromTo(int fromNum,int toNum,List<Map<String, Object>> list){
//		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
//		for(;fromNum<toNum;fromNum++){
//			Map<String,Object> map=new HashMap<String, Object>();
//			resultList.add(map);
//		}
//		return resultList;
//	}
}
