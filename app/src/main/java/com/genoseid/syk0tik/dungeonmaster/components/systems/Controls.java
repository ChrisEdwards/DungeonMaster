package com.genoseid.syk0tik.dungeonmaster.components.systems;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;
import com.genoseid.syk0tik.dungeonmaster.components.systems.framework.MultiTouchHandler;

import java.util.List;

public class Controls implements com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Controls {
	MultiTouchHandler handler;

	public Controls(GameActivity parent, float scaleX, float scaleY) {
		handler = new MultiTouchHandler(parent.view, scaleX, scaleY);
	}

	public boolean isTouchDown(int pointer) {
		return handler.isTouchDown(pointer);
	}

	public int getTouchX(int pointer) {
		return handler.getTouchX(pointer);
	}

	public int getTouchY(int pointer) {
		return handler.getTouchY(pointer);
	}

	public List<com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Controls.TouchEvent> getTouchEvents() {
		return handler.getTouchEvents();
	}

}