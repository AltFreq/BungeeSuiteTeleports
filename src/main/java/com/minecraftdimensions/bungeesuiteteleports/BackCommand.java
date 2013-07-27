package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BackCommand implements CommandExecutor {
	BungeeSuiteTeleports plugin;

	public static final String[] PERMISSION_NODES = {
			"bungeesuite.teleport.back",
			"bungeesuite.teleport.back.*", "bungeesuite.teleport.*",
			"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	public BackCommand(BungeeSuiteTeleports bst) {
		plugin = bst;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if (!CommandUtil.hasPermission(sender, PERMISSION_NODES)) {
			plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
			return true;
		}

			plugin.utils.sendPlayerBack(sender.getName());
			return true;
	}

}
