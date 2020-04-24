package com.isanbirdin.vk_bot.command_management.commands;

import com.isanbirdin.vk_bot.vk.VKMessenger;
import com.vk.api.sdk.objects.messages.Message;

public class UnknownCommand extends AbstractCommand {

    public UnknownCommand(String name) {
        super(name);
    }

    @Override
    public void execute(Message message) {
        new VKMessenger().sendMessage("Unknown command", message.getPeerId());
    }
}
