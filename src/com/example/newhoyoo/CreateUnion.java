package com.example.newhoyoo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.entity.RUnionApplication;
import com.huyoo.entity.RUnionPerson;
import com.huyoo.global.Application;
import com.ryg.expandable.ui.CustomActionbar;
import com.ryg.expandable.ui.HorizontalListView;

public class CreateUnion extends Activity{
	AQuery aq ;
	public HashMap<String, HashMap<String,Object>> selectedMap;
	public String[] type = {"综合类","休闲类","竞技类","娱乐类"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_union);
		this.aq = new AQuery(this);
		CustomActionbar actionbar = (CustomActionbar)findViewById(R.id.create_union_actionbar);
		actionbar.setImageResource(R.drawable.back_image_button);
		actionbar.setTitle("创建公会");
		actionbar.setButtonVisibility(View.GONE);
		this.aq.id(R.id.actionbar_left).clicked(this, "back");
		this.aq.id(R.id.add_logo_imagebutton).clicked(this, "addLogo");
		ArrayAdapter<String> adapter  = new ArrayAdapter<>(this, R.layout.item_union_type,type);
		this.aq.id(R.id.union_type_spinner).adapter(adapter);

		this.aq.id(R.id.add_friends_imageview).clicked(this, "addFriends");
		this.selectedMap = new HashMap<String, HashMap<String,Object>>();
		this.aq.id(R.id.notice_textview).clicked(this, "notice");
		this.aq.id(R.id.has_read_checkbox).clicked(this, "hasRead");
		this.aq.id(R.id.apply_button).clicked(this, "apply");
		EditText union_name_edittext = (EditText)findViewById(R.id.union_name_edittext);
		union_name_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					verify();
				}
			}
		});

		Spinner spinner = (Spinner)findViewById(R.id.union_type_spinner);
		spinner.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				lostFocus();
				aq.id(R.id.click_spinner_imageview).image(R.drawable.bt_29_press);
				return false;
			}
		});
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				aq.id(R.id.click_spinner_imageview).image(R.drawable.bt_29_nor);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				aq.id(R.id.click_spinner_imageview).image(R.drawable.bt_29_nor);
			}
		});
	}

	public void apply(){
		//如果申请公会表中已经存在了该用户的申请，且申请还未批复，或者申请已批复，即已拥有公会，则不能提交申请
		Toast.makeText(getApplicationContext(),this.aq.id(R.id.union_type_spinner).getSelectedItem().toString(), 1).show();

		//提交申请后，向EUnion添加一条数据
		EUnion union = new EUnion();
		union.setChairmanId(Application.getLoginInfo().getPerson().getId());
		union.setIcon("");
		union.setName(this.aq.id(R.id.union_name_edittext).getText().toString());
		union.setStatus("approvalling");
		union.setTime(new Date().getTime());
		union.setType(this.aq.id(R.id.union_type_spinner).getSelectedItem().toString());

		//保存并获取id
		int unionId = Application.getUnionService().saveUnion(union);

		//向RUnionPerson中插入数据
		List<RUnionPerson> ups = new ArrayList<RUnionPerson>();
		Iterator<String> it = this.selectedMap.keySet().iterator();
		while (it.hasNext()) {
			RUnionPerson up = new RUnionPerson();
			int id = Integer.parseInt(it.next());
			up.setPersonId(id);
			up.setUnionId(unionId);
			up.setStatus("approvalling");
			up.setTime(new Date().getTime());
			ups.add(up);
		}
		Application.getUnionService().saveUnionPerson(ups);
		Intent intent = new Intent();
		intent.setClass(this, FinishCreateUnion.class);
		startActivity(intent);
	}
	public void hasRead(){
		lostFocus();
		verify();
	}
	//验证公会名称
	public boolean verifyUnionName(){
		if("".equals(this.aq.id(R.id.union_name_edittext).getText().toString().trim())){
			this.aq.id(R.id.name_verify_textview).text("公会名不能为空!");
		}else{
			this.aq.id(R.id.name_verify_textview).text("");
			return true;
		}
		return false;
	}

	//验证邀请的好友人数，至少为5人
	public boolean verifyFriends(){
		if(selectedMap !=null && selectedMap.size()>4)return true;
		return false;
	} 

	//验证是否阅读《建立工会须知》
	public boolean verifyNotice(){
		return this.aq.id(R.id.has_read_checkbox).isChecked();
	}
	public boolean verify(){
		if(verifyUnionName()&&verifyFriends()&&verifyNotice()){
			this.aq.id(R.id.apply_button).enabled(true);
			this.aq.id(R.id.apply_button).background(R.drawable.background_red_effect);
			return true;
		}else{
			this.aq.id(R.id.apply_button).enabled(false).background(R.color.Grey);
			return false;
		}
	}
	public void notice(){
		lostFocus();
		ScrollView view = new ScrollView(this);
		view.setLayoutParams(new LayoutParams(  
				LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT)); 

		TextView  notice = new TextView(this);//自定义一个布局文件  
		notice.setText(Application.getUnionService().getApplyUnionNotice());
		view.addView(notice);
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("建立工会须知").setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void addFriends(){
		lostFocus();
		List<EPerson> friends = Application.getPersonService().getFriends(Application.getLoginInfo().getPerson().getId());
		LinearLayout linearLayoutMain = new LinearLayout(this);//自定义一个布局文件  
		linearLayoutMain.setLayoutParams(new LayoutParams(  
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));  
		ListView listView = new ListView(this);
		final List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();//建立一个数组存储listview上显示的数据  
		for (int i = 0; i < friends.size(); i++) {//initData为一个list类型的数据源  
			HashMap<String, Object> map = new HashMap<String, Object>();  
			EPerson person = friends.get(i);
			map.put("id", person.getId());
			map.put("icon", person.getIcon()); 
			map.put("name",person.getName());
			map.put("checked", isInSelectedMap(person.getId()));
			map.put("isChecked",isInSelectedMap(person.getId())?R.drawable.icon_02:R.drawable.icon_03);
			EUnion union =  Application.getUnionService().getEUnionByID(person.getUnionId());
			map.put("union",union!=null&&"normal".equals(union.getStatus())?union.getName():"");
			list.add(map);  
		}  
		CustomListViewAdapter adapter = new CustomListViewAdapter(this, list, R.layout.item_friends_listview, 
				new String[]{"icon","name","union","isChecked","checked"},
				new int[]{R.id.icon_imageview,R.id.name_textview,R.id.union_textview,R.id.isselected_imageview,R.id.isselected_checkbox}, 
				new String[]{"image","text","text","imageLocal","check"});
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AQuery aq = new AQuery(view);
				if(aq.id(R.id.isselected_checkbox).isChecked()){
					aq.id(R.id.isselected_imageview).image(R.drawable.icon_03);
					aq.id(R.id.isselected_checkbox).checked(false);
					selectedMap.remove(list.get(parent.getPositionForView(view)).get("id").toString()); //.remove(list.get(parent.getPositionForView(view)));
				}else{
					//如果该好友已经有公会，则不能选中，并弹出提示框
					int personId =Integer.parseInt(list.get(parent.getPositionForView(view)).get("id").toString());
					EPerson person = Application.getPersonService().getEPersonById(personId);
					EUnion union = Application.getUnionService().getEUnionByID(person.getUnionId());
					if(union!=null&&"normal".equals(union.getStatus())){
						Toast.makeText(getApplicationContext(), "该好友已经有公会", Toast.LENGTH_LONG).show();
					}else{
						aq.id(R.id.isselected_imageview).image(R.drawable.icon_02);
						aq.id(R.id.isselected_checkbox).checked(true);
						selectedMap.put(list.get(parent.getPositionForView(view)).get("id")+"",list.get(parent.getPositionForView(view)));
					}
				}
			}
		});
		linearLayoutMain.addView(listView);
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setTitle("请选择好友：")
		.setView(linearLayoutMain)
		.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				HorizontalListView friendsListView = (HorizontalListView)findViewById(R.id.friends_horizontallistview);
				if(selectedMap == null || selectedMap.size() == 0){
					friendsListView.setVisibility(View.GONE);
				}
				else{
					List<HashMap<String, Object>> selectedList = new ArrayList<HashMap<String,Object>>();
					for(HashMap<String, Object> map:selectedMap.values()){
						selectedList.add(map);
					}
					LayoutParams params = friendsListView.getLayoutParams();
					params.width = getResources().getDimensionPixelSize(R.dimen.friends_item_width)*selectedList.size();
					CustomListViewAdapter adapter = new CustomListViewAdapter(CreateUnion.this, selectedList, R.layout.item_friends_list, new String[]{"icon"}, new int[]{R.id.friend_imageview}, new String[]{"image"});
					friendsListView.setAdapter(adapter);
				}
				dialog.cancel();
				verify();
			}
		}).create();
		dialog.show();

	}
	public boolean isInSelectedMap(int id){
		if(this.selectedMap == null || this.selectedMap.size()==0)return false;
		if(this.selectedMap.containsKey(id+""))return true;
		return false;
	}
	public void back(){
		this.finish();
	}
	public void addLogo(){
		lostFocus();
		Intent intent= new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, 1);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {    
			Uri uri = data.getData();    
			ContentResolver cr = this.getContentResolver();    
			try {    
				Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));    
				ImageView imageView = (ImageView) findViewById(R.id.union_icon_imageview);    
				/* 将Bitmap设定到ImageView */    
				imageView.setImageBitmap(bitmap);    
			} catch (FileNotFoundException e) {    
				Log.e("Exception", e.getMessage(),e);    
			}    
		}    
		super.onActivityResult(requestCode, resultCode, data);    
	}
	public void lostFocus(){
		EditText union_name_edittext = (EditText)findViewById(R.id.union_name_edittext);
		InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(union_name_edittext.getWindowToken(), 0);
		union_name_edittext.clearFocus();
	}

}
