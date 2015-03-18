package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;
import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newhoyoo.Main;
import com.example.newhoyoo.R;
import com.example.newhoyoo.UnionNewsActivity;
import com.example.newhoyoo.YaoqingActivity;
import com.example.newhoyoo.adapter.InvitationListAdapter;
import com.exp.demo.MultiLayoutSimpleAdapter.ViewBinder;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;
import com.viewflow.xlistviewfresh.XListView;
import com.viewflow.xlistviewfresh.XListView.IXListViewListener;


public class InviteFragment extends ListFragment implements IXListViewListener {

	private XListView mListView;
	
	private TextView no_union_textview;
//  SimpleAdapter mAdapter1;
	private InvitationListAdapter mAdapter2;
	private Handler mHandler;
//	private ArrayList<HashMap<String, Object>> dlist;
	private ArrayList<Map<String, Object>> dlist;
	
//	int c=0;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_inviteview, container, false);
		DispatchEvent.addEventListener("unionStatusChanged", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				// TODO Auto-generated method stub
				init();
			}
			
		});
		return rootView;
	}
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
		
    	init();
	}
	public void init(){
		EUnion union = Application.getLoginInfo().getUnion();
		CustomActionbar actionbar = (CustomActionbar)getActivity().findViewById(R.id.main_actionbar);
		mListView = (XListView) getActivity().findViewById(android.R.id.list);// 你这个listview是在这个layout里面
		no_union_textview = (TextView)getActivity().findViewById(R.id.no_union__invite_textview);
		if(union != null && "normal".equals(union.getStatus())){
			no_union_textview.setVisibility(View.GONE);
			actionbar.setButton("发一个");
			actionbar.setButtonVisibility(View.VISIBLE);
			mListView.setVisibility(View.VISIBLE);
			
			/** 下拉刷新，上拉加载 */
//			dlist = new ArrayList<HashMap<String, Object>>();
			dlist = new ArrayList<Map<String, Object>>();
			
			mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
			
			mAdapter2 =new InvitationListAdapter(this.getActivity());
			
			mAdapter2.setInvitationList(Application.getInvitationService().getInvitationsMapByUnionId(Application.getLoginInfo().getLevel().getId(),0,5));
					/*new MultiLayoutSimpleAdapter(InviteFragment.this.getActivity(), 
													 getData(),
													 new int[]{R.layout.invitation_item}, 
													 new String[] {"touxiang","name","faburiqi",
									  				 			   "neirong","riqi","shijian","dizhi","jindu"}, //0-18为普通布局 //19为判断布局标记 //20-36为转发布局变量
									  				 new int[] {R.id.image_us_photo, R.id.text_us_name,R.id.text_fabu_date,
								   								R.id.text_yaoq_info,R.id.text_yaoq_date,R.id.text_yaoq_time,
								   								R.id.text_yaoq_address,R.id.canjia_progress});//0-18为普通布局//19~35为转发布局*/
			
			mListView.setAdapter(mAdapter2);
			DispatchEvent.addEventListener("invitationListItemClick", new IMethod<Map<String,Object>>() {

				public void excute(DEvent<Map<String,Object>> event) {
					Intent intent=new Intent();
//					intent.putExtra("type", "公会新闻");
					HashMap<String, Object> item = (HashMap<String, Object>) event.getTarget();
					intent.putExtra("item", item);
//					intent.setClass(getActivity(), UnionNewsActivity.class);
					intent.setClass(getActivity(), YaoqingActivity.class);
					getActivity().startActivity(intent);
//					startActivity(new Intent(getActivity(),YaoqingActivity.class));  
				}
			});
			mListView.setXListViewListener(this);
			mHandler = new Handler();
		}else{
			actionbar.setButtonVisibility(View.GONE);
			mListView.setVisibility(View.GONE);
			no_union_textview.setVisibility(View.VISIBLE);
		}
	}
	
	
	/** 初始化本地数据 */
//	String data[] = new String[] { "唐嫣  江南大学LIE桌游公会  会长  等级：大神", "10月18日", "周六晚上小伙伴们来蜗牛咖啡厅打狼人！有妹子！饮料免费，快快来呦~",
//			"日期:2014年10月20日    星期日    时间:18:30-20:30", "地址:江南大学南门外-锦江之心-渔远庭2楼" ,"20","100","Y","30" };
	
//	private ArrayList<Map<String, Object>> getData() {
////...........之后用从网络上获取的信息
//		for (int i = 0; i < 6; i++) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("touxiang", R.drawable.near_icon2);
//			map.put("name", "唐嫣");
//			map.put("faburiqi", "2014年10月18日 12:21");
//			map.put("neirong", "周六晚上小伙伴们来蜗牛咖啡厅打狼人！有妹子！饮料免费，快快来呦~");
//			map.put("riqi", "2014年11月11日");
//			map.put("shijian", "11:11");
//			map.put("dizhi", "江南大学南门外-锦江之心-渔远庭2楼");
////			ImageView ivDrawable = (ImageView)getActivity().findViewById(R.id.canjia_progress);
//
//		      
////		    ivDrawable.setImageDrawable(drawable);
//		        Map jind=new HashMap<String, Object>();
//		        jind.put("total", 20);
//		        jind.put("comp", 10);
//			map.put("jindu", jind);
//		
//			dlist.add(map);
//		}
//		return dlist;
//	}

	/** 停止刷新， */
	private void onLoad() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}

	// 刷新
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
//				getData();
//				mListView.setAdapter(mAdapter1);
				mListView.setAdapter(mAdapter2);
				onLoad();
			}
		}, 2000);
	}

	// 加载更多
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				//getData();
//				mAdapter1.notifyDataSetChanged();
				mAdapter2.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	
    @Override
	public void onListItemClick(ListView l, View v, int position, long id){
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        l.getItemAtPosition(position);
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			 this.getActivity().finish();
         }
         return false;
	}

	public void skip_zhuanfa()
	{
        startActivity(new Intent(this.getActivity(),Main.class));  
        getActivity().finish(); 
	}
	
	public void skip_pinglun()
	{
        startActivity(new Intent(this.getActivity(),Main.class));  
        getActivity().finish(); 
	}
	
	public void skip_tupian()
	{
        startActivity(new Intent(this.getActivity(),Main.class));  
        getActivity().finish(); 
	}
	
	
	//...............................自定义适配器..................................
	class MultiLayoutSimpleAdapter extends BaseAdapter implements Filterable {
	    //布局1中的各种控件
	    private class buttonViewHolder {
	        ImageView touxiang,jindu;
	        TextView name,faburiqi,neirong,riqi,dizhi,shijian;
	    }
	    
	    private buttonViewHolder holder;
		
		private int[] mTo;
		private String[] mFrom;
	    private ViewBinder mViewBinder;

	    protected ArrayList<Map<String, Object>> mData;

	    private int[] mResources;
	    private LayoutInflater mInflater;

	    private SimpleFilter mFilter;
	    private ArrayList<Map<String, Object>> mUnfilteredData;
	    
	    /**
	     * 构造函数
	     *
	     * @param context 与 SimpleAdapter 关联的运行着的视图的上下文.
	     * @param data Map 的列表.列表中的每个条目对应一行.Maps 中包含所有在 from 中指定的数据.
	     * @param resource 定义列表项目的视图布局的资源 ID.布局文件至少应该包含在 to 中定义了的名称.
	     * @param from 与 Map 中的项目建立关联的列名的列表.
	     * @param to 用于显示 from 中参数中的列的视图列表.这些视图应该都是 TextView 类型的.
	     * 该列表中的第 N 个视图显示从参数 from 中的第 N 列获取的值.
	     */
	    public MultiLayoutSimpleAdapter(Context context, ArrayList<Map<String, Object>> data,
	            int[] resources, String[] from, int[] to ) {
	        mData = data;
	        mResources = resources;
	        mFrom = from;
	        mTo = to;

	        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    @Override
		public int getViewTypeCount() {
			return mResources.length;
		}

		/**
	     * @see android.widget.Adapter#getCount()
	     */
	    public int getCount() {
	        return mData.size();
	    }

	    /**
	     * @see android.widget.Adapter#getItem(int)
	     */
	    public Object getItem(int position) {
	        return mData.get(position);
	    }

	    /**
	     * @see android.widget.Adapter#getItemId(int)
	     */
	    public long getItemId(int position) {
	        return position;
	    }

	    /**
	     * @see android.widget.Adapter#getView(int, View, ViewGroup)
	     */
	    public View getView(int position, View convertView, ViewGroup parent) {
	        return createViewFromResource(position, convertView, parent, mResources[getItemViewType(position)]);
	    }
	    
	    private View createViewFromResource(int position, View convertView,
	            ViewGroup parent, int resource) {
	    	Map<String, Object> dataSet = mData.get(position);
	    	
	        View v = null;

	        if (convertView == null) {
	            v = mInflater.inflate(resource, parent, false);
	       //如果是布局1将控件绑定     
	            holder = new buttonViewHolder();
	            holder.touxiang = (ImageView)v.findViewById(mTo[0]);
	            holder.name = (TextView)v.findViewById(mTo[1]);
	            holder.faburiqi = (TextView)v.findViewById(mTo[2]);
	            holder.neirong = (TextView)v.findViewById(mTo[3]);
	            holder.riqi = (TextView)v.findViewById(mTo[4]);
	            holder.shijian = (TextView)v.findViewById(mTo[5]);
	            holder.dizhi = (TextView)v.findViewById(mTo[6]);
	            holder.jindu = (ImageView)v.findViewById(mTo[7]);

	            
	            v.setTag(holder); 
	        } else {
	            v = convertView;
	            holder = (buttonViewHolder) v.getTag();
	        }
	        
	        bindView2(position, v);
	        
	        return v;
	        
	    }

	    /**
	     * <p>设置创建下拉列表视图的布局资源 ID.</p>
	     *
	     * @param resource 定义下拉列表视图的布局资源 ID.
	     * @see #getDropDownView(int, android.view.View, android.view.ViewGroup)
	     */
	    public void setDropDownViewResource(int[] resources) {
	    }

	    @Override
	    public View getDropDownView(int position, View convertView, ViewGroup parent) {
	        return createViewFromResource(position, convertView, parent, mResources[getItemViewType(position)]);
	    }
	    
	    //..................................数据绑定.........................................
	    private void bindView2(int position,View view)
	    {
	        final Map<String, Object> dataSet = mData.get(position);
	        if (dataSet != null) {
	        	
	        	//获取传入的数据
	            int touxiang2 = (Integer) dataSet.get(mFrom[0]);
	            String name2 = (String)dataSet.get(mFrom[1]);
	            String faburiqi2 = (String)dataSet.get(mFrom[2]);
	            String neirong2 = (String)dataSet.get(mFrom[3]);
	            String riqi2 = (String)dataSet.get(mFrom[4]);
	            String shijian= (String)dataSet.get(mFrom[5]);
	            String dizhi2 = (String)dataSet.get(mFrom[6]);
	            
	            Map<String,Object> jindu = ( Map<String,Object>)dataSet.get(mFrom[7]);
//	            int renshujindutiao2 =(Integer) dataSet.get(mFrom[6]);
//	            String renshu2 = (String)dataSet.get(mFrom[7]);
//	            String zongrenshu2 =(String)dataSet.get(mFrom[8]);
//	            int tupian2_1 = (Integer) dataSet.get(mFrom[9]);
//	            int tupian2_2 = (Integer) dataSet.get(mFrom[10]);
//	            int tupian2_3 = (Integer) dataSet.get(mFrom[11]);
//	            int tupian2_4 = (Integer) dataSet.get(mFrom[12]);
	      //    int zhuanfa2 = (Integer) dataSet.get(mFrom[13]);
	      //    int pinglun2 = (Integer) dataSet.get(mFrom[14]);
//	            String canjia2 = (String) dataSet.get(mFrom[15]);            
//	            String dianzan2 = (String) dataSet.get(mFrom[16]);
//	            String dianzan_renshu = (String) dataSet.get(mFrom[18]);
	            buttonViewHolder holder = (buttonViewHolder) view.getTag();
	            //使用获取的数据对之前绑定的控件进行处理
	            holder.touxiang.setImageDrawable(holder.touxiang.getResources().getDrawable(touxiang2));
	            holder.name.setText(name2);
	            holder.faburiqi.setText(faburiqi2);
	            holder.neirong.setText(neirong2);
	            holder.riqi.setText(riqi2);
	            holder.shijian.setText(riqi2);
	            holder.dizhi.setText(dizhi2);
	            int total = (Integer) jindu.get("total");
	            int comp = (Integer) jindu.get("comp");
	            CircularProgressDrawable  drawable = new CircularProgressDrawable(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
		                getResources().getColor(android.R.color.darker_gray),
		                getResources().getColor(android.R.color.holo_red_dark),
		                getResources().getColor(android.R.color.holo_red_dark));
	            float p=(float) (comp/(total+0.0));
		        drawable.setProgress(p);
		        drawable.setCircleScale(p);
		        holder.jindu.setTag(drawable);
	            holder.jindu.setImageDrawable(drawable);
	            holder.jindu.setOnClickListener(new lvButtonListener(position,holder));
//	            holder.renshujindutiao.setProgress(renshujindutiao2);
//	            holder.renshu.setText(renshu2);
//	            holder.zongrenshu.setText(zongrenshu2);
//	            holder.tupian1.setImageDrawable(holder.tupian1.getResources().getDrawable(tupian2_1));
//	            holder.tupian2.setImageDrawable(holder.tupian2.getResources().getDrawable(tupian2_2));
//	            holder.tupian3.setImageDrawable(holder.tupian3.getResources().getDrawable(tupian2_3));
//	            holder.tupian4.setImageDrawable(holder.tupian4.getResources().getDrawable(tupian2_4));
//	            holder.tupian4.setImageDrawable(holder.tupian4.getResources().getDrawable(tupian2_4));
	 //         holder.zhuanfa.setBackgroundDrawable(holder.zhuanfa.getResources().getDrawable(zhuanfa2));
	 //         holder.pinglun.setBackgroundDrawable(holder.pinglun.getResources().getDrawable(pinglun2));
//	            holder.canjia.setText(canjia2);
//	            if(canjia2=="参加")
//	            {
//	            	holder.canjia.setTextColor(Color.BLACK);
//	            }else
//	            {
//	            	holder.canjia.setTextColor(Color.RED);
//	            }
	 //         holder.dianzan.setImageDrawable(holder.dianzan.getResources().getDrawable(dianzan2));  
//	            if(dianzan2=="Y")
//	            {
//	            	holder.dianzan.setVisibility(0x00000000);
//	            	holder.dianzan2.setVisibility(0x00000008);
//	            	holder.dianzan_renshu.setVisibility(0x00000008);
//	            }else
//	            {
//	            	holder.dianzan.setVisibility(0x00000008);
//	            	holder.dianzan2.setVisibility(0x00000000);
//	            	holder.dianzan_renshu.setVisibility(0x00000000);	
//	            	holder.dianzan_renshu.setText(dianzan_renshu);
//	            }
//	            
//	            //点击事件监听
//	            
	            holder.neirong.setOnClickListener(new lvButtonListener(position,holder));
//	            holder.tupian2.setOnClickListener(new lvButtonListener(position));
//	            holder.tupian3.setOnClickListener(new lvButtonListener(position));
//	            holder.tupian4.setOnClickListener(new lvButtonListener(position));
//	            
//	            holder.zhuanfa.setOnClickListener(new lvButtonListener(position));
//	            holder.pinglun.setOnClickListener(new lvButtonListener(position));
//	            holder.canjia.setOnClickListener(new lvButtonListener(position));
//	            holder.dianzan.setOnClickListener(new lvButtonListener(position)); 
//	            holder.dianzan2.setOnClickListener(new lvButtonListener(position)); 
	            
	        }        
	    }
	    
	    class lvButtonListener implements OnClickListener {
	        private int position2;
	        buttonViewHolder holder;

	        lvButtonListener(int pos, buttonViewHolder h) {
	            position2 = pos;
	            holder=h;
	        }
	        
	        @Override
	        public void onClick(View v) {
	        	//点击事件的处理
	            int vid=v.getId();
	            if(vid==holder.neirong.getId())
	            {
	            	 startActivity(new Intent(getActivity(),YaoqingActivity.class));  
//	                 getActivity().finish(); 
	            	//skip_tupian();
	            }
	            if(vid==holder.jindu.getId())
	            {
	            	CircularProgressDrawable tag = (CircularProgressDrawable) holder.jindu.getTag();
	            	tag.setCircleScale_(tag.getCircleScale()+0.1f);
	            	tag.setProgress_(tag.getProgress()+0.1f);
	            	Log.i("xxxx", "xxxx:"+tag.getProgress());
	            }
	        }
	    }
	    
	    public void gengxin()
	    {
	        this.notifyDataSetChanged();
	    }
	    //........................................
	    
	    
	    
	    private void bindView(int position, View view) {
	        final Map<String, Object> dataSet = mData.get(position);
	        if (dataSet == null) {
	            return;
	        }

	        final ViewBinder binder = mViewBinder;
	        final String[] from = mFrom;
	        final int[] to = mTo;
	        final int count = to.length;

	        for (int i = 0; i < count; i++) 
	        {
	            final View v = view.findViewById(to[i]);
	            if (v != null) 
	            {
	                final Object data = dataSet.get(from[i]);
	                String text = data == null ? "" : data.toString();
	                if (text == null) 
	                {
	                    text = "";
	                }

	                boolean bound = false;
	                if (binder != null) 
	                {
	                    bound = binder.setViewValue(v, data, text);
	                }

	                if (!bound) 
	                {
	                    if (v instanceof Checkable) 
	                    {
	                        if (data instanceof Boolean) 
	                        {
	                            ((Checkable) v).setChecked((Boolean) data);
	                        } else if (v instanceof TextView) 
	                        {
	                            // Note: keep the instanceof TextView check at the bottom of these
	                            // ifs since a lot of views are TextViews (e.g. CheckBoxes).
	                            setViewText((TextView) v, text);
	                        } else 
	                        {
	                            throw new IllegalStateException(v.getClass().getName() +
	                                    " should be bound to a Boolean, not a " +
	                                    (data == null ? "<unknown type>" : data.getClass()));
	                        }
	                    } else if (v instanceof TextView) 
	                    {
	                        // Note: keep the instanceof TextView check at the bottom of these
	                        // ifs since a lot of views are TextViews (e.g. CheckBoxes).
	                        setViewText((TextView) v, text);
	                    } else if (v instanceof ImageView) 
	                    {
	                        if (data instanceof Integer) 
	                        {
	                            setViewImage((ImageView) v, (Integer) data);
	                        } else 
	                        {
	                            setViewImage((ImageView) v, text);
	                        }
	                    } else if (v instanceof Spinner) 
	                    {
	                    	if (data instanceof Integer) 
	                    	{
	                            ((Spinner)v).setSelection((Integer) data);
	                        } else 
	                        {
	                        	continue;
	                        }
						} else 
						{
	                        throw new IllegalStateException(v.getClass().getName() + " is not a " +
	                                " view that can be bounds by this SimpleAdapter");
	                    }
	                }
	            }
	        }
	    }

	    /**
	     * 返回用于将数据绑定到视图的 {@link ViewBinder}.
	     *
	     * @return ViewBinder，如果绑定器不存在则返回 null.
	     *
	     * @see #setViewBinder(android.widget.SimpleAdapter.ViewBinder)
	     */
	    public ViewBinder getViewBinder() {
	        return mViewBinder;
	    }

	    /**
	     * 设置用于将数据绑定到视图的绑定器.
	     *
	     * @param viewBinder 用于将数据绑定到视图的绑定器.设置为 null，可以删除既存的绑定器.
	     *
	     * @see #getViewBinder()
	     */
	    public void setViewBinder(ViewBinder viewBinder) {
	        mViewBinder = viewBinder;
	    }

	    /**
	     * 由 bindView() 方法调用，用于为 ImageView 设置图像.只在
	     * ViewBinder 不存在或者既存的 ViewBinder 无法处理 ImageView 的绑定时才调用.
	     *
	     * 如果调用 {@link #setViewImage(ImageView, String)} 方法时提供的
	     * value 参数可以转换为整数类型，则会自动调用本方法.
	     *
	     * @param v 接收图像的 ImageView.
	     * @param value 从数据集获取到的值
	     *
	     * @see #setViewImage(ImageView, String)
	     */
	    public void setViewImage(ImageView v, int value) {
	        v.setImageResource(value);
	    }

	    /**
	     * 由 bindView() 方法调用，用于为 ImageView 设置图像.只在
	     * ViewBinder 不存在或者既存的 ViewBinder 无法处理 ImageView 的绑定时才调用.
	     *
	     * 本方法默认将 value 作为图像资源 ID 来对待；当 value
	     * 无法变换为整数类型时，才作为图像的 Uri 来使用.
	     *
	     * @param v 接收图像的 ImageView.
	     * @param value 从数据集获取到的值.
	     *
	     * @see #setViewImage(ImageView, int)
	     */
	    public void setViewImage(ImageView v, String value) {
	        try {
	            v.setImageResource(Integer.parseInt(value));
	        } catch (NumberFormatException nfe) {
	            v.setImageURI(Uri.parse(value));
	        }
	    }

	    /**
	     * 由 bindView() 方法调用，用于为 TextView 设置文本.只在
	     * ViewBinder 不存在或者既存的 ViewBinder 无法处理 TextView 的绑定时才调用.
	     *
	     * @param v 接收文本的 TextView.
	     * @param text 设置到 TextView 的文本.
	     */
	    public void setViewText(TextView v, String text) {
	        v.setText(text);
	    }

	    public Filter getFilter() {
	        if (mFilter == null) {
	            mFilter = new SimpleFilter();
	        }
	        return mFilter;
	    }

	    /**
	     * 该类用于 SimpleAdapter 的外部客户将适配器的值绑定到视图.
	     *
	     * 你可以使用此类将 SimpleAdapter 不支持的值绑定到视图，或者改变 SimpleAdapter
	     * 支持的视图的绑定方式.
	     *
	     * @see MultiLayoutSimpleAdapter#setViewImage(ImageView, int)
	     * @see MultiLayoutSimpleAdapter#setViewImage(ImageView, String)
	     * @see MultiLayoutSimpleAdapter#setViewText(TextView, String)
	     */
//	    public static interface ViewBinder {
	        /**
	         * 绑定指定的数据到指定的视图.
	         *
	         * 当使用 ViewBinder 绑定了数据时，必须返回真.如果该方法返回假，
	         * SimpleAdapter 会用自己的方式去绑定数据.
	         *
	         * @param view 要绑定数据的视图
	         * @param data 绑定用的数据
	         * @param textRepresentation 代表所提供的数据的安全字符串：
	         *        或者是 data.toString()，或者是空串，不能为空.
	         *
	         * @return 如果将数据绑定到了视图，返回真；否则返回假.
	         */
//	        boolean setViewValue(View view, Object data, String textRepresentation);
//	    }

	    /**
	     * <p>An array filters constrains the content of the array adapter with
	     * a prefix. Each item that does not start with the supplied prefix
	     * is removed from the list.</p>
	     */
	    private class SimpleFilter extends Filter {

	        @Override
	        protected FilterResults performFiltering(CharSequence prefix) {
	            FilterResults results = new FilterResults();

	            if (mUnfilteredData == null) {
	                mUnfilteredData = new ArrayList<Map<String, Object>>(mData);
	            }

	            if (prefix == null || prefix.length() == 0) {
	                ArrayList<Map<String, Object>> list = mUnfilteredData;
	                results.values = list;
	                results.count = list.size();
	            } else {
	                String prefixString = prefix.toString().toLowerCase();

	                ArrayList<Map<String, Object>> unfilteredValues = mUnfilteredData;
	                int count = unfilteredValues.size();

	                ArrayList<Map<String, Object>> newValues = new ArrayList<Map<String, Object>>(count);

	                for (int i = 0; i < count; i++) {
	                    Map<String, Object> h = unfilteredValues.get(i);
	                    if (h != null) {

	                        int len = mTo.length;

	                        for (int j=0; j<len; j++) {
	                            String str =  (String)h.get(mFrom[j]);

	                            String[] words = str.split(" ");
	                            int wordCount = words.length;

	                            for (int k = 0; k < wordCount; k++) {
	                                String word = words[k];

	                                if (word.toLowerCase().startsWith(prefixString)) {
	                                    newValues.add(h);
	                                    break;
	                                }
	                            }
	                        }
	                    }
	                }

	                results.values = newValues;
	                results.count = newValues.size();
	            }

	            return results;
	        }

	        @Override
	        protected void publishResults(CharSequence constraint, FilterResults results) {
	            //noinspection unchecked
	            mData = (ArrayList<Map<String, Object>>) results.values;
	            if (results.count > 0) {
	                notifyDataSetChanged();
	            } else {
	                notifyDataSetInvalidated();
	            }
	        }
	    }
	}

}
