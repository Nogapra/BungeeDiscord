package me.prouser123.bungee.discord;

import java.util.Arrays;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class InGameCommand extends Command {

	public InGameCommand() {
		super("bd", "bungeediscord.use");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if ( args.length == 0 )
        {
            sender.sendMessage(new TextComponent(this.title + " " + ChatColor.GRAY + Main.inst().getDescription().getVersion()));
            
            if (Discord.api != null) {
                sender.sendMessage(new TextComponent("Connected to " + Discord.api.getServers().size() + " servers."));
                sender.sendMessage(new TextComponent(Discord.api.getServers().toString()));
                
            } else {
            	sender.sendMessage(new TextComponent(ChatColor.RED + "Authentication Failure! Please use /bd token <token> or the config file to enter your bot token." ));
            }
            
        // bd help command
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(new TextComponent(this.title + ChatColor.GRAY + " Commands"));
            sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN + "/bd" + ChatColor.GRAY + " - " + ChatColor.GOLD + "View plugin and status information"));
            sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN + "/bd help" + ChatColor.GRAY + " - " + ChatColor.GOLD + "This page."));
            sender.sendMessage(new TextComponent(ChatColor.DARK_GREEN + "/bd token" + ChatColor.GRAY + " - " + ChatColor.GOLD + "Set the bot token."));
        	
        // bd token command
        } else if (args[0].equalsIgnoreCase("token")) {
        	// 2 command arguments (e.g. /bd token <token>)
        	if (args.length == 2) {
        		sender.sendMessage(new TextComponent("token: " + args[1]));
                sender.sendMessage(new TextComponent(ChatColor.WHITE + "[" + this.title + ChatColor.WHITE + "] " + ChatColor.GRAY));
        	
            // User only put /bd token (1 command argument)
        	} else {
                sender.sendMessage(new TextComponent(ChatColor.RED + "Please specify a bot token with: /bd token <bot token>"));
                this.helpMessage(sender);
        	}
        	
        // Show generic usage help command
        } else {
            this.invalidMessage(sender);
            sender.sendMessage(new TextComponent("args: " +  Arrays.toString(args)));
        }
		
	}
	
	public void helpMessage(CommandSender sender) {
        sender.sendMessage(new TextComponent(ChatColor.RED + this.helpMessageText));
	}
	
	public void invalidMessage(CommandSender sender) {
        sender.sendMessage(new TextComponent(ChatColor.RED + this.invalidCommandText + " " + this.helpMessageText));
	}
	
	
	// Strings for messages so that they can be edited easily.
	public String helpMessageText = "Use /bd help for help.";
	public String invalidCommandText = "Invalid command.";
	
	public String title = (ChatColor.DARK_AQUA + "Bungee" + ChatColor.LIGHT_PURPLE + "Discord");
	
}