package com.example.newhoyoo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;

public class EditInvite extends Activity{
	private ImageButton leftImageButton;
	private Button issueButton;
	private ImageButton addImageButton;
	private ImageButton deleteImageButton;
	private ImageButton timeImageButton;
	private ImageButton dateImageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_invite);
		leftImageButton=(ImageButton)findViewById(R.id.leftImageButton);
		final EditInvite target=this;
		leftImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				target.finish();
			}
		});
		issueButton=(Button)findViewById(R.id.issueButoon);
		addImageButton=(ImageButton)findViewById(R.id.addImageButton);
		deleteImageButton=(ImageButton)findViewById(R.id.deleteImageButton);
		timeImageButton=(ImageButton)findViewById(R.id.timeImageButton);
		dateImageButton=(ImageButton)findViewById(R.id.dateImageButton);		
	}
	
	

}
