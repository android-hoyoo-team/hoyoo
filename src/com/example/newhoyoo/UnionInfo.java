package com.example.newhoyoo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.androidquery.AQuery;
import com.exp.demo.AchieveFragment;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;
/**
 * 公会信息界面,展示公会的详细信息.
 * @author XF
 *
 */
public class UnionInfo extends Activity {
	
	AQuery aq;
	EUnion union;
	ELevel level;
	 HashMap<String, Object> map1=new HashMap<String,Object>(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.union_info);
		this.aq = new AQuery(this);
		init();
		initBenifitList();
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.unioninfo_actionbar);
		actionbar.setTitle("公会信息");
		actionbar.setButtonVisibility(View.GONE);
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
	}
	
	public void init(){
		union = Application.getLoginInfo().getUnion();
		level = Application.getLevelService().getELevelByID(union.getLevelId());
		
		this.aq.id(R.id.union_icon_imageview).image(union.getIcon());
		this.aq.id(R.id.union_name_textview).text(union.getName());
		this.aq.id(R.id.unionlvl_textview).text(level.getName());
		this.aq.id(R.id.union_type_textview).text(union.getType());
		this.aq.id(R.id.unionlvl1_textview).text(level.getName());
		this.aq.id(R.id.exp_textview).text(union.getCurrentExp()+"/"+level.getUpgradeExp());
		this.aq.id(R.id.unionnum_textview).text(union.getTotalNum()+"");
		this.aq.id(R.id.union_exp_textview).text(union.getCurrentExp()+"/"+level.getUpgradeExp());
		this.aq.id(R.id.union_birthday_textview).text(new SimpleDateFormat("yyyy.MM.dd").format(new Date(union.getTime())));
		this.aq.id(R.id.activitynum_textview).text(union.getActivityNum()+"个");
	}
	
	public void initBenifitList(){
		
		ArrayList<HashMap<String, Object>> benifitList = new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<10;i++)
		{
			map1.put("name", "大置顶术");
			map1.put("discription", "公会成员的邀请在发布之后会在粉丝的邀请界面置顶1个小时。");
			benifitList.add(map1);
		}
		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this,   
				benifitList,
        		//myArrayList,//数据源   
                R.layout.unionbenifit_item,//ListView内部数据展示形式的布局文件listitem.xml   
                new String[]{"name","discription" },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
                new int[]{R.id.benifit_name,R.id.benifit_discription });
        /*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   
           
		ListView benifitListView = (ListView)findViewById(R.id.benifit_listview);
		benifitListView.setAdapter(mySimpleAdapter);   
	}
	public void back(){
		this.finish();
	}
}
