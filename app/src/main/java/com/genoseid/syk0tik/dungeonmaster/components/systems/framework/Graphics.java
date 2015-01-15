package com.genoseid.syk0tik.dungeonmaster.components.systems.framework;

import android.graphics.Paint;
import android.media.Image;

import com.genoseid.syk0tik.dungeonmaster.components.entities.EntityMap;
import com.genoseid.syk0tik.dungeonmaster.components.entities.Player;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Map;
import com.genoseid.syk0tik.dungeonmaster.components.leveldata.Tile;

public interface Graphics {

	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public void setView(int xView, int yView);

	public Image newImage(String fileName, ImageFormat format);

	public void drawMap(Map map);

	public void drawEntities(EntityMap entities);

	public void drawPlayer(Player player);

	public void drawTile(Tile tile, int xLoc, int yLoc);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);

	public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

	public void drawImage(Image Image, int x, int y);

	void drawString(String text, int x, int y, Paint paint);

	public int getWidth();

	public int getHeight();

	public void drawARGB(int i, int j, int k, int l);

	public void clearScreen(int color);

}