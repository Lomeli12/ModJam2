package net.lomeli.insectia.core;

import java.io.File;

import net.lomeli.insectia.lib.BlockIDs;
import net.lomeli.insectia.lib.ItemIDs;
import net.lomeli.insectia.lib.ModInts;
import net.minecraftforge.common.Configuration;

public class ConfigMod {
	
	public ConfigMod(){}
	
	public static void configureMod(File configFile){
		configureModItemIDs(configFile);
		configureModBlockIDs(configFile);
		configureModOptions(configFile);
	}
	
	private static void configureModItemIDs(File configFile){
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		//Produced Items
		ItemIDs.stickyStringID = config.get("Items", "StickString", 650).getInt(650);
		ItemIDs.trappedBugID = config.get("Items", "trappedBug", 651).getInt(651);
		ItemIDs.leafID = config.get("Items", "leaf", 652).getInt(652);
		ItemIDs.appleBitID = config.get("Items", "appleBit", 653).getInt(653); 
		ItemIDs.cookieCrumbID = config.get("Items", "cookieCrumbs", 654).getInt(654);
		ItemIDs.breadCrumbID = config.get("Items", "breadCrumbs", 655).getInt(655);
		ItemIDs.steakPieceID = config.get("Items", "steakPiece", 656).getInt(656);
		ItemIDs.silkID = config.get("Items", "silk", 657).getInt(657);
		ItemIDs.woolFluffID = config.get("Items", "woolFluff", 658).getInt(658);
		ItemIDs.dungID = config.get("Items", "dung", 659).getInt(659);
		ItemIDs.fertilizerID = config.get("Items", "fertilizer", 660).getInt(660);
		
		//Spiders
		ItemIDs.bRecluseSpiderID = config.get("Bugs", "BrownRecluseSpider", 661).getInt(661);
		ItemIDs.longLegSpiderID = config.get("Bugs", "DaddyLongLegSpider", 662).getInt(662);
		ItemIDs.bWidowSpiderID = config.get("Bugs", "BlackWidowSpider", 663).getInt(663);
		
		//Ants
		ItemIDs.fireAntsID = config.get("Bugs", "FireAnts", 664).getInt(664);
		ItemIDs.armyAntsID = config.get("Bugs", "ArmyAnts", 665).getInt(665);
		
		//SilkWorms
		ItemIDs.greenWormID = config.get("Bugs", "GreenSilkWorm", 666).getInt(666);
		ItemIDs.redWormID = config.get("Bugs", "RedSilkWorm", 667).getInt(667);
		ItemIDs.pinkWormID = config.get("Bugs", "PinkSilkWorm", 668).getInt(668);
		
		//DungBeetles
		ItemIDs.dungBeetlesID = config.get("Bugs", "DungBeetle", 669).getInt(669);
		
		//Tools
		ItemIDs.webNetID = config.get("Tools", "webNet", 680).getInt(680);
		ItemIDs.dirtNetID = config.get("Tools", "dirtNet", 681).getInt(681);
		ItemIDs.sandNetID = config.get("Tools", "sandNet", 682).getInt(682);
		ItemIDs.leafNetID = config.get("Tools", "leafNet", 683).getInt(683);
		
		config.save();
	}
	
	private static void configureModBlockIDs(File configFile){
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		BlockIDs.livingQuartersID = config.get("Blocks", "insectLivingQuarters", 630).getInt(630);
		BlockIDs.frameBlockID = config.get("Blocks", "frameBlock", 631).getInt(631);
		BlockIDs.statusBlockID = config.get("Blocks", "statusBlock", 632).getInt(632);
		
		config.save();
	}
	
	private static void configureModOptions(File configFile){
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		ModInts.chanceOfBite = config.get("Options", "ChanceOfBugBite", 40).getInt(40);
		
		config.save();
	}

}
