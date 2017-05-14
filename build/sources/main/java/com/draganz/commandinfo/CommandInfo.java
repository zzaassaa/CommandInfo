package com.draganz.commandinfo;

import com.draganz.commandinfo.commands.CmdInitial;
import com.draganz.commandinfo.handler.ConfigHandler;
import com.draganz.commandinfo.initialization.InitCommands;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = CommandInfo.MOD_ID, 
	 name = "CommandInfo", 
	 version = "1.11.2-0.0.0.1-alpha",
	 guiFactory = CommandInfo.GUI_FACTORY)
public class CommandInfo {
	
	public static final String MOD_ID = "commandinfo";
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
		MinecraftForge.EVENT_BUS.register(new ConfigHandler());
		InitCommands.init();
	}
	
}
