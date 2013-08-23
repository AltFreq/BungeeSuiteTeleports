package com.minecraftdimensions.bungeesuiteteleports.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.minecraftdimensions.bungeesuiteteleports.managers.TeleportsManager;

public class TeleportsListener implements Listener {
	
	@EventHandler
	public void playerConnect (PlayerJoinEvent e){
		if(TeleportsManager.pendingTeleports.containsKey(e.getPlayer().getName())){
			Player t = TeleportsManager.pendingTeleports.get(e.getPlayer().getName());
			TeleportsManager.pendingTeleports.remove(e.getPlayer().getName());
			if(!t.isOnline()){
				e.getPlayer().sendMessage("Player is no longer online");
				return;
			}
			e.getPlayer().teleport(t);
			
		}else if (TeleportsManager.pendingTeleportLocations.containsKey(e.getPlayer().getName())){
			Location l = TeleportsManager.pendingTeleportLocations.get(e.getPlayer().getName());
			e.getPlayer().teleport(l);
		}
	}

	
}
