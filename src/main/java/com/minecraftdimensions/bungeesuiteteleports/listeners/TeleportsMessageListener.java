package com.minecraftdimensions.bungeesuiteteleports.listeners;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.minecraftdimensions.bungeesuiteteleports.managers.TeleportsManager;

public class TeleportsMessageListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player,
			byte[] message) {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(
				message));
		String task = null;
		try {
			task = in.readUTF();
		if(task.equals("TeleportToPlayer")){
			TeleportsManager.teleportPlayerToPlayer(in.readUTF(), in.readUTF());
		}
		
		if(task.equals("TeleportToLocation")){
			String name = in.readUTF();
			String loc = in.readUTF();
			String locs [] = loc.split("~!~");
			TeleportsManager.teleportPlayerToLocation(name, locs[1], Double.parseDouble(locs[2]),Double.parseDouble(locs[3]),Double.parseDouble(locs[4]));
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
