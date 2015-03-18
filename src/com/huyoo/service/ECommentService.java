package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EComment;

public class ECommentService {

	public List<EComment> getEComments(Map<String,Object> params)
	{
		return null;
	}
	public List<EComment> getCommentByInvitationId(int invitationId){
		List<EComment> list=new ArrayList<EComment>();
		EComment eComment1=new EComment();
		eComment1.setId(1);
		eComment1.setPersonId(1);
		eComment1.setTime(1426583209941l);
		eComment1.setContent("来吧来吧来吧");
		eComment1.setInvitationId(1);
		eComment1.setCommentIdTo(0);
		list.add(eComment1);
		
		EComment eComment2=new EComment();
		eComment2.setId(2);
		eComment2.setPersonId(1);
		eComment2.setTime(1426583209941l);
		eComment2.setContent("来吧来吧来吧");
		eComment2.setInvitationId(1);
		eComment2.setCommentIdTo(0);
		list.add(eComment2);
		
		EComment eComment3=new EComment();
		eComment3.setId(3);
		eComment3.setPersonId(1);
		eComment3.setTime(1426583209941l);
		eComment3.setContent("来吧来吧来吧");
		eComment3.setInvitationId(1);
		eComment3.setCommentIdTo(0);
		list.add(eComment3);
		
		EComment eComment4=new EComment();
		eComment4.setId(4);
		eComment4.setPersonId(1);
		eComment4.setTime(1426583209941l);
		eComment4.setContent("来吧来吧来吧");
		eComment4.setInvitationId(1);
		eComment4.setCommentIdTo(0);
		list.add(eComment4);
		
		EComment eComment5=new EComment();
		eComment5.setId(5);
		eComment5.setPersonId(1);
		eComment5.setTime(1426583209941l);
		eComment5.setContent("来吧来吧来吧");
		eComment5.setInvitationId(1);
		eComment5.setCommentIdTo(0);
		list.add(eComment5);
		return list;
	}
}
