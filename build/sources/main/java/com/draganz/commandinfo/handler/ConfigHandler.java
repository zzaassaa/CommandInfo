package com.draganz.commandinfo.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.draganz.commandinfo.CommandInfo;
import com.draganz.commandinfo.Library;
import com.draganz.commandinfo.api.ICommandInfoMapping;
import com.draganz.commandinfo.api.IStringPacket;
import com.draganz.commandinfo.api.SimpleCommandInfoMapping;
import com.draganz.commandinfo.streamio.FileIO;

import net.minecraft.command.ICommand;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
	
	public static Configuration config;
	
	private static final String CATEGORY_COMMAND_INFO = "general.commandinfo";
	
	public static void init(File file){

		if(config == null){
			config = new Configuration(file);
			loadConfig();
		}
		
	}
	
	private static void loadConfig(){
		
		Library.Config.cmdInitialPermission = config.getInt(
				Library.Config.CMD_INITIAL_NAME,
				CATEGORY_COMMAND_INFO,
				Library.Config.CMD_INITIAL_DEFAULT,
				Library.Config.CMD_INITIAL_MIN,
				Library.Config.CMD_INITIAL_MAX,
				I18n.translateToLocal(Library.Config.CMD_INITIAL_COMMENT),
				Library.Config.CMD_INITIAL_LABEL);
		Library.Config.cmdInfoPermission = config.getInt(
				Library.Config.CMD_INFO_NAME,
				CATEGORY_COMMAND_INFO,
				Library.Config.CMD_INFO_DEFAULT,
				Library.Config.CMD_INFO_MIN,
				Library.Config.CMD_INFO_MAX,
				I18n.translateToLocal(Library.Config.CMD_INFO_COMMENT),
				Library.Config.CMD_INFO_LABEL);
		
		
		if(config.hasChanged()) config.save();
	}
	
	
	@SubscribeEvent
	public void onConfigChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equalsIgnoreCase(CommandInfo.MOD_ID)){
			loadConfig();
		}
	}
}
