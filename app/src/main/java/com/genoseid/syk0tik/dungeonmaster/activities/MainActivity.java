package com.genoseid.syk0tik.dungeonmaster.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.genoseid.syk0tik.dungeonmaster.components.systems.SaveData;
import com.genoseid.syk0tik.dungeonmaster.components.systems.Settings;
import com.genoseid.syk0tik.dungeonmaster.R;

import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_SAVE_STATUS = "com.genoseid.syk0tik.dungeonmaster.LEVEL_SETTINGS";
	public static SharedPreferences sharedPreferences;

    public void newGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_SAVE_STATUS, 4);
	    startActivity(intent);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
	    this.sharedPreferences = sharedPreferences;

        /*
        ** SaveData will be written in the following format:
        ** Save 0 Game Level, Save 0 Player Level, Save 0 Player Name, Save 1 Game Level, etc.
         */
        String saveDataString = sharedPreferences.getString("SaveData", null);
        if (saveDataString != null) {
            Scanner stringScanner = new Scanner(saveDataString);
            for (int i = 0; i < SaveData.savedGames.length; i++) {
                SaveData.savedGames[i].gameLevel = stringScanner.nextInt();
                SaveData.savedGames[i].playerLevel = stringScanner.nextInt();
                SaveData.savedGames[i].playerName = stringScanner.next();
            }
        }

        String gameSettingsString = sharedPreferences.getString("GameSettings", null);
	    Settings gameSettings = Settings.getGameSettings();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPreferences();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
