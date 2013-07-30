package com.minecraftdimensions.bungeesuiteteleports;


import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeSuiteTeleports extends JavaPlugin {

	public Utilities utils;

	static String OUTGOING_PLUGIN_CHANNEL = "BungeeSuite";
	static String INCOMING_PLUGIN_CHANNEL = "BungeeSuiteTp";
	
	public HashMap<String, Player>tpqueue = new HashMap<String,Player>();
	public HashMap<String, Location>locqueue = new HashMap<String,Location>();

	@Override
	public void onEnable() {
		utils = new Utilities(this);
		registerListeners();
		registerChannels();
		registerCommands();
	}
	
	private void registerCommands() {
		getCommand("tp").setExecutor(new TPCommand(this));
		getCommand("tphere").setExecutor(new TPHereCommand(this));
		getCommand("tpall").setExecutor(new TPAllCommand(this));
		getCommand("tpa").setExecutor(new TPACommand(this));
		getCommand("tpahere").setExecutor(new TPAHereCommand(this));
		getCommand("tpaccept").setExecutor(new TPAcceptCommand(this));
		getCommand("tpdeny").setExecutor(new TPDenyCommand(this));
		getCommand("back").setExecutor(new BackCommand(this));
		getCommand("tptoggle").setExecutor(new ToggleCommand(this));
	}

	private void registerChannels() {
		Bukkit.getMessenger().registerIncomingPluginChannel(this,
				INCOMING_PLUGIN_CHANNEL, new TeleportsListener(this));
		Bukkit.getMessenger().registerOutgoingPluginChannel(this,
				OUTGOING_PLUGIN_CHANNEL);
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(
				new TeleportsListener(this), this);
	}


}
