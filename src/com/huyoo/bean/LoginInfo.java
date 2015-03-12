package com.huyoo.bean;

import java.util.List;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionPerson;
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
		if(union==null ||getPerson().getUnionId()!=union.getId()){
			List<RUnionPerson> ups = Application.getPersonService().getUnionPerson(this.getPerson().getUnionId());
			for(RUnionPerson up:ups){
				if("in".equals(up.getStatus())){
					union = Application.getUnionService().getEUnionByID(up.getUnionId());
				}
			}
		}
		if(union!=null&&union.getStatus().equals("normal")){
			return union;
		}
		return null;
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
