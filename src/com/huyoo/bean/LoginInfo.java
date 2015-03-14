package com.huyoo.bean;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Application;

public class LoginInfo {
	private EPerson person;
	private EUnion union;
	private ELevel level;
	private String status;

	/**
	 * 当公会为空(还未查出公会信息)或者个人的公会id改变(更换公会)时，需要查询公会信息。
	 * @return
	 */
	public EUnion getUnion() {
			Map<String,EUnion> map = new HashMap<String,EUnion>();
			List<RUnionPerson> ups = Application.getPersonService().getUnionPerson(this.getPerson().getId());
			for(RUnionPerson up:ups){
				if("in".equals(up.getStatus())){
					union = Application.getUnionService().getEUnionByID(up.getUnionId());
					map.put(union.getStatus(),union);
				}
			}
			EUnion union = new EUnion();
			if(map.get("normal")!=null)return map.get("normal");
			if(map.get("applying")!=null){
				union =  map.get("applying");
				if("pass".equals(getStatus()))union.setStatus("normal");
				if("unpass".equals(getStatus()))return null;
			}
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
