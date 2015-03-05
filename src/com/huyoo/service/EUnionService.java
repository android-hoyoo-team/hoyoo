package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;

import android.webkit.WebView;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.EArticle;
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
		union.setCurrentExp(12100);
		union.setIcon("http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
		union.setId(1);
		union.setLevelId(2);
		union.setTime(1000000000l);
		union.setTotalNum(570);
		union.setType("综合类");
		union.setActivityNum(5);
		return union;
	}
	public List<EUnion> getAllEUnion()
	{
		return null;
	}
	/**
	 * 返回公会最新动态，服务端返回html字符串
	 * @param id
	 * @param num
	 * @return
	 */
	public List<String> getLatestNews(int id,int num)
	{
		List<String> list = new ArrayList<String>();
		for(int i =0;i<num;i++)
		{
			String data ="<img height=\"15dp;\" src=\"http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4\"/>"
					+"<span style=\"font-weight:bold;\">王昆</span>完成了"
					+"<span style=\"color:#ffbb33\">[这尼玛是什么]</span>成就";
			list.add(data);
		}
		return list;
	}
}
