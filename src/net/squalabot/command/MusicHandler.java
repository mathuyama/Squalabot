package net.squalabot.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.squalabot.Statics;

public class MusicHandler implements Command {
	
	public MusicHandler() {
		
	}
	
	public String description() {
		return "Music commands:\n"
				+ Statics.prefix+"play [url]: plays a youtube video given by url.";
	}
	
	public void handle(MessageReceivedEvent event) {
		
	}
	
}
