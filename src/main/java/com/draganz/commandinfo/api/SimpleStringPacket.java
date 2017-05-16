package com.draganz.commandinfo.api;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * This is a simple packet to hold the message arguments that can be used standardly; the chat message will use localization
 * where available in the send change message, for simplicity of code
 * 
 * To note, this methods uses TextComponentTranslation, thus any formatting must be done by hand, in the lang file, though this
 * class does allow for multiple messages to be displayed, though it is recomened that if one attempts to use this as a "new
 * line" means, one could just use EasyStringPacket instead, and just format things right, cause you can specify new line
 * via the lang file, by use \n and makeing sure your lang file has #PARSE_ESCAPES alone on the first line
 * 
 * @author draganz
 *
 */
public class SimpleStringPacket implements IStringPacket{

	private String[] MESSAGES;
	
	public SimpleStringPacket( final String... formatedMessage ){
		MESSAGES = formatedMessage;
	}
	
	@Override
	public void setComments(String... comments) {
		MESSAGES = comments;
	}
	
	@Override
	public String[] getComments() {
		return MESSAGES;
	}
	
	/**
	 * When using this default provided method, due to usage of TextComponentTranslation, the user should make sure to use 
	 * JSON language translation rather than direct text input
	 */
	@Override
	public void doChatMessage(MinecraftServer server, ICommandSender sender,
			String[] args) {
		
		for( int i = 0; i < MESSAGES.length; i++){
			sender.sendMessage(new TextComponentTranslation(MESSAGES[i]));
		}
		
	}
	
}
