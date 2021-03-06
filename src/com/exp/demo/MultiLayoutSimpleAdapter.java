﻿
/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exp.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.newhoyoo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 这是一个简单的适配器，可以将静态数据映射到XML文件中定义好的视图.
 * 你可以将 Maps 的 ArrayList 指定为用于列表的数据.ArrayList 中的每一项对应列表中的一行.
 * Maps 中包含用于一行的数据.你也可以指定 XML 文件，其中定义了用于显示行的视图，通过
 * Map 的关键字映射到指定的视图.
 * 绑定数据到视图分两个阶段.首先，如果 {@link android.widget.SimpleAdapter.ViewBinder} 是有效的，
 * 则调用 {@link ViewBinder#setViewValue(android.view.View, Object, String)} 方法.
 * 如果返回值为真，则执行绑定.如果返回值为假，则按以下顺序绑定视图：
 * <ul>
 * <li> 实现了 Checkable 的视图（例如 CheckBox），期望绑定值是布尔类型.
 * <li> TextView，期望绑定值是字符串类型，通过调用 {@link #setViewText(TextView, String)} 绑定.
 * <li> ImageView，期望绑定值是资源 ID 或者一个字符串，通过调用
 * {@link #setViewImage(ImageView, int)} 或 {@link #setViewImage(ImageView, String)}绑定.
 * </ul>
 * 如果没有合适的绑定发生，将会抛出 {@link IllegalStateException} 异常.
 * @author translate by 德罗德
 * @author convert by cnmahj
 */
public class MultiLayoutSimpleAdapter extends BaseAdapter implements Filterable {
    
    private class buttonViewHolder {
        ImageView touxiang,tupian1,tupian2,tupian3,tupian4;
        TextView name,faburiqi,neirong,riqi,dizhi,renshu;
        ProgressBar renshujindutiao;
        Button zhuanfa,pinglun,canjia;
        ImageButton dianzan;
    }
	
	
    private buttonViewHolder holder;
	
	
	
	
	private int[] mTo;
    private String[] mFrom;
    private ViewBinder mViewBinder;

    protected ArrayList<Map<String, Object>> mData;

    private int[] mResources;
    private int[] mDropDownResources;
    private LayoutInflater mInflater;

    private SimpleFilter mFilter;
    private ArrayList<Map<String, Object>> mUnfilteredData;
    
    //private scroll_view scroll=new scroll_view();

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
            int[] resources, String[] from, int[] to) {
        mData = data;
        mResources = mDropDownResources = resources;
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
    	
    	
        View v = null;

        if(position!=0)
        {
        if (convertView == null) {
            v = mInflater.inflate(resource, parent, false);
            
            holder = new buttonViewHolder();
            holder.touxiang = (ImageView)v.findViewById(mTo[0]);
            holder.name = (TextView)v.findViewById(mTo[1]);
            holder.faburiqi = (TextView)v.findViewById(mTo[2]);
            holder.neirong = (TextView)v.findViewById(mTo[3]);
            holder.riqi = (TextView)v.findViewById(mTo[4]);
            holder.dizhi = (TextView)v.findViewById(mTo[5]);
            holder.renshujindutiao = (ProgressBar)v.findViewById(mTo[6]);
            holder.renshu = (TextView)v.findViewById(mTo[7]);
            
            holder.tupian1 = (ImageView)v.findViewById(mTo[8]);
            holder.tupian2 = (ImageView)v.findViewById(mTo[9]);
            holder.tupian3 = (ImageView)v.findViewById(mTo[10]);
            holder.tupian4 = (ImageView)v.findViewById(mTo[11]);
            
            holder.zhuanfa = (Button)v.findViewById(mTo[12]);
            holder.pinglun = (Button)v.findViewById(mTo[13]);
            holder.canjia = (Button)v.findViewById(mTo[14]);
            holder.dianzan = (ImageButton)v.findViewById(mTo[15]);
            
            v.setTag(holder); 
        } else {
            v = convertView;
            holder = (buttonViewHolder) v.getTag();
        }
        
        bindView2(position, v);
        
        return v;
        }else if(position==0)
        {
            if (convertView == null) {
                v = mInflater.inflate(resource, parent, false);
            } else {
                v = convertView;
            }
        	//scroll.start(v);
        	
        	return v;
        }
		return v;
        
        
/*        if(position==0)
        {
        	scroll.start(v);
        }
        
        bindView2(position, v);
*/
 //       return v;
    }

    /**
     * <p>设置创建下拉列表视图的布局资源 ID.</p>
     *
     * @param resource 定义下拉列表视图的布局资源 ID.
     * @see #getDropDownView(int, android.view.View, android.view.ViewGroup)
     */
    public void setDropDownViewResource(int[] resources) {
        this.mDropDownResources = resources;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent, mResources[getItemViewType(position)]);
    }
    @Override
    public int getItemViewType(int position)
    {
    	if(position==0)
    	{
    		return 0;
    	}else
    	{
    		return 1;
    	}
    }
    
    //..................................尝试.........................................
    private void bindView2(int position,View view)
    {
        final Map<String, Object> dataSet = mData.get(position);
        if (dataSet != null) {
        	
            int touxiang2 = (Integer) dataSet.get(mFrom[0]);
            String name2 = (String)dataSet.get(mFrom[1]);
            String faburiqi2 = (String)dataSet.get(mFrom[2]);
            String neirong2 = (String)dataSet.get(mFrom[3]);
            String riqi2 = (String)dataSet.get(mFrom[4]);
            String dizhi2 = (String)dataSet.get(mFrom[5]);
        //    int renshujindutiao2 =(Integer) dataSet.get(mFrom[6]);
            String renshu2 = (String)dataSet.get(mFrom[7]);
            int tupian2_1 = (Integer) dataSet.get(mFrom[8]);
            int tupian2_2 = (Integer) dataSet.get(mFrom[9]);
            int tupian2_3 = (Integer) dataSet.get(mFrom[10]);
            int tupian2_4 = (Integer) dataSet.get(mFrom[11]);
      //      int zhuanfa2 = (Integer) dataSet.get(mFrom[12]);
      //      int pinglun2 = (Integer) dataSet.get(mFrom[13]);
      //      int canjia2 = (Integer) dataSet.get(mFrom[14]);
            int dianzan2 = (Integer) dataSet.get(mFrom[15]);            
            
            holder.touxiang.setImageDrawable(holder.touxiang.getResources().getDrawable(touxiang2));
            holder.name.setText(name2);
            holder.faburiqi.setText(faburiqi2);
            holder.neirong.setText(neirong2);
            holder.riqi.setText(riqi2);
            holder.dizhi.setText(dizhi2);
    //        holder.renshujindutiao.setBackgroundDrawable(holder.renshujindutiao.getResources().getDrawable(renshujindutiao2));
            holder.renshu.setText(renshu2);
            holder.tupian1.setImageDrawable(holder.tupian1.getResources().getDrawable(tupian2_1));
            holder.tupian2.setImageDrawable(holder.tupian2.getResources().getDrawable(tupian2_2));
            holder.tupian3.setImageDrawable(holder.tupian3.getResources().getDrawable(tupian2_3));
            holder.tupian4.setImageDrawable(holder.tupian4.getResources().getDrawable(tupian2_4));
 //           holder.zhuanfa.setBackgroundDrawable(holder.zhuanfa.getResources().getDrawable(zhuanfa2));
 //           holder.pinglun.setBackgroundDrawable(holder.pinglun.getResources().getDrawable(pinglun2));
 //           holder.canjia.setBackgroundDrawable(holder.canjia.getResources().getDrawable(canjia2));
            holder.dianzan.setImageDrawable(holder.dianzan.getResources().getDrawable(dianzan2));            
            
            holder.tupian1.setOnClickListener(new lvButtonListener(position));
            holder.tupian2.setOnClickListener(new lvButtonListener(position));
            holder.tupian3.setOnClickListener(new lvButtonListener(position));
            holder.tupian4.setOnClickListener(new lvButtonListener(position));
            holder.zhuanfa.setOnClickListener(new lvButtonListener(position));
            holder.pinglun.setOnClickListener(new lvButtonListener(position));
            holder.canjia.setOnClickListener(new lvButtonListener(position));
            holder.dianzan.setOnClickListener(new lvButtonListener(position));            
            
        }        
    }
    
    class lvButtonListener implements OnClickListener {
        private int position2;

        lvButtonListener(int pos) {
            position2 = pos;
        }
        
        @Override
        public void onClick(View v) {
            int vid=v.getId();
            if(vid==holder.tupian1.getId())
            {

            }else if(vid==holder.tupian2.getId())
            {

            }else if(vid==holder.tupian3.getId())
            {
 
            }else if(vid==holder.tupian1.getId())
            {

            }else if(vid==holder.zhuanfa.getId())
            {

            }else if(vid==holder.pinglun.getId())
            {
        
            }else if(vid==holder.canjia.getId())
            {
            	
            }else if(vid==holder.dianzan.getId())
            {
 //           	holder.dianzan.setImageResource(R.drawable.d20a);
            	Map<String, Object> mp = mData.get(position2);
                if(mp.containsKey("dianzan")){  
                    mp.remove("dianzan");  

                    mp.put("dianzan", R.drawable.d20a); 
                }  
                mData.remove(position2);//清除此行对应数据集中的数据  
                
                mData.add(position2, mp);//增加修改后的数据行  
                gengxin();//更新  
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
    public static interface ViewBinder {
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
        boolean setViewValue(View view, Object data, String textRepresentation);
    }

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
