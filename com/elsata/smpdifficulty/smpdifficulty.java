package com.elsata.smpdifficulty;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public class smpdifficulty extends JavaPlugin{

	private static final Logger log = Logger.getLogger("Minecraft");
	private final smpdifficultyEntityListener EntityListener = new smpdifficultyEntityListener(this);
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, EntityListener, Event.Priority.Normal, this);
		try{
			File folder = new File("plugins/smpdifficulty");
			if(!folder.exists())
				folder.mkdir();
			File file = new File(folder, "config.yml");
			if(!file.exists())
			{
				file.createNewFile();
				this.getConfiguration().setProperty("arrow", 2.0);
				this.getConfiguration().setProperty("playerarrow", 2.0);
				this.getConfiguration().setProperty("zombie", 2.5);
				this.getConfiguration().setProperty("spider", 1.0);
				this.getConfiguration().setProperty("ratiocreeper", 2.0);
				this.getConfiguration().save();
			}
		}
		catch (IOException e){
			System.out.println("error when creating config file");
		}	
		this.getConfiguration().load();
		log.info("smpdifficulty 0.1 loaded - by elsata");
		
	}

}
