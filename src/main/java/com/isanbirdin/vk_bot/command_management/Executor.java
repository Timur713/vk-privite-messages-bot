package com.isanbirdin.vk_bot.command_management;

import com.vk.api.sdk.objects.messages.Message;

public class Executor {

    /**
     * Execute determined command
     * @param message input message
     */
    public static void execute(Message message){
        Determinant.getCommand(Caretaker.getCommands(), message).execute(message);
    }
}
