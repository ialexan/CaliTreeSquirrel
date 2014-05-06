package com.alexandev.calitreesquirrel.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class PreferencesCheck {

	private final String MY_PREFERENCES = "Squirrels_preferences";  

	public boolean isFirst(Context context, String str){
		SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE); 
		
		boolean first = reader.getBoolean("isFirst", true);
		
		if(first && str.equals("second")){
			final SharedPreferences.Editor editor = reader.edit();
			editor.putBoolean("isFirst", false); 
			editor.commit();
		}
		return first;
	}

	
	public void resetFirstTime(Context context){
		SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE); 

		SharedPreferences.Editor editor = reader.edit();
		editor.putBoolean("isFirst", true); 
		editor.commit();
	}
	
	
	public Bundle getLoggedIn(Context context){
		SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		
		Bundle bundle = new Bundle();
		bundle.putString( "username", pref.getString("username", null) );
		bundle.putString( "password", pref.getString("password", null) );
		
		return bundle;
	}
	
	
	public void setLoggedIn(Context context, String username, String password){
		
		SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor = pref.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

}
