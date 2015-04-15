package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.bean.Result;
import com.huyoo.entity.EInvitation;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RInvitationLike;
import com.huyoo.entity.RInvitationPerson;
import com.huyoo.global.Achievement;
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
			int hits = cursor.getInt(cursor.getColumnIndex("hits"));
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

	private long updateInvitation(EInvitation invitation){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db  = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("personId", invitation.getPersonId());
		cv.put("activityTime",invitation.getActivityTime());
		cv.put("title", invitation.getTitle());
		cv.put("content", invitation.getContent());
		cv.put("address", invitation.getAddress());
		cv.put("maxNum", invitation.getMaxNum());
		cv.put("currentNum",invitation.getCurrentNum());
		cv.put("forwardIdFrom", invitation.getForwardIdFrom());
		cv.put("originalId", invitation.getOriginalId());
		cv.put("status",invitation.getStatus());
		cv.put("issueTime", invitation.getIssueTime());
		cv.put("hits", invitation.getHits());
		cv.put("icons", invitation.getIcons());
		return db.update("EInvitation", cv, "id = ?", new String[]{invitation.getId()+""});
	}
	public Map<String,Object> getInvitationMapById(int id){
		EInvitation invitation = getInvitationById(id);
		if(invitation!=null)
		{
			Map<String,Object> inMap = new HashMap<String, Object>();
			EPerson person  =  Application.getPersonService().getEPersonById(invitation.getPersonId());
			inMap.put("id", invitation.getId());
			inMap.put("personId", person.getId());
			inMap.put("personName", person.getName());
			inMap.put("personLevel", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
			inMap.put("issueTime", invitation.getIssueTime());
			inMap.put("activityTime", invitation.getActivityTime());
			inMap.put("personUrl", person.getIcon());
			inMap.put("content", invitation.getContent());
			inMap.put("address", invitation.getAddress());
			inMap.put("currentNum", invitation.getCurrentNum());
			inMap.put("maxNum", invitation.getMaxNum());
			if(getInvitationPersonBy(invitation.getId(),Application.getLoginInfo().getPerson().getId())!=null){
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
	public List<Map<String,Object>> getInvitationMapsByPersonId(int personId,int from ,int size,String sortBy){
		List<Map<String,Object>> inMaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("forwardIdFrom", from);
		List<EInvitation> invitations = getInvitations(params);
		switch (sortBy) {
		case "issueTime":
			Collections.sort(invitations, new Comparator<EInvitation>(){
				
				@Override
				public int compare(EInvitation lhs, EInvitation rhs) {
					// TODO Auto-generated method stub
					if(lhs.getIssueTime()<rhs.getIssueTime())
						return 1;
					else if(lhs.getIssueTime()>rhs.getIssueTime())
						return -1;
					return 0;
				}
			});
			break;
		case "hits":
			Collections.sort(invitations, new Comparator<EInvitation>(){
				
				@Override
				public int compare(EInvitation lhs, EInvitation rhs) {
					// TODO Auto-generated method stub
					if(lhs.getHits()<rhs.getHits())
						return 1;
					else if(lhs.getHits()>rhs.getHits())
						return -1;
					return 0;
				}
			});
			break;
		default:
			break;
		}
		
		if(invitations!=null&&invitations.size()>0){
			for (int i=0;i<invitations.size();i++) {
				EInvitation invitation =invitations.get(i);
				if(invitation.getPersonId()==personId){
					Map<String,Object> inMap = new HashMap<String, Object>();
					EPerson person  =  Application.getPersonService().getEPersonById(invitation.getPersonId());
					inMap.put("id", invitation.getId());
					inMap.put("personId", person.getId());
					inMap.put("personName", person.getName());
					inMap.put("personLevel", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
					inMap.put("issueTime", invitation.getIssueTime());
					inMap.put("activityTime", invitation.getActivityTime());
					inMap.put("personUrl", person.getIcon());
					inMap.put("content", invitation.getContent());
					inMap.put("address", invitation.getAddress());
					inMap.put("currentNum", invitation.getCurrentNum());
					inMap.put("maxNum", invitation.getMaxNum());
					if(getInvitationPersonBy(invitation.getId(),Application.getLoginInfo().getPerson().getId())!=null){
						inMap.put("isJoin","1");
					}else{
						inMap.put("isJoin","0");
					}
					inMap.put("hits", invitation.getHits());
					inMap.put("icons", invitation.getIcons());
					inMaps.add(inMap);
					if(inMaps.size()>=size)
					{
						break;
					}
				}
			}
		}
		
		return inMaps;
	}
	public List<Map<String,Object>> getInvitationsMapByUnionId(int unionId,int from ,int size,String sortBy){
		List<Map<String,Object>> inMaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("forwardIdFrom", from);
		List<EInvitation> invitations = getInvitations(params);
		switch (sortBy) {
		case "issueTime":
			Collections.sort(invitations, new Comparator<EInvitation>(){
				
				@Override
				public int compare(EInvitation lhs, EInvitation rhs) {
					// TODO Auto-generated method stub
					if(lhs.getIssueTime()<rhs.getIssueTime())
						return 1;
					else if(lhs.getIssueTime()>rhs.getIssueTime())
						return -1;
					return 0;
				}
			});
			break;
		case "hits":
			Collections.sort(invitations, new Comparator<EInvitation>(){
				
				@Override
				public int compare(EInvitation lhs, EInvitation rhs) {
					// TODO Auto-generated method stub
					if(lhs.getHits()<rhs.getHits())
						return 1;
					else if(lhs.getHits()>rhs.getHits())
						return -1;
					return 0;
				}
			});
			break;
		default:
			break;
		}
		
		if(invitations!=null&&invitations.size()>0){
			for (int i=0;i<invitations.size();i++) {
				EInvitation invitation =invitations.get(i);
				EUnion unionByPersonId = Application.getUnionService().getUnionByPersonId(invitation.getPersonId());
				if(unionByPersonId!=null&&unionByPersonId.getId()==unionId){
					Map<String,Object> inMap = new HashMap<String, Object>();
					EPerson person  =  Application.getPersonService().getEPersonById(invitation.getPersonId());
					inMap.put("id", invitation.getId());
					inMap.put("personId", person.getId());
					inMap.put("personName", person.getName());
					inMap.put("personLevel", Application.getLevelService().getELevelByID(person.getLevelId()).getName());
					inMap.put("issueTime", invitation.getIssueTime());
					inMap.put("activityTime", invitation.getActivityTime());
					inMap.put("personUrl", person.getIcon());
					inMap.put("content", invitation.getContent());
					inMap.put("address", invitation.getAddress());
					inMap.put("currentNum", invitation.getCurrentNum());
					inMap.put("maxNum", invitation.getMaxNum());
					if(getInvitationPersonBy(invitation.getId(),Application.getLoginInfo().getPerson().getId())!=null){
						inMap.put("isJoin","1");
					}else{
						inMap.put("isJoin","0");
					}
					inMap.put("hits", invitation.getHits());
					inMap.put("icons", invitation.getIcons());
					inMaps.add(inMap);
					if(inMaps.size()>=size)
					{
						break;
					}
				}
			}
		}
		
		return inMaps;
	}

	/*=====================================================================================*/

	private List<RInvitationPerson> getInvitationPersons(Map<String,Object> params){
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
		String[] array = selectionArgs.toArray(args);
		Cursor cursor = db.rawQuery(sql,array);
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

	private RInvitationPerson getInvitationPersonBy(int invitationId,int personId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("invitationId", invitationId);
		params.put("personId", personId);
		List<RInvitationPerson> invitationPersons = getInvitationPersons(params);
		if(invitationPersons!=null&&invitationPersons.size()>0)return invitationPersons.get(0);
		return null;
	}

	private long addInvitationPerson(RInvitationPerson invitationPerson){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("invitationId", invitationPerson.getInvitationId());
		cv.put("personId", invitationPerson.getPersonId());
		cv.put("time", invitationPerson.getTime());
		return db.insert("RInvitationPerson", null, cv);
	}
	public Result<EInvitation> publishInvitation(EInvitation invitation){
		Result<EInvitation>  res =new Result<EInvitation>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		long insertInvitation = helper.insertInvitation(invitation);
		if(insertInvitation>0)
		{
			EInvitation invitationById = this.getInvitationById((int) insertInvitation);
			if(invitationById!=null)
			{
				res.setMessage("success");
				res.setStatus("success");
				res.setResult(invitationById);
				return res;
			}
		}
		res.setMessage("保存失败");
		res.setStatus("error");
		return res;
		
	}
	private long deleteInvitationPerson(int id){
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.delete("RInvitationPerson", "id=?", new String[]{id+""});
	}
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
					RInvitationPerson invitationPerson = new RInvitationPerson();
					invitationPerson.setInvitationId(Integer.parseInt(in.get("id").toString()));
					invitationPerson.setPersonId(Application.getLoginInfo().getPerson().getId());
					invitationPerson.setTime(new Date().getTime());
					addInvitationPerson(invitationPerson);

					EInvitation invitation = getInvitationById(Integer.parseInt(in.get("id").toString()));
					if(invitation!=null){
						invitation.setCurrentNum(currentNum+1);
						updateInvitation(invitation);
					}
					//					in.put("isJoin", 1);
					//					in.put("currentNum", currentNum+1);
					res.setStatus("success");
					Achievement.joinInvitation();
				}
			}
		}
		else
		{
			if((""+0).equals(in.get("isJoin")+""))
			{
				res.setMessage("你没有参与");
				res.setStatus("error");
			}
			else 
			{
				int maxNum=Integer.parseInt(in.get("maxNum")==null||in.get("maxNum").toString().trim().equals("")?"0":in.get("maxNum").toString());
				int currentNum=Integer.parseInt(in.get("currentNum")==null||in.get("currentNum").toString().trim().equals("")?"0":in.get("currentNum").toString());
				RInvitationPerson invitationPerson = getInvitationPersonBy(Integer.parseInt(in.get("id").toString()), Application.getLoginInfo().getPerson().getId());
				if(invitationPerson!=null)
					deleteInvitationPerson(invitationPerson.getId());
				EInvitation invitation = getInvitationById(Integer.parseInt(in.get("id").toString()));
				if(invitation!=null){
					invitation.setCurrentNum(currentNum-1);
					updateInvitation(invitation);
				}
				res.setStatus("success");
			}
		}
		res.setResult(getInvitationMapById(id));
		return res;
	}
	/**
	 * @author clu
	 * 获取工会最新的邀请
	 * @param num
	 * @param unionId
	 * @return
	 */
	public List<Map<String,Object>> getLeastInvitationsByUnionId(int num,int unionId){
		return getInvitationsMapByUnionId(unionId,0,num,"issueTime");
	}
	/**
	 * @author hjl
	 * 获取工会最热门信息
	 * @param num
	 * @param unionId
	 * @return
	 */
	public List<Map<String,Object>> getHotInvitationMapsByUnionId(int num,int unionId){
		return getInvitationsMapByUnionId(unionId,0,num,"hits");
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
