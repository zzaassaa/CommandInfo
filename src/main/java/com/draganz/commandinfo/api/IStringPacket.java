package com.draganz.commandinfo.api;

import java.util.Map.Entry;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * 
 * @author draganz
 * 
 * This interface is used in the central commands to deliver an output a message
 * NOTE: as one should expect, this uses the chat component, thus (obviously) the output should be localized toward the 
 * Language files, and that this is done autonomously by Minecraft (just do something like mod_id.thing.info); unfortunately, I
 * fell this may not be apparent for some
 *
 */
public interface IStringPacket {
	
	public String[] getComments();
	
	/**
	 * This is called when the command is executed and has found a specific command that matches, and thus calls this method
	 * To be used in the case that the provided default packet isn't sufficient;
	 * 
	 * @param server
	 * @param sender
	 * @param args, this is the default (unmodified) array of command arguments, this excludes the initial /<arg> called,
	 * and merely includes all arguments post the fact (standard command args)
	 */
	public void doChatMessage(MinecraftServer server, ICommandSender sender, String[] args);
	
}
