package present.me.reasonswhy.presents.Me.reasonswhy;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import present.me.reasonswhy.presents.Me.reasonswhy.commands.PresentCommand;
import present.me.reasonswhy.presents.Me.reasonswhy.listeners.MenuListener;
import present.me.reasonswhy.presents.Me.reasonswhy.listeners.PresentListener;
import present.me.reasonswhy.presents.Me.reasonswhy.menu.PlayerMenuUtility;

import java.util.HashMap;

public final class Elf extends JavaPlugin {

    private static Elf plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("present").setExecutor(new PresentCommand());
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new PresentListener(), this);

        plugin = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Elf getPlugin() {
        return plugin;
    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }

}

