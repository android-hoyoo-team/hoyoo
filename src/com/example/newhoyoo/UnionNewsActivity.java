package com.example.newhoyoo;

import java.util.Date;

import com.androidquery.AQuery;
import com.huyoo.entity.EArticle;
import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;
import com.huyoo.utils.DateUtil;

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
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class UnionNewsActivity extends Activity {
	private ImageButton image_button_left;
//	private ImageView union_news_imge;
//	private TextView union_news_topic_text;
//	private ImageView union_news_author_image;
//	private TextView union_news_author_text;
//	private TextView union_news_time_text;
//	private TextView union_news_content_text;
//	private TextView title_text_view;
	private AQuery aq=new AQuery(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.union_news);
		Intent intent=getIntent();
		String type=intent.getStringExtra("type");
		int id=Integer.parseInt(intent.getStringExtra("id"));
		//绑定文章类别控件
		aq.id(R.id.title_text_view).text(type);
		image_button_left=(ImageButton)findViewById(R.id.image_button_left);
		image_button_left.setOnClickListener(new ImageButtonListener());
		if("公会新闻".equals(type)){
			//根据传入的id获取新闻
			EArticle news=Application.getArticleSercice().getNewsById(id);
			//根据获得的personId获取EPerson
			EPerson person=Application.getPersonService().getEPersonById(news.getPersonId());
			//绑定数据
			aq.id(R.id.union_news_image).image(news.getIcon());
			aq.id(R.id.union_news_topic_text).text(news.getTitle());			
			aq.id(R.id.union_news_author_image).image(person.getIcon());
			aq.id(R.id.union_news_author_text).text(person.getName());
			String time=DateUtil.getTimeBeforeNow(new Date().getTime()-news.getTime());
			aq.id(R.id.union_news_time_text).text(time);
//			aq.id(R.id.union_news_content_text).text(news.getContent());
			WebView webView=(WebView)findViewById(R.id.union_news_content_text);
			webView.loadDataWithBaseURL(null, loadWebView(news.getContent()), "text/html", "utf-8", null);
			
		}
		else{
			//根据传入的id获取成功秘笈
			EArticle tip=Application.getArticleSercice().getTipById(id);
			//根据获得的personId获取EPerson
			EPerson person=Application.getPersonService().getEPersonById(tip.getPersonId());
			//绑定数据
			aq.id(R.id.union_news_image).image(tip.getIcon());
			aq.id(R.id.union_news_topic_text).text(tip.getTitle());			
			aq.id(R.id.union_news_author_image).image(person.getIcon());
			aq.id(R.id.union_news_author_text).text(person.getName());
			String time=DateUtil.getTimeBeforeNow(new Date().getTime()-tip.getTime());
			aq.id(R.id.union_news_time_text).text(time);
//			aq.id(R.id.union_news_content_text).text(tip.getContent());
			WebView webView=(WebView)findViewById(R.id.union_news_content_text);
			webView.loadDataWithBaseURL(null, loadWebView(tip.getContent()), "text/html", "utf-8", null);
			
			
		}
//		title_text_view=(TextView)findViewById(R.id.title_text_view);
//		title_text_view.setText(title);
//		image_button_left=(ImageButton)findViewById(R.id.image_button_left);
//		image_button_left.setOnTouchListener(new ImgButtonLeftOnTouch());
//		image_button_left.setOnClickListener(new ImageButtonListener());
//		union_news_content_text=(TextView)findViewById(R.id.union_news_content_text);
//		union_news_content_text.setText("指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +" 指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得"
//            +"指社会上人与人的交际往来，是人们运用一定的方式（工具）传递信息、交流思想的意识，以达到某种目的的社会各项活动。当今时代，经济和社会环境的变化使得人与人之间的交往显得");
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
	public String loadWebView(String content){
		StringBuilder sb=new StringBuilder();
		sb.append("<html><head><style type=\"text/css\"> * {background-color: #dbdcdc !important;font-size: 12px !important;}</style></head><body style=\"font-size=10\">");
		sb.append(content);
		sb.append("</body></html>");
		return sb.toString();
		
	}
}
