package com.minecraftdimensions.bungeesuiteteleports;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utilities {
	BungeeSuiteTeleports plugin;

	private static final String[] PERMISSION_NODES_SILENT = {
			"bungeesuite.teleport.silent", "bungeesuite.teleport.*",
			"bungeesuite.admin", "bungeesuite.*" };
	private static final String[] PERMISSION_NODES_BACK = {
			"bungeesuite.teleport.back.teleport",
			"bungeesuite.teleport.back.*", "bungeesuite.teleport.*",
			"bungeesuite.admin", "bungeesuite.*" };

	public Utilities(BungeeSuiteTeleports bst) {
		plugin = bst;
	}

	public void teleportToPlayerRequest(String player, String target) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TeleportToPlayerRequest");
			out.writeUTF(player);
			out.writeUTF(target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public Player getClosestPlayer(String name) {
		if (Bukkit.getPlayer(name) != null) {
			return Bukkit.getPlayer(name);
		}
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (players.getName().toLowerCase().contains(name.toLowerCase())) {
				return players;
			}
		}
		return null;
	}

	public void getMessage(String sender, String message) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("GetServerMessage");
			out.writeUTF(sender);
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void tpAll(String player, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpAll");
			out.writeUTF(player);
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void tpaRequest(String sender, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpaRequest");
			out.writeUTF(sender);
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void tpaHereRequest(String sender, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpaHereRequest");
			out.writeUTF(sender);
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void tpAccept(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpAccept");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void tpDeny(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpDeny");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void sendBackLocation(Player p) {
		if (!CommandUtil.hasPermission(p, PERMISSION_NODES_BACK)) {
			return;
		}
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PlayersBackLocation");
			out.writeUTF(p.getName());
			Location l = p.getLocation();
			out.writeDouble(l.getX());
			out.writeDouble(l.getY());
			out.writeDouble(l.getZ());
			out.writeUTF(l.getWorld().getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void sendPlayerBack(String name) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendPlayerBack");
			out.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);

	}

	public void teleportToLocation(String player, String loc) {
		Player p = Bukkit.getPlayer(player);

		String locs[] = loc.split("~");
		double x = Double.parseDouble(locs[1]);
		double y = Double.parseDouble(locs[2]);
		double z = Double.parseDouble(locs[3]);
		World w = Bukkit.getWorld(locs[4]);
		Location location = new Location(w, x, y, z);
		if(p==null){
			plugin.locqueue.put(player, location);
			return;
		}
		sendBackLocation(p);
			p.teleport(location);
	}

	public void teleportToPlayer(String player, String target) {
		Player p = Bukkit.getPlayer(player);
		Player t = Bukkit.getPlayer(target);
		if(p==null){
			plugin.tpqueue.put(player, t);
			return;
		}
		p.teleport(t.getLocation());	
		if(!CommandUtil.hasPermission(p.getName(), PERMISSION_NODES_SILENT)){
			t.sendMessage(ChatColor.DARK_GREEN+p.getDisplayName()+" has teleported to you");
		}
		p.sendMessage(ChatColor.DARK_GREEN+"You have teleported to "+ t.getDisplayName());
	}

	public void toggleTeleports(String name) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ToggleTeleports");
			out.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new PluginMessageTask(this.plugin, Bukkit.getOnlinePlayers()[0], b)
				.runTaskAsynchronously(plugin);
		
	}

}
