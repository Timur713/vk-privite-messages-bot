package com.isanbirdin.vk_bot.command_management.commands;

import com.vk.api.sdk.objects.messages.Message;

/**
 * Abstract class for all executable classes-commands
 * Field {@link #name} identification command,he is called by this name
 *
 * @author Timur Isanbirdin
 * @version 1.1
 */
public abstract class AbstractCommand {

    protected final String name;

    public AbstractCommand(String name){
        this.name = name;
    }

    /**
     * Method-executor
     * @param message input message
     */
    public abstract void execute(Message message);

    /**
     * @return toString formatted
     */
    @Override
    public String toString() {
        return String.format("name: %s", this.name);
    }

    /**
     * Get hash of {@link #name}
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }


    /**
     * @param o Comparable object.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractCommand){
            return name.equals(((AbstractCommand) o).name);
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
