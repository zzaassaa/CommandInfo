package com.draganz.commandinfo.network;

import com.draganz.commandinfo.CommandInfo;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class InfoNetwork {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(CommandInfo.MOD_ID);
	
	public static void init(){
		int i = 0;
		INSTANCE.registerMessage(MsgSyncInfo.MessageHandler.class, MsgSyncInfo.class, i++, Side.CLIENT);
	}
	
}
