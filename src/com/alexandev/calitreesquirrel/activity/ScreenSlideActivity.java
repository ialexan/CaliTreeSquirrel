// Ishag Alexnaian

package com.alexandev.calitreesquirrel.activity;

import java.util.List;

import com.alexandev.calitreesquirrel.R;
import com.alexandev.calitreesquirrel.fragment.ScreenSlidePageFragment;
import com.alexandev.calitreesquirrel.task.SubmitPhotoTask;
import com.alexandev.calitreesquirrel.util.NetworkDataConnection;
import com.alexandev.calitreesquirrel.util.PreferencesCheck;
import com.alexandev.calitreesquirrel.util.StorageSighting;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.MenuItem;
import android.widget.Toast;

public class ScreenSlideActivity extends FragmentActivity  {

	// The number of pages (wizard steps) to show in this demo.
	private static final int NUM_PAGES = 5;

	//The pager widget, which handles animation and allows swiping horizontally to access previous
	//and next wizard steps.
	private ViewPager mPager;

	//The pager adapter, which provides the pages to the view pager widget.
	private PagerAdapter mPagerAdapter;

	PreferencesCheck pref = new PreferencesCheck();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);
		
		
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When changing pages, reset the action bar actions since they are dependent
				// on which page is currently active. An alternative approach is to have each
				// fragment expose actions itself (rather than the activity exposing actions),
				// but for simplicity, the activity provides the actions in this sample.
				invalidateOptionsMenu();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

		menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

		// Add either a "next" or "finish" button to the action bar, depending on which page
		// is currently selected.
		MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
				(mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
				? R.string.action_finish
						: R.string.action_next);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.action_previous:
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			return true;

		case R.id.action_next:
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			return true;

		case R.id.action_send_saved_sightings:
			// This is where you send the list of sightings

 		   if (new NetworkDataConnection(this).checkConnection()){
 			  List<Bundle> dataList = new StorageSighting().read(this.getApplicationContext()); 
 			  
 			  if (dataList.isEmpty())
 					Toast.makeText( this, "No Saved Sightings, Nothing is Sent!", Toast.LENGTH_LONG ).show();
 			  else{
 				 for (Bundle mBundle : dataList)
 					 new SubmitPhotoTask(this).execute(mBundle.getString("timestamp"), mBundle.getString( "latitude"), mBundle.getString( "longitude"), 
 							 mBundle.getInt("species")+"", this.getString( R.string.sendURL ), "noPic" );
 				 
 				 Toast.makeText( this, "Sighting Sent!", Toast.LENGTH_LONG ).show();
 			  }
          			
  		   	} 
 		   	else 
 		   		Toast.makeText( this, "No Network Connection, Saved Sightings not sent!", Toast.LENGTH_LONG ).show();   		 
		
			return true;
			
		case R.id.action_info:    	
			startActivity( new Intent( this, CoverPageActivity.class ) );
			return true; 
			
		case R.id.action_instructions:
			startActivity( new Intent( this, InstructionsActivity.class ) );
			return true;
			
		case R.id.action_signout:    
			pref.setLoggedIn( this, null, null );
			startActivity( new Intent( this, LoginActivity.class ) );
			return true;     

		}

		return super.onOptionsItemSelected(item);
	}


	//A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in sequence.
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return ScreenSlidePageFragment.create(position);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}

}
