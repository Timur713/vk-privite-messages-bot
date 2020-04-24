package com.isanbirdin.vk_bot.vk;

import com.isanbirdin.vk_bot.command_management.Executor;
import com.vk.api.sdk.objects.messages.Message;

public class VKManager implements Runnable {
    private Message message;

    public VKManager(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        Executor.execute(message);
    }
}
