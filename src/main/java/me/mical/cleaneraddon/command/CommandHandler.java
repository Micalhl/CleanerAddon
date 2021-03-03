package me.mical.cleaneraddon.command;

import me.mical.cleaneraddon.CleanerAddon;
import me.mical.cleaneraddon.command.subcommands.OpenCommand;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.subcommands.DebugCommand;
import org.serverct.parrot.parrotx.command.subcommands.HelpCommand;
import org.serverct.parrot.parrotx.command.subcommands.ReloadCommand;
import org.serverct.parrot.parrotx.command.subcommands.VersionCommand;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;

@PAutoload
@SuppressWarnings("unused")
public class CommandHandler extends org.serverct.parrot.parrotx.command.CommandHandler {

    public CommandHandler() {
        super(ParrotXAPI.getPlugin(CleanerAddon.class), "cleaneraddon");
        register(new OpenCommand());
        register(new VersionCommand(plugin));
        register(new ReloadCommand(plugin, ".reload"));
        register(new DebugCommand(plugin, ".debug"));
        register(new HelpCommand(plugin));
    }
}
