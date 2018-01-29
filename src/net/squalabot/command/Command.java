package net.squalabot.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
	public String description(); // returns a description of the given command
	public void handle(MessageReceivedEvent event); // main function to handle the given command
}
