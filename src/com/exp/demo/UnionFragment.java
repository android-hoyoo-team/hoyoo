package com.exp.demo;

import java.util.List;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.example.newhoyoo.UnionInviteList;
import com.example.newhoyoo.UnionMemberList;
import com.example.newhoyoo.UnionNewsList;
import com.example.newhoyoo.UnionInfo;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

public class UnionFragment extends Fragment {

	AQuery aq;
	/**************************************************************/
	EUnion union;
	ELevel level;
	/**************************************************************/
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_union, container, false);
		this.aq = new AQuery(this.getActivity(),rootView);
		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		init();
		loadWebView();
		this.aq.id(R.id.union_invite).background(R.drawable.buttoneffect);
		this.aq.id(R.id.union_invite).clicked(this, "unionInvite");
		this.aq.id(R.id.union_info).background(R.drawable.buttoneffect);
		this.aq.id(R.id.union_info).clicked(this, "unionInfo");
		this.aq.id(R.id.union_news).background(R.drawable.buttoneffect);
		this.aq.id(R.id.union_news).clicked(this, "unionNews");
		this.aq.id(R.id.union_member).background(R.drawable.buttoneffect);
		this.aq.id(R.id.union_member).clicked(this, "unionMember");
		this.aq.id(R.id.union_manager).background(R.drawable.buttoneffect);
		this.aq.id(R.id.union_manager).clicked(this, "unionManager");
	}

	public void init(){
		union = Application.getLoginInfo().getUnion();
		level = Application.getLevelService().getELevelByID(union.getLevelId());

		this.aq.id(R.id.union_icon_imageview).image(union.getIcon());
		this.aq.id(R.id.union_name_textview).text(union.getName());
		this.aq.id(R.id.unionlvl_textview).text(level.getName());
		this.aq.id(R.id.union_type_textview).text(union.getType());
		this.aq.id(R.id.unionnum_textview).text(union.getTotalNum()+"");
		this.aq.id(R.id.president_name_textview).text(Application.getPersonService().getEPersonById(union.getChairmanId()).getName());
		this.aq.id(R.id.union_achievement_textview).text(union.getCurrentExp()+"/"+level.getUpgradeExp());
	}

	public void loadWebView(){
		List<String> news = Application.getUnionService().getLatestNews(union.getId(), 5);
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body style=\"font-size:12\">");
		for (String str : news) {
			sb.append(str);
			sb.append("</br>");
		}
		sb.append("</body></html>");
		WebView webview = (WebView)getActivity().findViewById(R.id.union_news_webview);
		webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
	}


	public void unionInvite()
	{
		Intent intent=new Intent();
		intent.putExtra("unionId", Application.getLoginInfo().getUnion().getId());
//		intent.setComponent(Utils.getTopActivity(appContext));
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClass(getActivity(),UnionInviteList.class);
		getActivity().startActivity(intent);
	}
	public void unionInfo()
	{
		Intent intent = new Intent();
		intent.setClass(getActivity(),UnionInfo.class);
		getActivity().startActivity(intent);
	}
	public void unionNews()
	{
		Intent intent = new Intent();
		intent.setClass(getActivity(), UnionNewsList.class);
		getActivity().startActivity(intent);
	}
	public void unionMember()//返回主界面
	{
		Intent intent = new Intent();
		intent.setClass(getActivity(), UnionMemberList.class);
		getActivity().startActivity(intent);
	}
	public void unionManager()//返回主界面
	{
		Toast.makeText(getActivity().getApplicationContext(),    
				"你选择了第"+5+"个Item，itemTitle的值是："+5+"itemContent的值是:"+5,   
				Toast.LENGTH_SHORT).show(); 
	}
	private void skip_shezhi()//返回主界面
	{

	}
}
