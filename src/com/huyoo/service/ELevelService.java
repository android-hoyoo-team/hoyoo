package com.huyoo.service;

import java.util.List;

import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.RPersonAchievement;

/**
 * 
 * @author HJL 20150302
 *
 */
public class ELevelService {
	
	public ELevel getELevelByID(int id)
	{
		ELevel level = null;
		if(id == 1)
		{
			level = new ELevel();
			level.setName("成就大魔导师");
			level.setUpgradeExp(3000);
			level.setType("person");
		}
		else if(id == 2)
		{
			level = new ELevel();
			level.setName("超级工会");
			level.setUpgradeExp(13000);
			level.setType("union");
		}
		else{
			level = new ELevel();
			level.setName("其他等级");
			level.setUpgradeExp(0);
			level.setType("other");
		}
		return level;
	}
	public List<ELevel> getAllELevels()
	{
		return null;
	}
}
