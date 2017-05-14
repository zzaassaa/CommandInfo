package com.draganz.commandinfo.client.gui;

import com.draganz.commandinfo.CommandInfo;
import com.draganz.commandinfo.handler.ConfigHandler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig{

	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen,
			  new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
			  CommandInfo.MOD_ID,
			  false,
			  false,
			  GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
	
}
