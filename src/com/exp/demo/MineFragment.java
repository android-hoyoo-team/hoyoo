package com.exp.demo;

import java.util.HashMap;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.androidquery.AQuery;
import com.example.newhoyoo.*;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.huyoo.service.ELevelService;
import com.huyoo.service.EPersonService;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MineFragment extends Fragment {
	SeekBar seekbar1;
	SeekBar seekbar2;

	AQuery aq;
	EPersonService personService;
	EPerson person;
	ELevel level;
	EUnion union;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mine, container,
				false);
		DispatchEvent.addEventListener("personUpdateEvent", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				init();
			}
		});
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.aq = new AQuery(view);
		init();
		this.aq.id(R.id.focus).clicked(this, "skiptofocus");
		this.aq.id(R.id.fans).clicked(this, "skiptofans");
		this.aq.id(R.id.achieve).clicked(this, "skiptoachieve");

		this.aq.id(R.id.skip_to_detail_imageview).image(R.drawable.skip_to_detail_image);
		this.aq.id(R.id.skip_to_detail_imageview).clicked(this, "skiptodetail");
	}

	public void init(){
		person = Application.getLoginInfo().getPerson();
		level = Application.getLoginInfo().getLevel();
		union = Application.getLoginInfo().getUnion();
		
		this.aq.id(R.id.head_imageview).image(person.getIcon());
		this.aq.id(R.id.name_textview).text(person.getName());
		if("男".equals(person.getSex()))
			this.aq.id(R.id.sex_textview).text("♂").textColorId(R.color.blue);
		else
			this.aq.id(R.id.sex_textview).text("♀").textColorId(R.color.red);
		this.aq.id(R.id.title_textview).text(level.getName());;
		this.aq.id(R.id.union_textview).text(union.getName());
		this.aq.id(R.id.union_position_textview).text((union.getChairmanId() == person.getId()) ? "会长" : "会员");
		this.aq.id(R.id.attention_textview).text(Application.getPersonService().getFocusCount(person.getId())+"");
		this.aq.id(R.id.fans_textview).text(Application.getPersonService().getFansCount(person.getId())+"");
		this.aq.id(R.id.achievement_textview).text(person.getCurrentExp()+"");
		this.aq.id(R.id.achievement_progress_textview).text(person.getCurrentExp()+"/"+level.getUpgradeExp());
		this.aq.id(R.id.vp_textview).text(person.getVp()+"/"+150);

		seekbar1 = (SeekBar) getActivity().findViewById(R.id.seekBar1);
		seekbar1.setEnabled(false);
		seekbar1.setMax(level.getUpgradeExp());
		seekbar1.setProgress(person.getCurrentExp());
		seekbar2 = (SeekBar) getActivity().findViewById(R.id.seekBar2);
		seekbar2.setEnabled(false);
		seekbar2.setMax(150);
		seekbar2.setProgress(person.getVp());

		this.aq.id(R.id.invitation_imagebutton).background(R.drawable.image1);
		this.aq.id(R.id.invitation_imagebutton).clicked(this, "myinvited");
		this.aq.id(R.id.response_imagebutton).background(R.drawable.image2);
		this.aq.id(R.id.response_imagebutton).clicked(this, "respond");
		this.aq.id(R.id.comment_imagebutton).background(R.drawable.image3);
		this.aq.id(R.id.comment_imagebutton).clicked(this, "comment");
		this.aq.id(R.id.good_imagebutton).background(R.drawable.image4);
		this.aq.id(R.id.good_imagebutton).clicked(this, "good");
		this.aq.id(R.id.letter_imagebutton).background(R.drawable.image5);
		this.aq.id(R.id.letter_imagebutton).clicked(this, "letter");
	}
	public void skiptodetail() {
		Intent intent = new Intent();
		intent.setClass(getActivity(), PersonInfo.class);
		getActivity().startActivity(intent);
	}

	public void skiptofocus() {
		this.aq.id(R.id.focus).background(R.drawable.buttoneffect);
		Toast.makeText(
				getActivity().getApplicationContext(),
				"你选择了第" + 5 + "个Item，itemTitle的值是：" + 5 + "itemContent的值是:" + 5,
				Toast.LENGTH_SHORT).show();
	}

	public void skiptofans() {
		this.aq.id(R.id.fans).background(R.drawable.buttoneffect);
		Toast.makeText(
				getActivity().getApplicationContext(),
				"你选择了第" + 5 + "个Item，itemTitle的值是：" + 5 + "itemContent的值是:" + 5,
				Toast.LENGTH_SHORT).show();
	}

	public void skiptoachieve() {
		this.aq.id(R.id.achieve).background(R.drawable.buttoneffect);
		Toast.makeText(
				getActivity().getApplicationContext(),
				"你选择了第" + 5 + "个Item，itemTitle的值是：" + 5 + "itemContent的值是:" + 5,
				Toast.LENGTH_SHORT).show();
	}

	public void myinvited()// 返回主界面
	{
	}

	public void respond()// 返回主界面
	{

	}

	public void comment()// 返回主界面
	{

	}

	public void good()// 返回主界面
	{

	}

	public void letter()// 返回主界面
	{

	}
}
