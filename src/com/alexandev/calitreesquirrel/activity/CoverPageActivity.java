package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;
import com.alexandev.calitreesquirrel.util.StorageSighting;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class CoverPageActivity extends Activity {
	
	PreferencesCheck pref = new PreferencesCheck();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_page);
	}
	 
    public void sendToNextPage( View view )
    {	
    	Intent intent;
    	
    	if ( pref.isFirst(this) ){
			intent = new Intent(this, InstructionsActivity.class);
			new StorageSighting().create(this.getApplicationContext());;
    	}
		else 
			intent = new Intent( this, LoginActivity.class );
	        
		startActivity(intent);
		finish();
    }
}
