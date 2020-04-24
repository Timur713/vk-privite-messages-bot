package com.isanbirdin.vk_bot.vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VKServer {
    private static final Logger logger = LoggerFactory.getLogger(VKServer.class);

    public static VKCore vkCore;

    static {
        vkCore = new VKCore();
    }

    /*
    Making request to server every 300 milliseconds
    If an error occurs, program will automatically reconnect
     */
    public static void main(String[] args) throws NullPointerException, ApiException, InterruptedException {


        System.out.println("Running server...");
        while (true) {
            Thread.sleep(300);

            try {
                Message message = vkCore.getMessage();
                if (message != null) {
                    ExecutorService executor = Executors.newCachedThreadPool();
                    logger.warn("From: {}, text: {} ", message.getFromId(), message.getText());
                    executor.execute(new VKManager(message));
                }
            } catch (ClientException e) {
                final int RECONNECT_TIME = 10000;
                logger.error("Client Exception occurred. Reconnecting after {} seconds...", RECONNECT_TIME / 1000);
                Thread.sleep(RECONNECT_TIME);
            }
        }
    }
}
