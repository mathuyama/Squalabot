package net.squalabot;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand {

	public HelpCommand (MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		channel.sendMessage(
				"Liste des commandes :\n"
				+ "---\n"
				+ "&coucou : Le bot vous adresse ses plus sinc�res salutations !\n"
				+ "&play : Lire une musique (ouais on verra NYD)\n"
				+ "&meteo : Jean-Pierre Pernaut vous contacte personnellement pour vous donner en exclusivit� les meilleures pr�visions m�t�o (alias: &metevobot)\n"
				).queue();
	}
	
}
