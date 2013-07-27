package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TPDenyCommand implements CommandExecutor {

	BungeeSuiteTeleports plugin;
	
	private static final String[] PERMISSION_NODES = { "bungeesuite.teleport.tpaccept","bungeesuite.teleport.user", "bungeesuite.teleport.*",
		"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	public TPDenyCommand(BungeeSuiteTeleports bungeeSuiteTeleports){
		plugin = bungeeSuiteTeleports;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}
			plugin.utils.tpDeny(sender.getName());
			return true;
	}

}
