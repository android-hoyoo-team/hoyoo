package com.example.newhoyoo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleMusicDicesDrawable;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;

public class Loginloading extends Activity {
	/*Dynamically*/
	ProgressBar mProgressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginloading);
		mProgressBar=(ProgressBar)findViewById(R.id.google_progress);
		/**Dynamically*/
		Rect bounds = mProgressBar.getIndeterminateDrawable().getBounds();
		mProgressBar.setIndeterminateDrawable(getProgressDrawable());
		mProgressBar.getIndeterminateDrawable().setBounds(bounds);
	}
	/***************************@Override protected void onResume() {        super.onResume();    }****************load Google ProgressBar***************************************************/
	private Drawable getProgressDrawable() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		int value = Integer.parseInt(prefs.getString(getString(R.string.progressBar_pref_key), getString(R.string.progressBar_pref_defValue)));
		Drawable progressDrawable = null;

		progressDrawable = new FoldingCirclesDrawable.Builder(this)
		.colors(getProgressDrawableColors())
		.build();

		return progressDrawable;
	}

	private int[] getProgressDrawableColors() {
		int[] colors = new int[4];
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		colors[0] = prefs.getInt(getString(R.string.firstcolor_pref_key),getResources().getColor(R.color.red));
		colors[1] = prefs.getInt(getString(R.string.secondcolor_pref_key),getResources().getColor(R.color.blue));
		colors[2] = prefs.getInt(getString(R.string.thirdcolor_pref_key),getResources().getColor(R.color.yellow));
		colors[3] = prefs.getInt(getString(R.string.fourthcolor_pref_key), getResources().getColor(R.color.green));
		return colors;
	}
	/*******************************************load Google ProgressBar***************************************************/
}
