package com.isanbirdin.vk_bot.command_management.commands;

import com.isanbirdin.vk_bot.vk.VKMessenger;
import com.vk.api.sdk.objects.messages.Message;

public class HelloCommand extends AbstractCommand {

    public HelloCommand(String name) {
        super(name);
    }

    @Override
    public void execute(Message message) {
        new VKMessenger().sendMessage("Hello! Write 'help' to get command list :)", message.getPeerId());
    }
}
