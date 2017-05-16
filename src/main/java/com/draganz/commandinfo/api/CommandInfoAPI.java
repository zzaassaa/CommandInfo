package com.draganz.commandinfo.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.draganz.commandinfo.CommandInfo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;

/**
 * Central API control center, for all your registry needs; if help as to what to do is needed, try a look at the curseforge page,
 * where you most likely downloaded this in the first place...
 * 
 * @author draganz
 *
 */
public final class CommandInfoAPI {
	private static final Logger log = LogManager.getLogger(CommandInfo.MOD_ID);
	
	
	private static final Map<String, ICommandInfoMapping> COMMAND_INFO = new LinkedHashMap();
	private static final Map<String, ICommandInfoMapping> TEMP_COMMAND_INFO = new LinkedHashMap();
	
	private static final List<CommandBase> COMMANDS = new ArrayList();
	private static final List<String> COMMAND_METHODS = new ArrayList();
	
	
	private static final void registerTempCommandInfo( final String mod_id, final ICommandInfoMapping mapping){
		if(!TEMP_COMMAND_INFO.containsKey(mod_id)){
			TEMP_COMMAND_INFO.put(mod_id, mapping);
		}else{
			log.warn("Attempted to register %s twice", mod_id);
		}
	}
	
	/**
	 * Central register method. Anything registered here will auto have a corresponding JSON file made for config
	 * purposes; should be used over registerUncheckedCommandInfo
	 * 
	 * @param mapping
	 */
	public static final void registerCommandInfo( final ICommandInfoMapping mapping){
		registerTempCommandInfo(mapping.getAssociatedModID(), mapping);
	}
	
	/**
	 * Registers the ICommandInfoMapping to the final copy of the LinkedHashMap, that is called through all commands. This method
	 * should only be called by those who handle their own JSON file writing, or those who wish to opt-out of it.
	 * 
	 * @param mapping
	 */
	public static final void registerUncheckedCommandInfo( final ICommandInfoMapping mapping){
		if(!COMMAND_INFO.containsKey(mapping.getAssociatedModID())){
			COMMAND_INFO.put(mapping.getAssociatedModID(), mapping);
		}else{
			boolean also = TEMP_COMMAND_INFO.containsKey(mapping.getAssociatedModID());
			log.warn("Attempted to register %s twice, in the final copy; the temp map showed this to be %s", mapping.getAssociatedModID(), also);
		}
	}
	
	public static final Map<String, ICommandInfoMapping> getTempCommandInfo(){
		return TEMP_COMMAND_INFO;
	}
	
	public static final Map<ICommand, IStringPacket> getTempCommandMapping(String mod_id){
		return TEMP_COMMAND_INFO.get(mod_id).getCommandInfo();
	}
	
	
	public static final Map<String, ICommandInfoMapping> getCommandInfo(){
		return COMMAND_INFO;
	}
	
	
	public static final Map<ICommand, IStringPacket> getCommandInfoMapping(String mod_id){
		return COMMAND_INFO.get(mod_id).getCommandInfo();
	}
	
	/**
	 * This allows for the user to register a "sub command" that be run along with all other commands, just that a registered 
	 * command of apple might be called by /commandInfo apple 
	 * 
	 * It should be noted, that any information as how the "/commandInfo" command runs this can be found on
	 * https://github.com/zzaassaa/CommandInfo
	 * 
	 * @param cmd
	 */
	public static final void registerSubCommand( final CommandBase cmd){
		COMMANDS.add(cmd);
		COMMAND_METHODS.add(cmd.getName());
	}
	
	public static final Collection<CommandBase> getSubCommands(){
		return COMMANDS;
	}
	
	public static final Collection<String> getSubCommandMethods(){
		return COMMAND_METHODS;
	}
	
}
