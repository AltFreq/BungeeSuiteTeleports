package com.minecraftdimensions.bungeesuiteteleports;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class TeleportsListener implements PluginMessageListener, Listener {

	BungeeSuiteTeleports plugin;
	
	private static final String[] PERMISSION_NODES = { "bungeesuite.teleport.back.death", "bungeesuite.teleport.back.*", "bungeesuite.teleport.*",
		"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };
	
	public TeleportsListener(BungeeSuiteTeleports bungeeSuiteTeleports) {
		plugin = bungeeSuiteTeleports;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		if(!(e.getEntity() instanceof Player))return;
		Player p = e.getEntity();
		if(CommandUtil.hasPermission(p, PERMISSION_NODES)){
			plugin.utils.sendBackLocation(p);
			plugin.utils.getMessage(p.getName(), "DEATH_BACK");
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		if(plugin.tpqueue.containsKey(e.getPlayer().getName())){
			plugin.utils.teleportToPlayer(e.getPlayer().getName(), plugin.tpqueue.get(e.getPlayer().getName()).getName());
			plugin.tpqueue.remove(e.getPlayer().getName());
			return;
		}else if(plugin.locqueue.containsKey(e.getPlayer().getName())){
			e.getPlayer().teleport(plugin.locqueue.get(e.getPlayer().getName()));
			plugin.locqueue.remove(e.getPlayer().getName());
			plugin.utils.sendBackLocation(e.getPlayer());
		}
	}

	@Override
	public void onPluginMessageReceived(String pluginChannel, Player reciever,
			byte[] message) {
		if (!pluginChannel
				.equalsIgnoreCase(BungeeSuiteTeleports.INCOMING_PLUGIN_CHANNEL))
			return;

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(
				message));

		String channel = null;
		try {
			channel = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String player = null;
		try {
			player = in.readUTF();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (channel.equalsIgnoreCase("TeleportToPlayer")) {
			String target = null;
			try {
				target = in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			plugin.utils.teleportToPlayer(player, target);
			return;
		}
		if (channel.equalsIgnoreCase("TeleportPlayerToLocation")) {
			String loc = null;
			try {
				loc = in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			plugin.utils.teleportToLocation(player, loc);
			return;
		}
		if(channel.equals("StorePlayersBackLocation")){
			plugin.utils.sendBackLocation(Bukkit.getPlayer(player));
		}
	}

}
