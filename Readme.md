# VK privite messages bot.
## How to launch:
1. Create `/src/main/resources/vkconfig.properties`.
2. Set your `group_id` and `access_token` 
You can create `access_token` in your group settings `->` API usage `->` Create token.
3. At last you need enable longpoll in `2. step` settings.

## Description:
#### vk-package:
1. VKCore: configure properties to establish connection.
2. VKManager: simple class to execute command in new thread.
3. VKMessenger: to send message and get response.
4. VKServer: establish connection and launch infinite loop.

#### util-package:
1. RandomUtil: prototype of random classs.

#### command_management-package:
1. Caretaker: keep list of commands.
2. Determinant: determine the command name.
3. Executor: execute determined command.

##### commands-package:
 1. AbstractCommand: parent of all command classes
 2. HelloCommand, UnknownCommand and other are realization of AbstractCommand class. 
