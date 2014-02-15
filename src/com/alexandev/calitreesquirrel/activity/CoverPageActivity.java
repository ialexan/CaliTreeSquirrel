package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class CoverPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_page);
	}
	 
    public void sendToNextPage( View view )
    {
    	PreferencesCheck pref = new PreferencesCheck();
    	
		if ( pref.isFirst(this) ){
			Intent intent = new Intent(this, InstructionsActivity.class);
			startActivity(intent);
		}
		else {
			Intent intent = new Intent( this, LoginActivity.class );
	        startActivity( intent );
		}
		finish();
    }
}
