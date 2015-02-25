package com.example.newhoyoo;

import android.app.Application;  
  
public class myApp extends Application{  
    public String accesstoken;  
    public CharSequence username;
    
    public static String web(String clip){
    	String website = null;
    	website="http://192.168.0.112:4000/"+clip;
    	return website;
    }
  
    public String getaccesstoken() {  
        return accesstoken;  
    }  
  
    public void setaccesstoken(String accesstoken) {  
        this.accesstoken = accesstoken;  
    }   
    
    public CharSequence getusername() {  
        return username;  
    }  
  
    public void setusername(CharSequence username) {  
        this.username = username;  
    }
    
    @Override    
    public void onCreate() {    
        // TODO Auto-generated method stub    
        super.onCreate();    
        setaccesstoken("Hello!");   //初始化全局变量    
        setusername("Hello!");
    }
      
} 
