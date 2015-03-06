package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	private ArrayList<List<Achievement>> childList;

	private MyexpandableListAdapter adapter;
	/************************************************************/
	/** Called when the activity is first created. */   
	ListView myListView; 

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

		//List<EAchievement> latestData = Application.getAchievementService().getLastestEAchievements(personId, num)
		HorizontalListView latestAchievement = (HorizontalListView)getActivity().findViewById(R.id.horizon_listview);
		ArrayList<HashMap<String,Object>> latestList=new ArrayList<HashMap<String,Object>>();  
		for(int i = 0;i < 5;i++)
		{
			HashMap<String, Object> mMap=new HashMap<String,Object>(); 
			mMap.put("image", "http://note.youdao.com/yws/public/resource/2344ca2b1fd08f2a39ddf152e5fa54ab/9855C5331E004040B1A5D6C9D8483108");
			mMap.put("text", "有车有房还有什么呢");
			latestList.add(mMap);
		}
		final HorizontalListViewAdapter simpleAdapter = new HorizontalListViewAdapter(getActivity(), 
				latestList,
				new String[]{"image","text"},
				new int[]{R.id.img_list_item,R.id.text_list_item},
				new String[]{"image","text"}
				);
		latestAchievement.setAdapter(simpleAdapter);

		/************************************************************/
		//生成ListView对象   
		myListView=(ListView)getActivity().findViewById(R.id.main_list2);
		ArrayList<HashMap<String,Object>> programeList=new ArrayList<HashMap<String,Object>>();  
		for(int i=0;i<5;i++) 
		{ 
			switch(i){
			case 0:
				pMap.put("picture",R.drawable.d05); 
				pMap.put("name", "全是邀请");  
				pMap.put("describe", "全是邀请 qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");  
				programeList.add(pMap);
				break;
			case 1:

				pMap1.put("picture",R.drawable.d06); 
				pMap1.put("name", "我的公会");  
				pMap1.put("describe", "全是邀请全是邀请全是邀请全是邀请全是邀请全是邀请全是邀请全是邀请全是邀请"); 
				programeList.add(pMap1);
				break;
			case 2:

				pMap2.put("picture",R.drawable.d07); 
				pMap2.put("name", "我的成就");  
				pMap2.put("describe","全是邀请 qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"); 
				programeList.add(pMap2);
				break;
			case 3:

				pMap3.put("picture",R.drawable.d08); 
				pMap3.put("name", "我的信息");  
				pMap3.put("describe", "全是邀请 qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"); 
				programeList.add(pMap3);
				break;
			}
		} 

		//生成SimpleAdapter适配器对象   
		final SimpleAdapter mySimpleAdapter=new SimpleAdapter(this.getActivity(),   
				programeList,
				//myArrayList,//数据源   
				R.layout.item_achieve,//ListView内部数据展示形式的布局文件listitem.xml   
				//	R.layout.child,
				new String[]{"picture","name","describe"  },//HashMap中的两个key值 itemTitle和itemContent   ,"itemContent"
				new int[]{R.id.achievement_imageview_item,R.id.achievement_name_item,R.id.achievement_describe_item	});
		/*布局文件listitem.xml中组件的id    ,R.id.itemContent布局文件的各组件分别映射到HashMap的各元素上，完成适配*/   

		myListView.setAdapter(mySimpleAdapter);   
		//添加点击事件   
		myListView.setOnItemClickListener(new OnItemClickListener(){   
			@Override   
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
					long arg3) {   
				//获得选中项的HashMap对象   
				HashMap<String,String> map=(HashMap<String,String>)myListView.getItemAtPosition(arg2);   
				String title=map.get("itemTitle");   
				String content=map.get("itemContent");  

				switch(arg2){
				case 0:
					pMap.put("picture",R.drawable.d05a);
					myListView.setAdapter(mySimpleAdapter);
					Toast.makeText(getActivity().getApplicationContext(),    
							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
							Toast.LENGTH_SHORT).show();	        		
					break;
				case 1:
					pMap1.put("picture",R.drawable.d06a);
					myListView.setAdapter(mySimpleAdapter);
					Toast.makeText(getActivity().getApplicationContext(),    
							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
							Toast.LENGTH_SHORT).show();
					break;
				case 2:
					pMap2.put("picture",R.drawable.d07a);
					myListView.setAdapter(mySimpleAdapter);
					Toast.makeText(getActivity().getApplicationContext(),    
							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
							Toast.LENGTH_SHORT).show(); 
					break;
				case 3:
					pMap3.put("picture",R.drawable.d08a);
					myListView.setAdapter(mySimpleAdapter);
					Toast.makeText(getActivity().getApplicationContext(),    
							"你选择了第"+arg2+"个Item，itemTitle的值是："+title+"itemContent的值是:"+content,   
							Toast.LENGTH_SHORT).show(); 
					break;
				}

			}   

		});   
		/************************************************************/
		expandableListView = (PinnedHeaderExpandableListView) getActivity().findViewById(R.id.expandablelist);
		// stickyLayout = (StickyLayout)getActivity().findViewById(R.id.sticky_layout);
		initData();

		adapter = new MyexpandableListAdapter(getActivity());



		expandableListView.setAdapter(adapter);

		//getActivity().findViewById(R.id.id1).setLayoutParams(new LayoutParams(400, 800));
		// expandableListView.setLayoutParams(new LayoutParams(400, 800));

		// 展开所有group
		for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
			//expandableListView.expandGroup(i);
		}

		expandableListView.setOnHeaderUpdateListener(this);
		expandableListView.setOnChildClickListener(this);
		expandableListView.setOnGroupClickListener(this);
		// stickyLayout.setOnGiveUpTouchEventListener(this);

	}
	/**
	 * @return *************************************************************/
	/*加载数据*/
	public void initLatest(){
		
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

		childList = new ArrayList<List<Achievement>>();
		for (int i = 0; i < groupList.size(); i++) {
			ArrayList<Achievement> childTemp;
			if (i == 0) {
				childTemp = new ArrayList<Achievement>();
				for (int j = 0; j < 13; j++) {
					Achievement ac = new Achievement();
					ac.setName("社交成就"+j);
					ac.setDescription("这是第"+j+"个社交成就.");
					ac.setImageId(R.drawable.d05);
					childTemp.add(ac);
				}
			} else if (i == 1) {
				childTemp = new ArrayList<Achievement>();
				for (int j = 0; j < 8; j++) {
					Achievement ac = new Achievement();
					ac.setName("娱乐成就"+j);
					ac.setDescription("这是第"+j+"个娱乐成就.");
					ac.setImageId(R.drawable.d06);
					childTemp.add(ac);
				}
			} else if(i == 2){
				childTemp = new ArrayList<Achievement>();
				for (int j = 0; j < 8; j++) {
					Achievement ac = new Achievement();
					ac.setName("竞技成就"+j);
					ac.setDescription("这是第"+j+"个竞技成就.竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就竞技成就");
					ac.setImageId(R.drawable.d07);
					childTemp.add(ac);
				}
			} else if(i == 3){
				childTemp = new ArrayList<Achievement>();
				for (int j = 0; j < 8; j++) {
					Achievement ac = new Achievement();
					ac.setName("探索成就"+j);
					ac.setDescription("这是第"+j+"个探索成就.");
					ac.setImageId(R.drawable.d08);
					childTemp.add(ac);
				}
			} else {
				childTemp = new ArrayList<Achievement>();
				for (int j = 0; j < 8; j++) {
					Achievement ac = new Achievement();
					ac.setName("综合成就"+j);
					ac.setDescription("这是第"+j+"个综合成就.");
					ac.setImageId(R.drawable.d07);
					childTemp.add(ac);
				}
			}
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
			ChildHolder childHolder = null;
			if (convertView == null) {
				childHolder = new ChildHolder();
				convertView = inflater.inflate(R.layout.child, null);

				childHolder.textName = (TextView) convertView
						.findViewById(R.id.name);
				childHolder.textDiscription= (TextView) convertView
						.findViewById(R.id.discription);
				childHolder.imageView = (ImageView) convertView
						.findViewById(R.id.image);
				convertView.setTag(childHolder);
			} else {
				childHolder = (ChildHolder) convertView.getTag();
			}

			childHolder.textName.setText(((Achievement) getChild(groupPosition,
					childPosition)).getName());
			childHolder.textDiscription.setText(((Achievement) getChild(groupPosition,
					childPosition)).getDescription());
			childHolder.imageView.setImageResource(((Achievement)getChild(groupPosition,
					childPosition)).getImageId());
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

	class HorizontalListViewAdapter extends BaseAdapter{

		private Activity activity;
		private ArrayList<HashMap<String,Object>> data;
		private LayoutInflater inflater = null;
		private String[] from;
		private int[] to;
		private String[] operation;

		public HorizontalListViewAdapter(Activity a, ArrayList<HashMap<String, Object>> d,String[] f,int[] t,String [] op) {  
			activity = a;  
			data=d;  
			from = f;
			to = t;
			operation  = op;
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
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

			View v = inflater.inflate(R.layout.horizontal_list_item, null);
			AQuery aq = new AQuery(v);
			HashMap<String,Object> d = new HashMap<String, Object>();
			d = data.get(position);

			for(int i = 0 ; i < to.length ; i++){
				switch(operation[i])
				{
				case "image":
					aq.id(to[i]).image(d.get(from[i]).toString());
					break;
				case "text":
					aq.id(to[i]).text(d.get(from[i]).toString());
					break;
				default:
					aq.id(to[i]).text(d.get(from[i]).toString());
				}
			}
			return v;
		}
	}
}
