package com.example.newhoyoo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.androidquery.AQuery;
import com.huyoo.application.AppContext;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Achievement;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

/**
 * 编辑个人信息界面，用以修改个人信息.
 * @author XF
 *
 */
public class EditPersonInfo extends Activity implements View.OnTouchListener {
	AQuery aq ;
	Button btStyle2;
	Animator currentAnimation;
	EditText birthday_edittext;
	long birthday = 0l;
	EPerson person;
	EUnion union;
	ELevel level;
	int sexId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq = new AQuery(this);
		setContentView(R.layout.edit_personinfo);
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.editpersoninfo_actionbar);
		actionbar.setTitle("编辑信息");
		actionbar.setButton("完成");
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
		this.aq.id(R.id.actionbar_right).clicked(this, "edit");
		this.aq.id(R.id.imageView2).clicked(this, "changemypic");
		init();
		DispatchEvent.addEventListener("unionStatusChanged", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				// TODO Auto-generated method stub
				init();
			}
		});

	}

	public void init(){
		person = Application.getLoginInfo().getPerson();
		union = Application.getLoginInfo().getUnion();
		level = Application.getLoginInfo().getLevel();

		this.aq.id(R.id.head_imageview).image(person.getIcon());
		this.aq.id(R.id.name_edittext).text(person.getName());
		if("男".equals(person.getSex()))
		{
			this.aq.id(R.id.male_radio).checked(true);
			sexId = R.id.male_radio;
		}
		else
		{
			this.aq.id(R.id.female_radio).checked(true);
			sexId = R.id.female_radio;
		}
		this.aq.id(R.id.school_edittext).text(person.getDepartment());
		this.aq.id(R.id.birthday_edittext).text(new SimpleDateFormat("yyyy-MM-dd").format(new Date(person.getBirthday())));
		this.aq.id(R.id.phonenum_edittext).text(person.getPhoneNum());
		if(union!=null&&"normal".equals(union.getStatus())){
			this.aq.id(R.id.union_textview).text(union.getName());
			this.aq.id(R.id.unionlvl_textview).text(Application.getLevelService().getELevelByID(union.getLevelId()).getName());
			this.aq.id(R.id.union_role_textview).text(person.getId() == union.getChairmanId()?"会长":"会员");
		}
		this.aq.id(R.id.achievelvl_textview).text(level.getName());

		RadioGroup sex_radiogroup = (RadioGroup)findViewById(R.id.sex_radiogroup);
		sex_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				sexId = group.getCheckedRadioButtonId();
			}
		});

		birthday_edittext = (EditText)findViewById(R.id.birthday_edittext);
		birthday_edittext.setOnTouchListener(this);
		this.aq.id(R.id.change_header_imageview).clicked(this, "changeHeader");
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN && v.getId() == R.id.birthday_edittext)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = View.inflate(this, R.layout.date_picker_dialog, null);
			final DatePicker datePicker = (DatePicker)view.findViewById(R.id.date_picker);
			builder.setView(view);

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			datePicker.init(cal.get(Calendar.YEAR),cal.get( Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
			datePicker.setMaxDate(new Date().getTime());
			final int inType = birthday_edittext.getInputType(); 
			birthday_edittext.setInputType(InputType.TYPE_NULL); 
			birthday_edittext.onTouchEvent(event); 
			birthday_edittext.setInputType(inType); 
			birthday_edittext.setSelection(birthday_edittext.getText().length()); 

			builder.setTitle("请选择日期"); 
			builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() { 

				@Override 
				public void onClick(DialogInterface dialog, int which) { 
					Calendar c = Calendar.getInstance();
					c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
					birthday = c.getTime().getTime();
					StringBuffer sb = new StringBuffer(); 
					sb.append(String.format("%d-%02d-%02d",  
							datePicker.getYear(),  
							datePicker.getMonth() + 1, 
							datePicker.getDayOfMonth())); 
					birthday_edittext.setText(sb.toString());
					dialog.cancel(); 
				} 
			}); 
			Dialog dialog = builder.create();
			dialog.show();
		}

		return true;
	}

	public void edit()
	{
		new AlertDialog.Builder(this).setTitle("提示").setMessage("您确定保存更改吗？")
		.setNegativeButton("取消",new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//不变
			}
		})
		.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//保存修改
				boolean isChangeHeader = false;
				if(bitmap!=null){
					String path = null;
					try {
						path = AppContext.saveFile(bitmap, new Date().getTime()+".png");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					person.setIcon(path);
					isChangeHeader = true;
				}

				String name = aq.id(R.id.name_edittext).getText().toString().trim();
				String sex = (sexId == R.id.male_radio) ? "男" : "女";
				String department = aq.id(R.id.school_edittext).getText().toString();
				String phoneNum = aq.id(R.id.phonenum_edittext).getText().toString();
				person.setName(name);
				person.setSex(sex);
				person.setDepartment(department);
				if(birthday!=0l)person.setBirthday(birthday);
				person.setPhoneNum(phoneNum);
				Application.getLoginInfo().setPerson(person);
				long result =Application.getPersonService().updatePerson(person);
				if(result!=-1&&isChangeHeader){
					Achievement.uploadHeader();
				}
				DispatchEvent.dispatchEvent(new DEvent("personUpdateEvent","message"));
			}
		}).show();

	}


	public void changeHeader(){
		Intent intent= new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, 1);
	}
	Bitmap bitmap;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {    
			Uri uri = data.getData();    
			ContentResolver cr = this.getContentResolver();    
			try {    
				bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));    
				ImageView imageView = (ImageView) findViewById(R.id.head_imageview);    
				/* 将Bitmap设定到ImageView */    
				imageView.setImageBitmap(bitmap);    
			} catch (FileNotFoundException e) {    
				Log.e("Exception", e.getMessage(),e);    
			}    
		}    
		super.onActivityResult(requestCode, resultCode, data);    
	}
	public void back()
	{
		this.finish();
	}
	public void changemypic()
	{
		this.aq.id(R.id.imageView2).image(R.drawable.bt_17_press_01);
		//跳转更换头像界面
	}
}

