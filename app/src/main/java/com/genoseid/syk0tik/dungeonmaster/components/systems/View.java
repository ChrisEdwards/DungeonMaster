package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;

public class View extends SurfaceView implements Callback {

	GameActivity parent;

	public View(GameActivity parent) {

		super(parent);
		this.parent = parent;
		getHolder().addCallback(this);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		parent.onResume();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		parent.onPause();
	}
}

