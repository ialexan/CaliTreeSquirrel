package com.alexandev.calitreesquirrel.task;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.alexandev.calitreesquirrel.activity.ScreenSlideActivity;
import com.alexandev.calitreesquirrel.util.MD5;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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

			PreferencesCheck pref = new PreferencesCheck();
			MD5 md5Password = new MD5( params[1] );
			pref.setLoggedIn( currentActivity , params[0], md5Password.getEncryptedValue());
			
			return message;

		} catch (Exception e) {
			Toast.makeText( currentActivity.getApplicationContext(), "Connetion Failed", Toast.LENGTH_SHORT ).show();
		}

		return null;

	}

	@Override
	protected void onPostExecute( String str ) {
		
		if ( str.equals("loggedIn")) {
			Toast.makeText( currentActivity.getApplicationContext(), "Your Logged In", Toast.LENGTH_SHORT ).show(); 

			Intent intent = new Intent( currentActivity, ScreenSlideActivity.class );
			currentActivity.startActivity( intent );
			currentActivity.finish();
		}
		else  
			Toast.makeText( currentActivity.getApplicationContext(), "Username or Password invalid", Toast.LENGTH_SHORT ).show();

	}

}

