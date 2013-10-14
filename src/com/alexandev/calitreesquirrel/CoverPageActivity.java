package com.alexandev.calitreesquirrel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CoverPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cover_page, menu);
		return true;
	}
	
    // Called when the user clicks the Register 
    public void sendToLoginPage( View view )
    {
        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );
    }
}
