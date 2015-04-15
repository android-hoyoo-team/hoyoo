package com.example.newhoyoo;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.huyoo.bean.Result;
import com.huyoo.entity.EInvitation;
import com.huyoo.global.Application;
import com.huyoo.utils.DateUtil;

public class EditInvite extends Activity implements OnTouchListener{
	private ImageButton leftImageButton;
	private ImageButton issueButton;
	private ImageButton addImageButton;
	private ImageButton deleteImageButton;
	private ImageButton timeImageButton;
	private ImageButton dateImageButton;

	private EditText date_edittext;
	private EditText time_edittext;

	AQuery aq;
	private EditText address_edittext;
	private EditText num_edittext;
	private EditText telephoneText;
	private EditText inviteText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_invite);
		this.aq = new AQuery(this);
		Log.d("hjl", "publishInvitetion1");
		leftImageButton=(ImageButton)findViewById(R.id.leftImageButton);
		final EditInvite target=this;
		leftImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				target.finish();
			}
		});

		date_edittext = (EditText)findViewById(R.id.date_edittext);
		telephoneText = (EditText)findViewById(R.id.telephoneText);
		time_edittext = (EditText)findViewById(R.id.time_edittext);
		issueButton=(ImageButton)findViewById(R.id.issueButoon);
		addImageButton=(ImageButton)findViewById(R.id.addImageButton);
		deleteImageButton=(ImageButton)findViewById(R.id.deleteImageButton);
		timeImageButton=(ImageButton)findViewById(R.id.timeImageButton);
		address_edittext=(EditText)findViewById(R.id.address_edittext);
		num_edittext=(EditText)findViewById(R.id.num_edittext);
		inviteText=(EditText)findViewById(R.id.inviteText);
//		issueButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				publishInvitetion();
//			}
//		});
		this.aq.id(R.id.issueButoon).clicked(this, "publishInvitetionClick");
		this.aq.id(R.id.dateImageButton).clicked(this, "pickDate");
		this.aq.id(R.id.timeImageButton).clicked(this, "pickTime");
		date_edittext.setOnTouchListener(this);
		time_edittext.setOnTouchListener(this);
	}
	public void publishInvitetionClick()
	{
		Toast.makeText(this, publishInvitetion(),Toast.LENGTH_LONG).show();;
	}
	public String publishInvitetion()
	{
		Log.d("hjl", "publishInvitetion");
		EInvitation in=new EInvitation();
		String address = address_edittext.getText().toString();
		String _telephone = telephoneText.getText().toString();
		String _num = num_edittext.getText().toString();
		String content=inviteText.getText().toString();
		String message=null;
		if(content==null||content.trim().length()==0)
		{
			message="内容不能为空";
			return message;
		}
		if(_num==null)
		{
			message="不能为空";
			return message;
		}
		int num;
		try{
			num = Integer.parseInt(_num);
		}catch(Exception ex)
		{
			message="数字格式不正确";
			return message;
		}
		String _date = date_edittext.getText().toString();
		String _time = time_edittext.getText().toString();
		if(_date==null||_time==null)
		{
			message="时间不能为空";
			return message;
		}
		String _dateTime=_date.trim()+" "+_time.trim();
		Date dateTime = DateUtil.str2Date(_dateTime, "yyyy-MM-dd HH:mm");
		in.setPersonId(Application.getLoginInfo().getPerson().getId());
		in.setAddress(address);
		in.setMaxNum(num);
		in.setIssueTime(dateTime.getTime());
		in.setActivityTime(new Date().getTime());
		in.setContent(content);
		in.setIcons("[\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7CBBABD0E0FE4529BE7149E9A5DC58C4\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8C7A6E9EF7564477A071E89AD9444C47\","+
				"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/DFECC2460489423BBD7A9E11CAF5CE15\","
				+"\"http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/EF46280D7B8E4485A71A77FE7DC317A7\"]");
//		in.setId(id);
//		in.setActivityTime(activityTime);
		Result<EInvitation> res = Application.getInvitationService().publishInvitation(in);
		if(res.getStatus().equals("success"))
		{
			Toast.makeText(this, "发布成功",Toast.LENGTH_LONG).show();
			message="success";
			return message;
		}else
		{
			Toast.makeText(this, "发布失败",Toast.LENGTH_LONG).show();
			message="发布失败";
			return message;
		}
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
