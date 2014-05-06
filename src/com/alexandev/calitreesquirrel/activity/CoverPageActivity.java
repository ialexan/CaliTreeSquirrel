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
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			if ( !pref.isFirst(this, "first") ) {
				Intent intent = new Intent( this, LoginActivity.class );
				startActivity(intent);
				finish();
			}
		} 
		else {
			if ( !pref.isFirst(this, "second") ) { }
		}
				
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_page);
	}
	 
    public void sendToNextPage( View view ){	
    	
    	if ( pref.isFirst( this, "second" ) ){
    		new StorageSighting().create( this.getApplicationContext() );
    		Intent intent = new Intent( this, InstructionsActivity.class );
			startActivity(intent);
			finish();
    	}
		else {
			Intent intent = new Intent( this, LoginActivity.class );
			startActivity(intent);
			finish();
		}
	        
		
    }
}
