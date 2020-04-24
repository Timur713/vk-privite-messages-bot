package com.isanbirdin.vk_bot.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class VKCore {
    private VkApiClient vk;
    private static int ts;
    private GroupActor actor;
    private static int maxMsgId = -1;

    public VKCore() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);

        Properties props = new Properties();
        int groupId;
        String accessToken;

        try {
            props.load((new FileInputStream("src/main/resources/vkconfig.properties")));
            groupId = Integer.parseInt(props.getProperty("group_id"));
            accessToken = String.valueOf(props.getProperty("access_token"));
            actor = new GroupActor(groupId, accessToken);

            ts = vk.messages().getLongPollServer(actor).execute().getTs();
        } catch (IOException | ApiException | ClientException e) {
            e.printStackTrace();
            System.out.println("Error of loading configuration.");
        }
    }

    public GroupActor getActor() {
        return actor;
    }
    public VkApiClient getVk() {
        return vk;
    }


    public Message getMessage() throws ClientException, ApiException {
        MessagesGetLongPollHistoryQuery eventsQuery = vk.messages()
                .getLongPollHistory(actor)
                .ts(ts);
        if (maxMsgId > 0) {
            eventsQuery.maxMsgId(maxMsgId);
        }
        List<Message> messages = eventsQuery
                .execute()
                .getMessages()
                .getItems();

        if (!messages.isEmpty()){
            try {
                ts =  vk.messages()
                        .getLongPollServer(actor)
                        .execute()
                        .getTs();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }

        if (!messages.isEmpty() && !messages.get(0).isOut()) {
                /*
                messageId - максимально полученный ID, нужен, чтобы не было ошибки 10 internal server error,
                который является ограничением в API VK. В случае, если ts слишком старый (больше суток),
                а max_msg_id не передан, метод может вернуть ошибку 10 (Internal server error).
                 */
            int messageId = messages.get(0).getId();
            if (messageId > maxMsgId){
                maxMsgId = messageId;
            }

            return messages.get(0);
        }
        return null;
    }
}
