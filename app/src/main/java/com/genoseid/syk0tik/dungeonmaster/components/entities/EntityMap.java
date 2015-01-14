package com.genoseid.syk0tik.dungeonmaster.components.entities;

public class EntityMap {
	public Entity[] entities;
	public EntityMap(int width, int height) {
		entities = new Entity[width * height];
	}

	public void update() {}

}
