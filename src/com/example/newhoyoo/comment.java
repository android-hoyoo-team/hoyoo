package com.example.newhoyoo;

import com.androidquery.AQuery;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class comment extends Activity {
	AQuery aq ;

	Button btStyle2;

	Animator currentAnimation;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.aq=new AQuery(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.commentlayout);

		this.aq.id(R.id.imageButton1).background(R.drawable.backbuttoneffect);
		this.aq.id(R.id.imageButton1).clicked(this, "back");

		this.aq.id(R.id.button1).background(R.drawable.buttoneffect_red);
		this.aq.id(R.id.button1).clicked(this, "edit");
		/*
    	this.aq.id(R.id.changeMyPic).background(R.drawable.layerbuttoneffect);
    	this.aq.id(R.id.changeMyPic).clicked(this, "changemypic");

    	this.aq.id(R.id.text_name).text("liuzheng");
    	this.aq.id(R.id.text_sex).text("male");
    	this.aq.id(R.id.text_school).text("digitalmedia");
    	this.aq.id(R.id.text_birthday).text("XXXX");
    	this.aq.id(R.id.text_phonenum).text("XXXXXXXXXX");

    	this.aq.id(R.id.text_union).text("singingsong");
    	this.aq.id(R.id.text_unionlvl).text("99");
    	this.aq.id(R.id.text_unionrole).text("boss");
    	this.aq.id(R.id.text_achievelvl).text("top");
		 */
	}

	public void edit()
	{

	}
	public void back()
	{

	}
	public void changemypic()
	{

	}
}

