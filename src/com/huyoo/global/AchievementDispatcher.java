package com.huyoo.global;

import java.util.Date;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;
import android.util.Log;

import com.huyoo.entity.EAchievement;
import com.huyoo.entity.RPersonAchievement;
import com.huyoo.service.EAchievementService;
import com.huyoo.bean.Message;;
/**
 * 监听事件，并处理。如果达到成就所需的条件，注册一个事件，以便处理。
 * @author XF
 *
 */
public class AchievementDispatcher {

	public static void initDispatcher(){
		IMethod<Message<EAchievement>> message = new IMethod<Message<EAchievement>>() {

			@Override
			public void excute(DEvent<Message<EAchievement>> event) {
				// TODO Auto-generated method stub
				int personId = Application.getLoginInfo().getPerson().getId();
				EAchievementService service = Application.getAchievementService();
				int achievementId = 0;
				RPersonAchievement rpa = null;
				switch (event.getType()) {
				case "login":
					achievementId = 1;
					break;
				case "enterAchievement":
					achievementId = 4;
					break;
				case "uploadHeader":
					achievementId = 2;
					break;
				case "joinInvitation":
					achievementId = 3;
					break;
				case "payAttention":
					achievementId = 5;
					break;
				case "haveFriend":
					achievementId = 6;
					break;
				default:
					break;
				}
				rpa = service.getPersonAchievementBy(personId, achievementId);
				if(rpa == null){
					rpa = new RPersonAchievement();
					rpa.setPersonId(personId);
					rpa.setAchievementId(achievementId);
					rpa.setCurrentProgress(1);
					rpa.setTime(new Date().getTime());
					service.addPersonAchievement(rpa);
				}
				else{
					if(rpa.getCurrentProgress()==service.getEAchievementById(achievementId).getTotalProgress())return;
					rpa.setCurrentProgress(rpa.getCurrentProgress()+1);
					rpa.setTime(new Date().getTime());
					service.updatePersonAchievement(rpa);
				}
				if(rpa.getCurrentProgress() == service.getEAchievementById(achievementId).getTotalProgress()){
					Message<EAchievement> _message = new Message<EAchievement>();
					_message.setInfo(service.getEAchievementById(achievementId));
					_message.setTime(new Date());
					_message.setType("achievement");
					DispatchEvent.dispatchEvent(new DEvent("message", _message));
				}
			}
		};
		//监听login事件，当登录时会触发
		DispatchEvent.addEventListener("login",message);
		//监听进入成就页面的事件
		DispatchEvent.addEventListener("enterAchievement",message);
		//监听上传头像的事件
		DispatchEvent.addEventListener("uploadHeader",message);
		//监听参与要求的事件
		DispatchEvent.addEventListener("joinInvitation",message);
		//监听添加关注的事件
		DispatchEvent.addEventListener("payAttention",message);
		//监听拥有好友的事件
		DispatchEvent.addEventListener("haveFriend",message);
	}
}
