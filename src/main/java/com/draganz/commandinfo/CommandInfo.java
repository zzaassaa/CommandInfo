package com.draganz.commandinfo;

import com.draganz.commandinfo.commands.CmdInitial;
import com.draganz.commandinfo.handler.ConfigHandler;
import com.draganz.commandinfo.handler.InfoFileHandler;
import com.draganz.commandinfo.initialization.InitCommands;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = CommandInfo.MOD_ID, 
	 name = "@MOD_NAME@", 
	 version = "@MOD_VERSION@",
	 updateJSON = CommandInfo.UPDATE_JSON,
	 guiFactory = CommandInfo.GUI_FACTORY)
public class CommandInfo {
	
	public static final String MOD_ID = "commandinfo";
	public static final String UPDATE_JSON = "";//TODO
	public static final String GUI_FACTORY = "com.draganz.commandinfo.client.gui.GuiFactory";
	
	@Mod.Instance(CommandInfo.MOD_ID)
	public static CommandInfo instance;
	
	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event){
		event.registerServerCommand(new CmdInitial());
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		InfoFileHandler.preInit(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigHandler());
		InitCommands.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		InfoFileHandler.postInit();
	}
	
}
