package com.crashhascrashed.blankplugin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.logging.Logger;


public class Main extends JavaPlugin {
    private Logger logger;
    private FileConfiguration config;

    @Override
    public void onDisable() {
        //Output to console the plugin has been disabled
        logger.info( "Disabled Blank plugin");
    }

    @Override
    public void onEnable() {
        //Make a local variable of logger to make it more easily accessible
        this.logger = getLogger();

        //Create config if none exists
        this.saveDefaultConfig();

        //Obtaining config file
        this.config = getConfig();

        //Register MyListener so it can start listening for events
        Bukkit.getPluginManager().registerEvents(new MyListener(), this);

        //Output to console the plugin has been loaded
        logger.info("Enabled Blank plugin");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //When someone uses /doSomething
        if(label.equalsIgnoreCase("doSomething")){

            //If no arguments have been given, show error message
            if(args.length == 0){
                sender.sendMessage(ChatColor.RED+"Please specify an amount");
                return true;
            }

            //If first argument is not a number, show error message
            if(!StringUtils.isNumeric(args[0])){
                sender.sendMessage(ChatColor.RED + "Please specify a number");
                return true;
            }

            //Obtain the max amount from config file
            int maxAmount = config.getInt("maxAmount");

            //Convert first argument to Integer
            int amount = Integer.parseInt(args[0]);

            //If amount is higher than max allowed amount, show error message
            if(amount > maxAmount){
                sender.sendMessage(ChatColor.RED + "Max amount is "+maxAmount);
                return true;
            }

            //Show a silly message amount times
            for(int i = 0; i < amount; i++){
                sender.sendMessage(ChatColor.GRAY + "Doing something...");
            }

            return true;
        }

        if(label.equalsIgnoreCase("doReload")){
            //Reloads the config file
            reloadConfig();

            //Update local reference to config file
            this.config = getConfig();

            //Inform sender the config has been reloaded
            sender.sendMessage(ChatColor.GREEN + "Config reloaded!");

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //Show numbers 0 to 9 to auto complete
        if(label.equalsIgnoreCase("doSomething") && args.length == 1 && args[0].length() == 0) {
            return List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        }

        return null;
    }


}
