package com.draganz.commandinfo.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.draganz.commandinfo.Library;
import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.handler.ConfigHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class CmdInfo extends CommandBase{

	@Override
	public String getName() {
		return Library.Commands.CMD_INFO_NAME;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return Library.Commands.CMD_INFO_USAGE;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return Library.Config.cmdInfoPermission;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length >= 2){
			for(Entry<ICommand, String> out : CommandInfoAPI.getCommandInfo().entrySet()){
				if(out.getKey().getName().equalsIgnoreCase(args[1])){
					sender.sendMessage(new TextComponentTranslation(out.getValue(), args[1]));
				}
			}
		}else{
			throw new WrongUsageException(Library.Commands.CMD_INFO_USAGE);
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		String[] easyArgs = Arrays.copyOfRange(args, 1, args.length);
		
		if(args.length == 2){
			return getListOfStringsMatchingLastWord(easyArgs, getMethods(CommandInfoAPI.getCommandInfo().keySet()));
		}else{
			for(ICommand cmd : CommandInfoAPI.getCommandInfo().keySet()){
				if(cmd.getName().equalsIgnoreCase(easyArgs[0])){
					return cmd.getTabCompletions(server, sender, easyArgs, targetPos);
				}
			}
		}
		
		return Collections.<String>emptyList();
	}
	
	private List<String> getMethods(Collection<ICommand> blks){
		final List<String> out = new ArrayList();
		
		for(ICommand k : blks){
			out.add(k.getName());
		}
		
		return out;
	}
	
}
