package com.draganz.commandinfo.network;

import java.util.Map;
import java.util.Map.Entry;

import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.api.ICommandInfoMapping;
import com.draganz.commandinfo.handler.CompressionUtils;
import com.draganz.commandinfo.streamio.FileIO;
import com.google.gson.JsonParseException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Note: this is not my work, this is a refactered form of a class made by pahimar, no affiliation. See
 * https://github.com/pahimar
 * 
 * @author draganz(ish), this entire thing is just a refactered form of a class made by pahimar
 *
 */
public class MsgSyncInfo implements IMessage{

	public Map<String, ICommandInfoMapping> infoMap;
	
	public MsgSyncInfo(Map<String, ICommandInfoMapping> infoMap){
		this.infoMap = infoMap;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int flag = buf.readInt();
		
		if(flag != 0){
			byte[] tempMap = buf.readBytes(flag).array();
			
			if(tempMap != null){
				String jsonInput = CompressionUtils.decompress(tempMap);
				
				try{
					infoMap = FileIO.GSON.fromJson(jsonInput, FileIO.MAP_TYPE);
				}catch(JsonParseException e){
					infoMap = null;
					e.printStackTrace();
				}
				
			}else{
				infoMap = null;
			}
		}else{
			infoMap = null;
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		if(infoMap != null){
			byte[] tempMap = CompressionUtils.compress(FileIO.GSON.toJson(infoMap, FileIO.MAP_TYPE));
			
			if(tempMap != null){
				buf.writeInt(tempMap.length);
				buf.writeBytes(tempMap);
			}else{
				buf.writeInt(0);
			}
		}else{
			buf.writeInt(0);
		}
	}
	
	public static class MessageHandler implements IMessageHandler<MsgSyncInfo, IMessage>{
		
		@Override
		public IMessage onMessage(MsgSyncInfo message, MessageContext ctx) {
			if(message.infoMap != null){
				//CommandInfoAPI.registerUncheckedCommandInfo(message.infoMap);
			}
			return null;
		}
		
	}
	
}






