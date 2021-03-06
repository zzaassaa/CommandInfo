package com.draganz.commandinfo.streamio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

public class FileIO {
	
	public static final Type MAP_TYPE = new TypeToken< Map<String, String> >(){}.getType();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().enableComplexMapKeySerialization().create();
	
	public static void writeMapToFile(Map<String, String> map, File file){
		writeFile(file, GSON.toJson(map, MAP_TYPE));
	}
	
	public static Map<String, String> readMapFromFile(File file) throws FileNotFoundException{
		Map<String, String> map = new LinkedHashMap();
		
		try{
			map = GSON.fromJson(readFile(file), MAP_TYPE);
		}catch(JsonParseException e){
			e.printStackTrace();
		}
		
		return map;
	}
	
	private static void writeFile(File file, String contents){
		if(file != null){
			file.getParentFile().mkdirs();
			File inProcess = new File(file.getAbsolutePath().concat("_cnst"));
			
			if(inProcess.exists()){
				inProcess.delete();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(inProcess))){
				bw.write(contents);
				bw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
			if(file.exists()) file.delete();
			
			inProcess.renameTo(file);
		}
	}
	
	private static String readFile(File file) throws FileNotFoundException{
		StringBuilder builder = new StringBuilder();
		
		if(file != null){
			try(BufferedReader reader = new BufferedReader(new FileReader(file))){
				String cl;
				
				while((cl = reader.readLine()) != null){
					builder.append(cl);
				}
			}catch(FileNotFoundException e){
				throw e;
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return builder.toString();
	}
	
}
