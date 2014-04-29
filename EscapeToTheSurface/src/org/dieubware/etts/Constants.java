package org.dieubware.etts;

public class Constants {

	public static final int textureSize = 32;
	public static int playerSize;
	public static int borderSize = 30;
	public static float velocityX = 4.0f;
	public static float velocityY = 5.0f;

	public static float gravity = 0.09f;
	public static float insideBorder;
	public static float outsideBorder;
	//From center
	
	public static void initConstants(float width, float height) {

		insideBorder = width/5;
		outsideBorder = width/2 - width/5;
		playerSize = 32;
		switch((int)width) {
		case 600:
			insideBorder = 50;
			outsideBorder = 200;
		case 240:
			insideBorder = 42;
			outsideBorder = 100;
			playerSize = 16;
			velocityX = 2.0f;
			velocityY = 2.5f;
			gravity = 0.05f;
			borderSize = 15;
			break;
			
		}
		
	}
	
}
