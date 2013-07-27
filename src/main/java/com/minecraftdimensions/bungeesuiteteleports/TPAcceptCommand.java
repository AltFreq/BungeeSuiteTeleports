package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TPAcceptCommand implements CommandExecutor {

	BungeeSuiteTeleports plugin;
	
	private static final String[] PERMISSION_NODES = { "bungeesuite.teleport.tpaccept", "bungeesuite.teleport.user", "bungeesuite.teleport.*",
		"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	public TPAcceptCommand(BungeeSuiteTeleports bungeeSuiteTeleports){
		plugin = bungeeSuiteTeleports;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}
			plugin.utils.tpAccept(sender.getName());
			return true;
	}

}
