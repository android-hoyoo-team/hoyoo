package com.huyoo.bean;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;

public class LoginInfo {
	private EPerson person;
	private EUnion union;
	private ELevel level;
	
	/**
	 * 当公会为空(还未查出公会信息)或者个人的公会id改变(更换公会)时，需要查询公会信息。
	 * @return
	 */
	public EUnion getUnion() {
		if(union==null ||getPerson().getUnionId()!=union.getId())
			union=Application.getUnionService().getEUnionByID(this.getPerson().getUnionId());
		return union;
	}

	public EPerson getPerson() {
		return person;
	}

	public void setPerson(EPerson person) {
		this.person = person;
	}

	public ELevel getLevel() {
		if(level==null)
			level = Application.getLevelService().getELevelByID(this.person.getLevelId());
		return level;
	}
}
