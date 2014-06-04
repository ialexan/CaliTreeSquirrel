
package com.alexandev.calitreesquirrel.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.task.SubmitPhotoTask;
import com.alexandev.calitreesquirrel.util.NetworkDataConnection;
import com.alexandev.calitreesquirrel.util.StorageSighting;;

public class SquirrelsDialogFragment extends DialogFragment {
	
	private Activity currentActivity;
	
	private Bundle mBundle;
	
	private String message = "";
	
	private String positiveButtonMessage = "";
	
	private String negativeButtonMessage = "";
	
	public SquirrelsDialogFragment(){
		this.currentActivity = new Activity();
		this.mBundle = new Bundle();
	}
		
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setMessage(message)  
               .setPositiveButton(positiveButtonMessage, new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) {
                	   if (positiveButtonMessage.equals("Yes, Send")){
                		   NetworkDataConnection networkData = new NetworkDataConnection(currentActivity);
                		   
                		   // Check network connectivity
                		   if (networkData.checkConnection()){
                			   new SubmitPhotoTask(currentActivity).execute(mBundle.getString("timestamp"), mBundle.getString( "latitude"), mBundle.getString( "longitude"), 
                        			   mBundle.getInt("species")+"", currentActivity.getString( R.string.sendURL ), "noPic" );
                        		   
                        		//Toast.makeText( currentActivity.getApplicationContext(), "Calculating Squirrel location!", Toast.LENGTH_LONG ).show();
                		   }
                		   else {
                			   // Save it to send later on 
                			   new StorageSighting().store( currentActivity.getApplicationContext(), mBundle);
                			   
                			   Toast.makeText( currentActivity.getApplicationContext(), "No Network Connection, Sighting Saved to be Sent Later", Toast.LENGTH_LONG ).show();
                		   }
                		   
                	   }
                	   else if (positiveButtonMessage.equals("Yes")){
                           startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));                          
                	   }
                	   
                   }
               })
               .setNegativeButton(negativeButtonMessage, new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) {
                	   if (negativeButtonMessage.equals("No"))
                           dialog.cancel();   
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

	public Bundle getmBundle() {
		return mBundle;
	}

	public void setmBundle(Bundle mBundle) {
		this.mBundle = mBundle;
	}

	public String getPositiveButtonMessage() {
		return positiveButtonMessage;
	}

	public void setPositiveButtonMessage(String positiveButtonMessage) {
		this.positiveButtonMessage = positiveButtonMessage;
	}

	public String getNegativeButtonMessage() {
		return negativeButtonMessage;
	}

	public void setNegativeButtonMessage(String negativeButtonMessage) {
		this.negativeButtonMessage = negativeButtonMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
