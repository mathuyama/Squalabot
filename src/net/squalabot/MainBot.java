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
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.regex.*;

import net.squalabot.command.Command;
import net.squalabot.command.*;

/*
 * sweg
 */
public class MainBot extends ListenerAdapter{
	
	static JDABuilder builder;
	static JDA jda;
	
	static Hashtable<String, Command> commands = new Hashtable<>();
	
	private static void initCommands() {
		commands.put("coucou", new CoucouHandler());
		//commands.put("music", new MusicHandler());
		commands.put("crypto", new CryptoHandler());
	}
	
	public static void main(String[] args) 
			throws LoginException, InterruptedException {
		boolean stop = false;
		if (args.length < 1) {
	        System.out.println("Please specify bot token.");
	    }
		else {

				// JDA initialization
				System.out.println("Initialization of Discord bot.\nToken: " + args[0]);
				
				builder = new JDABuilder(AccountType.BOT)
						.setToken(args[0])
						.setAudioEnabled(true)
						.setGame(Game.of(Game.GameType.DEFAULT,Statics.prefix+"help"));
				
				builder.addEventListener(new MainBot());
				
				try {
					jda = builder.buildBlocking();
				}
				catch (LoginException e) {
					e.printStackTrace();
					System.out.println("Login exception occurred: attempt to connect (JDA initialization failed).");
				}
				catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Interrupted exception occurred: shutdown requested while trying to initialize JDA.");
				}
				catch (Exception e) {
					e.printStackTrace();
					if(!stop) {
						jda.shutdown();
						System.out.println("Unpredicted failure, JDA shutdown.");
					}
				}
				
				initCommands();
				
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
		String[] splitedContent = content.split(" ");
		String argv = splitedContent[0];
		
		boolean isBot = author.isBot();
		
		if(event.isFromType(ChannelType.TEXT)) {
			if(!message.isWebhookMessage() && !isBot && argv.charAt(0) == Statics.prefix) {
				switch (argv.substring(1, argv.length())) {
				case "coucou":
					commands.get("coucou").handle(event);
					break;
				case "help":
					channel.sendMessage("Liste des commandes:").queue();
					for(String key:commands.keySet()) {
						channel.sendMessage(commands.get(key).description()+ "\n").queue();
					}
					break;
				case "crypto":
					commands.get("crypto").handle(event);
					break;
				default:
					channel.sendMessage("Action invalide (ou pas encore impl�ment�e mdr), tapez '"+Statics.prefix+"help' pour plus d'informations sur les commandes.").queue();
				}
			}
		}
	}
}
