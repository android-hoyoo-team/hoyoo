package com.huyoo.global;

import java.util.Date;

import per.cz.event1_0.DEvent;
import per.cz.event1_0.DispatchEvent;
import per.cz.event1_0.IMethod;

import com.huyoo.entity.EAchievement;
import com.huyoo.entity.RPersonAchievement;
import com.huyoo.service.EAchievementService;
import com.huyoo.service.RPersonAchievementService;
import com.huyoo.bean.Message;;

public class AchievementDispatcher {

	int personId;
	RPersonAchievementService personAchievementService;
	EAchievementService achievementService;
	int achievementId;
	RPersonAchievement rpa;

	public AchievementDispatcher(){
		personId = Application.getLoginInfo().getPerson().getId();
		personAchievementService = Application.getPersonAchievementService();
		achievementService = Application.getAchievementService();
		IMethod<Message<EAchievement>> message = new IMethod<Message<EAchievement>>() {

			@Override
			public void excute(DEvent<Message<EAchievement>> event) {
				// TODO Auto-generated method stub
				switch (event.getType()) {
				case "login":
					achievementId = 1;
					break;
				case "enterAchievement":
					achievementId = 4;
					break;
				default:
					break;
				}
				rpa = personAchievementService.getPersonAchievementBy(personId, achievementId);
				if(rpa == null){
					rpa = new RPersonAchievement();
					rpa.setPersonId(personId);
					rpa.setAchievementId(achievementId);
					rpa.setCurrentProgress(1);
					rpa.setTime(new Date().getTime());
					personAchievementService.addPersonAchievement(rpa);
				}
				else{
					if(rpa.getCurrentProgress()==achievementService.getEAchievementById(achievementId).getTotalProgress())return;
					rpa.setCurrentProgress(rpa.getCurrentProgress()+1);
					rpa.setTime(new Date().getTime());
					personAchievementService.updatePersonAchievement(rpa);
				}
				if(rpa.getCurrentProgress() == achievementService.getEAchievementById(achievementId).getTotalProgress()){
					DispatchEvent.dispatchEvent(new DEvent("message", "您获得了成就【"+achievementService.getEAchievementById(achievementId).getName()+"】"));
				}
			}
		};
		DispatchEvent.addEventListener("login",message);
		DispatchEvent.addEventListener("enterAchievement",message);
	}
}
