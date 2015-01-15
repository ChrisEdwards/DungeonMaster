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
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Tile;

public class Graphics implements com.genoseid.syk0tik.dungeonmaster.components.systems.framework.Graphics {

	public GameActivity parent;

	private Bitmap frameBuffer;
	private Canvas canvas;
	private Paint paint;
	private Rect srcRect = new Rect();
	private Rect dstRect = new Rect();
	private int xView, yView, frameBufferWidth, frameBufferHeight;

	public Graphics(GameActivity parent, Bitmap frameBuffer, int frameBufferWidth, int frameBufferHeight) {

		this.parent = parent;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
		this.frameBufferWidth = frameBufferWidth;
		this.frameBufferHeight = frameBufferHeight;

	}

	@Override
	public void setView(int xView, int yView) {

		this.xView = xView;
		this.yView = yView;
	}


	/* !TODO!
	** Play around with the x1 and y1 values to make
	** sure we're not drawing too far out of bounds
	*/
	// Cycles and draws through visible tiles
	@Override
	public void drawMap(Map map) {

		// Set screen bounds
		int x0 = xView >> 4;
		int x1 = (xView + frameBufferWidth + 16) >> 4;
		int y0 = yView >> 4;
		int y1 = (yView + frameBufferHeight + 16) >> 4;

		for (int yLoc = y0; yLoc < y1; yLoc++) {
			for (int xLoc = x0; xLoc < x1; xLoc++) {
				drawTile(map.getTile(xLoc, yLoc), xLoc << 4, yLoc << 4);
			}
		}
	}

	@Override
	public void drawEntities(EntityMap entities) {

	}

	@Override
	public void drawPlayer(Player player) {

	}

	@Override
	public void drawTile(Tile tile, int xLoc, int yLoc) {

		xLoc -= xView;
		yLoc -= yView;
		canvas.drawBitmap(tile.sprite.bmp, xLoc, yLoc, null);
	}

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
	public void drawString(String text, int x, int y, Paint paint) {

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

		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
	}

}