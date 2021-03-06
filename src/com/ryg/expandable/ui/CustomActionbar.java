package com.ryg.expandable.ui;


import com.example.newhoyoo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 自定义actionbar
 * @author XF
 *
 */
public class CustomActionbar extends LinearLayout implements View.OnClickListener{

	private ImageView leftImage;

	private TextView centerText;

	private Button rightBtn;

	public CustomActionbar(Context context) {
		super(context);
	}
	public CustomActionbar(Context context,AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_actionbar_01, this);
		leftImage=(ImageView) findViewById(R.id.actionbar_left);
		centerText=(TextView)findViewById(R.id.actionbar_center);
		rightBtn = (Button)findViewById(R.id.actionbar_right);
	}
	/**
	 * 设置图片资源
	 */ 
	public void setImageResource(int resId) { 
		leftImage.setImageResource(resId); 
	}

	public void setTitle(String title)
	{
		centerText.setText(title);
	}

	public void setButton(String title)
	{
		rightBtn.setText(title);
	}
	
	public void setTitleVisibility(int visibility)
	{
		centerText.setVisibility(visibility);
	}
	public void setButtonVisibility(int visibility)
	{
		rightBtn.setVisibility(visibility);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
