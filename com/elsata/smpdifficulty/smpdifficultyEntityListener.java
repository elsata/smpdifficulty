package com.elsata.smpdifficulty;

//import java.util.logging.Logger;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Creeper;
import org.bukkit.util.config.Configuration;

public class smpdifficultyEntityListener extends EntityListener{
	public static smpdifficulty plugin;
//	private static final Logger log = Logger.getLogger("Minecraft");
	public smpdifficultyEntityListener(smpdifficulty instance) {
		plugin = instance;
	}


	public void onEntityDamage(EntityDamageEvent event) {
		if (event instanceof EntityDamageByProjectileEvent)
        	{
			EntityDamageByProjectileEvent proj = (EntityDamageByProjectileEvent) event;
        		if(proj.getProjectile() instanceof Arrow) {
				if(proj.getDamager() instanceof Player){
					event.setDamage((int)(2*plugin.getConfiguration().getDouble("playerarrow", 2.0)));
				}
				else{
					event.setDamage((int)(3*(2*plugin.getConfiguration().getDouble("arrow", 2.0)-1)));
				}
			}
		}
		else if (event instanceof EntityDamageByEntityEvent){
			if (event.getEntity() instanceof Player){
			EntityDamageByEntityEvent att = (EntityDamageByEntityEvent) event;
			if(att.getDamager() instanceof Zombie){
                        	event.setDamage((int)(2*plugin.getConfiguration().getDouble("zombie", 2.5)));
			}	
			else if(att.getDamager() instanceof Spider){
				event.setDamage((int)(2*plugin.getConfiguration().getDouble("spider", 1.0)));
                        }
			else if(att.getDamager() instanceof Creeper){
				event.setDamage((int)(event.getDamage()*plugin.getConfiguration().getDouble("ratiocreeper", 2.0)));
                        }
			//else event.setDamage(event.getDamage());
			}
			//else event.setDamage(event.getDamage());
		}
	//else event.setDamage(event.getDamage());
	plugin.getConfiguration().save();
	}
}
