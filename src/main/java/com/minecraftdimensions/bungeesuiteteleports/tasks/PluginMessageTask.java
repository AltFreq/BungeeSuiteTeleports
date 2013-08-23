package com.minecraftdimensions.bungeesuiteteleports.tasks;

import java.io.ByteArrayOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.minecraftdimensions.bungeesuiteteleports.BungeeSuiteTeleports;

public class PluginMessageTask extends BukkitRunnable {
    
    private final ByteArrayOutputStream bytes;
    
    public PluginMessageTask(ByteArrayOutputStream bytes) {
        this.bytes = bytes;
    }

    public void run() {
        Bukkit.getOnlinePlayers()[0].sendPluginMessage(BungeeSuiteTeleports.instance, BungeeSuiteTeleports.OUTGOING_PLUGIN_CHANNEL, bytes.toByteArray());
    }

}