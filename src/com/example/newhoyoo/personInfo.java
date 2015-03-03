package com.example.newhoyoo;

import java.util.Date;

import com.androidquery.AQuery;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.service.ELevelService;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class personInfo extends Activity {
	AQuery aq ;

	Button btStyle2;

	Animator currentAnimation;
	
	ELevelService levelService;
	EPerson person;

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

		EPerson person = (EPerson)(getIntent().getSerializableExtra("person"));
		EUnion union = (EUnion)(getIntent().getSerializableExtra("union"));
		ELevel level = (ELevel)(getIntent().getSerializableExtra("level"));
		levelService = new ELevelService();
		this.aq.id(R.id.text_name).text(person.getName());
		this.aq.id(R.id.text_sex).text(person.getSex());
		this.aq.id(R.id.text_school).text(person.getSchool());
		this.aq.id(R.id.text_birthday).text(new Date(person.getBirthday()).toString());
		this.aq.id(R.id.text_phonenum).text(person.getPhoneNum());

		this.aq.id(R.id.text_union).text(union.getName());
		this.aq.id(R.id.text_unionlvl).text(levelService.getELevelByID(union.getLevelId())+"");
		this.aq.id(R.id.text_unionrole).text((union.getChairmanId()==person.getId())?"会长":"会员");
		this.aq.id(R.id.text_achievelvl).text(level.getName());
	}

	public void edit()
	{
		Intent intent = getIntent();
		intent.setClass(personInfo.this,editPersonInfo.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("person", person);
		this.startActivity(intent);
	}
	public void back()
	{
		this.finish();
	}
}

