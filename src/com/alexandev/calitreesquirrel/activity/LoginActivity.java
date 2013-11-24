package com.alexandev.calitreesquirrel.activity;


import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.task.LoginTask;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		PreferencesCheck pref = new PreferencesCheck();
		if ( (pref.getLoggedIn(this).get("username") != null) && (pref.getLoggedIn(this).get("password") != null) ){
			Intent intent = new Intent( this, ScreenSlideActivity.class );
			this.startActivity( intent );
		}


	}

	@Override
	protected void onResume(){
		super.onResume();
		
		PreferencesCheck pref = new PreferencesCheck();
		if ( (pref.getLoggedIn(this).get("username") != null) && (pref.getLoggedIn(this).get("password") != null) ){
			Intent intent = new Intent( this, ScreenSlideActivity.class );
			this.startActivity( intent );
		}
	}

	// Called when the user clicks the Register 
	public void sendToRegister( View view )
	{
		Intent intent = new Intent( this, RegisterActivity.class );
		startActivity( intent );
	}

	// Called when the user clicks the Login 
	public void checkLogin( View view )
	{
		String username = ((EditText) findViewById( R.id.TextUsername )).getText().toString();

		String password = ((EditText) findViewById( R.id.TextPassword )).getText().toString();

		LoginTask loginTask = new LoginTask(this);
		loginTask.execute( username, password, getString( R.string.loginURL ) );
	}

}
