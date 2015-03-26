package com.example.newhoyoo;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.huyoo.entity.EAchievement;
import com.huyoo.global.Application;

public class AchievementDialog extends Activity {
	AQuery aq;
	EAchievement achievement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.achieved_dialog);
		RelativeLayout dialog = (RelativeLayout) LayoutInflater.from(this).inflate(
				R.layout.achieved_dialog, null);
		Intent intent = getIntent();
		aq = new AQuery(this);
//		achievement = Application.getAchievementService().getEAchievementById(
//				Integer.parseInt(intent.getStringExtra("id").toString()));
		achievement = (EAchievement) intent.getSerializableExtra("achievement");
//		AlertDialog alertDialog = new AlertDialog.Builder(context).setView(dialog).create();
		aq.id(R.id.dialog_achievement_name).text(achievement.getName());
		aq.id(R.id.dialog_achievement_addition).text(
				achievement.getAddition());
		aq.id(R.id.dialog_achievement_icon).image(achievement.getIcon());
		aq.id(R.id.dialog_score_icon).image(
				getExpIcon(achievement.getExp()));
		aq.id(R.id.dialog_frame)
				.image(getFrameIcon(achievement.getExp()));
		aq.id(R.id.dialog_close).clicked(this, "finish");
	}


	public static String getExpIcon(int exp) {
		switch (exp) {
		case 5:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/82F9609DD2B745A884785FE2A4F2CC78";
		case 10:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/625FCD5676DC47378A319B9F0A528F8D";

		case 15:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/14C1584D8B3F44F492240419B1DC779F";

		case 20:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/E9BB88AEF2A44A1EA02CD7B909D9350B";

		case 25:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/B48E94AB98644723B51E8608BE72B778";

		case 30:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/FED61598254745848F7C1927110DB41D";

		case 35:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/8B52BC9D4C6A4F93BB4FC0E7EA0E6DE1";

		case 40:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/0DDCBAD751AF4C43B94A8C39FDCF0E0E";

		case 45:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/BD5D1AB6F7A64835B513650A75638236";

		case 50:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/BF977AB1BD3B475DACC95FA1F1A70277";

		case 100:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/F27E546172894FE6A4D48C6AD6BB097F";

		case 150:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/A900A16C081B412AA2D85CDBBD3D7D09";

		case 200:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/077484B7DCBF478E8375311524400BE9";

		default:
			return null;
		}
	}
	public static String getFrameIcon(int exp) {
		switch (exp) {
		case 5:
		case 10:
		case 15:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/DEE431711AA941E4A4690D3C6AD32AAA";
		case 20:
		case 25:
		case 30:
		case 35:
		case 40:
		case 45:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/4C8A93F145614815B19D487F945C2A0C";
		case 50:
		case 100:
		case 150:
		case 200:
			return "http://note.youdao.com/yws/public/resource/3d558236602029f163ba7cdab36a2e71/7244556409B942C2A4482F934EF3B137";
		default:
			return null;
		}
	}
}
