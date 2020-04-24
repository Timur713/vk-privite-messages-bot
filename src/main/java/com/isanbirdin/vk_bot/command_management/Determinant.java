package com.isanbirdin.vk_bot.command_management;

import com.isanbirdin.vk_bot.command_management.commands.AbstractCommand;
import com.isanbirdin.vk_bot.command_management.commands.UnknownCommand;
import com.vk.api.sdk.objects.messages.Message;

import java.util.Collection;

public class Determinant {

    public static AbstractCommand getCommand(Collection<AbstractCommand> listOfCommands, Message message) {
        String text = message.getText().toLowerCase();

        for (AbstractCommand command : listOfCommands) {
            if (command.getName().equals(text)) {
                return command;
            }
        }
        return new UnknownCommand("unknown");
    }
}
