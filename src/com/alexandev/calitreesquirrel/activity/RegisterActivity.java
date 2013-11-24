package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.task.RegisterTask;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
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
    public void registerUser( View view )
    {   
		String username = ((EditText) findViewById( R.id.username_input )).getText().toString();
		String password = ((EditText) findViewById( R.id.password_input )).getText().toString();
		String email = ((EditText) findViewById( R.id.email_input )).getText().toString();
		String firstname = ((EditText) findViewById( R.id.firstname_input )).getText().toString();
		String lastname = ((EditText) findViewById( R.id.lastname_input )).getText().toString();
		
		RegisterTask registerTask = new RegisterTask(this);
		registerTask.execute( username, password, firstname, lastname, email, getString( R.string.registerURL ) );
    }

}
