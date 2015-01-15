package com.genoseid.syk0tik.dungeonmaster.components.leveldata;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.genoseid.syk0tik.dungeonmaster.R;
import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;

import java.io.IOException;

public class SpriteSheet {

	public static SpriteSheet environment;

	public Bitmap bmp;
	public int scale;

	public SpriteSheet(Bitmap bmp, int scale) {
		this.bmp = bmp;
		this.scale = scale;

	}

	public static void loadGraphics(GameActivity parent) {
		Bitmap bmp = BitmapFactory.decodeResource(parent.getResources(), R.drawable.environment);
		if (bmp == null) {
			System.out.println("NULL FOO");
		}
		environment = new SpriteSheet(bmp, 16);

	}

}
