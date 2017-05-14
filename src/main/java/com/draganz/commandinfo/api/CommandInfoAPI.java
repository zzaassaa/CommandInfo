package com.draganz.commandinfo.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	
	private static final Map<String, ICommandInfoMapping> PARSED_COMMAND_INFO = new LinkedHashMap();
	private static final Map<String, ICommandInfoMapping> TMP_CMD_INFO = new LinkedHashMap();
	
	private static final List<CommandBase> COMMANDS = new ArrayList();
	private static final List<String> COMMAND_METHODS = new ArrayList();
	
	/**
	 * This is the core registry method, though it is recommended to use the conjugate methods, with only ICommandInfoMapping
	 * as a parameter
	 * 
	 * Deprecated because, as said before, it is recommended one used the conjugate method, though this method shall
	 * be left usage indefinitely
	 * 
	 * @deprecated Use registerCommandInfo( ICommandInfoMapping ) instead
	 */
	@Deprecated
	public static final void registerCommandInfo( final String mod_id, final ICommandInfoMapping mapping){
		if(!TMP_CMD_INFO.containsKey(mod_id)){
			TMP_CMD_INFO.put(mod_id, mapping);
		}else{
			log.warn("Attempted to register %s twice", mod_id);
		}
	}
	public static final void registerCommandInfo( final ICommandInfoMapping mapping){
		registerCommandInfo(mapping.getAssociatedModID(), mapping);
	}
	
	/**
	 * This register is called only once on the core side, only after JSON reading/writing, thus is the final copy, for
	 * all intensive purposes
	 * 
	 * @param mapping
	 */
	public static final void registerCoreAllInfo( final ICommandInfoMapping mapping){
		if(!PARSED_COMMAND_INFO.containsKey(mapping.getAssociatedModID())){
			PARSED_COMMAND_INFO.put(mapping.getAssociatedModID(), mapping);
		}else{
			boolean also = TMP_CMD_INFO.containsKey(mapping.getAssociatedModID());
			log.warn("Attempted to register %s twice, in the final copy; the temp map showed this to be %s", mapping.getAssociatedModID(), also);
		}
	}
	
	public static final Map<String, ICommandInfoMapping> getTempCommandInfo(){
		return TMP_CMD_INFO;
	}
	
	public static final Map<ICommand, IStringPacket> getTempCommandInfo(String mod_id){
		return TMP_CMD_INFO.get(mod_id).getCommandInfo();
	}
	
	
	public static final Map<String, ICommandInfoMapping> getCommandInfo(){
		return PARSED_COMMAND_INFO;
	}
	
	
	public static final Map<ICommand, IStringPacket> getCommandInfo(String mod_id){
		return PARSED_COMMAND_INFO.get(mod_id).getCommandInfo();
	}
	
	/**
	 * This allows for the user to register a "core command" that be run along with all other commands, just that a registered 
	 * command of apple might be called by /commandInfo apple 
	 * 
	 * It should be noted, that any information as how the "/commandInfo" command runs this can be found on
	 * https://github.com/zzaassaa/CommandInfo
	 * 
	 * @param cmd
	 */
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
