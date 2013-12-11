package com.example.whaletrail;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class BouncingSkullView extends View {

	private Context mContext;
	private Handler h;
	int x;
	int y = 0;
	private int yVelocity = Constants.BOUNCE_VELOCITY;
	private int width, height;
	private final int FRAME_RATE = 30;

	public BouncingSkullView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		h = new Handler();
		
		//measure the height/width of the view, not the screen
		this.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		width = this.getMeasuredWidth();
		x = width/2;
		height = this.getMeasuredHeight();

	}

	
	private Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};

	protected void onDraw(Canvas c) {
		BitmapDrawable bmap = (BitmapDrawable) mContext.getResources()
				.getDrawable(R.drawable.skull);

		x = width/2;//bmap.getBitmap().getWidth()/2;
		y += yVelocity;

		if ((y>height-bmap.getBitmap().getHeight()) || (y < 0)) {
			yVelocity *= -1;
		}

		c.drawBitmap(bmap.getBitmap(), x, y, null);
		h.postDelayed(r, FRAME_RATE);

	}
}
