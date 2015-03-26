package com.exp.demo;

import java.util.HashMap;

import com.example.newhoyoo.LoginPhoneNum;
import com.example.newhoyoo.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class SettingFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		((Button)getActivity().findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")
						.setMessage("您确定退出程序并清除数据库？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								getActivity().getApplicationContext().deleteDatabase("hoyoo");
								Intent intent = new Intent();
								intent.setClass(getActivity(), LoginPhoneNum.class);
								getActivity().startActivity(intent);
								System.exit(0);
							}
						}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub

							}
						}).create();
				dialog.show();
			}
		});
	}
}
