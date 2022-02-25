package com.crashhascrashed.blankplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent){
        //Simple welcome message when a player joins the server
        playerJoinEvent.getPlayer().sendMessage("Welcome!");
    }
}
