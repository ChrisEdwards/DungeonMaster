package com.genoseid.syk0tik.dungeonmaster.components.systems;

public class Settings {

	private static Settings gameSettings;

	// Yay! It's a Singleton!
	public static Settings getGameSettings() {

		return gameSettings = (gameSettings == null) ? new Settings() : gameSettings;
	}

	private Settings() {

	}

}
