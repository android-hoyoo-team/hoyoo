package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.huyoo.entity.EAchievement;
import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.HorizontalListView;
import com.ryg.expandable.ui.PinnedHeaderExpandableListView;
import com.ryg.expandable.ui.PinnedHeaderExpandableListView.OnHeaderUpdateListener;
import com.ryg.expandable.ui.StickyLayout.OnGiveUpTouchEventListener;

public class AchieveFragment extends Fragment implements
ExpandableListView.OnChildClickListener,
ExpandableListView.OnGroupClickListener,
OnHeaderUpdateListener, OnGiveUpTouchEventListener {
	private PinnedHeaderExpandableListView expandableListView;
	private ArrayList<Group> groupList;
	private ArrayList<List<EAchievement>> childList;

	CustomListViewAdapter latestListViewAdapter;
	CustomListViewAdapter recommendListViewAdapter;
	private MyexpandableListAdapter adapter;
	CustomListViewAdapter listViewAdapter;

	/************************************************************/
	/** Called when the activity is first created. */   
	ListView myListView; 
	private EPerson person;
	HashMap<String, Object> pMap=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap1=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap2=new HashMap<String,Object>(); 
	HashMap<String, Object> pMap3=new HashMap<String,Object>();
	/************************************************************/

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_achieve, container, false);
		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		person = Application.getLoginInfo().getPerson();
		initLatest();

		initRecommend();

		initExpandableListView();

	}
	/**
	 * @return *************************************************************/
	/*加载数据*/
	/**
	 * 加载最新完成成就 HorizontalListView中的数据
	 */
	public void initLatest(){

		List<EAchievement> achievements = Application.getAchievementService().getLastestEAchievements(person.getId(), 4);
		HorizontalListView latestAchievement = (HorizontalListView)getActivity().findViewById(R.id.horizon_listview);
		ArrayList<HashMap<String,Object>> latestList=new ArrayList<HashMap<String,Object>>();  
		for(int i = 0;i < achievements.size();i++)
		{
			HashMap<String, Object> map=new HashMap<String,Object>(); 
			map.put("image", achievements.get(i).getIcon());
			map.put("text", achievements.get(i).getName());
			latestList.add(map);
		}
		latestListViewAdapter = new CustomListViewAdapter(getActivity(), 
				latestList,
				R.layout.horizontal_list_item,
				new String[]{"image","text"},
				new int[]{R.id.img_list_item,R.id.text_list_item},
				new String[]{"image","text"}
				);
		latestAchievement.setAdapter(latestListViewAdapter);
	}
	/**
	 * 加载成就推荐的listview
	 */
	public void initRecommend(){
		myListView=(ListView)getActivity().findViewById(R.id.recommend_achievement_listview);
		ArrayList<HashMap<String,Object>> recommendList=new ArrayList<HashMap<String,Object>>();  
		List<EAchievement> achievements = Application.getAchievementService().getRecommendEAchievements(person.getId(), 4);
		for (EAchievement achievement : achievements) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("image", achievement.getIcon());
			map.put("name", achievement.getName());
			map.put("description", achievement.getDescription());
			recommendList.add(map);
		}

		recommendListViewAdapter = new CustomListViewAdapter(getActivity(), 
				recommendList, 
				R.layout.item_achieve, 
				new String[]{"image","name","description"},
				new int[]{R.id.achievement_imageview_item,R.id.achievement_name_item,R.id.achievement_describe_item}, 
				new String[]{"image","text","text"});

		myListView.setAdapter(recommendListViewAdapter);   
		//添加点击事件   
		myListView.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
					long arg3) {
			}
		});
	}

	public void initExpandableListView(){
		expandableListView = (PinnedHeaderExpandableListView) getActivity().findViewById(R.id.expandablelist);
		initData();
		adapter = new MyexpandableListAdapter(getActivity());
		expandableListView.setAdapter(adapter);
		expandableListView.setOnHeaderUpdateListener(this);
		expandableListView.setOnChildClickListener(this);
		expandableListView.setOnGroupClickListener(this);
	}

	/**************************************************************/
	private void skip_quanshiyaoqing()
	{
		//startActivity(new Intent(this,yaoqing_view.class));  
		//finish();  
	}
	private void skip_gonghui()/*加入公会之前和之后需要进行数据的判断*/
	{
		//startActivity(new Intent(this,gonghui_view.class));  
		//finish();  
	}
	private void skip_wodechengjiu()
	{
		//startActivity(new Intent(this,chengjiu_view.class));  
		//finish();  
	}
	private void skip_wodexinxi()
	{
		//startActivity(new Intent(this,xinxi_view.class));  
		//finish();  
	}
	/**************************************************************/

	/***
	 * InitData
	 */
	void initData() {
		groupList = new ArrayList<Group>();
		Group group = null;
		String [] titles =  getResources().getStringArray(R.array.achievement_type);
		for(int i =0;i<titles.length;i++)
		{
			group = new Group();
			group.setTitle(titles[i]);
			groupList.add(group);
		}
		childList = new ArrayList<List<EAchievement>>();
		for (int i = 0; i < groupList.size(); i++) {
			List<EAchievement> childTemp = Application.getAchievementService().getEAchievementsByType(person.getId(), titles[i]);
			childList.add(childTemp);
		}
	}



	/**** 数据源   ** @author Administrator **/
	class MyexpandableListAdapter extends BaseExpandableListAdapter {
		private Context context;
		private LayoutInflater inflater;

		public MyexpandableListAdapter(Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);
		}

		// 返回父列表个数
		@Override
		public int getGroupCount() {
			return groupList.size();
		}

		// 返回子列表个数
		@Override
		public int getChildrenCount(int groupPosition) {
			return childList.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {

			return groupList.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childList.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {

			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupHolder groupHolder = null;
			if (convertView == null) {
				groupHolder = new GroupHolder();
				convertView = inflater.inflate(R.layout.group, null);
				groupHolder.textView = (TextView) convertView
						.findViewById(R.id.group);
				groupHolder.imageView = (ImageView) convertView
						.findViewById(R.id.image);
				convertView.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) convertView.getTag();
			}

			groupHolder.textView.setText(((Group) getGroup(groupPosition))
					.getTitle());
			if (isExpanded)// true is Expanded or false is not isExpanded
				groupHolder.imageView.setImageResource(R.drawable.bt_02_nor_up);
			else
				groupHolder.imageView.setImageResource(R.drawable.bt_02_nor_down);
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
//			ChildHolder childHolder = null;
//			if (convertView == null) {
//				childHolder = new ChildHolder();
//				convertView = inflater.inflate(R.layout.child, null);
//
//				childHolder.textName = (TextView) convertView
//						.findViewById(R.id.name);
//				childHolder.textDiscription= (TextView) convertView
//						.findViewById(R.id.discription);
//				childHolder.imageView = (ImageView) convertView
//						.findViewById(R.id.image);
//				convertView.setTag(childHolder);
//			} else {
//				childHolder = (ChildHolder) convertView.getTag();
//			}
//
//			childHolder.textName.setText(((EAchievement) getChild(groupPosition,
//					childPosition)).getName());
//			childHolder.textDiscription.setText(((EAchievement) getChild(groupPosition,
//					childPosition)).getDescription());
//			AQuery aq = new AQuery(childHolder.imageView);
//			childHolder.imageView.setImageResource(((EAchievement)getChild(groupPosition,
//					childPosition)).getIcon());
			return convertView;
		}

		@Override
		public void onGroupCollapsed(int groupPosition) {
			// TODO Auto-generated method stub
			super.onGroupCollapsed(groupPosition);
			LinearLayout ll=(LinearLayout)getActivity().findViewById(R.id.id1);

			android.view.ViewGroup.LayoutParams lp =ll.getLayoutParams();

			int child_height = getResources().getDimensionPixelSize(R.dimen.child_achievement_height);
			lp.height=lp.height-child_height*childList.get(groupPosition).size();
		}

		@Override
		public void onGroupExpanded(int groupPosition) {
			// TODO Auto-generated method stub
			super.onGroupExpanded(groupPosition);
			LinearLayout ll=(LinearLayout)getActivity().findViewById(R.id.id1);
			android.view.ViewGroup.LayoutParams lp =ll.getLayoutParams();
			int child_height = getResources().getDimensionPixelSize(R.dimen.child_achievement_height);
			lp.height=lp.height+ child_height*childList.get(groupPosition).size();
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	/**
	 * parent:ExpandableListView
	 * v:
	 */
	@Override
	public boolean onGroupClick(final ExpandableListView parent, final View v,
			int groupPosition, final long id) {

		return false;
	}




	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Toast.makeText(AchieveFragment.this.getActivity(),
				childList.get(groupPosition).get(childPosition).getName(), 1)
				.show();

		return false;
	}

	class GroupHolder {
		TextView textView;
		ImageView imageView;
	}

	class ChildHolder {
		TextView textName;
		TextView textDiscription;
		//TextView textAddress;
		ImageView imageView;
	}

	@Override
	public View getPinnedHeader() {
		View headerView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.group, null);
		headerView.setLayoutParams(new LayoutParams(
				0,0));
		//getResources().getInteger(R.integer.achievement_type_height)
		return headerView;
	}

	@Override
	public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
		Group firstVisibleGroup = (Group) adapter.getGroup(firstVisibleGroupPos);
		TextView textView = (TextView) headerView.findViewById(R.id.group);
		textView.setText(firstVisibleGroup.getTitle());
	}

	@Override
	public boolean giveUpTouchEvent(MotionEvent event) {
		if (expandableListView.getFirstVisiblePosition() == 0) {
			View view = expandableListView.getChildAt(0);
			if (view != null && view.getTop() >= 0) {
				return true;
			}
		}
		return false;
	}

	class CustomListViewAdapter extends SimpleAdapter{

		private LayoutInflater inflater = null;
		private List<HashMap<String,Object>> data;
		private String[] from;
		private int[] to;
		private String[] operation;
		private int resource;

		public CustomListViewAdapter(Context context,
				List<HashMap<String, Object>> d, int res,
				String[] f, int[] t,String[] op) {
			super(context, d, res, f, t);
			data = d;
			from = f;
			to = t;
			operation = op;
			resource = res;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v= inflater.inflate(resource, null);
			AQuery aq = new AQuery(v);
			HashMap<String,Object> d = new HashMap<String, Object>();
			d = data.get(position);
			for(int i = 0 ; i < to.length ; i++){
				switch(operation[i])
				{
				case "image":
					aq.id(to[i]).image(d.get(from[i]).toString(),false,true);
					break;
				case "text":
					aq.id(to[i]).text(d.get(from[i]).toString());
					break;
				}
			}
			return v;
		}
	}
}
