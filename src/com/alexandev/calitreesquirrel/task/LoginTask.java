package com.alexandev.calitreesquirrel.task;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.alexandev.calitreesquirrel.activity.ScreenSlideActivity;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


public class LoginTask extends AsyncTask<String, String, String> {

	Activity currentActivity;

	public LoginTask(Activity currentActivity){
		this.currentActivity = currentActivity;
	}

	@Override
	protected void onPreExecute() {
		//Toast.makeText( myActivity.getApplicationContext(), "Submitting", Toast.LENGTH_SHORT ).show();
	}

	@Override
	protected String doInBackground(String... params) {

		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();

			parameters.add( new BasicNameValuePair( "j_username", params[0] ) );
			parameters.add( new BasicNameValuePair( "j_password", params[1] ) );
			parameters.add( new BasicNameValuePair( "mobile", "mobile" ));

			HttpRemoteConnection con = new HttpRemoteConnection(); 
			JSONObject json = con.getStreamFromUrl( params[2],parameters );
			
			String message = (String) json.get("message");
			
//			Log.e( "log_tag", "This is parameters --" + params[0] + " - " + params[1] + " - " + params[2] + "- this is it.");
//			Log.e( "log_tag", "This is the json -" + message + "- this is it.");

			return message;

		} catch (Exception e) {
			Toast.makeText( currentActivity.getApplicationContext(), "Connetion Failed", Toast.LENGTH_SHORT ).show();
		}

		return null;

	}

	@Override
	protected void onPostExecute( String str ) {

//		Log.e( "log_tag", "This is str -" + str + "- this is it.");
		
		if ( str.equals("loggedIn")) {
			Toast.makeText( currentActivity.getApplicationContext(), "Your Logged In", Toast.LENGTH_LONG ).show(); 
			
			// Here you need to check preferences if it's a First Time user then you have to send it to instructions page
			
			PreferencesCheck.setLoggedIn( currentActivity, true );
			Intent intent = new Intent( currentActivity, ScreenSlideActivity.class );
			currentActivity.startActivity( intent );
		}
		else  
			Toast.makeText( currentActivity.getApplicationContext(), "Username or Password invalid", Toast.LENGTH_SHORT ).show();

	}



}

