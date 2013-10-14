package com.alexandev.calitreesquirrel.activity;


import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}

//	@Override
//	protected void onResume(){
//		Intent intent = new Intent(this, ScreenSlideActivity.class);
//        startActivity(intent);
//	}

	
    // Called when the user clicks the Login 
    public void checkLogin( View view )
    {
        Intent intent = new Intent( this, ScreenSlideActivity.class );
        startActivity( intent );
    }
    
    // Called when the user clicks the Register 
    public void sendToRegister( View view )
    {
        Intent intent = new Intent( this, RegisterActivity.class );
        startActivity( intent );
    }
    
}