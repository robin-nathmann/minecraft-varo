package de.robinnathmann.minecraft.varo.methods;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Tablist {

    private static Class<?> chatserial;

    static{
        if(compareMinecraftVersionServerIsHigherOrEqual("1.8.3")){
            chatserial = getNMSClass("IChatBaseComponent$ChatSerializer");
        }else{
            chatserial = getNMSClass("ChatSerializer");
        }
    }

    public static void sendHeaderAndFooter(Player p, String header, String footer) {
        try {
            Object packet = getNMSClass("PacketPlayOutPlayerListHeaderFooter").newInstance();
            getField(packet.getClass().getDeclaredField("a")).set(packet, chatserial.getMethod("a", String.class).invoke(null, "{'text': '" + header + "'}"));
            getField(packet.getClass().getDeclaredField("b")).set(packet, chatserial.getMethod("a", String.class).invoke(null, "{'text': '" + footer + "'}"));
            sendPacket(p, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendPacket(Player p, Object packet) {
        try {
            Object nmsPlayer = getNMSPlayer(p);
            Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
            connection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(connection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        return name.substring(name.lastIndexOf('.') + 1) + ".";
    }

    private static Class<?> getNMSClass(String className) {
        String fullName = "net.minecraft.server." + getVersion() + className;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static Object getNMSPlayer(Player p) {
        try {
            return p.getClass().getMethod("getHandle").invoke(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Field getField(Field f) {
        f.setAccessible(true);
        return f;
    }

    private static boolean compareMinecraftVersionServerIsHigherOrEqual(String version) {
        String serverVersion = Bukkit.getVersion();
        serverVersion = serverVersion.substring(serverVersion.indexOf("(MC: ") + 5, serverVersion.length());
        serverVersion = serverVersion.substring(0, serverVersion.lastIndexOf(")"));
        String[] serverVersionArray = serverVersion.split("\\.");
        String[] toCompareVersionArray = version.split("\\.");

        if (serverVersionArray.length == 2) {
            int serverFirst = Integer.valueOf(serverVersionArray[0]);
            int toCompareFirst = Integer.valueOf(toCompareVersionArray[0]);
            if (toCompareFirst != serverFirst) {
                return toCompareFirst < serverFirst;
            }
            int serverSecond = Integer.valueOf(serverVersionArray[1]);
            int toCompareSecond = Integer.valueOf(toCompareVersionArray[1]);
            if (toCompareSecond != serverSecond) {
                return toCompareSecond < serverSecond;
            }
            if (toCompareVersionArray.length == 3) {
                return false;
            }
            return true;
        }
        if (serverVersionArray.length == 3) {
            int serverFirst = Integer.valueOf(serverVersionArray[0]);
            int toCompareFirst = Integer.valueOf(toCompareVersionArray[0]);
            if (toCompareFirst != serverFirst) {
                return toCompareFirst < serverFirst;
            }
            int serverSecond = Integer.valueOf(serverVersionArray[1]);
            int toCompareSecond = Integer.valueOf(toCompareVersionArray[1]);
            if (toCompareSecond != serverSecond) {
                return toCompareSecond < serverSecond;
            }
            if (toCompareVersionArray.length != 3) {
                return true;
            }
            int serverThird = Integer.valueOf(serverVersionArray[2]);
            int toCompareThird = Integer.valueOf(toCompareVersionArray[2]);
            if (toCompareThird != serverThird) {
                return toCompareThird < serverThird;
            }
            return true;
        }
        return false;
    }

}
