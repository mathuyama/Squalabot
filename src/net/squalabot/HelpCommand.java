package net.squalabot;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand {

	public HelpCommand (MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		channel.sendMessage(
				"Liste des commandes :\n"
				+ "---\n"
				+ "&coucou : Le bot vous adresse ses plus sincères salutations !\n"
				+ "&play : Lire une musique (ouais on verra NYD)\n"
				+ "&meteo : Jean-Pierre Pernaut vous contacte personnellement pour vous donner en exclusivité les meilleures prévisions météo (alias: &metevobot)\n"
				).queue();
	}
	
}
