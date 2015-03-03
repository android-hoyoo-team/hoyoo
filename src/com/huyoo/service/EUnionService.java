package com.huyoo.service;

import java.util.List;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.ETitle;
import com.huyoo.entity.EUnion;

/**
 * 
 * @author HJL 20150302
 *
 */
public class EUnionService {
	
	public EUnion getEUnionByID(int id)
	{
		EUnion union = new EUnion();
		union.setName("TOWERS1111111");
		union.setChairmanId(1);
		union.setCurrentExp(2730);
		union.setIcon("");
		union.setId(1);
		union.setLevelId(2);
		union.setTime(1000000000l);
		union.setTotalNum(570);
		union.setType("综合类");
		return union;
	}
	public List<EUnion> getAllEUnion()
	{
		return null;
	}
}
