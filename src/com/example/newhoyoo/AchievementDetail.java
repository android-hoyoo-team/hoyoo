package com.example.newhoyoo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.example.newhoyoo.adapter.CustomListViewAdapter;
import com.huyoo.entity.EAchievement;
import com.huyoo.global.Application;
import com.huyoo.utils.Utils;
import com.ryg.expandable.ui.CustomActionbar;
import com.ryg.expandable.ui.HorizontalListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowId;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class AchievementDetail extends Activity {
	AQuery aq;
	HorizontalListView relativeListView;
	CustomListViewAdapter mAdapter;
	CustomActionbar actionbar;
	int id = 0;
	EAchievement achievement;
	WebView achievementDetail;
	WebView tips;
	AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.achievement_detail);

		actionbar = (CustomActionbar) findViewById(R.id.achievement_detail_actionbar);
		actionbar.setTitle("成就详情");
		actionbar.setButtonVisibility(View.GONE);

		Intent intent = getIntent();
		id = Integer.parseInt(intent.getStringExtra("id").toString());
		aq = new AQuery(this);
		achievement = Application.getAchievementService().getEAchievementById(
				id);
		aq.id(R.id.achievement_icon).image(achievement.getIcon());// 公会图片
		loadAchievementDetail(achievement);// 公会详情
		aq.id(R.id.achievement_exp_icon)
				.image(AchievementDialog.getExpIcon(achievement.getExp()));
		// loadExpIcon(achievement.getExp());//经验图片
		loadTips("", "2015.9.6", "2016.1.1");// 注
		aq.id(R.id.actionbar_left).clicked(this, "clickActionbarLeft");
		initRelativeAchievement();

		// 判断是否有完成该成就
//		RelativeLayout dialog = (RelativeLayout) getLayoutInflater().inflate(
//				R.layout.achieved_dialog, null);
//		AQuery dialogAq = new AQuery(dialog);
//		dialogAq.id(R.id.dialog_achievement_name).text(achievement.getName());
//		dialogAq.id(R.id.dialog_achievement_addition).text(
//				achievement.getAddition());
//		dialogAq.id(R.id.dialog_achievement_icon).image(achievement.getIcon());
//		dialogAq.id(R.id.dialog_score_icon).image(
//				getExpIcon(achievement.getExp()));
//		dialogAq.id(R.id.dialog_frame)
//				.image(getFrameIcon(achievement.getExp()));
//		dialogAq.id(R.id.dialog_close).clicked(this, "closeDialog");
//
//		alertDialog = new AlertDialog.Builder(this).setView(dialog).create();
//		alertDialog.setCanceledOnTouchOutside(false);
//		alertDialog.show();
		// dialog.setBackgroundColor(getResources().getColor(R.color.transparent));

		// AlertDialog alertDialog=new
		// AlertDialog.Builder(this,R.style.NobackDialog).create();

		// Window window=alertDialog.getWindow();
		// WindowManager.LayoutParams lp=window.getAttributes();
		// lp.alpha=0.6f;
		// window.setAttributes(lp);
		// alertDialog.setView(dialog);
		// alertDialog.show();

		// final View view =
		// LayoutInflater.from(this).inflate(R.layout.achieved_dialog, null);
		// dialog.setBackgroundColor(getResources().getColor(R.color.transparent));
		// Dialog dialog = new Dialog(this, R.style.achievement_detail_dialog);
		// dialog.setContentView(view);
		// dialog.show();
		// dialog.setCanceledOnTouchOutside(true);
		// WindowManager windowManager = getWindowManager();
		// Display display = windowManager.getDefaultDisplay();
		// WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		// // lp.width = (int)(display.getWidth() * 0.9);
		// // if(selectorList.size() > 7) {
		// // lp.height = (int)(display.getHeight() * 0.9);
		// // }
		// lp.alpha = 0.0f;
		// dialog.getWindow().setAttributes(lp);
		// new AlertDialog.Builder(this,R.style.achievement_detail_dialog)
		// .setView(dialog)
		// .create()
		// .show();
	}

	public void initRelativeAchievement() {
		relativeListView = (HorizontalListView) findViewById(R.id.relative_achievement);
		List<Map<String, Object>> relativeList = new ArrayList<Map<String, Object>>();
		List<EAchievement> list = Application.getAchievementService()
				.getRelativeAchievements(achievement.getId());
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("icon", list.get(i).getIcon());
			map.put("name", list.get(i).getName());
			map.put("id", list.get(i).getId());
			relativeList.add(map);
		}
		mAdapter = new CustomListViewAdapter(this, relativeList,
				R.layout.relative_achievement_list, new String[] { "icon",
						"name" }, new int[] { R.id.relative_achievement_icon,
						R.id.relative_achievement_name }, new String[] {
						"image", "text" });
		relativeListView.setAdapter(mAdapter);
	}

	public void loadAchievementDetail(EAchievement achievement) {
		achievementDetail = (WebView) findViewById(R.id.achievement_detail);
		StringBuilder sb = new StringBuilder();
		sb.append("<head><style>*{font-size:12px;} p { margin:5px;}</style></head><body><p style=\"font-weight:bolder\">");
		sb.append(achievement.getName());
		sb.append("</p><p style=\"color: #ffbb33;font-weight:bolder\">资料片-<span>");
		sb.append("病毒入侵");
		sb.append("</span></p><p>达成条件：<span style=\"font-weight:bolder\">");
		sb.append(achievement.getAddition());
		sb.append("</span></p><p>已完成：<span style=\"font-weight:bolder\">");
		sb.append(0);
		sb.append("/");
		sb.append(achievement.getExp());
		sb.append("</span></p><p style=\"font-size:9px;\">");
		sb.append(achievement.getDescription());
		sb.append("</p></body></html>");
		achievementDetail.loadDataWithBaseURL(null, sb.toString(), "text/html",
				"utf-8", null);
	}

	public void loadTips(String resource, String startTime, String endTime) {
		tips = (WebView) findViewById(R.id.tips);
		StringBuilder sb = new StringBuilder();
		sb.append("<head><style>*{font-size:9px;padding:0px;margin:0px;background-color: #dbdcdc !important;}</style></head><body><p><span style=\"color:#ff0000;font-weight:bolder\">注：</span><span style=\"color: #ffbb33;font-weight:bolder\">“资料片-");
		sb.append("病毒入侵");
		sb.append("”</span>的相关成就只能在资料片开放期间：");
		sb.append(startTime);
		sb.append("-");
		sb.append(endTime);
		sb.append("内完成，资料片结束后，所有获得成绩会列为光辉事迹，并无法再完成该资料片成就</p></body>");
		tips.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8",
				null);
	}
	public void clickActionbarLeft() {
		finish();
	}

	public void closeDialog() {
		alertDialog.cancel();
	}
}
