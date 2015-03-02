package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huyoo.entity.EPerson;

/**
 * 
 * @author xufei 20150302
 *
 */
public class EPersonService {
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<EPerson> getPersons(Map<String,Object> param)
	{
		List<EPerson> persons = new ArrayList<EPerson>();
		for(int i=0;i<10;i++)
		{
			EPerson person = new EPerson();
			person.setId(001);
			person.setName("莫小千");
		}
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public EPerson getEPersonById(int id)
	{
		return null;
	}
	
	/**
	 * 
	 * @param unionId
	 * @return
	 */
	public List<EPerson> getEPersonsByUnionId(int unionId)
	{
		return null;
	}
	
}
