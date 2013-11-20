package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.spellmaker.danmaku.Danmaku;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "danmaku";
		cfg.useGL20 = false;
		cfg.width = 1600;
		cfg.height = 960;
		
		new LwjglApplication(new Danmaku(), cfg);
	}
}
