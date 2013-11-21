
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
	
	public SquirrelsDialogFragment(){
		this.currentActivity = new Activity();
		this.mBundle = new Bundle();
	}
		
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setMessage("Do you want to take a picture?")
               .setPositiveButton("Yes, Snap a Picture!", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
               			Intent intent = new Intent( currentActivity , PhotoIntentActivity.class );
               			intent.putExtras(mBundle);
               			startActivity( intent );
                	   
//           		   Toast.makeText( currentActivity.getApplicationContext(), "The answer is yes", Toast.LENGTH_SHORT ).show();
//                	   Log.e( "log_tag", " The answer is Yes ");
                   }
               })
               .setNegativeButton("No, Just Send Sighting!", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
            	
                	   new SubmitPhotoTask(currentActivity).execute(mBundle.getString("timestamp"), mBundle.getString( "latitude"), mBundle.getString( "longitude"), 
                			   mBundle.getInt( "species")+"", currentActivity.getString( R.string.sendURL ), "noPic" );
                	   
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

	
    
}
