/*
 * Copyright (C) 2014 Saúl Díaz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.newhoyoo;

import com.androidquery.AQuery;

import main.java.com.sefford.circularprogressdrawable.sample.CircularProgressDrawable;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Circular progress drawable demonstration
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class YaoqingActivity extends Activity {
	float empty=0;
	float full=0.1f;
	float max;
	AQuery aq = new AQuery(this);
	ImageView img0,img1,img2,img3,img4,back;
	ImageButton imgB1,imgB2,imgB3,imgB4;
	Button button1,button2,button3;
	
    // Views
    ImageView ivDrawable;
    Button btStyle2;

    CircularProgressDrawable drawable;
    int tag=0;
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	if(tag>0)
        		return ;
        	drawable.setCircleScale_(drawable.getCircleScale()+0.1f);
        	drawable.setProgress_(drawable.getProgress()+0.1f);
        	tag++;
        }
    };

    Animator currentAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_yaoqing_list);
        
        back=(ImageView)findViewById(R.id.yaoqing_back);
        final YaoqingActivity target=this;
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				target.finish();
			}
		});
        img0=(ImageView)findViewById(R.id.imageView2);
        img1=(ImageView)findViewById(R.id.imageView3);
        img2=(ImageView)findViewById(R.id.imageView4);
        img3=(ImageView)findViewById(R.id.imageView5);
        img4=(ImageView)findViewById(R.id.imageView6);
        imgB1=(ImageButton)findViewById(R.id.imageButton1);
        imgB2=(ImageButton)findViewById(R.id.imageButton2);
        imgB3=(ImageButton)findViewById(R.id.imageButton3);
        imgB4=(ImageButton)findViewById(R.id.imageButton4);
        //init();
        img1.setVisibility(View.VISIBLE);
    	img2.setVisibility(View.GONE);
    	img3.setVisibility(View.GONE);
    	img4.setVisibility(View.GONE);
    	this.aq.id(R.id.imageButton1).clicked(this, "firstImage");
    	this.aq.id(R.id.imageButton2).clicked(this, "secondImage");
    	this.aq.id(R.id.imageButton3).clicked(this, "threeImage");
    	this.aq.id(R.id.imageButton4).clicked(this, "fourImage");
        
        ivDrawable = (ImageView) findViewById(R.id.iv_drawable);
        btStyle2 = (Button) findViewById(R.id.bt_style_2);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        drawable = new CircularProgressDrawable(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
                getResources().getColor(android.R.color.darker_gray),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_red_dark));
        
        drawable.setProgress(0.5f);
        drawable.setCircleScale(0.5f);
        ivDrawable.setImageDrawable(drawable);
        ivDrawable.setOnClickListener(listener);
        button1.setOnClickListener(new Button1Listener());
        button2.setOnClickListener(new Button2Listener());
//        btStyle2.setOnClickListener(listener);
    }
    
    class Button1Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(YaoqingActivity.this, InventTranspond.class);
			YaoqingActivity.this.startActivity(intent);
		}
    	
    }
    class Button2Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(YaoqingActivity.this, Comment.class);
			startActivity(intent);
		}
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        ivDrawable = (ImageView) findViewById(R.id.iv_drawable);
        btStyle2 = (Button) findViewById(R.id.bt_style_2);

        drawable = new CircularProgressDrawable(getResources().getDimensionPixelSize(R.dimen.drawable_ring_size),
                getResources().getColor(android.R.color.darker_gray),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_blue_dark));
        ivDrawable.setImageDrawable(drawable);
        btStyle2.setOnClickListener(listener);
        //hookUpListeners();
    }
  */  
    /**
     * Style 2 animation will fill the outer ring while applying a color effect from red to green
     *
     * @return Animation
     */
    private Animator prepareStyle2Animation() {
        AnimatorSet animation = new AnimatorSet();

        ObjectAnimator progressAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.PROGRESS_PROPERTY, empty, full);//0f1f
        progressAnimation.setDuration(1800);
        progressAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        
//        Animator innerCircleAnimation = ObjectAnimator.ofFloat(drawable, CircularProgressDrawable.CIRCLE_FILL_PROPERTY, empty, full);//0f1.2f
//        innerCircleAnimation.setDuration(1800);
//        innerCircleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(drawable, "ringColor", getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_dark));
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setDuration(3600);

        animation.playTogether(progressAnimation, colorAnimator);
//        animation.playTogether(progressAnimation, colorAnimator, innerCircleAnimation);
        return animation;
    }

    public void firstImage()
    {
    	img0.setImageDrawable(imgB1.getDrawable());
    	img1.setVisibility(View.VISIBLE);
    	img2.setVisibility(View.GONE);
    	img3.setVisibility(View.GONE);
    	img4.setVisibility(View.GONE);
    }
    public void secondImage()
    {
    	img0.setImageDrawable(imgB2.getDrawable());
    	img1.setVisibility(View.GONE);
    	img2.setVisibility(View.VISIBLE);
    	img3.setVisibility(View.GONE);
    	img4.setVisibility(View.GONE);
    }
    public void threeImage()
    {
    	img0.setImageDrawable(imgB3.getDrawable());
    	img1.setVisibility(View.GONE);
    	img2.setVisibility(View.GONE);
    	img3.setVisibility(View.VISIBLE);
    	img4.setVisibility(View.GONE);
    }
    public void fourImage()
    {
    	img0.setImageDrawable(imgB4.getDrawable());
    	img1.setVisibility(View.GONE);
    	img2.setVisibility(View.GONE);
    	img3.setVisibility(View.GONE);
    	img4.setVisibility(View.VISIBLE);
    }
    
    public void init()
    {
    	img1.setImageResource(0);
    	img2.setImageResource(0);
    	img3.setImageResource(0);
    	img4.setImageResource(0);
    }
}

