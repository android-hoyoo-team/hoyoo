package com.example.newhoyoo;

import com.androidquery.AQuery;

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
    	
    	this.aq.id(R.id.text_name).text("莫小千");
    	this.aq.id(R.id.text_sex).text("女");
    	this.aq.id(R.id.text_school).text("南方科技大学");
    	this.aq.id(R.id.text_birthday).text("2000年1月1日");
    	this.aq.id(R.id.text_phonenum).text("18888888888");
    	
    	this.aq.id(R.id.text_union).text("TOWERS");
    	this.aq.id(R.id.text_unionlvl).text("10");
    	this.aq.id(R.id.text_unionrole).text("会长");
    	this.aq.id(R.id.text_achievelvl).text("top");
    }
    
    public void edit()
    {
    	Intent intent = getIntent();
    	intent.setClass(personInfo.this,editPersonInfo.class);
    	this.startActivity(intent);
    }
    public void back()
    {
    	this.finish();
    }
}

