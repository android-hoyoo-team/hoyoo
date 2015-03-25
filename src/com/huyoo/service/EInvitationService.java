package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.bean.Result;
import com.huyoo.entity.EInvitation;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.RInvitationLike;
import com.huyoo.entity.RInvitationPerson;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

public class EInvitationService {

	public List<EInvitation> getInvitations(Map<String,Object> params){
		List<EInvitation> invitations = new ArrayList<EInvitation>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from EInvitation where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personId = cursor.getInt(cursor.getColumnIndex("personId"));
			long activityTime = cursor.getLong(cursor.getColumnIndex("activityTime"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			int maxNum = cursor.getInt(cursor.getColumnIndex("maxNum"));
			int currentNum = cursor.getInt(cursor.getColumnIndex("currentNum"));
			int forwardIdFrom = cursor.getInt(cursor.getColumnIndex("forwardIdFrom"));
			int originalId = cursor.getInt(cursor.getColumnIndex("originalId"));
			String status = cursor.getString(cursor.getColumnIndex("status"));
			long issueTime = cursor.getLong(cursor.getColumnIndex("issueTime"));
			String icons = cursor.getString(cursor.getColumnIndex("icons"));
			String hits = cursor.getString(cursor.getColumnIndex("hits"));
			EInvitation invitation = new EInvitation();
			invitation.setId(id);
			invitation.setPersonId(personId);
			invitation.setActivityTime(activityTime);
			invitation.setTitle(title);
			invitation.setContent(content);
			invitation.setAddress(address);
			invitation.setMaxNum(maxNum);
			invitation.setCurrentNum(currentNum);
			invitation.setForwardIdFrom(forwardIdFrom);
			invitation.setOriginalId(originalId);
			invitation.setStatus(status);
			invitation.setIssueTime(issueTime);
			invitation.setIcons(icons);
			invitation.setHits(hits);
			invitations.add(invitation);
		}
		return invitations;
	}

	public EInvitation getInvitationById(int id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<EInvitation> invitations = getInvitations(params);
		if(invitations!=null&&invitations.size()>0)return invitations.get(0);
		return null;
	}
	public Map<String,Object> getInvitationMapById(int id){
		EInvitation invitation = getInvitationById(id);
		if(invitation!=null)
		{
				Map<String,Object> inMap = new HashMap<String, Object>();
				EPerson person  =  Application.getPersonService().getEPersonById(invitation.getPersonId());
				inMap.put("id", invitation.getId());
				inMap.put("personName", person.getName());
				inMap.put("personLevel", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
				inMap.put("issueTime", invitation.getIssueTime());
				inMap.put("activityTime", invitation.getActivityTime());
				inMap.put("personUrl", person.getIcon());
				inMap.put("content", invitation.getContent());
				inMap.put("address", invitation.getAddress());
				inMap.put("currentNum", invitation.getCurrentNum());
				inMap.put("maxNum", invitation.getMaxNum());
				if(getInvitationPersonBy(invitation.getId(),person.getId())!=null){
					inMap.put("isJoin","1");
				}else{
					inMap.put("isJoin","0");
				}
				inMap.put("hits", invitation.getHits());
				inMap.put("icons", invitation.getIcons());
				return inMap;
		}
		return null;
	}
	public List<Map<String,Object>> getInvitationsMapByUnionId(int unionId,int from ,int size,String sortBy){
		List<Map<String,Object>> inMaps = new ArrayList<Map<String,Object>>();

		switch (sortBy) {
		case "issueTime":
			Collections.sort(inMaps, new Comparator<Map<String,Object>>(){

				@Override
				public int compare(Map<String, Object> lhs, Map<String, Object> rhs) {
					// TODO Auto-generated method stub
					if(Long.parseLong(lhs.get("issueTime").toString())<Long.parseLong(rhs.get("issueTime").toString()))
						return 1;
					else if(Long.parseLong(lhs.get("issueTime").toString())>Long.parseLong(rhs.get("issueTime").toString()))
						return -1;
					return 0;
				}
			});
			break;
		case "hits":
			Collections.sort(inMaps, new Comparator<Map<String,Object>>(){

				@Override
				public int compare(Map<String, Object> lhs, Map<String, Object> rhs) {
					// TODO Auto-generated method stub
					if(Long.parseLong(lhs.get("hits").toString())<Long.parseLong(rhs.get("hits").toString()))
						return 1;
					else if(Long.parseLong(lhs.get("hits").toString())>Long.parseLong(rhs.get("hits").toString()))
						return -1;
					return 0;
				}
			});
			break;
		default:
			break;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("forwardIdFrom", from);
		List<EInvitation> invitations = getInvitations(params);
		if(invitations!=null&&invitations.size()>0){
			for (EInvitation invitation : invitations) {
				if(Application.getUnionService().getUnionByPersonId(invitation.getPersonId()).getId()==unionId){
					Map<String,Object> inMap = new HashMap<String, Object>();
					EPerson person  =  Application.getPersonService().getEPersonById(invitation.getPersonId());
					inMap.put("id", invitation.getId());
					inMap.put("personName", person.getName());
					inMap.put("personLevel", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
					inMap.put("issueTime", invitation.getIssueTime());
					inMap.put("activityTime", invitation.getActivityTime());
					inMap.put("personUrl", person.getIcon());
					inMap.put("content", invitation.getContent());
					inMap.put("address", invitation.getAddress());
					inMap.put("currentNum", invitation.getCurrentNum());
					inMap.put("maxNum", invitation.getMaxNum());
					if(getInvitationPersonBy(invitation.getId(),person.getId())!=null){
						inMap.put("isJoin","1");
					}else{
						inMap.put("isJoin","0");
					}
					inMap.put("hits", invitation.getHits());
					inMap.put("icons", invitation.getIcons());
					inMaps.add(inMap);
				}
			}
		}
		return inMaps;
	}

	/*=====================================================================================*/

	public List<RInvitationPerson> getInvitationPersons(Map<String,Object> params){
		List<RInvitationPerson> invitationPersons = new ArrayList<RInvitationPerson>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from RInvitationPerson where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("personId")!=null){
				sb.append(" and personId=?");
				selectionArgs.add(params.get("personId").toString());
			}
			if(params.get("invitationId")!=null){
				sb.append(" and invitationId=?");
				selectionArgs.add(params.get("invitationId").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personId = cursor.getInt(cursor.getColumnIndex("personId"));
			int invitationId = cursor.getInt(cursor.getColumnIndex("invitationId"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			RInvitationPerson  invitationPerson = new RInvitationPerson();
			invitationPerson.setId(id);
			invitationPerson.setPersonId(personId);
			invitationPerson.setInvitationId(invitationId);
			invitationPerson.setTime(time);
			invitationPersons.add(invitationPerson);
		}
		return invitationPersons;
	}

	public RInvitationPerson getInvitationPersonBy(int invitationId,int personId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("invitationId", invitationId);
		params.put("personId", personId);
		List<RInvitationPerson> invitationPersons = getInvitationPersons(params);
		if(invitationPersons!=null&&invitationPersons.size()>0)return invitationPersons.get(0);
		return null;
		}
//		getInvitationsMapByUnionId_list=new ArrayList<Map<String,Object>>();
//		if(getInvitationsMapByUnionId_list.size()<=0)
//		{
//			Map<String ,Object> m=new HashMap<String, Object>();
//			m.put("id", 1);
//			m.put("personName", "张三");
//			m.put("personUrl", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
//			m.put("personLevel", "塔基大师");
//			m.put("issueTime", 1425545750006l);
//			m.put("activityTime", 1425546750006l);
//			m.put("content", "2015 痛仰乐队《愿爱无忧》全国巡演 无锡站");
//			m.put("address", "无锡 南长区 N1955南下塘文化创意园9号楼");
//			m.put("currentNum", 15);
//			m.put("maxNum", 50);
//			m.put("isJoin", 1);
//			m.put("hits", 123);
//			m.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
//					+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			Map<String ,Object> m1=new HashMap<String, Object>();
//			m1.put("id", 2);
//			m1.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/67B095895879445986C90C1A6FB74136");
//			m1.put("personName", "李四");
//			m1.put("personLevel", "塔基大师");
//			m1.put("issueTime", 1425545750006l);
//			m1.put("activityTime", 1425546750006l);
//			m1.put("content", "2015“我的手艺”首届中华手作创意嘉年华暨全国手工艺品创意品大型巡回展销会");
//			m1.put("address", "江苏省无锡市清明桥古运河景区南长街");
//			m1.put("currentNum", 15);
//			m1.put("maxNum", 30);
//			m1.put("isJoin", 0);
//			m1.put("hits", 234);
//			m1.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\"]"
//					);
//			Map<String ,Object> m2=new HashMap<String, Object>();
//			m2.put("id", 3);
//			m2.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B5F4A17929EF4A9A86C0EB697FC0F1EE");
//			m2.put("personName", "王二");
//			m2.put("personLevel", "塔基大师1");
//			m2.put("issueTime", 1425545750006l);
//			m2.put("activityTime", 1425546750006l);
//			m2.put("content", "周一晚上过来玩");
//			m2.put("address", "你想到的地方1");
//			m2.put("currentNum", 15);
//			m2.put("maxNum", 15);
//			m2.put("isJoin", 0);
//			m2.put("hits", 345);
//			m2.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			Map<String ,Object> m3=new HashMap<String, Object>();
//			m3.put("id", 4);
//			m3.put("personName", "李木木的世界");
//			m3.put("personLevel", "塔基塔基");
//			m3.put("issueTime", 1425545750006l);
//			m3.put("activityTime", 1425546750006l);
//			m3.put("content", "狼人杀的过来玩，一餐");
//			m3.put("address", "一餐桌上");
//			m3.put("currentNum", 10);
//			m3.put("maxNum", 10);
//			m3.put("isJoin", 1);
//			m3.put("hits", 456);
//			m3.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+		
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
//					+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			Map<String ,Object> m4=new HashMap<String, Object>();
//			m4.put("id", 5);
//			m4.put("personUrl", "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/D0AC385AAC114722A5AD76FC92DB82F4");
//			m4.put("personName", "虾米虾米");
//			m4.put("personLevel", "果奶拼盘");
//			m4.put("issueTime", 1425545750006l);
//			m4.put("activityTime", 1425546750006l);
//			m4.put("content", "周六晚上过来玩");
//			m4.put("address", "你想不到的地方");
//			m4.put("currentNum", 15);
//			m4.put("maxNum", 50);
//			m4.put("isJoin", 1);
//			m4.put("hits", 567);
//			m4.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
//					+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			Map<String ,Object> m5=new HashMap<String, Object>();
//			m5.put("id", 6);
//			m5.put("personName", "张三");
//			m5.put("personLevel", "塔基大师");
//			m5.put("issueTime", 1425545750006l);
//			m5.put("activityTime", 1425546750006l);
//			m5.put("content", "周六晚上过来玩");
//			m5.put("address", "你想不到的地方");
//			m5.put("currentNum", 15);
//			m5.put("maxNum", 50);
//			m5.put("isJoin", 1);
//			m5.put("hits", 678);
//			m5.put("icons", "[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\","
//					+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\"]");
//			Map<String ,Object> m6=new HashMap<String, Object>();
//			m6.put("id", 7);
//			m6.put("personName", "张三");
//			m6.put("personLevel", "塔基大师");
//			m6.put("issueTime", 1425545750006l);
//			m6.put("activityTime", 1425546750006l);
//			m6.put("content", "周六晚上过来玩");
//			m6.put("address", "你想不到的地方");
//			m6.put("currentNum", 15);
//			m6.put("maxNum", 50);
//			m6.put("isJoin", 1);
//			m6.put("hits", 789);
//			m6.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			Map<String ,Object> m7=new HashMap<String, Object>();
//			m7.put("id", 8);
//			m7.put("personName", "张三");
//			m7.put("personLevel", "塔基大师");
//			m7.put("issueTime", 1425545750006l);
//			m7.put("activityTime", 1425546750006l);
//			m7.put("content", "周六晚上过来玩");
//			m7.put("address", "你想不到的地方");
//			m7.put("currentNum", 15);
//			m7.put("maxNum", 50);
//			m7.put("isJoin", 1);
//			m7.put("hits", 890);
//			m7.put("icons","[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+ 
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4A6544428E98469D90AA22C9C5A5219A\","+		
//					"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7F237BC82FB2493FAFF1C15360511255\","
//					+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/387E04F85B414DF999B25DED8BEC0391\"]");
//			getInvitationsMapByUnionId_list.add(m);
//			getInvitationsMapByUnionId_list.add(m1);
//			getInvitationsMapByUnionId_list.add(m2);
//			getInvitationsMapByUnionId_list.add(m3);
//			getInvitationsMapByUnionId_list.add(m4);
//		list.add(m5);
//		list.add(m6);
//		list.add(m7);
//		return getInvitationsMapByUnionId_list;
//	}
	public Result<Map<String,Object>> joinInvitationById(int id,boolean join)
	{
		//List<Map<String,Object>> l = getInvitationsMapByUnionId();

		Result<Map<String,Object>> res=new Result<Map<String,Object>>();
		Map<String, Object> in = getInvitationMapById(id);
		if(in==null)
		{
			res.setMessage("没有该邀请");
			res.setStatus("error");
			return res;
		}
		if(join)
		{
			if((""+1).equals(in.get("isJoin")+""))
			{
				res.setMessage("已经参加");
				res.setStatus("error");
			}
			else 
			{
				int maxNum=Integer.parseInt(in.get("maxNum")==null||in.get("maxNum").toString().trim().equals("")?"0":in.get("maxNum").toString());
				int currentNum=Integer.parseInt(in.get("currentNum")==null||in.get("currentNum").toString().trim().equals("")?"0":in.get("currentNum").toString());
				if(maxNum<=currentNum)
				{
					res.setMessage("人数已满");
					res.setStatus("error");
				}
				else 
				{
					in.put("isJoin", 1);
					in.put("currentNum", currentNum+1);
					res.setStatus("success");
				}
			}
		}
		else
		{
			if((""+0).equals(in.get("isJoin")+""))
			{
				res.setMessage("你没有参见");
				res.setStatus("error");
			}
			else 
			{
				int maxNum=Integer.parseInt(in.get("maxNum")==null||in.get("maxNum").toString().trim().equals("")?"0":in.get("maxNum").toString());
				int currentNum=Integer.parseInt(in.get("currentNum")==null||in.get("currentNum").toString().trim().equals("")?"0":in.get("currentNum").toString());
				in.put("isJoin", 0);
				in.put("currentNum", currentNum-1);
				res.setStatus("success");
			}
		}
		res.setResult(in);
		return res;
	}
	/**
	 * @author clu
	 * 获取最新的邀请
	 * @param num
	 * @param unionId
	 * @return
	 */
	public List<Map<String,Object>> getTopInvitation(int num,int unionId){
		return getInvitationsMapByUnionId(unionId,0,num,"issueTime");
	}

	
	/*=============================================================================*/
	public List<RInvitationLike> getInvitationLikes(Map<String,Object> params){
		List<RInvitationLike> invitationLikes = new ArrayList<RInvitationLike>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from RInvitationLike where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
			}
			if(params.get("invitationId")!=null){
				sb.append(" and invitationId=?");
				selectionArgs.add(params.get("invitationId").toString());
			}
			if(params.get("personId")!=null){
				sb.append(" and personId=?");
				selectionArgs.add(params.get("personId").toString());
			}
		}
		sql += sb.toString();
		String[] args = new String[selectionArgs.size()];
		Cursor cursor = db.rawQuery(sql,selectionArgs.toArray(args));
		while(cursor.moveToNext()){
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int personId = cursor.getInt(cursor.getColumnIndex("personId"));
			int invitationId = cursor.getInt(cursor.getColumnIndex("invitationId"));
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			RInvitationLike invitationLike = new RInvitationLike();
			invitationLike.setId(id);
			invitationLike.setPersonId(personId);
			invitationLike.setInvitationId(invitationId);
			invitationLike.setTime(time);
			invitationLikes.add(invitationLike);
		}
		return invitationLikes;
	}
	
	public List<RInvitationLike> getInvitationLikesByInvitationId(int invitationId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("invitationId", invitationId);
		return getInvitationLikes(params);
	}
}
