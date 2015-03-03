package com.huyoo.bean;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;

public class LoginInfo {
	private EPerson person;
	private EUnion union;
	

	public EUnion getUnion() {
		if(union==null)
			union=Application.getUnionService().getEUnionByID(this.getPerson().getUnionId());
		return union;
	}

	public EPerson getPerson() {
		return person;
	}

	public void setPerson(EPerson person) {
		this.person = person;
	}
	
}
