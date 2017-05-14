package com.draganz.commandinfo.initialization;

import java.io.FileNotFoundException;

import com.draganz.commandinfo.CommandInfo;
import com.draganz.commandinfo.Library;
import com.draganz.commandinfo.api.CommandInfoAPI;
import com.draganz.commandinfo.api.ICommandInfoMapping;
import com.draganz.commandinfo.api.SimpleCommandInfoMapping;
import com.draganz.commandinfo.commands.CmdInfo;
import com.draganz.commandinfo.commands.CmdInitial;
import com.draganz.commandinfo.handler.ConfigHandler;

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

public class InitCommands {
	
	private static final SimpleCommandInfoMapping mc_mapping = new SimpleCommandInfoMapping("minecraft");//TODO use a method to get id
	private static final SimpleCommandInfoMapping commandinfo_mapping = new SimpleCommandInfoMapping(CommandInfo.MOD_ID);
		
	public static void init(){
		CommandInfoAPI.registerCoreCommand(new CmdInfo());
		
		registerCICommandInfo(new CmdInitial(), Library.Commands.CMD_INITIAL_USAGE, Library.Info.INITIAL);
		registerCICommandInfo(new CmdInfo(), Library.Commands.CMD_INFO_ID_USAGE, Library.Info.INFO);
		
		registerMCCommandInfo(new CommandTP(), "commands.tp.usage", Library.Info.TP);
		registerMCCommandInfo(new CommandTrigger(), "commands.trigger.usage", Library.Info.TRIGGER);
		registerMCCommandInfo(new CommandToggleDownfall(), "commands.downfall.usage", Library.Info.TRIGGER);
		registerMCCommandInfo(new CommandTitle(), "commands.title.usage", Library.Info.TITLE);
		registerMCCommandInfo(new CommandTime(), "commands.time.usage", Library.Info.TIME);
		registerMCCommandInfo(new CommandStopSound(), "commands.stopsound.usage", Library.Info.STOP_SOUND);
		registerMCCommandInfo(new CommandStats(), "commands.stats.usage", Library.Info.STATS);
		registerMCCommandInfo(new CommandSpreadPlayers(), "commands.spreadplayers.usage", Library.Info.SPREAD_PLAYERS);
		registerMCCommandInfo(new CommandShowSeed(), "commands.seed.usage", Library.Info.SHOW_SEED);
		registerMCCommandInfo(new CommandSetSpawnpoint(), "commands.spawnpoint.usage", Library.Info.SET_SPAWNPOINT);
		registerMCCommandInfo(new CommandLocate(), "commands.locate.usage", Library.Info.LOCATE);
		registerMCCommandInfo(new CommandSetPlayerTimeout(), "commands.setidletimeout.usage", Library.Info.SET_PLAYER_TIMEOUT);
		registerMCCommandInfo(new CommandServerKick(), "commands.kick.usage", Library.Info.SERVER_KICK);
		registerMCCommandInfo(new CommandReplaceItem(), "commands.replaceitem.usage", Library.Info.REPLACE_ITEM);
		registerMCCommandInfo(new CommandPlaySound(), "commands.playsound.usage", Library.Info.PLAY_SOUND);
		registerMCCommandInfo(new CommandParticle(), "commands.particle.usage", Library.Info.PARTICLE);
		registerMCCommandInfo(new CommandKill(), "commands.kill.usage", Library.Info.KILL);
		registerMCCommandInfo(new CommandHelp(), "commands.help.usage", Library.Info.HELP);
		registerMCCommandInfo(new CommandGive(), "commands.give.usage", Library.Info.GIVE);
		registerMCCommandInfo(new CommandGameRule(), "commands.gamerule.usage", Library.Info.GAME_RULE);
		registerMCCommandInfo(new CommandGameMode(), "commands.gamemode.usage", Library.Info.GAME_MODE);
		registerMCCommandInfo(new CommandFill(), "commands.fill.usage", Library.Info.FILL);
		registerMCCommandInfo(new CommandExecuteAt(), "commands.execute.usage", Library.Info.EXECUTE_AT);
		registerMCCommandInfo(new CommandEntityData(), "commands.entitydata.usage", Library.Info.ENTITY_DATA);
		registerMCCommandInfo(new CommandEnchant(), "commands.enchant.usage", Library.Info.ENCHANT);
		registerMCCommandInfo(new CommandEffect(), "commands.effect.usage", Library.Info.EFFECT);
		registerMCCommandInfo(new CommandDifficulty(), "commands.difficulty.usage", Library.Info.DIFFICULTY);
		registerMCCommandInfo(new CommandDefaultGameMode(), "commands.defaultgamemode.usage", Library.Info.DEFAULT_GAME_MODE);
		registerMCCommandInfo(new CommandDebug(), "commands.debug.usage", Library.Info.DEBUG);
		registerMCCommandInfo(new CommandCompare(), "commands.compare.usage", Library.Info.COMPARE);
		registerMCCommandInfo(new CommandClone(), "commands.clone.usage", Library.Info.CLONE);
		registerMCCommandInfo(new CommandClearInventory(), "commands.clear.usage", Library.Info.CLEAR_INVENTORY);
		registerMCCommandInfo(new CommandBlockData(), "commands.blockdata.usage", Library.Info.BLOCK_DATA);
		registerMCCommandInfo(new CommandWhitelist(), "commands.whitelist.usage", Library.Info.WHITELIST);
		registerMCCommandInfo(new CommandTestForBlock(), "commands.testforblock.usage", Library.Info.TEST_FOR_BLOCK);
		registerMCCommandInfo(new CommandTestFor(), "commands.testfor.usage", Library.Info.TEST_FOR);
		registerMCCommandInfo(new CommandTeleport(), "commands.teleport.usage", Library.Info.TELEPORT);
		registerMCCommandInfo(new CommandSummon(), "commands.summon.usage", Library.Info.SUMMON);
		registerMCCommandInfo(new CommandStop(), "commands.stop.usage", Library.Info.STOP);
		registerMCCommandInfo(new CommandSetDefaultSpawnpoint(), "commands.setworldspawn.usage", Library.Info.SET_DEFAULT_SPAWNPOINT);
		registerMCCommandInfo(new CommandSetBlock(), "commands.setblock.usage", Library.Info.SET_BLOCK);
		registerMCCommandInfo(new CommandScoreboard(), "commands.scoreboard.usage", Library.Info.SCOREBOARD);
		registerMCCommandInfo(new CommandSaveOn(), "commands.save-on.usage", Library.Info.SAVE_ON);
		registerMCCommandInfo(new CommandSaveOff(), "commands.save-off.usage", Library.Info.SAVE_OFF);
		registerMCCommandInfo(new CommandSaveAll(), "commands.save.usage", Library.Info.SAVE_ALL);
		registerMCCommandInfo(new CommandPublishLocalServer(), "commands.publish.usage", Library.Info.PUBLISH_LOCAL_SERVER);
		registerMCCommandInfo(new CommandPardonPlayer(), "commands.unban.usage", Library.Info.PARDON_PLAYER);
		registerMCCommandInfo(new CommandPardonIp(), "commands.unbanip.usage", Library.Info.PARDON_IP);
		registerMCCommandInfo(new CommandOp(), "commands.op.usage", Library.Info.OP);
		registerMCCommandInfo(new CommandMessageRaw(), "commands.tellraw.usage", Library.Info.MESSAGE_RAW);
		registerMCCommandInfo(new CommandMessage(), "commands.message.usage", Library.Info.MESSAGE);
		registerMCCommandInfo(new CommandListPlayers(), "commands.players.usage", Library.Info.LIST_PLAYERS);
		registerMCCommandInfo(new CommandListBans(), "commands.banlist.usage", Library.Info.LIST_BANS);
		registerMCCommandInfo(new CommandEmote(), "commands.me.usage", Library.Info.EMOTE);
		registerMCCommandInfo(new CommandDeOp(), "commands.deop.usage", Library.Info.DE_OP);
		registerMCCommandInfo(new CommandBroadcast(), "commands.say.usage", Library.Info.BROADCAST);
		registerMCCommandInfo(new CommandBanPlayer(), "commands.ban.usage", Library.Info.BAN_PLAYER);
		registerMCCommandInfo(new CommandBanIp(), "commands.banip.usage", Library.Info.BAN_IP);
		registerMCCommandInfo(new CommandAchievement(), "commands.achievement.usage", Library.Info.ACHIEVEMENT);
		registerMCCommandInfo(new CommandWeather(), "commands.weather.usage", Library.Info.WEATHER);
		registerMCCommandInfo(new CommandWorldBorder(), "commands.worldborder.usage", Library.Info.WORLD_BORDER);
		registerMCCommandInfo(new CommandXP(), "commands.xp.usage", Library.Info.XP);
		
		CommandInfoAPI.registerCommandInfo(mc_mapping);
		CommandInfoAPI.registerCommandInfo(commandinfo_mapping);
		
	}
	
	
	private static void registerMCCommandInfo(ICommand cmd, String... comments){
		mc_mapping.registerCommandInfo(cmd, comments);
	}
	private static void registerCICommandInfo(ICommand cmd, String... comments){
		commandinfo_mapping.registerCommandInfo(cmd, comments);
	}
	
	
	
}
