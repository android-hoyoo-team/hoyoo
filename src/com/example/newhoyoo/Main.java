package com.example.newhoyoo;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.exp.demo.AchieveFragment;
import com.exp.demo.InviteFragment;
import com.exp.demo.MineFragment;
import com.exp.demo.NoUnionFragment;
import com.exp.demo.UnionFragment;
import com.exp.demo.ResideMenu;
import com.exp.demo.ResideMenuItem;
import com.exp.demo.SettingFragment;
import com.exp.demo.HooyoFragment;
import com.huyoo.application.AppContext;
import com.huyoo.bean.LoginInfo;
import com.huyoo.entity.ELevel;
import com.huyoo.entity.EPerson;
import com.huyoo.entity.EUnion;
import com.huyoo.global.Application;
import com.huyoo.global.DatabaseHelper;
import com.huyoo.service.ELevelService;
import com.huyoo.service.EPersonService;
import com.huyoo.service.EUnionService;
import com.ryg.expandable.ui.CustomActionbar;

/***
 * 主界面，通过ResizeMenu进行fragement的切换
 * @author XF
 *
 */
public class Main extends FragmentActivity implements OnClickListener {
	//侧滑栏
	private ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemInvite;
	private ResideMenuItem itemAssociation;
	private ResideMenuItem itemAchievements;
	private ResideMenuItem itemMyself;
	private ResideMenuItem itemSetting;
	private ResideMenuItem itemMessage;


	private HooyoFragment hooyofragment;
	private SettingFragment settingFragment;
	private UnionFragment unionFragment;
	public void setUnionFragment(UnionFragment unionFragment) {
		this.unionFragment = unionFragment;
	}
	public UnionFragment getUnionFragment(){
		return this.unionFragment;
	}
	private MineFragment mineFragment;
	private NoUnionFragment noUnionFragment;
	private InviteFragment invitefragment;
	private AchieveFragment achievefragment;

	private FragmentManager fragmentManager;

	public static String currentFragment;

	CustomActionbar actionbar;

	public EPersonService personService;
	public ELevelService levelService;
	EUnionService unionService;
	public EPerson person;
	public ELevel level;
	public EUnion union;
	public LoginInfo loginInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		actionbar = (CustomActionbar)findViewById(R.id.main_actionbar);
		ImageView image = (ImageView)findViewById(R.id.actionbar_left);
		image.setClickable(true);
		initView();
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		fragmentManager = getSupportFragmentManager();
		//setTabSelection(itemAssociation);
		loadData();
		setTabSelection(itemHome);
		//注册一个监听，以响应个人信息更改，以便重新加载修改好后的个人信息.
		DispatchEvent.addEventListener("personUpdateEvent", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				// TODO Auto-generated method stub
				loadData();
			}
		});
		//注册一个监听，以响应公会状态改变，以便重新加载修改好后的公会信息.
		DispatchEvent.addEventListener("unionStatusChanged", new IMethod<String>() {

			@Override
			public void excute(DEvent<String> event) {
				// TODO Auto-generated method stub
				loadData();
			}
		});

	}
	
	//初始化侧滑栏
	public void initView() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.back001);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		resideMenu.setScaleValue(0.6f);

		itemHome = new ResideMenuItem(this, R.drawable.c001, "主页");
		itemInvite = new ResideMenuItem(this, R.drawable.c002, "邀请");
		itemAssociation = new ResideMenuItem(this, R.drawable.c003, "公会");
		itemAchievements = new ResideMenuItem(this, R.drawable.c004, "成就");
		itemMyself = new ResideMenuItem(this, R.drawable.c005, "个人");
		itemSetting = new ResideMenuItem(this, R.drawable.c006, "设置");
		itemMessage = new ResideMenuItem(this,R.drawable.c006,"消息");

		resideMenu.addMenuItem(itemHome);
		resideMenu.addMenuItem(itemInvite);
		resideMenu.addMenuItem(itemAssociation);
		resideMenu.addMenuItem(itemAchievements);
		resideMenu.addMenuItem(itemMyself);
		resideMenu.addMenuItem(itemSetting);
//		resideMenu.addMenuItem(itemMessage);

		itemHome.setOnClickListener(this);
		itemInvite.setOnClickListener(this);
		itemAssociation.setOnClickListener(this);
		itemAchievements.setOnClickListener(this);
		itemMyself.setOnClickListener(this);
		itemSetting.setOnClickListener(this);
		itemMessage.setOnClickListener(this);
	}
	
	public void loadData()
	{
		loginInfo = Application.getLoginInfo();
		person = loginInfo.getPerson();
		union = loginInfo.getUnion();
		level = loginInfo.getLevel();
		AQuery aq = new AQuery(resideMenu);
		aq.id(R.id.head_imageview).image(person.getIcon());
		aq.id(R.id.name_textview).text(person.getName());
		aq.id(R.id.title_textview).text(Application.getLevelService().getELevelByID(person.getLevelId()).getName());
		if(union!=null&&"normal".equals(union.getStatus()))aq.id(R.id.union_textview).text(union.getName());
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {

		@Override
		public void openMenu() {
			resideMenu.startAnim(Main.this);
		}

		@Override
		public void closeMenu() {
		}
	};

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == itemHome) {
			setTabSelection(itemHome);
			resideMenu.closeMenu();
		} else if (arg0 == itemInvite) {
			setTabSelection(itemInvite);
			resideMenu.closeMenu();
		} else if (arg0 == itemAssociation) {
			setTabSelection(itemAssociation);
			resideMenu.closeMenu();
		} else if (arg0 == itemAchievements) {
			setTabSelection(itemAchievements);
			resideMenu.closeMenu();
		} else if (arg0 == itemMyself) {
			setTabSelection(itemMyself);
			resideMenu.closeMenu();
		} else if (arg0 == itemSetting) {
			setTabSelection(itemSetting);
			resideMenu.closeMenu();
		}else if(arg0 == itemMessage){
			setTabSelection(itemMessage);
			resideMenu.closeMenu();
		}
	}
	/**
	 * 给主界面添加fragment，并和ResizeMenu上的选项绑定，再根据不同的fragment设置actionbar
	 * @param view
	 */
	private <T> void setTabSelection(View view) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		if (view == itemHome) {
			this.currentFragment = "itemHome";
			actionbar.setTitleVisibility(View.GONE);
			actionbar.setButtonVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.bt_01_selector);
			itemHome.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (hooyofragment == null) {
				hooyofragment = new HooyoFragment();
				transaction.add(R.id.main_fragment, hooyofragment);
			} else {
				transaction.show(hooyofragment);
				if (invitefragment != null) {
					transaction.hide(invitefragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
				if (achievefragment != null) {
					transaction.hide(achievefragment);
				}
				if(unionFragment!=null){
					transaction.hide(unionFragment);
				}
				if(noUnionFragment!=null){
					transaction.hide(noUnionFragment);
				}
			}
		} else if (view == itemInvite) {
			this.currentFragment = "itemInvite";
			actionbar.setTitleVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.bt_08_selector);
			if(union!=null&&"normal".equals(union.getStatus())){
				actionbar.setButtonVisibility(View.VISIBLE);
				actionbar.setButton("发一个");
			}
			Button btn = (Button)findViewById(R.id.actionbar_right);
			final FragmentActivity target=this;
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					target.startActivity(new Intent(target,EditInvite.class));  
				}
			});
			itemInvite.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (invitefragment == null) {
				invitefragment = new InviteFragment();
				transaction.add(R.id.main_fragment, invitefragment);
			} else {
				transaction.show(invitefragment);
				if (hooyofragment != null) {
					transaction.hide(hooyofragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
				if (achievefragment != null) {
					transaction.hide(achievefragment);
				}
				if(unionFragment!=null){
					transaction.hide(unionFragment);
				}
				if(noUnionFragment!=null){
					transaction.hide(noUnionFragment);
				}
			}
		} else if (view == itemAssociation) {
			this.currentFragment = "itemAssociation";
			actionbar.setButtonVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.bt_15_selector);
			itemAssociation.setBackgroundResource(R.drawable.left_item_selected_bg);
			//如果有工会，而且公会的状态正常，当点击ResizeMenu上的公会时，进入UnionFragment;否则进入NoUnionFragment。
			if(union!=null&&"normal".equals(union.getStatus()) )
			{
				/*************************************/
				//这里要监听一下所在公会名称修改以后的事件
				actionbar.setTitleVisibility(View.VISIBLE);
				actionbar.setTitle(union.getName());
				DispatchEvent.addEventListener("personUpdateEvent", new IMethod<String>() {

					@Override
					public void excute(DEvent<String> event) {
						// TODO Auto-generated method stub
						actionbar.setTitle(union.getName());
					}
				});
				/****************************************/
				if (unionFragment == null) {
					unionFragment = new UnionFragment();
					transaction.add(R.id.main_fragment, unionFragment);
					if(noUnionFragment!=null)
					{
						transaction.remove(noUnionFragment);
					}
				} else {
					transaction.show(unionFragment);
					if (hooyofragment != null) {
						transaction.hide(hooyofragment);
					}
					if (invitefragment != null) {
						transaction.hide(invitefragment);
					}
					if (settingFragment != null) {
						transaction.hide(settingFragment);
					}
					if (achievefragment != null) {
						transaction.hide(achievefragment);
					}
					if (mineFragment != null) {
						transaction.hide(mineFragment);
					}
				}
			}
			else{
				actionbar.setTitleVisibility(View.GONE);
				if(noUnionFragment == null)
				{
					noUnionFragment = new NoUnionFragment();
					transaction.add(R.id.main_fragment, noUnionFragment);
					if(unionFragment!=null){
						transaction.remove(unionFragment);
					}
				}else
				{
					transaction.show(noUnionFragment);
					if (hooyofragment != null) {
						transaction.hide(hooyofragment);
					}
					if (invitefragment != null) {
						transaction.hide(invitefragment);
					}
					if (settingFragment != null) {
						transaction.hide(settingFragment);
					}
					if (achievefragment != null) {
						transaction.hide(achievefragment);
					}
					if (mineFragment != null) {
						transaction.hide(mineFragment);
					}
				}
			}
		} else if (view == itemAchievements) {
			this.currentFragment = "itemAchievements";
			actionbar.setTitleVisibility(View.GONE);
			actionbar.setButtonVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.d08_selector);
			itemAchievements.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (achievefragment == null) {
				achievefragment = new AchieveFragment();
				transaction.add(R.id.main_fragment, achievefragment);
			} else {
				transaction.show(achievefragment);
				if (hooyofragment != null) {
					transaction.hide(hooyofragment);
				}
				if (invitefragment != null) {
					transaction.hide(invitefragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				if(unionFragment!=null){
					transaction.hide(unionFragment);
				}
				if(noUnionFragment!=null){
					transaction.hide(noUnionFragment);
				}
			}
		} else if (view == itemMyself) {
			this.currentFragment = "itemMyself";
			actionbar.setTitleVisibility(View.GONE);
			actionbar.setButtonVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.bt_14_selector);
			itemMyself.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (mineFragment == null) {
				mineFragment = new MineFragment();
				transaction.add(R.id.main_fragment, mineFragment);
			} else {
				transaction.show(mineFragment);
				if (hooyofragment != null) {
					transaction.hide(hooyofragment);
				}
				if (invitefragment != null) {
					transaction.hide(invitefragment);
				}
				if (settingFragment != null) {
					transaction.hide(settingFragment);
				}
				if (achievefragment != null) {
					transaction.hide(achievefragment);
				}
				if (unionFragment != null) {
					transaction.hide(unionFragment);
				}
			}
		} else if (view == itemSetting) {
			this.currentFragment = "itemSetting";
			actionbar.setTitleVisibility(View.GONE);
			actionbar.setButtonVisibility(View.GONE);
			actionbar.setImageResource(R.drawable.bt_16_selector);
			itemSetting.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (settingFragment == null) {
				settingFragment = new SettingFragment();
				transaction.add(R.id.main_fragment, settingFragment);
			} else {
				transaction.show(settingFragment);
				if (hooyofragment != null) {
					transaction.hide(hooyofragment);
				}
				if (invitefragment != null) {
					transaction.hide(invitefragment);
				}
				if (mineFragment != null) {
					transaction.hide(mineFragment);
				}
				if (achievefragment != null) {
					transaction.hide(achievefragment);
				}
				if(unionFragment!=null){
					transaction.hide(unionFragment);
				}
				if(noUnionFragment!=null){
					transaction.hide(noUnionFragment);
				}
			}
		}
		transaction.commit();
	}

	private void clearSelection() {
		itemHome.setBackgroundResource(android.R.color.transparent);
		itemHome.setIcon(R.drawable.c001);

		itemInvite.setBackgroundResource(android.R.color.transparent);
		itemInvite.setIcon(R.drawable.c002);

		itemAssociation.setBackgroundResource(android.R.color.transparent);
		itemAssociation.setIcon(R.drawable.c003);

		itemAchievements.setBackgroundResource(android.R.color.transparent);
		itemAchievements.setIcon(R.drawable.c004);

		itemMyself.setBackgroundResource(android.R.color.transparent);
		itemMyself.setIcon(R.drawable.c005);

		itemSetting.setBackgroundResource(android.R.color.transparent);
		itemSetting.setIcon(R.drawable.c006);
	}
	/**
	 * 隐藏主页面中的特定fragment
	 * @param transaction
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (hooyofragment != null) {
			transaction.hide(hooyofragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}

		if (invitefragment != null) {
			transaction.hide(invitefragment);
		}
		if (mineFragment != null) {
			transaction.hide(mineFragment);
		}
		if (achievefragment != null) {
			transaction.hide(achievefragment);
		}
		if (unionFragment != null) {
			transaction.hide(unionFragment);
		}
		if (noUnionFragment != null) {
			transaction.hide(noUnionFragment);
		}
	}
	/**
	 *重写手机上的返回键。由于主页面是一级页面，当进入主页面(这里的主页面包括主页面的各个fragment)时，如果再按返回键，则相当于退出应用程序。
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		// TODO Auto-generated method stub  
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){  
			//需要处理  
			AlertDialog dialog = new AlertDialog.Builder(this).setTitle("提示").setMessage("您确定退出huyoo吗？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							intent.setClass(Main.this, LoginPhoneNum.class);
							Main.this.startActivity(intent);
							System.exit(0);
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					}).create();
			dialog.show();
			return true;  
		}  
		return false;
	}  
}
