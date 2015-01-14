package com.genoseid.syk0tik.dungeonmaster.components.leveldata;

import android.graphics.Bitmap;

public class Sprite {

	public int size;
	public Bitmap bmp;

	public static Sprite grassSprite = new Sprite(SpriteSheet.environment, 0, 0);
	public static Sprite wallSprite = new Sprite(SpriteSheet.environment, 1, 0);
	public static Sprite rockSprite = new Sprite(SpriteSheet.environment, 2, 0);
	public static Sprite voidSprite = new Sprite(SpriteSheet.environment, 3, 0);

	public Sprite(SpriteSheet sheet, int xLoc, int yLoc) {
		this.size = sheet.scale;
		bmp = Bitmap.createBitmap(sheet.bmp, xLoc * size, yLoc * size, size, size);
	}

}
