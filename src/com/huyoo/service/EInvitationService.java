package com.huyoo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.EComment;
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
		/**
		 personName
		personLevel
		issueTime
		activityTime
		personUrl
		content
		address
		currentNum
		maxNum
		isJoin 0:1(join)
		 */
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
