package com.draganz.commandinfo.initialization;

import com.draganz.commandinfo.CommandInfo;
import com.draganz.commandinfo.Library;
import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.api.EasyStringPacket;
import com.draganz.commandinfo.api.SimpleCommandInfoMapping;
import com.draganz.commandinfo.commands.CmdInfo;
import com.draganz.commandinfo.commands.CmdInitial;

import net.minecraft.command.CommandBlockData;
import net.minecraft.command.CommandClearInventory;
import net.minecraft.command.CommandClone;
import net.minecraft.command.CommandCompare;
import net.minecraft.command.CommandDebug;
import net.minecraft.command.CommandDefaultGameMode;
import net.minecraft.command.CommandDifficulty;
import net.minecraft.command.CommandEffect;
import net.minecraft.command.CommandEnchant;
import net.minecraft.command.CommandEntityData;
import net.minecraft.command.CommandExecuteAt;
import net.minecraft.command.CommandFill;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.CommandGameRule;
import net.minecraft.command.CommandGive;
import net.minecraft.command.CommandHelp;
import net.minecraft.command.CommandKill;
import net.minecraft.command.CommandLocate;
import net.minecraft.command.CommandParticle;
import net.minecraft.command.CommandPlaySound;
import net.minecraft.command.CommandReplaceItem;
import net.minecraft.command.CommandServerKick;
import net.minecraft.command.CommandSetPlayerTimeout;
import net.minecraft.command.CommandSetSpawnpoint;
import net.minecraft.command.CommandShowSeed;
import net.minecraft.command.CommandSpreadPlayers;
import net.minecraft.command.CommandStats;
import net.minecraft.command.CommandStopSound;
import net.minecraft.command.CommandTP;
import net.minecraft.command.CommandTime;
import net.minecraft.command.CommandTitle;
import net.minecraft.command.CommandToggleDownfall;
import net.minecraft.command.CommandTrigger;
import net.minecraft.command.CommandWeather;
import net.minecraft.command.CommandWorldBorder;
import net.minecraft.command.CommandXP;
import net.minecraft.command.ICommand;
import net.minecraft.command.server.CommandAchievement;
import net.minecraft.command.server.CommandBanIp;
import net.minecraft.command.server.CommandBanPlayer;
import net.minecraft.command.server.CommandBroadcast;
import net.minecraft.command.server.CommandDeOp;
import net.minecraft.command.server.CommandEmote;
import net.minecraft.command.server.CommandListBans;
import net.minecraft.command.server.CommandListPlayers;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.command.server.CommandOp;
import net.minecraft.command.server.CommandPardonIp;
import net.minecraft.command.server.CommandPardonPlayer;
import net.minecraft.command.server.CommandPublishLocalServer;
import net.minecraft.command.server.CommandSaveAll;
import net.minecraft.command.server.CommandSaveOff;
import net.minecraft.command.server.CommandSaveOn;
import net.minecraft.command.server.CommandScoreboard;
import net.minecraft.command.server.CommandSetBlock;
import net.minecraft.command.server.CommandSetDefaultSpawnpoint;
import net.minecraft.command.server.CommandStop;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.command.server.CommandTestFor;
import net.minecraft.command.server.CommandTestForBlock;
import net.minecraft.command.server.CommandWhitelist;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InitCommands {
	
	private static final SimpleCommandInfoMapping mc_mapping = new SimpleCommandInfoMapping("minecraft");
	private static final SimpleCommandInfoMapping commandinfo_mapping = new SimpleCommandInfoMapping(CommandInfo.MOD_ID);
		
	public static void init(){
		CommandInfoAPI.registerSubCommand(new CmdInfo());
		
		registerCICommandInfo(new CmdInitial(), Library.Commands.CMD_INITIAL_USAGE, Library.Info.INITIAL);
		registerCICommandInfo(new CmdInfo(), Library.Commands.CMD_INFO_ID_USAGE, Library.Info.INFO);
		
		
		registerMCCommandInfo(new CommandAchievement(), Library.Info.ACHIEVEMENT);
		registerMCCommandInfo(new CommandBanIp(), Library.Info.BAN_IP);
		registerMCCommandInfo(new CommandBanPlayer(), Library.Info.BAN_PLAYER);
		registerMCCommandInfo(new CommandBlockData(), Library.Info.BLOCK_DATA);
		registerMCCommandInfo(new CommandBroadcast(), Library.Info.BROADCAST);
		registerMCCommandInfo(new CommandClearInventory(), Library.Info.CLEAR_INVENTORY);
		registerMCCommandInfo(new CommandClone(), Library.Info.CLONE);
		registerMCCommandInfo(new CommandCompare(), Library.Info.COMPARE);
		registerMCCommandInfo(new CommandDebug(), Library.Info.DEBUG);
		registerMCCommandInfo(new CommandDefaultGameMode(), Library.Info.DEFAULT_GAME_MODE);
		registerMCCommandInfo(new CommandDeOp(), Library.Info.DE_OP);
		registerMCCommandInfo(new CommandDifficulty(), Library.Info.DIFFICULTY);
		registerMCCommandInfo(new CommandEffect(), Library.Info.EFFECT);
		registerMCCommandInfo(new CommandEmote(), Library.Info.EMOTE);
		registerMCCommandInfo(new CommandEnchant(), Library.Info.ENCHANT);
		registerMCCommandInfo(new CommandEntityData(), Library.Info.ENTITY_DATA);
		registerMCCommandInfo(new CommandExecuteAt(), Library.Info.EXECUTE_AT);
		registerMCCommandInfo(new CommandFill(), Library.Info.FILL);
		registerMCCommandInfo(new CommandGameMode(), Library.Info.GAME_MODE);
		registerMCCommandInfo(new CommandGameRule(), Library.Info.GAME_RULE);
		registerMCCommandInfo(new CommandGive(), Library.Info.GIVE);
		registerMCCommandInfo(new CommandHelp(), Library.Info.HELP);
		registerMCCommandInfo(new CommandKill(), Library.Info.KILL);
		registerMCCommandInfo(new CommandListBans(), Library.Info.LIST_BANS);
		registerMCCommandInfo(new CommandListPlayers(), Library.Info.LIST_PLAYERS);
		registerMCCommandInfo(new CommandLocate(), Library.Info.LOCATE);
		registerMCCommandInfo(new CommandMessage(), Library.Info.MESSAGE);
		registerMCCommandInfo(new CommandMessageRaw(), Library.Info.MESSAGE_RAW);
		registerMCCommandInfo(new CommandOp(), Library.Info.OP);
		registerMCCommandInfo(new CommandPardonIp(), Library.Info.PARDON_IP);
		registerMCCommandInfo(new CommandPardonPlayer(), Library.Info.PARDON_PLAYER);
		registerMCCommandInfo(new CommandParticle(), Library.Info.PARTICLE);
		registerMCCommandInfo(new CommandPlaySound(),  Library.Info.PLAY_SOUND);
		registerMCCommandInfo(new CommandPublishLocalServer(), Library.Info.PUBLISH_LOCAL_SERVER);
		registerMCCommandInfo(new CommandReplaceItem(), Library.Info.REPLACE_ITEM);
		registerMCCommandInfo(new CommandSaveAll(), Library.Info.SAVE_ALL);
		registerMCCommandInfo(new CommandSaveOff(), Library.Info.SAVE_OFF);
		registerMCCommandInfo(new CommandSaveOn(), Library.Info.SAVE_ON);
		registerMCCommandInfo(new CommandScoreboard(), Library.Info.SCOREBOARD);
		registerMCCommandInfo(new CommandServerKick(), Library.Info.SERVER_KICK);
		registerMCCommandInfo(new CommandSetBlock(), Library.Info.SET_BLOCK);
		registerMCCommandInfo(new CommandSetDefaultSpawnpoint(), Library.Info.SET_DEFAULT_SPAWNPOINT);
		registerMCCommandInfo(new CommandSetPlayerTimeout(), Library.Info.SET_PLAYER_TIMEOUT);
		registerMCCommandInfo(new CommandSetSpawnpoint(), Library.Info.SET_SPAWNPOINT);
		registerMCCommandInfo(new CommandShowSeed(), Library.Info.SHOW_SEED);
		registerMCCommandInfo(new CommandSpreadPlayers(), Library.Info.SPREAD_PLAYERS);
		registerMCCommandInfo(new CommandStats(), Library.Info.STATS);
		registerMCCommandInfo(new CommandStop(), Library.Info.STOP);
		registerMCCommandInfo(new CommandStopSound(), Library.Info.STOP_SOUND);
		registerMCCommandInfo(new CommandSummon(), Library.Info.SUMMON);
		registerMCCommandInfo(new CommandTeleport(), Library.Info.TELEPORT);
		registerMCCommandInfo(new CommandTestFor(), Library.Info.TEST_FOR);
		registerMCCommandInfo(new CommandTestForBlock(), Library.Info.TEST_FOR_BLOCK);
		registerMCCommandInfo(new CommandTime(), Library.Info.TIME);
		registerMCCommandInfo(new CommandTitle(), Library.Info.TITLE);
		registerMCCommandInfo(new CommandToggleDownfall(), Library.Info.TRIGGER);
		registerMCCommandInfo(new CommandTP(), Library.Info.TP);
		registerMCCommandInfo(new CommandTrigger(), Library.Info.TRIGGER);
		registerMCCommandInfo(new CommandWeather(), Library.Info.WEATHER);
		registerMCCommandInfo(new CommandWhitelist(), Library.Info.WHITELIST);
		registerMCCommandInfo(new CommandWorldBorder(), Library.Info.WORLD_BORDER);
		registerMCCommandInfo(new CommandXP(), Library.Info.XP);

		
		CommandInfoAPI.registerCommandInfo(mc_mapping);
		CommandInfoAPI.registerCommandInfo(commandinfo_mapping);
		
	}
	
	
	private static void registerMCCommandInfo(ICommand cmd, String comment){
		mc_mapping.registerCommandInfo(cmd, new EasyStringPacket(comment));
	}
	private static void registerCICommandInfo(ICommand cmd, String... comments){
		commandinfo_mapping.registerCommandInfo(cmd, comments);
	}
	
	
	
}
