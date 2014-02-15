package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;

public class InstructionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
		{
			setContentView(R.layout.activity_instructions);
		} else {
			setContentView(R.layout.activity_instructions_landscape);
		}		
		
	}

	
	public void sendToLoginPage( View view )
	{
		Intent intent = new Intent( this, LoginActivity.class );
		startActivity( intent );
		finish();
	}
	
}
