package com.alexandev.calitreesquirrel.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;


public class StorageSighting{

	private String FILENAME = "Squirrels_file";

	public void store(Context context, Bundle mBundle){
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_APPEND);

			String data = mBundle.getString("timestamp")+","+mBundle.getString( "latitude")+","+mBundle.getString( "longitude")+","+mBundle.getInt("species")+"\n";

			fos.write(data.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public List<Bundle> read(Context context){

		List<Bundle> dataList = new ArrayList<Bundle>();

		try {
			FileInputStream fis = context.openFileInput(FILENAME);

			if (fis.available() <= 5)
				return dataList;

			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(isr);
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				String[] data = line.split(",");

				Bundle mBundle = new Bundle();
				mBundle.putString("timestamp", data[0]);
				mBundle.putString( "latitude", data[1]);
				mBundle.putString( "longitude", data[2]);
				mBundle.putInt( "species", Integer.parseInt(data[3]));

				dataList.add(mBundle);   
			}

			create(context);
			
			fis.read();
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("******************** File not found ********************");
		} catch (UnsupportedEncodingException e) {
			System.out.println("******************** Encoding exeption ********************");
		} catch (IOException e) {
			System.out.println("******************** IO exception ********************");
		}catch (Exception e) {
			System.out.println("******************** Exception ********************");
		}

		return dataList;
	}

	public void create(Context context){
		String data = "";
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

			fos.write(data.getBytes());
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
