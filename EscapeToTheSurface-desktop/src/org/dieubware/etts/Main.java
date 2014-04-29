package org.dieubware.etts;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Ninja escape";
		cfg.useGL20 = true;
		cfg.width = 800;cfg.height = 600;
		//cfg.width = 240;cfg.height = 320;
		new LwjglApplication(new EttsGame(), cfg);
	}
}
