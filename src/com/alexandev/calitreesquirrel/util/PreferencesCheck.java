package com.alexandev.calitreesquirrel.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesCheck {

	private static final String MY_PREFERENCES = "my_preferences";  

	public static boolean isFirst(Context context){
		final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, 0); 
		
		final boolean first = reader.getBoolean("isFirst", true);
		if(first){
			final SharedPreferences.Editor editor = reader.edit();
			editor.putBoolean("isFirst", false); 
			editor.commit();
		}
		return first;
	}

	public static void resetFirstTime(Context context){
		final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, 0); 

		final SharedPreferences.Editor editor = reader.edit();
		editor.putBoolean("isFirst", true); 
		editor.commit();
	}
	
	public static boolean isLoggedIn(Context context){
		final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, 0); 
		
		final boolean loggedIn = reader.getBoolean("isLoggedIn", false);
		return loggedIn;
	}
	
	public static void setLoggedIn(Context context, Boolean loggedIn){
		final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, 0); 
		
		final SharedPreferences.Editor editor = reader.edit();
		editor.putBoolean("isLoggedIn", loggedIn); 
		editor.commit();
	}


}
