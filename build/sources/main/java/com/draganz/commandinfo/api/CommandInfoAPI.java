package com.draganz.commandinfo.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.draganz.commandinfo.CommandInfo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;

public final class CommandInfoAPI {
	private static final Logger log = LogManager.getLogger(CommandInfo.MOD_ID);
	
	private static final Map<ICommand, String> COMMAND_INFO = new HashMap();
	
	private static final List<CommandBase> COMMANDS = new ArrayList();
	private static final List<String> COMMAND_METHODS = new ArrayList();
	
	
	public static final void registerCommandInfo( final ICommand commandKey, final String comment ){
		if(!COMMAND_INFO.containsKey(commandKey)){
			COMMAND_INFO.put(commandKey, comment);
		}else{
			log.warn("Attempted to regiter %s twice", commandKey.toString());
		}
	}
	
	public static final Map<ICommand,String> getCommandInfo(){
		return COMMAND_INFO;
	}
	
	public static final void registerCoreCommand( final CommandBase cmd){
		COMMANDS.add(cmd);
		COMMAND_METHODS.add(cmd.getName());
	}
	
	public static final Collection<CommandBase> getCommands(){
		return COMMANDS;
	}
	
	public static final Collection<String> getCommandMethods(){
		return COMMAND_METHODS;
	}
	
}
