package com.genoseid.syk0tik.dungeonmaster.components.entities;

import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Map;
import com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Controls.TouchEvent;

import java.util.List;

public class Player {

	private int xLoc, yLoc;

	public Player(int xStart, int yStart) {
		xLoc = xStart;
		yLoc = yStart;
	}
	public void update(Map level, EntityMap entities, List<TouchEvent> touchEvents) {

	}
}