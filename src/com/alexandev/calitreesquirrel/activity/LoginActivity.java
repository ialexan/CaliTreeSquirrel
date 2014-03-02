package com.alexandev.calitreesquirrel.activity;


import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.task.LoginTask;
import com.alexandev.calitreesquirrel.util.NetworkDataConnection;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		PreferencesCheck pref = new PreferencesCheck();
		if ( (pref.getLoggedIn(this).get("username") != null) && (pref.getLoggedIn(this).get("password") != null) ){
			Intent intent = new Intent( this, ScreenSlideActivity.class );
			this.startActivity( intent );
			finish();
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

		if ( username.isEmpty() )
			Toast.makeText( this, "Username field is empty", Toast.LENGTH_SHORT ).show();
		else if ( password.isEmpty() )
			Toast.makeText( this, "Password field is empty", Toast.LENGTH_SHORT ).show();
		else {
			if (new NetworkDataConnection(this).checkConnection())
				new LoginTask(this).execute( username, password, getString( R.string.loginURL ) );
			else
				Toast.makeText( this, "No Network Connection!", Toast.LENGTH_LONG ).show(); 
		}

	}

}
