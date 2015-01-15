package com.genoseid.syk0tik.dungeonmaster.components.entities;

import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Map;

public class EntityMap {
	public Entity[] entities;
	public EntityMap(int width, int height) {
		entities = new Entity[width * height];
	}

	public void update(Player player, Map level) {}

}
