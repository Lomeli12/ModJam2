package net.lomeli.insectia;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.insectia.blocks.ModBlocks;
import net.lomeli.insectia.blocks.worldgen.WorldGenerator;
import net.lomeli.insectia.core.CommonProxy;
import net.lomeli.insectia.core.ConfigMod;
import net.lomeli.insectia.core.CreativeTabMod;
import net.lomeli.insectia.core.ModRecipes;
import net.lomeli.insectia.items.ModItems;
import net.lomeli.insectia.lib.ModStrings;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid=ModStrings.MOD_ID, name=ModStrings.MOD_NAME, version=ModStrings.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Insectia {
	
	@Mod.Instance(ModStrings.MOD_ID)
	public static Insectia instance;
	
	@SidedProxy(clientSide="net.lomeli.insectia.core.ClientProxy", serverSide="net.lomeli.insectia.core.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs modTab = new CreativeTabMod(CreativeTabs.getNextID(), ModStrings.MOD_NAME);
	
	private static ConfigMod modConfiguration;
	
	public static boolean limitWorkAtNight;

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		modConfiguration = new ConfigMod();
		modConfiguration.configureMod(event.getSuggestedConfigurationFile());
		
		ModBlocks.loadBlocks();
		ModItems.loadItems();
		
		ModRecipes.loadRecipes();
		
		proxy.registerTileEntities();
	}
	
	@Mod.EventHandler
	public static void Init(FMLInitializationEvent event){
		GameRegistry.registerWorldGenerator(new WorldGenerator());
	}
	
	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event){
		
	}
}
