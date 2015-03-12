package com.huyoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.webkit.WebView;

import com.huyoo.entity.EPerson;
import com.huyoo.entity.EArticle;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionPerson;

/**
 * 
 * @author HJL 20150302
 *
 */
public class EUnionService {
	
	public List<EUnion> getUnions(Map<String,Object> params){
		List<EUnion> unions = new ArrayList<EUnion>();
		for(int i =0;i<20;i++){
			EUnion union = new EUnion();
			union.setName("TOWERS"+i);
			union.setChairmanId(1);
			union.setCurrentExp(12100);
			union.setIcon("http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
			union.setId(i+1);
			union.setLevelId(2);
			union.setTime(1000000000l);
			union.setTotalNum(570);
			switch (i%4) {
			case 0:
				union.setStatus("normal");
				break;
			case 1:
				union.setStatus("approvalling");
				break;
			case 2:
				union.setStatus("dismissed");
				break;
			case 3:
				union.setStatus("unpassed");
				break;
			default:
				break;
			}
			unions.add(union);
		}
		return unions;
	}
	
	

	public EUnion getEUnionByID(int id)
	{
		EUnion union = null;
		switch (id) {
		case 1:
			union = new EUnion();
			union.setName("TOWERS1111111");
			union.setChairmanId(2);
			union.setCurrentExp(12100);
			union.setIcon("http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
			union.setId(2);
			union.setLevelId(2);
			union.setTime(1000000000l);
			union.setTotalNum(570);
			union.setStatus("normal");
//			switch (id%4) {
//			case 0:
//				union.setStatus("normal");
//				break;
//			case 1:
//				union.setStatus("approvalling");
//				break;
//			case 2:
//				union.setStatus("dismissed");
//				break;
//			case 3:
//				union.setStatus("unpassed");
//				break;
//			default:
//				break;
//			}
//			
			union.setType("综合类");
			union.setActivityNum(5);
			break;
		case 2:
			union = new EUnion();
			union.setName("TOWERS1111111");
			union.setChairmanId(1);
			union.setCurrentExp(12100);
			union.setIcon("http://a2.qpic.cn/psb?/V123gJXv3aiMj6/80WTBdgeP3GoowD3vBhdlA1xmlyksgJQOl.sxKSE99c!/b/dAQAAAAAAAAA&bo=yQDcAAAAAAABBzU!&rf=viewer_4");
			union.setId(1);
			union.setLevelId(2);
			union.setTime(1000000000l);
			union.setTotalNum(570);
			switch (id%4) {
			case 0:
				union.setStatus("normal");
				break;
			case 1:
				union.setStatus("approvalling");
				break;
			case 2:
				union.setStatus("dismissed");
				break;
			case 3:
				union.setStatus("unpassed");
				break;
			default:
				break;
			}
			
			union.setType("综合类");
			union.setActivityNum(5);
			break;
		default:
			break;
		}
		
		return union;
	}
	
	public EUnion getUnionByPersonId(int personId){
		return null;
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
	public String getApplyUnionNotice(){
		return "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。"
				+ "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。"
				+ "这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。这里是建立工会所必须要知道的。";
	}
	
	
	public int saveUnion(EUnion union){
		return 10;
	} 
	
	public void saveUnionPerson(List<RUnionPerson> ups){
		
	}
	
	public List<EUnion> getUnionsByPersonId(int personId){
		return getUnions(null);
	}
}
