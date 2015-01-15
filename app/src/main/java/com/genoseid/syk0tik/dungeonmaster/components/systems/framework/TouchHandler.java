package com.genoseid.syk0tik.dungeonmaster.components.systems.framework;

import android.view.View.OnTouchListener;

import com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Controls.TouchEvent;

import java.util.List;

public interface TouchHandler extends OnTouchListener {

	public boolean isTouchDown(int pointer);

	public int getTouchX(int pointer);

	public int getTouchY(int pointer);

	public List<TouchEvent> getTouchEvents();
}
