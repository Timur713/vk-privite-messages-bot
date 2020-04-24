package com.isanbirdin.vk_bot.command_management;

import com.isanbirdin.vk_bot.command_management.commands.AbstractCommand;
import com.isanbirdin.vk_bot.command_management.commands.HelloCommand;
import com.isanbirdin.vk_bot.command_management.commands.UnknownCommand;

import java.util.HashSet;

public class Caretaker {
    private static HashSet<AbstractCommand> commands = new HashSet<>();

    static {
        commands.add(new UnknownCommand("unknown"));
        commands.add(new HelloCommand("hello"));
    }

    public static HashSet<AbstractCommand> getCommands(){
        return commands;
    }
    public static void addCommand(AbstractCommand command) {
        commands.add(command);
    }
}
