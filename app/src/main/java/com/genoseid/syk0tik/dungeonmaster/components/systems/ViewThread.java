package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;

public class ViewThread extends SurfaceView implements Runnable {
	GameActivity parent;
	Bitmap frameBuffer;
	Thread thread = null;
	SurfaceHolder holder;
	private boolean running = false;
	private double fps = 30.0;

	public ViewThread(GameActivity parent, Bitmap frameBuffer) {
		super(parent);
		this.parent = parent;
		this.frameBuffer = frameBuffer;
		this.holder = getHolder();

	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void run() {
		Rect dstRect = new Rect();
		double ns = 1000000000.0 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();

		while(running) {
			if(!holder.getSurface().isValid()) continue;


			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				parent.handler.update();
				parent.handler.paint();
			}

			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(frameBuffer, null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);

		}
	}

	public void stop() {
		running = false;
		while(true) {
			try {
				thread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}

		}
	}


}

