package com.example.newhoyoo;

import java.util.Calendar;
import java.util.Date;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;

/**
 * 编辑邀请界面
 * @author HJL
 *
 */
public class EditInvite extends Activity implements OnTouchListener{
	private ImageButton leftImageButton;

	private EditText date_edittext;
	private EditText time_edittext;

	AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_invite);
		this.aq = new AQuery(this);
		leftImageButton=(ImageButton)findViewById(R.id.leftImageButton);
		final EditInvite target=this;
		leftImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				target.finish();
			}
		});

		this.aq.id(R.id.dateImageButton).clicked(this, "pickDate");
		this.aq.id(R.id.timeImageButton).clicked(this, "pickTime");
		date_edittext.setOnTouchListener(this);
		time_edittext.setOnTouchListener(this);
	}

	public void pickDate(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.date_picker_dialog, null);
		final DatePicker datePicker = (DatePicker)view.findViewById(R.id.date_picker);
		builder.setView(view);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		datePicker.init(cal.get(Calendar.YEAR),cal.get( Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
		builder.setTitle("请选择日期"); 
		builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 

			@Override 
			public void onClick(DialogInterface dialog, int which) { 
				Calendar c = Calendar.getInstance();
				c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
				StringBuffer sb = new StringBuffer(); 
				sb.append(String.format("%d-%02d-%02d",  
						datePicker.getYear(),  
						datePicker.getMonth() + 1, 
						datePicker.getDayOfMonth())); 
				date_edittext.setText(sb.toString());
				dialog.cancel(); 
			} 
		}); 
		Dialog dialog = builder.create();
		dialog.show();
	}

	public void pickTime(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.time_picker_dialog, null);
		final TimePicker timePicker = (TimePicker)view.findViewById(R.id.time_picker);
		timePicker.setIs24HourView(true);
		builder.setView(view);
		Time time = new Time("GMT+8");
		time.setToNow();
		timePicker.setCurrentHour(time.hour);
		timePicker.setCurrentMinute(time.minute);
		builder.setTitle("请选择时间"); 
		builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 

			@Override 
			public void onClick(DialogInterface dialog, int which) { 
				StringBuffer sb = new StringBuffer(); 
				sb.append(String.format("%02d:%02d",  
						timePicker.getCurrentHour(),  
						timePicker.getCurrentMinute())); 
				time_edittext.setText(sb.toString());
				dialog.cancel(); 
			} 
		}); 
		Dialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN )
		{
			switch (v.getId()) {
			case R.id.date_edittext:
				pickDate();
				break;
			case R.id.time_edittext:
				pickTime();
				break;
			}
		}
		return true;
	}
}
