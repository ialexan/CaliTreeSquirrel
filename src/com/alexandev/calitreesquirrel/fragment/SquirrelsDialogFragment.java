
package com.alexandev.calitreesquirrel.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.activity.PhotoIntentActivity;
import com.alexandev.calitreesquirrel.task.SubmitPhotoTask;


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
                	   if (positiveButtonMessage.equals("Yes, Snap a Picture!")){
                		    Intent intent = new Intent( currentActivity , PhotoIntentActivity.class );
               				intent.putExtras(mBundle);
               				startActivity( intent );
                	   }
                	   else if (positiveButtonMessage.equals("Yes")){
                           startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                          // Toast.makeText( currentActivity.getApplicationContext(), "Click Saw It again!", Toast.LENGTH_SHORT ).show();
                	   }
                	   
//           		   Toast.makeText( currentActivity.getApplicationContext(), "The answer is yes", Toast.LENGTH_SHORT ).show();
//                	   Log.e( "log_tag", " The answer is Yes ");
                   }
               })
               .setNegativeButton(negativeButtonMessage, new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) {
                	   if (negativeButtonMessage.equals("No, Just Send Sighting!")){
                		   new SubmitPhotoTask(currentActivity).execute(mBundle.getString("timestamp"), mBundle.getString( "latitude"), mBundle.getString( "longitude"), 
                			   mBundle.getInt( "species")+"", currentActivity.getString( R.string.sendURL ), "noPic" );
                		   
                			Toast.makeText( currentActivity.getApplicationContext(), "Sighting Sent!", Toast.LENGTH_LONG ).show();
                	   }
                	   else if (negativeButtonMessage.equals("No")){
                           dialog.cancel();
                	   }
                	   
//                	   Toast.makeText( currentActivity.getApplicationContext(), "The answer is no", Toast.LENGTH_SHORT ).show(); 
//                	   Log.e( "log_tag", "This answer is No");
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
