package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TPHereCommand implements CommandExecutor {
	BungeeSuiteTeleports plugin;

	public static final String[] PERMISSION_NODES = { "bungeesuite.teleport.tphere", "bungeesuite.teleport.*",
		"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	
	public TPHereCommand(BungeeSuiteTeleports bst) {
		plugin = bst;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}
		
		if (args.length == 1) {
			plugin.utils.teleportToPlayerRequest(args[0],sender.getName());
			return true;
		}
		return false;
	}

}
