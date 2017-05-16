package com.draganz.commandinfo.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.command.ICommand;

/**
 * This is used for data storage and access within the API; there is a little bit of repetition of usage, but this is to
 * help guarantee that the API will not have to go through any changes, as there are back ups; thus, yes I know, so don't 
 * complain
 * 
 * It is recommended to the user to register their own mod_id, as when this is registered to the mapping, a unique key 
 * is required, and chances are, their mod_id is the best bet
 * 
 * @author draganz
 *
 */
public interface ICommandInfoMapping {

	/**
	 * This method is used to put keys and values toward a stored map. This should permit overwriting of previous values, as
	 * it is called in the fileIO function
	 * 
	 * @param cmd
	 * @param packet
	 */
	public void registerCommandInfo(ICommand cmd, IStringPacket packet);
	
	/**
	 * Getter method
	 * 
	 * @return 
	 */
	public Map<ICommand, IStringPacket> getCommandInfo();
	
	/**
	 * getter method, used when registering the mapping, so don't be a dunderhead have this return ""
	 * 
	 * @return
	 */
	public String getAssociatedModID();
	
}
