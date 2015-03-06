package com.example.newhoyoo.util;

import java.net.URL;

import android.graphics.drawable.Drawable;

public class UrlToDrawable {
	public static Drawable loadImageFromNetwork(String imageUrl){
		Drawable drawable=null;
		try {
			drawable=Drawable.createFromStream(new URL(imageUrl).openStream(), "image.jpg");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return drawable;
	}
}
