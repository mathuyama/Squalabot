package net.squalabot;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CoucouCommand {

	public CoucouCommand(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();
		channel.sendMessage("Hey " + author.getName() + ", tu veux voir Magritte?").queue();
		channel.sendMessage("http://imagesanalyses.univ-paris1.fr/v4/wp-content/uploads/magritte_ceci_n_est_pas_une_pipe.jpg").queue();
	}
}
