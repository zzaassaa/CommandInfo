package com.draganz.commandinfo.api;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.command.ICommand;

/**
 * SimpleCommand container to hold map and id, that will be used in API mapping and command calling
 * 
 * @author draganz
 *
 */
public class SimpleCommandInfoMapping implements ICommandInfoMapping{

	private final Map<ICommand, IStringPacket> COMMAND_INFO = new LinkedHashMap();
	private final String MOD_ID;
	
	public SimpleCommandInfoMapping(String mod_id){
		MOD_ID = mod_id;
	}
	
	@Override
	public void registerCommandInfo(ICommand cmd, IStringPacket packet){
		COMMAND_INFO.put(cmd, packet);
	}
	public void registerCommandInfo(ICommand cmd, String... args){
		registerCommandInfo(cmd, new SimpleStringPacket(args));
	}
	
	@Override
	public Map<ICommand, IStringPacket> getCommandInfo(){
		return COMMAND_INFO;
	}
	
	@Override
	public String getAssociatedModID(){
		return MOD_ID;
	}
	
}
