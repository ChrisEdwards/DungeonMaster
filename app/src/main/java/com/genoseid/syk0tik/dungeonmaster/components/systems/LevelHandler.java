package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.graphics.Color;
import android.graphics.Paint;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;
import com.genoseid.syk0tik.dungeonmaster.components.entities.EntityMap;
import com.genoseid.syk0tik.dungeonmaster.components.entities.Player;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Map;
import com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Controls.TouchEvent;

import java.util.List;

public class LevelHandler {

	enum GameState {
		Loading, Menu, Ready, Running, Paused, GameOver
	}

	public Map level;
	public EntityMap entities;
	public Player player;
	private GameActivity parent;
	private GameState state = GameState.Ready;
	private Paint paint;
	private int xView, yView;

	public LevelHandler(GameActivity parent) {
		this.parent = parent;
		level = new Map(64, 64, 16);
		level.generateLevel();

		entities = new EntityMap(64, 64);
		player = new Player();

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

	}

	public void update() {

		List<TouchEvent> touchEvents = parent.controls.getTouchEvents();
		if (state == GameState.Loading) updateLoading();
		if (state == GameState.Menu) updateMenu(touchEvents);
		if (state == GameState.Ready) updateReady(touchEvents);
		if (state == GameState.Running) updateRunning(touchEvents);
		if (state == GameState.Paused) updatePaused(touchEvents);
		if (state == GameState.GameOver) updateGameOver(touchEvents);

	}

	private void updateLoading() {}

	private void updateMenu(List<TouchEvent> touchEvents) {}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents) {


	}

	private void updatePaused(List<TouchEvent> touchEvents) {

		if (touchEvents.size() > 0) state = GameState.Running;

	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		for (int i = 0; i < touchEvents.size(); i++) {
			Controls.TouchEvent event = touchEvents.get(i);
			if (event.type == Controls.TouchEvent.TOUCH_UP) {
				if (event.x > 300 && event.x < 980 && event.y > 100 && event.y < 500) {
					clear();
					state = GameState.Menu;
					return;
				}
			}
		}
	}

	private void updateCamera() {
		xView = 0;
		yView = 0;
	}

	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	public void paint() {

		if (state == GameState.Ready) drawReadyUI();
		if (state == GameState.Running) drawRunningUI();
		if (state == GameState.Paused) drawPausedUI();
		if (state == GameState.GameOver) drawGameOverUI();

	}

	private void drawReadyUI() {
		Graphics g = parent.graphics;

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap each side of the screen to move in that direction.", 640, 300, paint);

	}

	private void drawRunningUI() {

		Graphics g = parent.graphics;

		g.drawMap(level, xView, yView);
	}

	private void drawPausedUI() {
		Graphics g = parent.graphics;

		g.drawARGB(155, 0, 0, 0);

	}

	private void drawGameOverUI() {
		Graphics g = parent.graphics;

		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 640, 300, paint);

	}

	private void clear() {

		paint = null;

		System.gc();

	}

}