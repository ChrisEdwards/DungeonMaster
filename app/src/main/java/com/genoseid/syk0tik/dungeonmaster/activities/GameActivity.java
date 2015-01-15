package com.genoseid.syk0tik.dungeonmaster.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.genoseid.syk0tik.dungeonmaster.components.leveldata.SpriteSheet;
import com.genoseid.syk0tik.dungeonmaster.components.systems.*;

public class GameActivity extends Activity {

	public View view;
	public WorkThread thread;
	public Graphics graphics;
	public Audio audio;
	public Controls controls;
	public LevelHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Get save status from Main Activity and set as active game
		SaveData.activeGameNumber = getIntent().getIntExtra(MainActivity.EXTRA_SAVE_STATUS, SaveData.savedGames.length);
		if (SaveData.activeGameNumber == 4) {
			SaveData.activeGame = new SaveData();
		} else {
			SaveData.activeGame = SaveData.savedGames[SaveData.activeGameNumber];
		}

		/* !Q!
		** Is there anything wrong with doing this?
		** It seems to be the only way to call my
		** methods without passing myself around.
		 */
		SaveData.activeGame.gameActivity = this;

		// Load graphics into SpriteSheets
		SpriteSheet.loadGraphics(this);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		super.onCreate(savedInstanceState);

		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		int frameBufferWidth = isPortrait ? 800 : 1200;
		int frameBufferHeight = isPortrait ? 1200 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		float scaleX = (float) frameBufferWidth / displaymetrics.widthPixels;
		float scaleY = (float) frameBufferHeight / displaymetrics.heightPixels;

		// Initialize components
		thread = new WorkThread(this, frameBuffer);
		view = new View(this);
		graphics = new Graphics(this, frameBuffer, frameBufferWidth, frameBufferHeight);
		controls = new Controls(this, scaleX, scaleY);
		handler = new LevelHandler(this);
		setContentView(view);

	}

	public void startGame(View view) {
		thread.start(view);
	}


	@Override
	public void onResume() {
		super.onResume();
		thread.start(view);
	}

	@Override
	public void onPause() {
		super.onPause();
		thread.stop();
		handler.pause();
	}

	// Disables back button
	@Override
	public void onBackPressed() {
	}

	// Called by saveGame if all saves full
	public int overwriteCheck() {

		return 4;

	}

}
