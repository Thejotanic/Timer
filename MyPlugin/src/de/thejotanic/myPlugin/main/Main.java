package de.thejotanic.myPlugin.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.thejotanic.myPlugin.countdown.Scheduler;
import de.thejotanic.myPlugin.countdown.SchedulerChat;
import de.thejotanic.myPlugin.listener.GameModeChangeListener;
import de.thejotanic.myPlugin.listener.JoinListener;
import de.thejotanic.myPlugin.listener.QuitListener;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		getCommand("countdown").setExecutor(new Scheduler());
		getCommand("chatcountdown").setExecutor(new SchedulerChat());
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new GameModeChangeListener(), this);
	}

	public void onDisable() {
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
