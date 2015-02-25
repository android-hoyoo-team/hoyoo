package com.example.newhoyoo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class UnionNewsActivity extends Activity {
	private ImageButton image_button_left;
	private ImageView union_news_imge;
	private TextView union_news_topic_text;
	private ImageView union_news_author_image;
	private TextView union_news_author_text;
	private TextView union_news_time_text;
	private TextView union_news_content_text;
	private TextView title_text_view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.union_news);
		Intent intent=getIntent();
		String title=intent.getStringExtra("title");
		title_text_view=(TextView)findViewById(R.id.title_text_view);
		title_text_view.setText(title);
		image_button_left=(ImageButton)findViewById(R.id.image_button_left);
//		image_button_left.setOnTouchListener(new ImgButtonLeftOnTouch());
		image_button_left.setOnClickListener(new ImageButtonListener());
		union_news_content_text=(TextView)findViewById(R.id.union_news_content_text);
		union_news_content_text.setText("指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +" 指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得");
	}
	
//	class ImgButtonLeftOnTouch implements OnTouchListener{
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			// TODO Auto-generated method stub
//			if(event.getAction()==MotionEvent.ACTION_DOWN){
//				v.setBackgroundResource(R.drawable.bt_02_press);
//			}
//			else if(event.getAction()==MotionEvent.ACTION_UP){
//				v.setBackgroundResource(R.drawable.bt_02_nor);
//			}
//			return false;
//		}
//		
//	}
	class ImageButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
}
