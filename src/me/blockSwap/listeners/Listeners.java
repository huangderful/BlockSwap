package me.blockSwap.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import me.blockSwap.customEntities.CustomPhantom;
import net.minecraft.server.v1_16_R3.WorldServer;

public class Listeners implements Listener{
	

	@EventHandler
	public void playerInteractWithEntityEvent(PlayerInteractAtEntityEvent event) {
		
		if(event.getRightClicked().getType().equals(EntityType.PHANTOM)) {
			event.getRightClicked().addPassenger(event.getPlayer());
		}
		

	}
	@EventHandler
	public void playerBreakBush(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.DEAD_BUSH)) {
			Location loc = event.getPlayer().getLocation();
			CustomPhantom p = new CustomPhantom(loc);
			WorldServer world = ((CraftWorld)event.getPlayer().getWorld()).getHandle();
			world.addEntity(p);
			p.setPosition(loc.getX(), loc.getY(), loc.getZ());
			System.out.println("oi");
			
		}
	}
	
}
