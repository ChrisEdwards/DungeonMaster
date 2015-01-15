package com.genoseid.syk0tik.dungeonmaster.components.systems;

import android.content.SharedPreferences;

import com.genoseid.syk0tik.dungeonmaster.activities.GameActivity;
import com.genoseid.syk0tik.dungeonmaster.activities.MainActivity;

public class SaveData {

	/*
	** I'm pretty proud of this class. Although looking at it in retrospect, it's pretty obvious that this
	** implementation was the easiest way to go, it took a lot of back and forth about where to keep the active
	** game and save game processes, once I decided to just figure out how to keep everything under one roof,
	** it became so easy to implement everything. I have my activeGame as a singleton of sorts, accessible
	** from anywhere, and my saveGame went from being a sprawling mess across 4 different classes to a fairly
	** clean-looking method with an almost completely decoupled UI element. It would be nice if I didn't have
	** to load a GameActivity as an instance variable just to access the UI. I'm sure there's a workaround for
	** that I just don't know about yet.
	 */

	public static SaveData[] savedGames = new SaveData[4];
	public static SaveData activeGame;
	public static int activeGameNumber;

	public int gameLevel;
	public int playerLevel;
	public String playerName;
	public GameActivity gameActivity;

	// Initialize new SaveData object with default values
	public SaveData() {

		gameLevel = 0;
		playerLevel = 0;
		playerName = "Newcomer";

	}

	// Saves game to array then to preferences, returns true if not enough space overwrite declined
	public boolean saveGame() {

		if (activeGameNumber == savedGames.length) {
			for (int position = 0; position < savedGames.length; position++) {
				if (savedGames[position] == null) {
					activeGameNumber = position;
					writeProcess();
					return false;
				}
			}
			activeGameNumber = gameActivity.overwriteCheck();
			if (activeGameNumber == SaveData.savedGames.length) return true;
		}
		writeProcess();
		return false;

	}

	private void writeProcess() {

		savedGames[activeGameNumber] = activeGame;
		StringBuilder writeString = new StringBuilder();
		SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();

		for (int i = 0; i < savedGames.length; i++) {
			writeString.append(savedGames[i].gameLevel + " " + savedGames[i].playerLevel + " " + savedGames[i].playerName + " ");
		}

		editor.putString("GameSettings", writeString.toString());
		editor.commit();

	}

}
