package com.draganz.commandinfo;

import com.draganz.commandinfo.commands.CmdInitial;
import com.draganz.commandinfo.handler.ConfigHandler;
import com.draganz.commandinfo.handler.InfoFileHandler;
import com.draganz.commandinfo.initialization.InitCommands;
import com.draganz.commandinfo.network.InfoNetwork;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = CommandInfo.MOD_ID, 
	 name = "CommandInfo", 
	 version = "1.11.2-0.0.1.1",
	 updateJSON = CommandInfo.UPDATE_JSON,
	 guiFactory = CommandInfo.GUI_FACTORY)
public class CommandInfo {
	
	public static final String MOD_ID = "commandinfo";
	public static final String UPDATE_JSON = "https://github.com/zzaassaa/CommandInfo/blob/master/versions.json";
	public static final String GUI_FACTORY = "com.draganz.commandinfo.client.gui.GuiFactory";
	
	@Mod.Instance(CommandInfo.MOD_ID)
	public static CommandInfo instance;
	
	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event){
		event.registerServerCommand(new CmdInitial());
		InfoFileHandler.postInit();
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		InfoFileHandler.preInit(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigHandler());
		//InfoNetwork.init();
		InitCommands.init();
	}
	
}
