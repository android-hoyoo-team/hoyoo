package com.example.newhoyoo;

import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class loginloadingfragment extends Fragment {
	/*Dynamically*/
	ProgressBar mProgressBar;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View loginloadingLayout = inflater.inflate(R.layout.loginloading_layout, container, false);
		return loginloadingLayout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mProgressBar = (ProgressBar) view.findViewById(R.id.google_progress2);
		/**Dynamically*/
		Rect bounds = mProgressBar.getIndeterminateDrawable().getBounds();
		mProgressBar.setIndeterminateDrawable(getProgressDrawable());
		mProgressBar.getIndeterminateDrawable().setBounds(bounds);
	}
	/***************************@Override protected void onResume() {        super.onResume();    }****************load Google ProgressBar***************************************************/
	private Drawable getProgressDrawable() {
		Drawable progressDrawable = null;

		progressDrawable = new FoldingCirclesDrawable.Builder(getActivity())
		.colors(getProgressDrawableColors())
		.build();

		return progressDrawable;
	}

	private int[] getProgressDrawableColors() {
		int[] colors = new int[4];
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		colors[0] = prefs.getInt(getString(R.string.firstcolor_pref_key),getResources().getColor(R.color.red));
		colors[1] = prefs.getInt(getString(R.string.secondcolor_pref_key),getResources().getColor(R.color.blue));
		colors[2] = prefs.getInt(getString(R.string.thirdcolor_pref_key),getResources().getColor(R.color.yellow));
		colors[3] = prefs.getInt(getString(R.string.fourthcolor_pref_key), getResources().getColor(R.color.green));
		return colors;
	}
	/*******************************************load Google ProgressBar***************************************************/
}

