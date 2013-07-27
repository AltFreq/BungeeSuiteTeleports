package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class TPAllCommand implements CommandExecutor {

	BungeeSuiteTeleports plugin;

	private static final String[] PERMISSION_NODES = { "bungeesuite.teleport.tpall", "bungeesuite.teleport.*",
		"bungeesuite.admin", "bungeesuite.*" };

	public TPAllCommand(BungeeSuiteTeleports bungeeSuiteTeleports){
		plugin = bungeeSuiteTeleports;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}
		if(args.length==0){
			plugin.utils.tpAll(sender.getName(),sender.getName());
			return true;
		}
		if(args.length==1){
			plugin.utils.tpAll(sender.getName(),args[0]);
			return true;
		}
		return false;
	}

}
