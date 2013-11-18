package com.alexandev.calitreesquirrel.task;

import java.io.File;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

public class SubmitPhotoTask extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		//Toast.makeText( myActivity.getApplicationContext(), "Submitting", Toast.LENGTH_SHORT ).show();
	}

	@Override
	protected String doInBackground(String... params) {

		try {
			
//			File file = new File(Environment.getExternalStoragePublicDirectory(
//					Environment.DIRECTORY_DCIM).toString()
//					+ "/Squirrels/"+params);

			MultipartEntity entity = new MultipartEntity();
			entity.addPart("timestamp", new StringBody(params[0]));
			entity.addPart("latitude", new StringBody(params[1]));
			entity.addPart("longitude", new StringBody(params[2]));
			entity.addPart("species", new StringBody(params[3]));
			
			if ( params[5] != null ){
				File file = new File ( params[5] );
				entity.addPart("uploadedFile", new FileBody(file)); 
			}
			
			HttpRemoteConnection con = new HttpRemoteConnection(); 
			JSONObject json = con.sendImageToServer( params[4],entity );
             
			Log.e( "log_tag", "This is the json -" + json.toString() + "- this is it.");

			return json.toString();

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
