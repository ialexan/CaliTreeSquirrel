package com.alexandev.calitreesquirrel.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.app.DialogFragment;
import android.content.DialogInterface;

import com.alexandev.calitreesquirrel.R;


public class SquirrelsDialogFragment extends DialogFragment {
	
	private Activity currentActivity;
	
	public SquirrelsDialogFragment(){
		currentActivity = new Activity();
	}
		
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setMessage("Do you want to take a picture")
               .setPositiveButton("Yes, Snap a Pic", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Log.e( "log_tag", " The answer is Yes ");
                   }
               })
               .setNegativeButton("No, Just Send", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Log.e( "log_tag", "This answer is No");
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }


	public Activity getCurrentActivity() {
		return currentActivity;
	}


	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}
    
    
}
