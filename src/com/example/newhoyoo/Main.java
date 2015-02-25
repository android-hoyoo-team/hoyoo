package com.example.newhoyoo;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.exp.demo.AchieveFragment;
import com.exp.demo.InviteFragment;
import com.exp.demo.MineFragment;
import com.exp.demo.NeaerFragment;
import com.exp.demo.ResideMenu;
import com.exp.demo.ResideMenuItem;
import com.exp.demo.SettingFragment;
import com.exp.demo.hooyoFragment;

public class Main extends FragmentActivity implements OnClickListener {

	private ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemInvite;
	private ResideMenuItem itemAssociation;
	private ResideMenuItem itemAchievements;
	private ResideMenuItem itemMyself;
	private ResideMenuItem itemSetting;

	private hooyoFragment hooyofragment;
	private SettingFragment settingFragment;
	private NeaerFragment nearFragment;
	private MineFragment mineFragment;

	private InviteFragment invitefragment;
	private AchieveFragment achievefragment;

	private FragmentManager fragmentManager;

	ActionBar actionBar;
	TextView tx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		setActionBarLayout(R.layout.custom_actionbar);
	Resources  res = getBaseContext().getResources();
		Drawable d = res.getDrawable(R.drawable.red);
		actionBar = getActionBar();  
//		actionBar.setTitle("公会");
		actionBar.setDisplayHomeAsUpEnabled(true);//点击action的Home后，返回前一个activity
			actionBar.setBackgroundDrawable(d);  
		initView();
//		//Toast.makeText(getApplicationContext(),(actionBar.getSelectedTab()==null)+"", Toast.LENGTH_LONG).show();
		fragmentManager = getSupportFragmentManager();
		setTabSelection(itemHome);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			break;
			default:
				Toast.makeText(this, featureId+""+item,Toast.LENGTH_LONG).show();
		}
		return false;
	}
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem item)
	 {
		Toast.makeText(getApplicationContext(), item+"", Toast.LENGTH_LONG).show();
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
		return false;
	 }
		
	/**
	 * 
	 * @param layoutId
	 * 
	 * */
	public void setActionBarLayout( int layoutId ){
	     actionBar = getActionBar( );
		if( null != actionBar ){
			//actionBar.setDisplayShowHomeEnabled( true );
			actionBar.setDisplayShowCustomEnabled(true);
			LayoutInflater inflator = (LayoutInflater)   this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(layoutId, null);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			actionBar.setCustomView(v,layout);
			tx = (TextView)findViewById(R.id.actionbar_title);
		}
	}

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

		resideMenu.addMenuItem(itemHome);
		resideMenu.addMenuItem(itemInvite);
		resideMenu.addMenuItem(itemAssociation);
		resideMenu.addMenuItem(itemAchievements);
		resideMenu.addMenuItem(itemMyself);
		resideMenu.addMenuItem(itemSetting);

		itemHome.setOnClickListener(this);
		itemInvite.setOnClickListener(this);
		itemAssociation.setOnClickListener(this);
		itemAchievements.setOnClickListener(this);
		itemMyself.setOnClickListener(this);
		itemSetting.setOnClickListener(this);
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
		}
	}

	private void setTabSelection(View view) {
		setActionBarLayout(R.layout.custom_actionbar);
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		if (view == itemHome) {

			//actionBar.setSubtitle("主页"); 
			actionBar.setIcon(R.drawable.bt_01_nor);
//			actionBar.setCustomView(R.layout.null_actionbar);
			itemHome.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (hooyofragment == null) {
				hooyofragment = new hooyoFragment();
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
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		} else if (view == itemInvite) {

			actionBar.setIcon(R.drawable.bt_08_nor_01);
			actionBar.setCustomView(R.layout.yaoqing_actionbar);
			View customView = actionBar.getCustomView();
			TextView fayige=(TextView)findViewById(R.id.yaoqing_fayige);
			fayige.setClickable(true);
			final FragmentActivity target=this;
			fayige.setOnClickListener(new OnClickListener() {
				
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
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		} else if (view == itemAssociation) {

			actionBar.setIcon(R.drawable.bt_15_nor_01);
			tx.setText("TOWERS");
			itemAssociation.setBackgroundResource(R.drawable.left_item_selected_bg);
			if (nearFragment == null) {
				nearFragment = new NeaerFragment();
				transaction.add(R.id.main_fragment, nearFragment);
			} else {
				transaction.show(nearFragment);
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
		} else if (view == itemAchievements) {
			actionBar.setIcon(R.drawable.d08);
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
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		} else if (view == itemMyself) {

			actionBar.setIcon(R.drawable.bt_14_nor_01);
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
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		} else if (view == itemSetting) {

			actionBar.setIcon(R.drawable.bt_16_nor_01);
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
				if (nearFragment != null) {
					transaction.hide(nearFragment);
				}
			}
		}
		transaction.commit();
	}

	private void clearSelection() {
//		tx.setText("");
		
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
		if (nearFragment != null) {
			transaction.hide(nearFragment);
		}
	}

}
