package com.example.newhoyoo;

import java.util.Calendar;
import java.util.Date;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditInvite extends Activity{
	private ImageButton leftImageButton;
	private Button issueButton;
	private ImageButton addImageButton;
	private ImageButton deleteImageButton;
	private ImageButton timeImageButton;
	private ImageButton dateImageButton;
	
	private EditText date_editview;
	
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
		
		date_editview = (EditText)findViewById(R.id.date_editview);
		issueButton=(Button)findViewById(R.id.issueButoon);
		addImageButton=(ImageButton)findViewById(R.id.addImageButton);
		deleteImageButton=(ImageButton)findViewById(R.id.deleteImageButton);
		timeImageButton=(ImageButton)findViewById(R.id.timeImageButton);
		//dateImageButton=(ImageButton)findViewById(R.id.dateImageButton);
		this.aq.id(R.id.dateImageButton).clicked(this, "pickDate");
	}
	
	public void pickDate(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.date_picker_dialog, null);
		final DatePicker datePicker = (DatePicker)view.findViewById(R.id.date_picker);
		builder.setView(view);

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		datePicker.init(cal.get(Calendar.YEAR),cal.get( Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
		datePicker.setMaxDate(new Date().getTime());

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
				date_editview.setText(sb.toString());
				dialog.cancel(); 
			} 
		}); 
		Dialog dialog = builder.create();
		dialog.show();
	}

}
