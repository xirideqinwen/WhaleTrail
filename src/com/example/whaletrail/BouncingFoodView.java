package com.example.whaletrail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class BouncingFoodView extends View {

	private int xCenter;
	private int yCenter;
	private int y = 0;
	private int yVeloc = Constants.BOUNCE_VELOCITY;
	private int bounceDist = 100;
	private final int FRAME_RATE = 30;
	private Context mContext;
	private Handler handler;

	public BouncingFoodView(Context context) {
		super(context);
		mContext = context;
		handler = new Handler();
	}

	public BouncingFoodView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		handler = new Handler();
	}

	public BouncingFoodView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		handler = new Handler();
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		BitmapDrawable bmap = (BitmapDrawable) mContext.getResources()
				.getDrawable(R.drawable.food);

		// figure out centers
		xCenter = getWidth() / 2 - bmap.getMinimumWidth() / 2;
		yCenter = getHeight() / 2 - bmap.getMinimumHeight() / 2;
		// bounce the bmap up and down
		if (this.y < yCenter || this.y > yCenter+bounceDist) {
			this.yVeloc = -this.yVeloc;
		}
		this.y += this.yVeloc;

		canvas.drawBitmap(bmap.getBitmap(), xCenter, y, null);
		handler.postDelayed(runnable, FRAME_RATE);
	}

}
