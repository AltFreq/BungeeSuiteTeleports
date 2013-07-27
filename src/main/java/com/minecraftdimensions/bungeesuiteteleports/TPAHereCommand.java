package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TPAHereCommand implements CommandExecutor {

	BungeeSuiteTeleports plugin;
	
	private static final String[] PERMISSION_NODES = { "bungeesuite.teleport.tpahere", "bungeesuite.teleport.user", "bungeesuite.teleport.*",
		"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	public TPAHereCommand(BungeeSuiteTeleports bungeeSuiteTeleports){
		plugin = bungeeSuiteTeleports;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}
		if(args.length>0){
			plugin.utils.tpaHereRequest(sender.getName(),args[0]);
			return true;
		}
		return false;
	}

}
