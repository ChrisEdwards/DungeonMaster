package com.genoseid.syk0tik.dungeonmaster.components.leveldata;

public class Tile {

	public int x, y;
	public Sprite sprite;
	public boolean collidable;

	public static Tile grassTile = new Tile(Sprite.grassSprite, false);
	public static Tile wallTile = new Tile(Sprite.wallSprite, true);
	public static Tile rockTile = new Tile(Sprite.rockSprite, true);
	public static Tile voidTile = new Tile(Sprite.voidSprite, true);


	public Tile(Sprite sprite, boolean collidable) {
		this.sprite = sprite;
		this.collidable = collidable;
	}

	public void update() {}

}
