package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.task.RegisterTask;
import com.alexandev.calitreesquirrel.util.NetworkDataConnection;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	// Called when the user clicks the Register
	public void registerUser( View view )
	{   
		String username = ((EditText) findViewById( R.id.username_input )).getText().toString();
		String password = ((EditText) findViewById( R.id.password_input )).getText().toString();
		String email = ((EditText) findViewById( R.id.email_input )).getText().toString();
		String firstname = ((EditText) findViewById( R.id.firstname_input )).getText().toString();
		String lastname = ((EditText) findViewById( R.id.lastname_input )).getText().toString();


		if ( firstname.isEmpty() )
			Toast.makeText( this, "Firstname field is empty", Toast.LENGTH_SHORT ).show();
		else if ( lastname.isEmpty() )
			Toast.makeText( this, "Lastname field is empty", Toast.LENGTH_SHORT ).show();
		else if ( email.isEmpty() )
			Toast.makeText( this, "Email field is empty", Toast.LENGTH_SHORT ).show();
		else if ( username.isEmpty() )
			Toast.makeText( this, "Username field is empty", Toast.LENGTH_SHORT ).show();
		else if ( password.isEmpty() )
			Toast.makeText( this, "Password field is empty", Toast.LENGTH_SHORT ).show();
		else {
			if (new NetworkDataConnection(this).checkConnection())
				new RegisterTask(this).execute( username, password, firstname, lastname, email, getString( R.string.registerURL ) );
			else
				Toast.makeText( this, "No Network Connection!", Toast.LENGTH_LONG ).show(); 
		}
		
	}

}
