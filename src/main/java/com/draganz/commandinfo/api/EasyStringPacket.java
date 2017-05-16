package com.draganz.commandinfo.api;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Provided implementation IStringPacket
 * Takes in a single string, that should be a reference to the .lang file, that run run using the Commentcomponent 
 * ITextComponent
 * 
 * @author draganz
 *
 */
public class EasyStringPacket implements IStringPacket{

	private String MESSAGE;
	
	public EasyStringPacket( final String input ){
		MESSAGE = input;
	}
	
	@Override
	public void setComments( final String... comments ) {
		MESSAGE = comments[0];
	}
	
	@Override
	public String[] getComments() {
		return new String[]{MESSAGE};
	}

	@Override
	public void doChatMessage( final MinecraftServer server, final ICommandSender sender, final String[] args ) {
		
		sender.sendMessage(new CommentComponent(MESSAGE));
		
	}

	
	
}
