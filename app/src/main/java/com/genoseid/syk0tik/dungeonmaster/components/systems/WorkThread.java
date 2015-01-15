package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;

public class WorkThread implements Runnable {
	GameActivity parent;
	Bitmap frameBuffer;
	Thread thread = null;
	SurfaceHolder holder;
	private volatile boolean running = false;
	private double fps = 30.0;

	public WorkThread(GameActivity parent, Bitmap frameBuffer) {

		this.parent = parent;
		this.frameBuffer = frameBuffer;

	}

	public void start(View view) {

		holder = view.getHolder();
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {

		Canvas canvas;
		Rect dstRect = new Rect();
		double ns = 1000000000.0 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();

		while (running) {
			if (!holder.getSurface().isValid()) continue;


			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				parent.handler.update();
				parent.handler.render();
				delta = 0;
			}

			canvas = null;
			try {

				canvas = holder.lockCanvas(null);
				canvas.getClipBounds(dstRect);
				canvas.drawBitmap(frameBuffer, null, dstRect, null);

			} finally {

				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);

				}
			}

		}
	}

	public void stop() {

		running = false;
		while (true) {
			try {
				thread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}

		}
	}
}

