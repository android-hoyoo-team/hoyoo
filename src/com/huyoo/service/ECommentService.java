package com.huyoo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huyoo.entity.EComment;
import com.huyoo.entity.ELevel;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;

public class ECommentService {

	public List<EComment> getEComments(Map<String,Object> params)
	{
		List<EComment> comments = new ArrayList<EComment>();
		DatabaseHelper helper = Application.getDatabaseHelper();
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer sb = new StringBuffer();
		List<String> selectionArgs = new ArrayList<String>();
		String sql = "select * from EComment where 1=1";
		if(params!=null){
			if(params.get("id")!=null){
				sb.append(" and id=?");
				selectionArgs.add(params.get("id").toString());
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
			long time = cursor.getLong(cursor.getColumnIndex("time"));
			String content = cursor.getString(cursor.getColumnIndex("content"));
			int invitationId = cursor.getInt(cursor.getColumnIndex("invitationId"));
			int commentIdTo = cursor.getInt(cursor.getColumnIndex("commentIdTo"));
			EComment comment = new EComment();
			comment.setId(id);
			comment.setPersonId(personId);
			comment.setTime(time);
			comment.setContent(content);
			comment.setInvitationId(invitationId);
			comment.setCommentIdTo(commentIdTo);
			comments.add(comment);
		}
		return comments;
	}
	
	public List<EComment> getCommentByInvitationId(int invitationId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("invitationId", invitationId);
		return getEComments(params);
	}
//	public List<EComment> getCommentByInvitationId(int invitationId){
//		List<EComment> list=new ArrayList<EComment>();
//		EComment eComment1=new EComment();
//		eComment1.setId(1);
//		eComment1.setPersonId(1);
//		eComment1.setTime(1426583209941l);
//		eComment1.setContent("来吧来吧来吧");
//		eComment1.setInvitationId(1);
//		eComment1.setCommentIdTo(0);
//		list.add(eComment1);
//		
//		EComment eComment2=new EComment();
//		eComment2.setId(2);
//		eComment2.setPersonId(1);
//		eComment2.setTime(1426583209941l);
//		eComment2.setContent("来吧来吧来吧");
//		eComment2.setInvitationId(1);
//		eComment2.setCommentIdTo(0);
//		list.add(eComment2);
//		
//		EComment eComment3=new EComment();
//		eComment3.setId(3);
//		eComment3.setPersonId(1);
//		eComment3.setTime(1426583209941l);
//		eComment3.setContent("来吧来吧来吧");
//		eComment3.setInvitationId(1);
//		eComment3.setCommentIdTo(0);
//		list.add(eComment3);
//		
//		EComment eComment4=new EComment();
//		eComment4.setId(4);
//		eComment4.setPersonId(1);
//		eComment4.setTime(1426583209941l);
//		eComment4.setContent("来吧来吧来吧");
//		eComment4.setInvitationId(1);
//		eComment4.setCommentIdTo(0);
//		list.add(eComment4);
//		
//		EComment eComment5=new EComment();
//		eComment5.setId(5);
//		eComment5.setPersonId(1);
//		eComment5.setTime(1426583209941l);
//		eComment5.setContent("来吧来吧来吧");
//		eComment5.setInvitationId(1);
//		eComment5.setCommentIdTo(0);
//		list.add(eComment5);
//		return list;
//	}
}
