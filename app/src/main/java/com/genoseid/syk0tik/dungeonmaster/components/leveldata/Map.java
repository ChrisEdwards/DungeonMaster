package com.genoseid.syk0tik.dungeonmaster.components.leveldata;

import com.genoseid.syk0tik.dungeonmaster.components.entities.EntityMap;
import com.genoseid.syk0tik.dungeonmaster.components.entities.Player;

import java.util.Random;

public class Map {

	public int width, height, scale;
	public Tile[] tiles;

	public Map(int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		tiles = new Tile[width * height];
	}

	// Generate new random level
	public void generateLevel() {

		Random random = new Random();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
					tiles[x + y * width] = Tile.wallTile;
				} else {
					if (random.nextInt(5) == 0) {
						tiles[x + y * width] = Tile.rockTile;
					} else {
						tiles[x + y * width] = Tile.grassTile;
					}
				}
			}
		}
	}

	public void update(Player player, EntityMap entities) {
		for (Tile tile : tiles) {
			tile.update();
		}

	}

	// Returns tile at given location
	public Tile getTile(int xLoc, int yLoc) {
		if(xLoc < 0 || yLoc < 0 || xLoc >= width || yLoc >= height || tiles[xLoc + yLoc * width] == null) {
			return Tile.voidTile;
		}
		return tiles[xLoc + yLoc * width];
	}

}