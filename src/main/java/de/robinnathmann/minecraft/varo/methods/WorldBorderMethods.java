package de.robinnathmann.minecraft.varo.methods;

import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class WorldBorderMethods {
	
    public static boolean isOutsideOfBorder(Player p) {
        Location loc = p.getLocation();
        WorldBorder border = p.getWorld().getWorldBorder();
        double x = loc.getX();
        double z = loc.getZ();
        double size = border.getSize();
        
        double geteilt = size / 2;
        double plus = geteilt + 1;
        
        return ((x > plus || (-x) > plus) || (z > plus || (-z) > plus));
    }

}
