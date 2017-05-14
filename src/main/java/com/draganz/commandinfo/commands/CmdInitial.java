package com.draganz.commandinfo.commands;

import java.util.Collections;
import java.util.List;

import com.draganz.commandinfo.Library;
import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.handler.ConfigHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CmdInitial extends CommandBase{

	@Override
	public String getName() {
		return Library.Commands.CMD_INITIAL_NAME;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return Library.Commands.CMD_INITIAL_USAGE;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return Library.Config.cmdInitialPermission;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(args.length >= 1){
			for(CommandBase cmd : CommandInfoAPI.getCommands()){
				if(cmd.getName().equalsIgnoreCase(args[0]) && cmd.checkPermission(server, sender)){
					cmd.execute(server, sender, args);
				}
			}
		}else{
			throw new WrongUsageException(Library.Commands.CMD_INITIAL_USAGE);
		}
		
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		if(args.length == 1){
			return getListOfStringsMatchingLastWord(args, CommandInfoAPI.getCommandMethods());
		}else{
			for(CommandBase cmd : CommandInfoAPI.getCommands()){
				if(cmd.getName().equalsIgnoreCase(args[0])){
					return cmd.getTabCompletions(server, sender, args, targetPos);
				}
			}
		}
		
		return Collections.<String>emptyList();
	}
	
}