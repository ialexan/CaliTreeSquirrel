//package com.alexandev.calitreesquirrel.util;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
//import android.content.Context;
//import android.os.Bundle;
//
//public class StorageSighting {
//	
//	private String FILENAME = "Squirrels_file";
//	
//	public void store(Bundle mBundle){
//		FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
//		
//		
//		fos.write(data.getBytes());
//		fos.close();
//	}
//
//	public list read(){
//		
//		FileInputStream fos = openFileInput(FILENAME);
//		
//		fos.read();
//		fos.close();
//	}
//	
//	public void clear(){
//		String data = "";
//
//		FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//		fos.write(data.getBytes());
//		fos.close();
//	}
//}
