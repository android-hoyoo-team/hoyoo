package com.exp.demo;

import java.util.HashMap;

import com.androidquery.AQuery;
import com.example.newhoyoo.*;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.service.ELevelService;
import com.huyoo.service.EPersonService;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MineFragment extends Fragment {
	SeekBar seekbar1;
	SeekBar seekbar2;
	ImageView head_imageview;
	TextView name_textview;
	TextView sex_textview;
	TextView title_textview;
	TextView union_textview;
	TextView union_position_textview;
	TextView attention_textview;
	TextView fans_textview;
	TextView achievement_textview;
	TextView achievement_progress_textview;
	TextView vp_textview;
	ImageButton invitation_imagebutton;
	ImageButton response_imagebutton;
	ImageButton comment_imagebutton;
	ImageButton good_imagebutton;
	ImageButton letter_imagebutton;

	AQuery aq;
	EPersonService personService;
	EPerson person;
	ELevel level;
	EUnion union;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mine, container,
				false);
		//this.aq = new AQuery(this.getActivity(), rootView);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.aq = new AQuery(view);
		Main main = (Main) getActivity();
		person = main.person;
		level = main.level;
		union = main.union;
		personService = main.personService;
		
		String url = "http://d.pcs.baidu.com/thumbnail/3b290259706e6121f86758c54f7472c6?fid=2151049481-250528-285158885884961&time=1425362400&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-Y8L6Kf7jcXKYxc4H2Y1lUYV%2FeNo%3D&rt=sh&expires=2h&r=465472984&sharesign=unknown&size=c710_u500&quality=100";
		this.aq.id(R.id.head_imageview).image(url,true,true,0,R.drawable.union_icon_01);
		name_textview = (TextView)(view.findViewById(R.id.name_textview));
		name_textview.setText(person.getName());
		sex_textview = (TextView)(view.findViewById(R.id.sex_textview));
		sex_textview.setText(person.getSex().equals("男")?"♂":"♀");
		title_textview = (TextView)(view.findViewById(R.id.title_textview));
		title_textview.setText(level.getName());
		union_textview = (TextView)(view.findViewById(R.id.union_textview));
		union_textview.setText(union.getName());
		union_position_textview = (TextView) (view.findViewById(R.id.union_position_textview));
		union_position_textview.setText((union.getChairmanId() == person.getId()) ? "会长" : "会员");
		attention_textview = (TextView)(view.findViewById(R.id.attention_textview));
		attention_textview.setText(personService.getFocusCount(person.getId())+"");
		fans_textview = (TextView)(view.findViewById(R.id.fans_textview));
		fans_textview.setText(personService.getFansCount(person.getId())+"");
		achievement_textview = (TextView)(view.findViewById(R.id.achievement_textview));
		achievement_textview.setText(person.getCurrentExp()+"");
		seekbar1 = (SeekBar) getActivity().findViewById(R.id.seekBar1);
		seekbar1.setEnabled(false);
		seekbar1.setMax(level.getUpgradeExp());
		seekbar1.setProgress(person.getCurrentExp());
		achievement_progress_textview = (TextView)(view.findViewById(R.id.achievement_progress_textview));
		achievement_progress_textview.setText(person.getCurrentExp()+"/"+level.getUpgradeExp());
		seekbar2 = (SeekBar) getActivity().findViewById(R.id.seekBar2);
		seekbar2.setEnabled(false);
		seekbar2.setMax(150);
		seekbar2.setProgress(person.getVp());
		vp_textview = (TextView)(view.findViewById(R.id.vp_textview));
		vp_textview.setText(person.getVp()+"/"+150);
		
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

		this.aq.id(R.id.focus).clicked(this, "skiptofocus");
		this.aq.id(R.id.fans).clicked(this, "skiptofans");
		this.aq.id(R.id.achieve).clicked(this, "skiptoachieve");
		
		this.aq.id(R.id.skip_to_detail_imageview).image(R.drawable.skip_to_detail_image);
		this.aq.id(R.id.skip_to_detail_imageview).clicked(this, "skiptodetail");
	}

	public void skiptodetail() {
		Intent intent = new Intent();
		intent.setClass(getActivity(), personInfo.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("person", person);
		bundle.putSerializable("union", union);
		bundle.putSerializable("level", level);
		intent.putExtras(bundle);
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
