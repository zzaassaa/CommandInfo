package com.draganz.commandinfo.initialization;

import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.commands.CmdInfo;

import net.minecraft.command.CommandDifficulty;

public class InitCommands {
	
	public static void init(){
		CommandInfoAPI.registerCoreCommand(new CmdInfo());
		
		//TODO
		CommandInfoAPI.registerCommandInfo(new CommandDifficulty(),  "test");
	}
	
}
