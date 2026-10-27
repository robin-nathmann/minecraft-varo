package de.robinnathmann.minecraft.varo.methods;

import java.util.Date;

import de.robinnathmann.minecraft.varo.main.Main;

public class Server {

	public static Boolean isServerOpen() {
		
		int StartOpen = 16; // Ab 16 Uhr
		int EndOpen = 20; // Bis 21 Uhr
		int offset = 2;  // Echte Zeit MINUS Server Zeit
		
		String sdf2 = Main.sdf.format(new Date());
		int hour = (Integer.parseInt(sdf2));

		if(hour > (EndOpen-offset) | hour < (StartOpen-offset)) {
			
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}
