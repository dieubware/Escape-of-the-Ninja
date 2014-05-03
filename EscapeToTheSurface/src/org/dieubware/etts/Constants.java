package org.dieubware.etts;

public class Constants {

	public static final int textureSize = 32;
	public static int playerSize;
	public static int borderSize = 30;
	public static float velocityX;
	public static float velocityY;

	public static float gravity;
	public static float insideBorder;
	public static float outsideBorder;
	//From center
	
	public static void initConstants(float width, float height) {

		insideBorder = width/5;
		outsideBorder = width/2 - width/5;
		playerSize = 32;
		switch((int)width) {
		case 800:
			insideBorder = 75;
			outsideBorder = 200;

			velocityX = 60.0f * 6.0f;
			velocityY = 60.0f * 5.0f;
			gravity =   60.0f * 6.0f;
			break;
		case 240:
			insideBorder = 30;
			outsideBorder = 75;
			playerSize = 24;
			velocityX = 60 * 3.0f;
			velocityY = 60 * 2.5f;
			gravity =   60 * 3f;
			borderSize = 24;
			break;
			
		}
		
	}
	
}
