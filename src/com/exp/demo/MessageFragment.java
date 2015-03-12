package com.exp.demo;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.example.newhoyoo.R;
import com.huyoo.entity.EMessage;
import com.huyoo.global.Application;

public class MessageFragment extends Fragment {
	AQuery aq;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_message, container, false);
		this.aq = new AQuery(rootView);
		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
//		List<EMessage> list = Application.getMessageService().getMessagesByTarget(Application.getLoginInfo().getPerson().getId());
		
		
	}
}
