package net.lomeli.insectia.core;

import java.io.File;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.lib.BlockIDs;
import net.lomeli.insectia.lib.ItemIDs;
import net.lomeli.insectia.lib.ModInts;
import net.minecraftforge.common.Configuration;

public class ConfigMod {
	
	public ConfigMod(){}
	
	public void configureMod(File configFile){
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
		ItemIDs.forkkEaterID = config.get("Bugs", "forkkEater", 664).getInt(664);
		
		//Ants
		ItemIDs.fireAntsID = config.get("Bugs", "FireAnts", 665).getInt(665);
		ItemIDs.armyAntsID = config.get("Bugs", "ArmyAnts", 666).getInt(666);
		
		//SilkWorms
		ItemIDs.bivoltineWormID = config.get("Bugs", "bivoltineSilkWorm", 667).getInt(667);
		ItemIDs.polyvoltineWormID = config.get("Bugs", "polyvoltineSilkWorm", 668).getInt(668);
		ItemIDs.univoltineWormID = config.get("Bugs", "univoltineSilkWorm", 669).getInt(669);
		
		//DungBeetles
		ItemIDs.dungBeetlesID = config.get("Bugs", "DungBeetle", 670).getInt(670);
		
		//Tools
		ItemIDs.webNetID = config.get("Tools", "webNet", 680).getInt(680);
		ItemIDs.dirtNetID = config.get("Tools", "dirtNet", 681).getInt(681);
		ItemIDs.sandNetID = config.get("Tools", "sandNet", 682).getInt(682);
		ItemIDs.leafNetID = config.get("Tools", "leafNet", 683).getInt(683);
		ItemIDs.treatedStickID = config.get("Items", "stick", 684).getInt(684);
		ItemIDs.insectLarvaeID = config.get("Bugs", "insectLarvae", 685).getInt(685);
		ItemIDs.insectClockID = config.get("Tools", "clock", 686).getInt(686);
		
		config.save();
	}
	
	private static void configureModBlockIDs(File configFile){
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		BlockIDs.livingDarkID = config.get("Blocks", "livingQuartersDark", 630).getInt(630);
		BlockIDs.livingSweetID = config.get("Blocks", "livingQuartersSweet", 631).getInt(631);
		BlockIDs.livingGreenID = config.get("Blocks", "livingQuartersGreen", 632).getInt(632);
		BlockIDs.livingSmellyID = config.get("Blocks", "livingQuartersSmelly", 633).getInt(633);
		BlockIDs.frameBlockID = config.get("Blocks", "frameBlock", 634).getInt(634);
		BlockIDs.statusBlockID = config.get("Blocks", "statusBlock", 635).getInt(635);
		BlockIDs.recluseTrapID = config.get("Blocks", "recluseTrap", 636).getInt(636);
		BlockIDs.incubationTankID = config.get("Blocks", "incubator", 637).getInt(637);
		BlockIDs.advancedFrameID = config.get("Blocks", "advancedFrame", 638).getInt(638);
		BlockIDs.advancedHousingID = config.get("Blocks", "advancedHousing", 639).getInt(639);
		
		config.save();
	}
	
	private static void configureModOptions(File configFile){
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		ModInts.chanceOfBite = config.get("Options", "ChanceOfBugBite", 40).getInt(40);
		Insectia.limitWorkAtNight = config.get("Options", "limitWorkAtNight", true).getBoolean(true);
		
		config.save();
	}

}
