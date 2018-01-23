package net.squalabot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.ChannelType;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

/*
 * sweg
 */
public class MainBot extends ListenerAdapter{
	
	private char prefix = '&';
	
	public static void main(String[] args) 
			throws LoginException, InterruptedException {
		boolean stop = false;
		if (args.length < 1) {
	        System.out.println("Please specify bot token.");
	    }
		else {
			try {
				// JDA initialization
				System.out.println("Initialization of Discord bot.\nToken: " + args[0]);
				JDA jda = new JDABuilder(AccountType.BOT).setToken(args[0]).addEventListener(new MainBot()).buildBlocking();
				System.out.println("Bot successfully initialized.");
				// Loop for terminal commands (server based part)
				while(!stop) {
					Scanner scanner = new Scanner(System.in);
					String cmd = scanner.next();
					if(cmd.equalsIgnoreCase("stop")) {
						System.out.println("Bot disconnection...");
						jda.shutdown();
						stop=!stop;
						System.out.println("Bot successfully disconnected.");
					}
				}
			}
			catch (LoginException e) {
				e.printStackTrace();
				System.out.println("Login exception occurred: attempt to connect (JDA initialization failed).");
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Interrupted exception occurred: shutdown requested while trying to initialize JDA.");
			}
			finally {
				if(!stop) {
					System.out.println("Unpredicted failure.");
				}
			}
		}
	}
	
	/*
	 * Overridden method to deal with user's messages from the discord chat perspective
	 * (non-Javadoc)
	 * @see net.dv8tion.jda.core.hooks.ListenerAdapter#onMessageReceived(net.dv8tion.jda.core.events.message.MessageReceivedEvent)
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// Basic information on event
		JDA jda = event.getJDA();
		long responseNumber = event.getResponseNumber();
		
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		
		String content = message.getContentDisplay();
		
		boolean isBot = author.isBot();
		
		if(event.isFromType(ChannelType.TEXT)) {
			if(!message.isWebhookMessage() && content.charAt(0) == prefix) {
				switch (content) {
				case "&help":
					new HelpCommand(event);
					break;
				case "&coucou":
					new CoucouCommand(event);
					break;
				case "&shutdown":
					new ShutdownCommand(event);
					break;
				case "&loupgarou":
					new LoupgarouCommand(event);
					break;
				default:
					channel.sendMessage("Action invalide (ou pas encore implémentée mdr), tapez '&help' pour plus d'informations sur les commandes.").queue();
				}
			}
		}
	}
}
