package com.draganz.commandinfo.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.api.ICommandInfoMapping;
import com.draganz.commandinfo.api.IStringPacket;
import com.draganz.commandinfo.network.InfoNetwork;
import com.draganz.commandinfo.network.MsgSyncInfo;
import com.draganz.commandinfo.streamio.FileIO;

import net.minecraft.command.ICommand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfoFileHandler {

	public static File infoDirectory;
		
	public static void preInit(File file){
		infoDirectory = new File(file.getParentFile(), "information");
		infoDirectory.mkdirs();
	}
	
	public static void postInit(){
		
		try {
			loadMapsIO(CommandInfoAPI.getTempCommandInfo());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadMapsIO(Map<String, ICommandInfoMapping> core) throws FileNotFoundException{
		
		for(Entry<String, ICommandInfoMapping> entry : core.entrySet()){
			File file = new File(infoDirectory, constructFileName(entry.getKey()));
			
			if(file.exists()){
				ICommandInfoMapping out = entry.getValue();
				Map<String, String> data = FileIO.readMapFromFile(file);
				
				for(ICommand cmd : entry.getValue().getCommandInfo().keySet()){
					for(Entry<String, String> dataEntry : data.entrySet()){
						if(cmd.getName().equals(dataEntry.getKey())){
							
							out.getCommandInfo().get(cmd).setComments(convertToArray(dataEntry.getValue()));
														
						}
					}
				}
				
				CommandInfoAPI.registerUncheckedCommandInfo(out);//msg
			}else{
				FileIO.writeMapToFile( convertMapping(entry.getValue().getCommandInfo()), file);
				CommandInfoAPI.registerUncheckedCommandInfo(entry.getValue());//msg
			}
		}
	}
	
	private static String constructFileName(String id){
		return id.concat("_info.json");
	}
	
	private static Map<String, String> convertMapping(Map<ICommand, IStringPacket> in){
		Map<String, String> output = new LinkedHashMap();
		
		for(Entry<ICommand, IStringPacket> out : in.entrySet()){
			output.put( out.getKey().getName(), Arrays.toString(out.getValue().getComments()) );
		}
		
		return output;
	}
	
	private static String[] convertToArray(String in){
		return Arrays.stream(in.substring(1, in.length() - 1).split(",")).map(String::trim).toArray(String[]::new);
	}
	
}
