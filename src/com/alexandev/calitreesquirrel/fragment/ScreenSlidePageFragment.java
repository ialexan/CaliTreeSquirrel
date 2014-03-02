// Ishag Alexnaian

package com.alexandev.calitreesquirrel.fragment;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.alexandev.calitreesquirrel.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ScreenSlidePageFragment extends Fragment implements LocationListener{
	
	// The argument key for the page number this fragment represents.
	public static final String ARG_PAGE = "page";

	// The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
	private int mPageNumber;


	// Hold a reference to the current animator, so that it can be canceled mid-way.
	private Animator mCurrentAnimator;

	// The system "short" animation time duration, in milliseconds. This duration is ideal for
	// subtle animations or animations that occur very frequently.
	private int mShortAnimationDuration;

	private static Double latitude;
	private static Double longitude;
	private LocationManager locationManager;


	// Factory method for this fragment class. Constructs a new fragment for the given page number.
	public static ScreenSlidePageFragment create(int pageNumber) {
		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ScreenSlidePageFragment() {
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPageNumber = getArguments().getInt(ARG_PAGE);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final ViewGroup rootView;



		if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
		{
			// Inflate the layout containing a title and body text.
			rootView = (ViewGroup) inflater
					.inflate(R.layout.fragment_screen_slide_page, container, false);
		} else {
			rootView = (ViewGroup) inflater
					.inflate(R.layout.fragment_screen_slide_page_landscape, container, false);
		}

		// Set the title view to show the page number.
		//((TextView) rootView.findViewById(android.R.id.text1)).setText(
		//        getString(R.string.title_template_step, mPageNumber + 1));
		switch (mPageNumber + 1) {
		case 1:
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.name1));
			((TextView) rootView.findViewById(android.R.id.text2)).setText(
					getString(R.string.desciption1));   
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(
					R.drawable.squirrel1);     			
			((Button) rootView.findViewById(R.id.buttonPhototakenBy)).setText(
					getString(R.string.photoby1)); 
			break;
			
		case 2:
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.name2));
			((TextView) rootView.findViewById(android.R.id.text2)).setText(
					getString(R.string.desciption2));
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(
					R.drawable.squirrel2); 
			((Button) rootView.findViewById(R.id.buttonPhototakenBy)).setText(
					getString(R.string.photoby2)); 
			break;
			
		case 3:
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.name3));
			((TextView) rootView.findViewById(android.R.id.text2)).setText(
					getString(R.string.desciption3));
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(
					R.drawable.squirrel3); 
			((Button) rootView.findViewById(R.id.buttonPhototakenBy)).setText(
					getString(R.string.photoby3)); 
			break;
			
		case 4:
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.name4));
			((TextView) rootView.findViewById(android.R.id.text2)).setText(
					getString(R.string.desciption4));
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(
					R.drawable.squirrel4); 
			((Button) rootView.findViewById(R.id.buttonPhototakenBy)).setText(
					getString(R.string.photoby4)); 
			break;
		case 5:
			((TextView) rootView.findViewById(android.R.id.text1)).setText(
					getString(R.string.name5));
			((TextView) rootView.findViewById(android.R.id.text2)).setText(
					getString(R.string.desciption5));
			((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(
					R.drawable.squirrel5); 
			((Button) rootView.findViewById(R.id.buttonPhototakenBy)).setText(
					getString(R.string.photoby5)); 
		}


		final View thumb1View = rootView.findViewById(R.id.imageView1);
		thumb1View.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if ((mPageNumber + 1) == 1)
					zoomImageFromThumb(thumb1View, R.drawable.squirrel1_large, rootView);
				else if ((mPageNumber + 1) == 2)
					zoomImageFromThumb(thumb1View, R.drawable.squirrel2_large, rootView);
				else if ((mPageNumber + 1) == 3)
					zoomImageFromThumb(thumb1View, R.drawable.squirrel3_large, rootView);
				else if ((mPageNumber + 1) == 4)
					zoomImageFromThumb(thumb1View, R.drawable.squirrel4_large, rootView);
				else if ((mPageNumber + 1) == 5)
					zoomImageFromThumb(thumb1View, R.drawable.squirrel5_large, rootView);

			}
		});

		// Retrieve and cache the system's default "short" animation time.
		mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);


		// Get the location manager
		locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		final String provider = locationManager.getBestProvider(criteria, false);

		final LocationListener locListener = this;
		final String name = ((TextView) rootView.findViewById(android.R.id.text1)).getText().toString() ;

		View sawItButtonView = (Button) rootView.findViewById(R.id.button1);
		
	
		sawItButtonView.setOnClickListener( new View.OnClickListener() {
			public void onClick(final View view) {
				
				locationManager.requestLocationUpdates(provider, 400, 1, locListener);
				Location location = locationManager.getLastKnownLocation(provider);

				// GPS check
				if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
					FragmentTransaction ft = getFragmentManager().beginTransaction();
				    SquirrelsDialogFragment dialogFragment = new SquirrelsDialogFragment();
					dialogFragment.setCurrentActivity(getActivity());
			        dialogFragment.setMessage("Your GPS seems to be disabled, do you want to enable it?");
			        dialogFragment.setPositiveButtonMessage("Yes");
			        dialogFragment.setNegativeButtonMessage("No");
			        dialogFragment.show(ft, "dialog");
		        }
							
				latitude =  location.getLatitude();
				longitude = location.getLongitude();
		
				String date =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SS").format(new Date());

				sendToServer(date, latitude, longitude, name);
				locationManager.removeUpdates(locListener);				
			}
		});

		return rootView;
	}



	// Returns the page number represented by this fragment object.
	public int getPageNumber() {
		return mPageNumber;
	}


	// Sending the location coordinates, timestamp and squirrel type to the server
	public void sendToServer(String timestamp, Double latitude, Double longitude, String squirrelName){
		
		int species = 0;

		if ( squirrelName == getString(R.string.name1))
			species = 1;
		else if ( squirrelName == getString(R.string.name2))
			species = 2;
		else if ( squirrelName == getString(R.string.name3))
			species = 3;
		else if ( squirrelName == getString(R.string.name4))
			species = 4;
		else if ( squirrelName == getString(R.string.name5))
			species = 5;
		
		Bundle mBundle = new Bundle();
		mBundle.putString("timestamp", timestamp);
		mBundle.putString( "latitude", latitude.toString());
		mBundle.putString( "longitude", longitude.toString());
		mBundle.putInt( "species", species);

		
		if ( locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			SquirrelsDialogFragment dialogFragment = new SquirrelsDialogFragment();
	    
			dialogFragment.setCurrentActivity(getActivity());
			dialogFragment.setmBundle(mBundle);
			dialogFragment.setMessage("Do you want to submit the sighting ?");
			dialogFragment.setPositiveButtonMessage("Yes, Send");
			dialogFragment.setNegativeButtonMessage("No");
			dialogFragment.show(ft, "dialog");
		}
		
	
	}

	

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this.getActivity(), "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this.getActivity(), "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}



	/**
	 * "Zooms" in a thumbnail view by assigning the high resolution image to a hidden "zoomed-in"
	 * image view and animating its bounds to fit the entire activity content area. More
	 * specifically:
	 *
	 * <ol>
	 *   <li>Assign the high-res image to the hidden "zoomed-in" (expanded) image view.</li>
	 *   <li>Calculate the starting and ending bounds for the expanded view.</li>
	 *   <li>Animate each of four positioning/sizing properties (X, Y, SCALE_X, SCALE_Y)
	 *       simultaneously, from the starting bounds to the ending bounds.</li>
	 *   <li>Zoom back out by running the reverse animation on click.</li>
	 * </ol>
	 *
	 * @param thumbView  The thumbnail view to zoom in.
	 * @param imageResId The high-resolution version of the image represented by the thumbnail.
	 */
	private void zoomImageFromThumb(final View thumbView, int imageResId, View rootView) {
		// If there's an animation in progress, cancel it immediately and proceed with this one.
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}

		// Load the high-resolution "zoomed-in" image.
		final ImageView expandedImageView = (ImageView) rootView.findViewById(R.id.expanded_image);
		expandedImageView.setImageResource(imageResId);

		// Calculate the starting and ending bounds for the zoomed-in image. This step
		// involves lots of math. Yay, math.
		final Rect startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();

		// The start bounds are the global visible rectangle of the thumbnail, and the
		// final bounds are the global visible rectangle of the container view. Also
		// set the container view's offset as the origin for the bounds, since that's
		// the origin for the positioning animation properties (X, Y).
		thumbView.getGlobalVisibleRect(startBounds);
		rootView.findViewById(R.id.content).getGlobalVisibleRect(finalBounds, globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

		// Adjust the start bounds to be the same aspect ratio as the final bounds using the
		// "center crop" technique. This prevents undesirable stretching during the animation.
		// Also calculate the start scaling factor (the end scaling factor is always 1.0).
		float startScale;
		if ((float) finalBounds.width() / finalBounds.height()
				> (float) startBounds.width() / startBounds.height()) {
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		} else {
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}

		// Hide the thumbnail and show the zoomed-in view. When the animation begins,
		// it will position the zoomed-in view in the place of the thumbnail.
		thumbView.setAlpha(0f);
		expandedImageView.setVisibility(View.VISIBLE);

		// Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
		// the zoomed-in view (the default is the center of the view).
		expandedImageView.setPivotX(0f);
		expandedImageView.setPivotY(0f);

		// Construct and run the parallel animation of the four translation and scale properties
		// (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		set
		.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
				finalBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
						finalBounds.top))
						.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
						.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mCurrentAnimator = null;
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;

		// Upon clicking the zoomed-in image, it should zoom back down to the original bounds
		// and show the thumbnail instead of the expanded image.
		final float startScaleFinal = startScale;
		expandedImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mCurrentAnimator != null) {
					mCurrentAnimator.cancel();
				}

				// Animate the four positioning/sizing properties in parallel, back to their
				// original values.
				AnimatorSet set = new AnimatorSet();
				set
				.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
				.with(ObjectAnimator
						.ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
						.with(ObjectAnimator
								.ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new DecelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						thumbView.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation) {
						thumbView.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}
				});
				set.start();
				mCurrentAnimator = set;
			}
		});
	}



}
