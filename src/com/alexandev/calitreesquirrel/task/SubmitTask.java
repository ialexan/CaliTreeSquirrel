package com.alexandev.calitreesquirrel.task;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;



public class SubmitTask extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		//Toast.makeText( myActivity.getApplicationContext(), "Submitting", Toast.LENGTH_SHORT ).show();
	}

	@Override
	protected String doInBackground(String... params) {

		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();

			parameters.add( new BasicNameValuePair( "timestamp", params[0] ) );
			parameters.add( new BasicNameValuePair( "latitude", params[1] ) );
			parameters.add( new BasicNameValuePair( "longitude", params[2] ) );
			parameters.add( new BasicNameValuePair( "species", params[3] ) );

			HttpRemoteConnection con = new HttpRemoteConnection(); 
			JSONObject json = con.getStreamFromUrl( params[4],parameters );

			Log.e( "log_tag", "This is the json -" + json.toString() + "- this is it.");
			
			Log.e( "log_tag", "BLAAAAAAAAAAAA" + params[0] + "  " + params[1] + "  " + params[2] + "  " + params[3] + "  " + params[4]);

			String str = "HI";

			return str;

		} catch (Exception e) {
			//Toast.makeText( myActivity.getApplicationContext(), "Connetion Failed", Toast.LENGTH_SHORT ).show();
		}

		return null;

	}

	@Override
	protected void onPostExecute( String str ) {

//		if ( str != null)
//			Toast.makeText( myActivity.getApplicationContext(), "Success is (" + str + ")", Toast.LENGTH_LONG ).show(); 
//		else
//			Toast.makeText( myActivity.getApplicationContext(), "Failed!", Toast.LENGTH_LONG ).show();

	}



}
