package com.example.newhoyoo;

import java.util.Date;

import com.androidquery.AQuery;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.huyoo.service.ELevelService;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class PersonInfo extends Activity {
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.person_info);

		this.aq.id(R.id.imageButton_1).background(R.drawable.backbuttoneffect);
		this.aq.id(R.id.imageButton_1).clicked(this, "back");

		this.aq.id(R.id.button1).background(R.drawable.buttoneffect_red);
		this.aq.id(R.id.button1).clicked(this, "edit");

		person = Application.getLoginInfo().getPerson();
		union = Application.getLoginInfo().getUnion();
		level = Application.getLoginInfo().getLevel();
		this.aq.id(R.id.text_name).text(person.getName());
		this.aq.id(R.id.text_sex).text(person.getSex());
		this.aq.id(R.id.text_school).text(person.getSchool());
		this.aq.id(R.id.text_birthday).text(new Date(person.getBirthday()).toString());
		this.aq.id(R.id.text_phonenum).text(person.getPhoneNum());

		this.aq.id(R.id.text_union).text(union.getName());
		this.aq.id(R.id.text_unionlvl).text(Application.getLevelService().getELevelByID(union.getLevelId())+"");
		this.aq.id(R.id.text_unionrole).text((union.getChairmanId()==person.getId())?"会长":"会员");
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

