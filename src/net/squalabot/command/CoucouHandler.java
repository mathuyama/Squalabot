package net.squalabot.command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.squalabot.Statics;

public class CoucouHandler implements Command {
	
	public CoucouHandler() {
	}
	
	public String description() {
		return Statics.prefix+"coucou: Le bot vous envoie ses plus sincères salutations!";
	}
	
	public void handle(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();
		channel.sendMessage("Hey " + author.getName() + ", tu veux voir Magritte?").queue();
		channel.sendMessage("http://imagesanalyses.univ-paris1.fr/v4/wp-content/uploads/magritte_ceci_n_est_pas_une_pipe.jpg").queue();
	}
}
