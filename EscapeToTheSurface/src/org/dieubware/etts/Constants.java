package org.dieubware.etts;

public class Constants {

	public static final int textureSize = 32;
	public static int playerSize;
	public static float borderSize = 30;
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
		
		case 240:
			insideBorder = 30;
			outsideBorder = 75;
			playerSize = 24;
			velocityX = 60 * 3.0f;
			velocityY = 60 * 2.5f;
			gravity =   60 * 3f;
			break;
		case 320:
			insideBorder = 30;
			outsideBorder = 75;
			playerSize = 24;
			velocityX = 60 * 3.0f;
			velocityY = 60 * 2.5f;
			gravity =   60 * 3f;
			break;
		case 480:
			insideBorder = 60;
			outsideBorder = 100;
			playerSize = 28;
			velocityX = 60 * 5.0f;
			velocityY = 60 * 4f;
			gravity =   60 * 5f;
			break;
		case 640:
			insideBorder = 67;
			outsideBorder = 150;
			playerSize = 30;
			velocityX = 60 * 5.5f;
			velocityY = 60 * 4.5f;
			gravity =   60 * 5.5f;
			break;
		default:
		case 800:
			insideBorder = 75;
			outsideBorder = 200;

			velocityX = 60.0f * 6.0f;
			velocityY = 60.0f * 5.0f;
			gravity =   60.0f * 6.0f;
			break;
		}
		
		borderSize = playerSize;
		
	}
	
}
