package com.example.newhoyoo;

import java.util.Date;

import com.androidquery.AQuery;
import com.huyoo.entity.EArticle;
import com.huyoo.entity.EPerson;
import com.huyoo.global.Application;
import com.huyoo.utils.DateUtil;
import com.ryg.expandable.ui.CustomActionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

/**
 * 公会信息界面
 * @author CL
 *
 */
public class UnionNewsActivity extends Activity {
	private AQuery aq=new AQuery(this);
	private CustomActionbar actionbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.union_news);
		Intent intent=getIntent();
		String type=intent.getStringExtra("type");
		int id=Integer.parseInt(intent.getStringExtra("id"));
		//绑定文章类别控件
		actionbar=(CustomActionbar)findViewById(R.id.news_actionbar);
		actionbar.setButtonVisibility(View.GONE);
		actionbar.setTitle(type);
		aq.id(R.id.actionbar_left).clicked(new ImageButtonListener());
		if("公会新闻".equals(type)){

			//根据传入的id获取新闻
			EArticle news=Application.getArticleSercice().getArticleById(id);

			//根据获得的personId获取EPerson
			EPerson person=Application.getPersonService().getEPersonById(news.getPersonId());
			//绑定数据
			aq.id(R.id.union_news_image).image(news.getIcon());
			aq.id(R.id.union_news_topic_text).text(news.getTitle());			
			aq.id(R.id.union_news_author_image).image(person.getIcon());
			aq.id(R.id.union_news_author_text).text(person.getName());

			String time=DateUtil.getTimeBeforeNow(new Date().getTime()-news.getTime());
			aq.id(R.id.union_news_time_text).text(time);
			WebView webView=(WebView)findViewById(R.id.union_news_content_text);
			webView.loadDataWithBaseURL(null, loadWebView(news.getContent()), "text/html", "utf-8", null);

		}
		else{
			//根据传入的id获取成功秘笈
			EArticle tip=Application.getArticleSercice().getArticleById(id);
			//根据获得的personId获取EPerson
			EPerson person=Application.getPersonService().getEPersonById(tip.getPersonId());
			//绑定数据
			aq.id(R.id.union_news_image).image(tip.getIcon());
			aq.id(R.id.union_news_topic_text).text(tip.getTitle());			
			aq.id(R.id.union_news_author_image).image(person.getIcon());
			aq.id(R.id.union_news_author_text).text(person.getName());
			String time=DateUtil.getTimeBeforeNow(new Date().getTime()-tip.getTime());
			aq.id(R.id.union_news_time_text).text(time);
			WebView webView=(WebView)findViewById(R.id.union_news_content_text);
			webView.loadDataWithBaseURL(null, loadWebView(tip.getContent()), "text/html", "utf-8", null);
		}
	}

	class ImageButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}

	}
	/**
	 * 给要通过webView显示的内用 加上 html标签
	 * @param content
	 * @return
	 */
	public String loadWebView(String content){
		StringBuilder sb=new StringBuilder();
		sb.append("<html><head><style type=\"text/css\"> * {background-color: #dbdcdc !important;font-size: 12px !important;}</style></head><body style=\"font-size=10\">");
		sb.append(content);
		sb.append("</body></html>");
		return sb.toString();

	}
}
