package com.alexandev.calitreesquirrel.activity;

import com.alexandev.calitreesquirrel.R;

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
	 
    public void sendToLoginPage( View view )
    {
        Intent intent = new Intent( this, LoginActivity.class );
        startActivity( intent );
    }
}
