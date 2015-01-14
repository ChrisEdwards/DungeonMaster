package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;
import com.genoseid.syk0tik.dungeonmaster.components.entities.EntityMap;
import com.genoseid.syk0tik.dungeonmaster.components.entities.Player;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Map;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Sprite;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Tile;

public class Graphics implements com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Graphics {

	public GameActivity parent;

	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public Graphics(GameActivity parent, Bitmap frameBuffer) {
		this.parent = parent;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();

	}

	@Override
	public void drawMap(Map map, int x, int y) {
		for (int xPos = 0; xPos < map.width; xPos++) {
			for (int yPos = 0; yPos < map.height; yPos++) {
				if (map.tiles[xPos + yPos * map.width] == null) {
					canvas.drawBitmap(Sprite.voidSprite.bmp, xPos * map.scale, yPos * map.scale, paint);
				} else {
					canvas.drawBitmap(map.tiles[xPos + yPos * map.width].sprite.bmp, xPos * map.scale, yPos * map.scale, paint);
				}
			}
		}
	}

	@Override
	public void drawEntities(EntityMap entities) {}

	@Override
	public void drawPlayer(Player player) {}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
	}

	@Override
	public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {

	}

	@Override
	public void drawImage(Image Image, int x, int y) {

	}

	@Override
	public void drawARGB(int a, int r, int g, int b) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawARGB(a, r, g, b);
	}

	@Override
	public void drawString(String text, int x, int y, Paint paint){
		canvas.drawText(text, x, y, paint);
	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}

	@Override
	public Image newImage(String fileName, ImageFormat format) {

		return null;
	}

	@Override
	public void clearScreen(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}

}
