package net.squalabot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ShutdownCommand {
	
	public ShutdownCommand(MessageReceivedEvent event)	{
		JDA jda = event.getJDA();
		MessageChannel channel = event.getChannel();
		channel.sendMessage("Bien compris mon capitaine, je tire ma révérence !").queue();
		jda.shutdown();
	}
}
