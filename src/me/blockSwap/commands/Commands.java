package me.blockSwap.commands;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.blockSwap.main.Main;


public class Commands implements CommandExecutor{
	Main plugin;
	boolean shutup = false;
	boolean changer = true;
	
	boolean rando;
	int easyRounds, mediumRounds, hardRounds, insaneRounds;
	Player a, b, c;
	boolean aOnBlock, bOnBlock, cOnBlock;
	int scoreA = 0, scoreB = 0, scoreC = 0;
	
	public static final Material[] easyMaterials = new Material[] {
			Material.DIRT, Material.GRASS_BLOCK, Material.COARSE_DIRT, Material.GRAVEL, Material.SAND, Material.CLAY,
			Material.STONE, Material.COBBLESTONE, Material.GRANITE, Material.POLISHED_GRANITE, Material.DIORITE,
			Material.POLISHED_DIORITE, Material.ANDESITE, Material.POLISHED_ANDESITE, Material.SANDSTONE, Material.CUT_SANDSTONE,
			Material.WATER, Material.COAL_ORE, Material.IRON_ORE, Material.FARMLAND, Material.GRASS_PATH, Material.STONE_BRICKS,
			Material.CRAFTING_TABLE, Material.FURNACE, Material.CHEST, Material.BARREL, Material.CARTOGRAPHY_TABLE,
			Material.FLETCHING_TABLE, Material.LOOM, Material.GRINDSTONE
	};
	public static final Material[] mediumMaterials = new Material[] {
			Material.BEDROCK, Material.OBSIDIAN, Material.BRICK, Material.TERRACOTTA, Material.CHISELED_SANDSTONE, Material.SMOOTH_SANDSTONE,
			Material.BONE_BLOCK, Material.LAVA, Material.GOLD_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.COAL_BLOCK,
			Material.IRON_BLOCK, Material.SMOOTH_STONE, Material.REDSTONE_BLOCK, Material.LAPIS_BLOCK, Material.GLASS, Material.CHISELED_STONE_BRICKS,
			Material.NETHERRACK, Material.NETHER_QUARTZ_ORE, Material.NETHER_GOLD_ORE, Material.QUARTZ_BLOCK, Material.MAGMA_BLOCK, Material.SOUL_SAND,
			Material.NETHER_BRICK, Material.NETHER_PORTAL, Material.STONECUTTER, Material.BLAST_FURNACE, Material.SMOKER, Material.SMITHING_TABLE,
			Material.LECTERN, Material.CAKE, Material.TNT, Material.DISPENSER, Material.DROPPER, Material.OBSERVER, Material.NOTE_BLOCK, Material.HOPPER,
			Material.DAYLIGHT_DETECTOR,
			
			Material.WHITE_WOOL, Material.GLASS, Material.BLUE_STAINED_GLASS, Material.BLUE_WOOL, Material.BLUE_CONCRETE,
			Material.BLUE_CONCRETE_POWDER, Material.WHITE_BED
			
			
	};
	public static final Material[] hardMaterials = new Material[] {
			Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.GOLD_BLOCK, Material.EMERALD_BLOCK, Material.BOOKSHELF, Material.PUMPKIN,
			Material.CARVED_PUMPKIN, Material.JACK_O_LANTERN, Material.MELON, Material.HAY_BLOCK, Material.DRIED_KELP_BLOCK, Material.CACTUS,
			Material.OAK_LEAVES, Material.BIRCH_LEAVES, Material.ACACIA_LEAVES, Material.SPRUCE_LEAVES, Material.OAK_LOG, Material.BIRCH_LOG,
			Material.ACACIA_LOG, Material.SPRUCE_LOG, Material.STRIPPED_ACACIA_LOG, Material.STRIPPED_OAK_LOG, Material.STRIPPED_BIRCH_LOG,
			Material.STRIPPED_SPRUCE_LOG, Material.WARPED_STEM, Material.CRIMSON_STEM, Material.STRIPPED_CRIMSON_STEM, Material.STRIPPED_WARPED_STEM,
			Material.BEE_NEST, Material.BEEHIVE, Material.GLOWSTONE, Material.SOUL_SOIL, Material.BASALT, Material.POLISHED_BASALT,
			Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM, Material.SHROOMLIGHT, Material.BLACKSTONE, Material.POLISHED_BLACKSTONE, Material.POLISHED_BLACKSTONE_BRICKS,
			Material.CHISELED_POLISHED_BLACKSTONE, Material.CRYING_OBSIDIAN, Material.BREWING_STAND, Material.CAULDRON, Material.ANVIL, Material.BELL, Material.JUKEBOX
			
	};
	public static final Material[] insaneMaterials = new Material[] {
			Material.PODZOL, Material.MYCELIUM, Material.RED_SAND, Material.SPAWNER,  Material.MOSSY_COBBLESTONE,
			Material.RED_SANDSTONE, Material.CHISELED_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE,  Material.SNOW, Material.SNOW_BLOCK,
			Material.ICE, Material.BLUE_ICE, Material.PACKED_ICE, Material.DIAMOND_BLOCK, Material.MOSSY_STONE_BRICKS, Material.CRACKED_STONE_BRICKS,
			Material.PRISMARINE, Material.SEA_LANTERN, Material.DARK_PRISMARINE, Material.PRISMARINE_BRICKS, Material.SPONGE, Material.WET_SPONGE,
			Material.SLIME_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.BROWN_MUSHROOM_BLOCK, Material.MUSHROOM_STEM, Material.HONEY_BLOCK,
			Material.END_STONE, Material.PURPUR_BLOCK, Material.END_PORTAL_FRAME, Material.BEACON, Material.SCAFFOLDING, Material.RESPAWN_ANCHOR,
			Material.LODESTONE, Material.ANCIENT_DEBRIS
	};
	
	public Commands(Main plugin){
		plugin.getCommand("shutup").setExecutor(this);

		plugin.getCommand("blockSwap1").setExecutor(this);
		plugin.getCommand("blockSwap3").setExecutor(this);
		plugin.getCommand("setOnBlockOrNot").setExecutor(this);
		plugin.getCommand("blockSwap").setExecutor(this);


		this.plugin = plugin;
		{
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Player player = (Player)sender; 

		switch(cmd.getName()){
			case "shutup":
				shutup = !shutup;
				Bukkit.broadcastMessage(ChatColor.GRAY + "shutup set to " + shutup);
				
			break;
			case "blockswap1":
				try {
					Player a = Bukkit.getPlayer(args[0]);
					long time = Integer.parseInt(args[1]) * 1000;
					easyRounds = Integer.parseInt(args[2]);
					mediumRounds = Integer.parseInt(args[3]);
					hardRounds = Integer.parseInt(args[4]);
					insaneRounds = Integer.parseInt(args[5]);
					rando = Boolean.parseBoolean(args[6]);
					
					if(shutup) {
						return false;
					}
					Timer timer = new Timer();
					
					TimerTask myTask = new TimerTask() {
						
					    @Override
					    public void run() {
					    	new Thread(new Runnable() {
								boolean onBlock = false;
								long time1 = System.currentTimeMillis();
								long time2 = System.currentTimeMillis();
								long difference = 10;
								long originalDiff = 10;
								
								
								public void run(){
									Material m = Material.AIR;
									if(rando) {
										int selector = (int)(Math.random() * 4);
										if(easyRounds > 0 && selector == 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											int random = (int) (Math.random() * easyMaterials.length);
											m = easyMaterials[random];
										} else if(mediumRounds > 0 && selector == 1) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											int random = (int) (Math.random() * mediumMaterials.length);
											m = mediumMaterials[random];
										} else if(hardRounds > 0 && selector == 2) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											int random = (int) (Math.random() * hardMaterials.length);
											m = hardMaterials[random];
										} else if(insaneRounds > 0 && selector == 3) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											int random = (int) (Math.random() * insaneMaterials.length);
											m = insaneMaterials[random];
										} else {
											if(m.equals(Material.AIR)) {
												Bukkit.broadcastMessage("aight ur done nice");
												return;
											}
										}
									} else {
										if(easyRounds > 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											int random = (int) (Math.random() * easyMaterials.length);
											m = easyMaterials[random];
										} else if(mediumRounds > 0) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											int random = (int) (Math.random() * mediumMaterials.length);
											m = mediumMaterials[random];
										} else if(hardRounds > 0) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											int random = (int) (Math.random() * hardMaterials.length);
											m = hardMaterials[random];
										} else if(insaneRounds > 0) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											int random = (int) (Math.random() * insaneMaterials.length);
											m = insaneMaterials[random];
										} else {
											if(m.equals(Material.AIR)) {
												Bukkit.broadcastMessage("aight ur done nice");
												return;
											}
										}
									}
									
									
									Bukkit.broadcastMessage(ChatColor.AQUA + "Go and find " + m);
									while(time2 - time1 < time && !onBlock) {
										if(shutup) {
											timer.cancel();
											return;
										}
										if(a.getLocation().add(0, -1,0).getBlock().getType().equals(m)) {
											onBlock = true;
										}
										difference -= time2 - time1;
										difference = (int)difference/1000;
										if(difference != originalDiff) {
											
											long showtime = (time/1000) + difference;
											if(showtime % 60 == 0 && showtime != 0) {
												Bukkit.broadcastMessage(ChatColor.GREEN + "Time Left: " + showtime);
					 
											} else if(showtime == 45 || showtime == 30) {
												Bukkit.broadcastMessage(ChatColor.YELLOW + "Time Left: " + showtime);
											} else if(showtime > 9 && showtime < 30 && showtime % 5 == 0) {
												Bukkit.broadcastMessage(ChatColor.GOLD + "Time Left: " + showtime);
											} else if(showtime < 10) {
												Bukkit.broadcastMessage(ChatColor.RED + "Time Left: " + showtime);
											}
											
											originalDiff = difference;
										}
										
										
										time2 = System.currentTimeMillis();
					
								}
								if(onBlock) {
									try {
										new BukkitRunnable() {
									        public void run() {
									        	Bukkit.broadcastMessage(ChatColor.GREEN + a.getName() + " has gotten to the BLOCK!!!");
									        	Bukkit.dispatchCommand(sender, "blockswap1 " + a.getName() + " " + time/1000
									        			+ " " + easyRounds + " " + mediumRounds + " " + hardRounds + " " + insaneRounds + " " + rando);
									        	scoreA++;
								        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + a.getName() + " has " + scoreA + " points!");
									        }
									    }.runTask(plugin);
									    
									} catch(Exception e) {
										throw new IllegalStateException("ERROR YEEEEEEEEEEEEt");
									}
								}
								else {
									new BukkitRunnable() {
								        public void run() {
							        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + a.getName() + " has " + scoreA + " points!");

								        }
								    }.runTask(plugin);
								}
								
					
							}}).start();
					    	
					    }
					    
					};
					timer.schedule(myTask, 0);

										
										
				}
				catch(Exception e) {
					player.sendMessage("lol wrong args");
					throw new ArrayIndexOutOfBoundsException("lol wrong args");
				}
				
				break;
			case "blockswap":
				try {
					ArrayList<Player> players = new ArrayList<Player>();
					int i = 0;
					while(i < Integer.parseInt(args[0])) {
						players.add(Bukkit.getPlayer(args[i + 1]));
						i++;
					}
					Material[] materials = new Material[Integer.parseInt(args[0])];
					Boolean[] onBlocks = new Boolean[Integer.parseInt(args[0])];
					int[] scores = new int[Integer.parseInt(args[0])];
					long time = Integer.parseInt(args[i + 1]) * 1000;
					easyRounds = Integer.parseInt(args[i + 1]);
					mediumRounds = Integer.parseInt(args[i + 1]);
					hardRounds = Integer.parseInt(args[i + 1]);
					insaneRounds = Integer.parseInt(args[i + 1]);
					rando = Boolean.parseBoolean(args[i + 1]);
					
					if(shutup) {
						return false;
					}
					Timer timer = new Timer();
					
					TimerTask myTask = new TimerTask() {
						
					    @Override
					    public void run() {
					    	new Thread(new Runnable() {
								
								long time1 = System.currentTimeMillis();
								long time2 = System.currentTimeMillis();
								long difference = 10;
								long originalDiff = 10;
								
								
								public void run(){
									for(int x = 0; x < Integer.parseInt(args[0]); x++) {
										materials[x] = Material.AIR;
										onBlocks[x] = false;
									}
										
				
									if(rando) {
										int selector = (int)(Math.random() * 4);
										if(easyRounds > 0 && selector == 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * easyMaterials.length);
												materials[x] = easyMaterials[random];
											}
							
										} else if(mediumRounds > 0 && selector == 1) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * mediumMaterials.length);
												materials[x] = mediumMaterials[random];
											}
										} else if(hardRounds > 0 && selector == 2) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * hardMaterials.length);
												materials[x] = hardMaterials[random];
											}
										} else if(insaneRounds > 0 && selector == 3) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * insaneMaterials.length);
												materials[x] = insaneMaterials[random];
											}
										} else {
											if(materials[0].equals(Material.AIR)) {
												
												Bukkit.broadcastMessage(ChatColor.UNDERLINE + "Point Tallies " + a.getName() + ": " + scoreA + "\n " +
												b.getName() + ": " + scoreB + "\n " + c.getName()+ ": "+ scoreC);
												return;
											}
										}
									} else {
										if(easyRounds > 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * easyMaterials.length);
												materials[x] = easyMaterials[random];
											}
										} else if(mediumRounds > 0) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * mediumMaterials.length);
												materials[x] = mediumMaterials[random];
											}
										} else if(hardRounds > 0) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * hardMaterials.length);
												materials[x] = hardMaterials[random];
											}
										} else if(insaneRounds > 0) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											for(int x = 0; x < Integer.parseInt(args[0]); x++) {
												int random = (int) (Math.random() * insaneMaterials.length);
												materials[x] = insaneMaterials[random];
											}
										} else {
											if(materials[0].equals(Material.AIR)) {

												Bukkit.broadcastMessage(ChatColor.UNDERLINE + "Point Tallies " + a.getName() + ": " + scoreA + "\n " +
												b.getName() + ": " + scoreB + "\n " + c.getName()+ ": "+ scoreC);												
												return;
											}
										}
									}
									
									for(int x = 0; x < players.size(); x++) {
										Bukkit.broadcastMessage(ChatColor.AQUA + players.get(x).getName() + " go and find " + materials[x]);

									}
					
									while(time2 - time1 < time && !shutup && (!aOnBlock || !bOnBlock || !cOnBlock)) {
										for(int x = 0; x < players.size(); x++) {
											if(players.get(x).getLocation().add(0, -1,0).getBlock().getType().equals(materials[x]) && !onBlocks[x]) {
												onBlocks[x] = true;
									        	Bukkit.broadcastMessage(ChatColor.DARK_GREEN + players.get(x).getName() + " has gotten to the " + materials[x]);
											}
										}
										
										difference -= time2 - time1;
										difference = (int)difference/1000;
										if(difference != originalDiff) {
											
											long showtime = (time/1000) + difference;
											if(showtime % 60 == 0 && showtime != 0) {
												Bukkit.broadcastMessage(ChatColor.GREEN + "Time Left: " + showtime);
					
											} else if(showtime == 45 || showtime == 30) {
												Bukkit.broadcastMessage(ChatColor.YELLOW + "Time Left: " + showtime);
											} else if(showtime > 9 && showtime < 30 && showtime % 5 == 0) {
												Bukkit.broadcastMessage(ChatColor.GOLD + "Time Left: " + showtime);
											} else if(showtime < 10) {
												Bukkit.broadcastMessage(ChatColor.RED + "Time Left: " + showtime);
											}
											
											originalDiff = difference;
										}
										
										
										time2 = System.currentTimeMillis();
					
								}
								
								try {
									new BukkitRunnable() {
								        public void run() {
								        	for(int i = 0; i < players.size(); i++) {
								        		if(onBlocks[i]) {
								        			scores[i]++;
									        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + players.get(i).getName() + " has " + scores[i] + " points!");
								        		}
								        	}
								        	String command = "blockswap ";
								        	for(int i = 0; i < players.size(); i++) {
								        		command += players.get(i).getName() + " ";
								        	}
							        		
								        	Bukkit.dispatchCommand(sender, command + time/1000
								        			+ " " + easyRounds + " " + mediumRounds + " " + hardRounds + " " + insaneRounds + " " + rando);
								        	
								        }
								    }.runTask(plugin);
								    
								} catch(Exception e) {
									throw new IllegalStateException("ERROR YEEEEEEEEEEEEt");
								}
								
								
					
							}}).start();
					    	
					    }
					    
					};
					if(!shutup) {
						timer.schedule(myTask, 0);

					}

										
										
				}
				catch(Exception e) {
					player.sendMessage("lol wrong args");
					throw new ArrayIndexOutOfBoundsException("lol wrong args");
				}
				
				break;	
				
				
				
				
			case "blockswap3":
				try {
					a = Bukkit.getPlayer(args[0]);
					b = Bukkit.getPlayer(args[1]);
					c = Bukkit.getPlayer(args[2]);
					long time = Integer.parseInt(args[3]) * 1000;
					easyRounds = Integer.parseInt(args[4]);
					mediumRounds = Integer.parseInt(args[5]);
					hardRounds = Integer.parseInt(args[6]);
					insaneRounds = Integer.parseInt(args[7]);
					rando = Boolean.parseBoolean(args[8]);
					
					if(shutup) {
						return false;
					}
					Timer timer = new Timer();
					
					TimerTask myTask = new TimerTask() {
						
					    @Override
					    public void run() {
					    	new Thread(new Runnable() {
								
								long time1 = System.currentTimeMillis();
								long time2 = System.currentTimeMillis();
								long difference = 10;
								long originalDiff = 10;
								
								
								public void run(){
									Material mA = Material.AIR;
									Material mB = Material.AIR;
									Material mC = Material.AIR;
									aOnBlock = false;
									bOnBlock = false;
									cOnBlock = false;
									if(rando) {
										int selector = (int)(Math.random() * 4);
										if(easyRounds > 0 && selector == 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											
											int random = (int) (Math.random() * easyMaterials.length);
											mA = easyMaterials[random];
											random = (int) (Math.random() * easyMaterials.length);
											mB = easyMaterials[random];
											random = (int)( Math.random() * easyMaterials.length);
											mC = easyMaterials[random];
										} else if(mediumRounds > 0 && selector == 1) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											int random = (int) (Math.random() * mediumMaterials.length);
											mA = mediumMaterials[random];
											random = (int) (Math.random() * mediumMaterials.length);
											mB = mediumMaterials[random];
											random = (int) (Math.random() * mediumMaterials.length);
											mC = mediumMaterials[random];
										} else if(hardRounds > 0 && selector == 2) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											int random = (int) (Math.random() * hardMaterials.length);
											mA = hardMaterials[random];
											random = (int) (Math.random() * hardMaterials.length);
											mB = hardMaterials[random];
											random = (int)( Math.random() * hardMaterials.length);
											mC = hardMaterials[random];
										} else if(insaneRounds > 0 && selector == 3) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											int random = (int) (Math.random() * insaneMaterials.length);
											mA = insaneMaterials[random];
											random = (int) (Math.random() * insaneMaterials.length);
											mB = insaneMaterials[random];
											random = (int) (Math.random() * insaneMaterials.length);
											mC = insaneMaterials[random];
										} else {
											if(mA.equals(Material.AIR)) {
												
												Bukkit.broadcastMessage(ChatColor.UNDERLINE + "Point Tallies " + a.getName() + ": " + scoreA + "\n " +
												b.getName() + ": " + scoreB + "\n " + c.getName()+ ": "+ scoreC);
												return;
											}
										}
									} else {
										if(easyRounds > 0) {
											easyRounds--;
											Bukkit.broadcastMessage("filthy casual.");
											
											int random = (int) (Math.random() * easyMaterials.length);
											mA = easyMaterials[random];
											random = (int) (Math.random() * easyMaterials.length);
											mB = easyMaterials[random];
											random = (int)( Math.random() * easyMaterials.length);
											mC = easyMaterials[random];
										} else if(mediumRounds > 0) {
											mediumRounds--;
											Bukkit.broadcastMessage("respectable casual.");
											int random = (int) (Math.random() * mediumMaterials.length);
											mA = mediumMaterials[random];
											random = (int) (Math.random() * mediumMaterials.length);
											mB = mediumMaterials[random];
											random = (int) (Math.random() * mediumMaterials.length);
											mC = mediumMaterials[random];
										} else if(hardRounds > 0) {
											hardRounds--;
											Bukkit.broadcastMessage("Easy mode.");
											int random = (int) (Math.random() * hardMaterials.length);
											mA = hardMaterials[random];
											random = (int) (Math.random() * hardMaterials.length);
											mB = hardMaterials[random];
											random = (int)( Math.random() * hardMaterials.length);
											mC = hardMaterials[random];
										} else if(insaneRounds > 0) {
											insaneRounds--;
											Bukkit.broadcastMessage("lol good luck.");
											int random = (int) (Math.random() * insaneMaterials.length);
											mA = insaneMaterials[random];
											random = (int) (Math.random() * insaneMaterials.length);
											mB = insaneMaterials[random];
											random = (int) (Math.random() * insaneMaterials.length);
											mC = insaneMaterials[random];
										} else {
											if(mA.equals(Material.AIR)) {

												Bukkit.broadcastMessage(ChatColor.UNDERLINE + "Point Tallies " + a.getName() + ": " + scoreA + "\n " +
												b.getName() + ": " + scoreB + "\n " + c.getName()+ ": "+ scoreC);												
												return;
											}
										}
									}
									
									
									Bukkit.broadcastMessage(ChatColor.AQUA + a.getName() + " go and find " + mA);
									Bukkit.broadcastMessage(ChatColor.AQUA + b.getName() + " go and find " + mB);
									Bukkit.broadcastMessage(ChatColor.AQUA + c.getName() + " go and find " + mC);

									while(time2 - time1 < time && !shutup && (!aOnBlock || !bOnBlock || !cOnBlock)) {
										if(a.getLocation().add(0, -1,0).getBlock().getType().equals(mA) && !aOnBlock) {
											aOnBlock = true;
								        	Bukkit.broadcastMessage(ChatColor.DARK_GREEN + a.getName() + " has gotten to the " + mA);
										}
										if(b.getLocation().add(0, -1,0).getBlock().getType().equals(mB) && !bOnBlock) {
											bOnBlock = true;
								        	Bukkit.broadcastMessage(ChatColor.DARK_GREEN + b.getName() + " has gotten to the " + mB);
										}
										if(c.getLocation().add(0, -1,0).getBlock().getType().equals(mC) && !cOnBlock) {
											cOnBlock = true;
								        	Bukkit.broadcastMessage(ChatColor.DARK_GREEN + c.getName() + " has gotten to the " + mC);
										}
										
										difference -= time2 - time1;
										difference = (int)difference/1000;
										if(difference != originalDiff) {
											
											long showtime = (time/1000) + difference;
											if(showtime % 60 == 0 && showtime != 0) {
												Bukkit.broadcastMessage(ChatColor.GREEN + "Time Left: " + showtime);
					
											} else if(showtime == 45 || showtime == 30) {
												Bukkit.broadcastMessage(ChatColor.YELLOW + "Time Left: " + showtime);
											} else if(showtime > 9 && showtime < 30 && showtime % 5 == 0) {
												Bukkit.broadcastMessage(ChatColor.GOLD + "Time Left: " + showtime);
											} else if(showtime < 10) {
												Bukkit.broadcastMessage(ChatColor.RED + "Time Left: " + showtime);
											}
											
											originalDiff = difference;
										}
										
										
										time2 = System.currentTimeMillis();
					
								}
								
								try {
									new BukkitRunnable() {
								        public void run() {
								        	if(aOnBlock) {
								        		scoreA++;
								        	}
								        	if(bOnBlock) {
								        		scoreB++;
								        	}
								        	if(cOnBlock) {
								        		scoreC++;
								        	}
							        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + a.getName() + " has " + scoreA + " points!");
							        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + b.getName() + " has " + scoreB + " points!");
							        		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + c.getName() + " has " + scoreC + " points!");
								        	Bukkit.dispatchCommand(sender, "blockswap3 " + a.getName() + " " + b.getName() + " " + c.getName() + " " + time/1000
								        			+ " " + easyRounds + " " + mediumRounds + " " + hardRounds + " " + insaneRounds + " " + rando);
								        	
								        }
								    }.runTask(plugin);
								    
								} catch(Exception e) {
									throw new IllegalStateException("ERROR YEEEEEEEEEEEEt");
								}
								
								
					
							}}).start();
					    	
					    }
					    
					};
					if(!shutup) {
						timer.schedule(myTask, 0);

					}

										
										
				}
				catch(Exception e) {
					player.sendMessage("lol wrong args");
					throw new ArrayIndexOutOfBoundsException("lol wrong args");
				}
				
				break;
				case "setOnBlockOrNot":
					Player p = Bukkit.getPlayer(args[0]);
					if(p.getName().equals(a.getName())) {
						aOnBlock = true;
						Bukkit.broadcastMessage(a.getName() + " onBlock = " + aOnBlock);
					} else if(p.getName().equals(b.getName())) {
						bOnBlock = true;
						Bukkit.broadcastMessage(b.getName() + " onBlock = " + bOnBlock);

					} else if(p.getName().equals(c.getName())) {
						cOnBlock = true;
						Bukkit.broadcastMessage(c.getName() + " onBlock = " + cOnBlock);


					} else {
						Bukkit.broadcastMessage(ChatColor.RED + "uh there's been an error awkward");
					}
				break;
				
				
				
				
		}
		return false;
	}



}
