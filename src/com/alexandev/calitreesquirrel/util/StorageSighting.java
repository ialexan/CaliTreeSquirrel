package com.alexandev.calitreesquirrel.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class StorageSighting extends Activity{

	private String FILENAME = "Squirrels_file";

	public void store(Bundle mBundle){
		try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);

			String data = mBundle.getString("timestamp")+","+mBundle.getString( "latitude")+","+mBundle.getString( "longitude")+","+mBundle.getInt("species")+"\n";

			fos.write(data.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public List<Bundle> read(){

		List<Bundle> dataList = new ArrayList<Bundle>();

		try {
			FileInputStream fis = openFileInput(FILENAME);

			int content; 
			String line = "";
			
			if (fis.available() <= 10)
				return dataList;
			
			while ((content = fis.read()) != -1) {

				if ((char) content == '\n' ){
					String[] data = line.split(",");
					
					Bundle mBundle = new Bundle();
					mBundle.putString("timestamp", data[0]);
					mBundle.putString( "latitude", data[1]);
					mBundle.putString( "longitude", data[2]);
					mBundle.putInt( "species", Integer.getInteger(data[3]));
					
					dataList.add(mBundle);
					line = "";
				}
				else
					line += (char) content;	
			}

			fis.read();
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}

	public void create(){
		String data = "";
		try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

			fos.write(data.getBytes());
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
