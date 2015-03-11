package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EAchievement;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;
import com.ryg.expandable.ui.HorizontalListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class AchievementDetail extends Activity{
	AQuery aq;
	HorizontalListView relativeListView;
	CustomListViewAdapter mAdapter;
	CustomActionbar actionbar;
	int id=0;
	EAchievement achievement;
	WebView achievementDetail;
	WebView tips;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.achievement_detail);
		
		
		actionbar=(CustomActionbar)findViewById(R.id.achievement_detail_actionbar);
		actionbar.setTitle("成就详情");
		actionbar.setButtonVisibility(View.GONE);
		
		Intent intent=getIntent();
		id=Integer.parseInt(intent.getStringExtra("id").toString());
		aq=new AQuery(this);
		achievement=Application.getAchievementService().getEAchievementById(id);
		aq.id(R.id.achievement_icon).image(achievement.getIcon());//公会图片
		loadAchievementDetail(achievement);//公会详情
		loadExpIcon(achievement.getExp());//经验图片
		loadTips("", "2015.9.6", "2016.1.1");//注
		aq.id(R.id.actionbar_left).clicked(this,"clickActionbarLeft");
		initRelativeAchievement();
	}
	
	public void initRelativeAchievement(){
		relativeListView=(HorizontalListView)findViewById(R.id.relative_achievement);
		List<HashMap<String, Object>> relativeList=new ArrayList<HashMap<String,Object>>();
		List<EAchievement> list=Application.getAchievementService().getRelativeAchievements(achievement.getId());
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("icon", list.get(i).getIcon());
			map.put("name", list.get(i).getName());
			map.put("id", list.get(i).getId());
			relativeList.add(map);
		}
		mAdapter=new CustomListViewAdapter(this, 
				relativeList, 
				R.layout.relative_achievement_list,
				new String[]{"icon","name"}, 
				new int[]{R.id.relative_achievement_icon,R.id.relative_achievement_name},
				new String[]{"image","text"});
		relativeListView.setAdapter(mAdapter);
	}
	public void loadAchievementDetail(EAchievement achievement){
		achievementDetail=(WebView)findViewById(R.id.achievement_detail);
		StringBuilder sb=new StringBuilder();
		sb.append("<head><style>*{font-size:12px;} p { margin:5px;}</style></head><body><p style=\"font-weight:bolder\">");
		sb.append(achievement.getName());
		sb.append("</p><p style=\"color: #ffbb33;font-weight:bolder\">资料片-<span>");
		sb.append("病毒入侵");
		sb.append("</span></p><p>达成条件：<span style=\"font-weight:bolder\">");
		sb.append(achievement.getAddition());
		sb.append("</span></p><p>已完成：<span style=\"font-weight:bolder\">");
		sb.append(0);
		sb.append("/");
		sb.append(achievement.getExp());
		sb.append("</span></p><p style=\"font-size:9px;\">");
		sb.append(achievement.getDescription());
		sb.append("</p></body></html>");
		achievementDetail.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
	}
	public void loadTips(String resource,String startTime,String endTime){
		tips=(WebView)findViewById(R.id.tips);
		StringBuilder sb=new StringBuilder();
		sb.append("<head><style>*{font-size:9px;padding:0px;margin:0px;background-color: #dbdcdc !important;}</style></head><body><p><span style=\"color:#ff0000;font-weight:bolder\">注：</span><span style=\"color: #ffbb33;font-weight:bolder\">“资料片-");
		sb.append("病毒入侵");
		sb.append("”</span>的相关成就只能在资料片开放期间：");
		sb.append(startTime);
		sb.append("-");
		sb.append(endTime);
		sb.append("内完成，资料片结束后，所有获得成绩会列为光辉事迹，并无法再完成该资料片成就</p></body>");
		tips.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
	}
	public void loadExpIcon(int exp){
		switch (exp) {
		case 5:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/82F9609DD2B745A884785FE2A4F2CC78");
			break;
		case 10:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/625FCD5676DC47378A319B9F0A528F8D");
			break;
		case 15:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/14C1584D8B3F44F492240419B1DC779F");
			break;
		case 20:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/E9BB88AEF2A44A1EA02CD7B909D9350B");
			break;
		case 25:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B48E94AB98644723B51E8608BE72B778");
			break;
		case 30:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/FED61598254745848F7C1927110DB41D");
			break;
		case 35:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B52BC9D4C6A4F93BB4FC0E7EA0E6DE1");
			break;
		case 40:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/0DDCBAD751AF4C43B94A8C39FDCF0E0E");
			break;
		case 45:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/BD5D1AB6F7A64835B513650A75638236");
			break;
		case 50:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/BF977AB1BD3B475DACC95FA1F1A70277");
			break;
		case 100:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/F27E546172894FE6A4D48C6AD6BB097F");
			break;
		case 150:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A900A16C081B412AA2D85CDBBD3D7D09");
			break;
		case 200:
			aq.id(R.id.achievement_exp_icon).image("http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/077484B7DCBF478E8375311524400BE9");
			break;
		default:
			break;
		}
	}
	public void clickActionbarLeft(){
		finish();
	}
	
}
