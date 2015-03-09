package com.example.newhoyoo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;

import com.androidquery.AQuery;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;

import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
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

		this.aq.id(R.id.union_textview).text(union.getName());
		this.aq.id(R.id.unionlvl_textview).text(Application.getLevelService().getELevelByID(union.getLevelId()).getName());
		this.aq.id(R.id.union_role_textview).text(person.getId() == union.getChairmanId()?"会长":"会员");
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
				DispatchEvent.dispatchEvent(new DEvent("personUpdateEvent","message"));
			}
		}).show();

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

