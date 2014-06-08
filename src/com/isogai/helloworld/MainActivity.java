package com.isogai.helloworld;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {
	final MainActivity self = this;
	private static List<String> urlStrings = new ArrayList<String>();
	private static int counter = 0;
	private static NetworkImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private ImageLoader imageLoader;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			urlStrings
					.add("http://image1-1.tabelog.k-img.com/rvw/5f31cb8/660x370c/24879613.jpg");
			urlStrings
					.add("http://image1-1.tabelog.k-img.com/rvw/19b6cb7/660x370c/24887558.jpg");
			urlStrings
					.add("http://image1-2.tabelog.k-img.com/rvw/4c403bb/660x370c/25323250.jpg");

			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			imageView = (NetworkImageView) rootView
					.findViewById(R.id.network_image_view);

			Runnable rotateImages = new Runnable() {

				@Override
				public void run() {
					if (urlStrings == null || urlStrings.size() < 0
							|| urlStrings.size() - 1 < counter) {
						return;
					}

					if (imageLoader == null) {
						imageLoader = new ImageLoader(
								Volley.newRequestQueue(getActivity()),
								new LruCacheSample());
					}
					
					imageView.setImageUrl(urlStrings.get(counter++),
							imageLoader);

					if (counter > 2)
						counter = 0;

					Handler handler = new Handler();
					handler.postDelayed(this, 10000);
				}
			};

			Handler handler = new Handler();
			handler.post(rotateImages);

			return rootView;
		}
	}

}
