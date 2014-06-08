package com.isogai.helloworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

public class MyNetworkImageView extends NetworkImageView {
	
	public MyNetworkImageView(Context context) {
		super(context);
	}
	
	public MyNetworkImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	public MyNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	 @Override
	 public void setImageBitmap(Bitmap bm) {
		 TransitionDrawable td = new TransitionDrawable(new Drawable[]{
				 new ColorDrawable(android.R.color.transparent),
				 new BitmapDrawable(getContext().getResources(), bm)
				 });
		 
		 setImageDrawable(td);
		 td.startTransition(1000);
	 }

}
