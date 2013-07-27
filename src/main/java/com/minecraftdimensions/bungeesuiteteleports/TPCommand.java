package com.minecraftdimensions.bungeesuiteteleports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCommand implements CommandExecutor {
	BungeeSuiteTeleports plugin;

	public static final String[] PERMISSION_NODES = {
			"bungeesuite.teleport.tp", "bungeesuite.teleport.*",
			"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	public static final String[] PERMISSION_NODES_LOC = {
			"bungeesuite.teleport.tp.location", "bungeesuite.teleport.*",
			"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };
	public static final String[] PERMISSION_NODES_LOC_OTHER = {
			"bungeesuite.teleport.tp.otherlocation", "bungeesuite.teleport.*",
			"bungeesuite.mod", "bungeesuite.admin", "bungeesuite.*" };

	private static final String[] PERMISSION_NODES_P2P = {
			"bungeesuite.teleport.tp.p2p", "bungeesuite.teleport.*",
			"bungeesuite.admin", "bungeesuite.*" };

	public TPCommand(BungeeSuiteTeleports bst) {
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
			plugin.utils.teleportToPlayerRequest(sender.getName(), args[0]);
			return true;
		}
		if (args.length == 2) {
			if (!CommandUtil.hasPermission(sender, PERMISSION_NODES_P2P)) {
				plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
				return true;
			}
			plugin.utils.teleportToPlayerRequest(args[0], args[1]);
			return true;
		}
		if (args.length == 3) {
			if (!CommandUtil.hasPermission(sender, PERMISSION_NODES_LOC)) {
				plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
				return true;
			}
			String x = args[0];
			String y = args[1];
			String z = args[2];
			String loc = "null"+"~"+x + "~" + y + "~" + z + "~"
					+ ((Player) sender).getWorld().getName();
			plugin.utils.teleportToLocation(sender.getName(), loc);
			return true;
		}
		if (args.length == 4) {
			if (!CommandUtil.hasPermission(sender, PERMISSION_NODES_LOC_OTHER)) {
				plugin.utils.getMessage(sender.getName(), "NO_PERMISSION");
				return true;
			}
			if (Bukkit.getPlayer(args[0]) != null) {
				String x = args[1];
				String y = args[2];
				String z = args[3];
				String loc = "null"+"~"+x + "~" + y + "~" + z + "~"
						+ ((Player) sender).getWorld().getName();
				plugin.utils.teleportToLocation(args[0], loc);
			}
			return true;
		}
		return false;
	}

}
