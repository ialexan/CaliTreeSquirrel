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


public class RegisterTask extends AsyncTask<String, String, String> {

	Activity currentActivity;

	public RegisterTask(Activity currentActivity){
		this.currentActivity = currentActivity;
	}

	@Override
	protected String doInBackground(String... params) {

		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();

			// Change password to MD5 encryption
			MD5 md5Password = new MD5( params[1] );

			parameters.add( new BasicNameValuePair( "email", params[4] ) );
			parameters.add( new BasicNameValuePair( "password", md5Password.getEncryptedValue() ) );
			parameters.add( new BasicNameValuePair( "lastName", params[3] ) );
			parameters.add( new BasicNameValuePair( "firstName", params[2] ) );
			parameters.add( new BasicNameValuePair( "username", params[0] ) );

			HttpRemoteConnection con = new HttpRemoteConnection(); 
			JSONObject json = con.getStreamFromUrl( params[5], parameters );

			String message = (String) json.get("user");
			
			PreferencesCheck pref = new PreferencesCheck();
			pref.setLoggedIn( currentActivity , params[0], md5Password.getEncryptedValue() );

			return message;

		} catch (Exception e) {
			Toast.makeText( currentActivity.getApplicationContext(), "Connetion Failed", Toast.LENGTH_SHORT ).show();
		}

		return null;

	}

	@Override
	protected void onPostExecute( String str ) {

		if ( str.equals("user registered")) {
			Toast.makeText( currentActivity.getApplicationContext(), "Congratulations you are registered", Toast.LENGTH_SHORT ).show(); 

			Intent intent = new Intent( currentActivity, ScreenSlideActivity.class );
			currentActivity.startActivity( intent );
			currentActivity.finish();
		}
		else  
			Toast.makeText( currentActivity.getApplicationContext(), "Invalid Entry!", Toast.LENGTH_SHORT ).show();
	}

}

