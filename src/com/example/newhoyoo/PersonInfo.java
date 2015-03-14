package com.example.newhoyoo;

import java.text.SimpleDateFormat;
import java.util.Date;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.androidquery.AQuery;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.huyoo.service.ELevelService;
import com.ryg.expandable.ui.CustomActionbar;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class PersonInfo extends FragmentActivity {
	AQuery aq ;

	Button btStyle2;

	Animator currentAnimation;

	EPerson person;
	EUnion union;
	ELevel level;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq = new AQuery(this);
		setContentView(R.layout.person_info);
		
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.personinfo_actionbar);
		actionbar.setTitle("详细信息");
		actionbar.setButton("编辑");
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
		this.aq.id(R.id.actionbar_right).clicked(this, "edit");

		init();
		DispatchEvent.addEventListener("personUpdateEvent", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				init();
			}
		});
		
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
		this.aq.id(R.id.text_name).text(person.getName());
		this.aq.id(R.id.text_sex).text(person.getSex());
		this.aq.id(R.id.text_school).text(person.getDepartment());
		this.aq.id(R.id.text_birthday).text(new SimpleDateFormat("yyyy-MM-dd").format(new Date(person.getBirthday())));
		this.aq.id(R.id.text_phonenum).text(person.getPhoneNum());
		
		if(union!=null&&"normal".equals(union.getStatus())){
			this.aq.id(R.id.text_union).text(union.getName());
			this.aq.id(R.id.text_unionlvl).text(Application.getLevelService().getELevelByID(union.getLevelId()).getName());
			this.aq.id(R.id.text_unionrole).text((union.getChairmanId()==person.getId())?"会长":"会员");
		}
		this.aq.id(R.id.text_achievelvl).text(level.getName());
	}
	
	public void edit()
	{
		Intent intent = getIntent();
		intent.setClass(PersonInfo.this,EditPersonInfo.class);
		this.startActivity(intent);
	}
	public void back()
	{
		this.finish();
	}
}

