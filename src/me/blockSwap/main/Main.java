package me.blockSwap.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.blockSwap.commands.Commands;
import me.blockSwap.listeners.Listeners;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
		new Commands(this);
		getServer().getPluginManager().registerEvents(new Listeners(), this);

	}
}
