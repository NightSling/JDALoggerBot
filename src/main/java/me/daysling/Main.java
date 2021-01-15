package me.daysling;

import me.daysling.gui.GUI;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends ListenerAdapter {

    Scanner scan;
    Scanner scan1;

    public void setupFiles(){
        File file1 = new File("token.txt");
        File file2 = new File("channelid.txt");
        if(!(file1.exists())){

            try {
                 file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new GUI().maine("Hey! The File 'token.txt' was created, Put your bot token there and restart application!");

        }

        if(!(file2.exists())){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new GUI().maine("Hey! The File 'channelid.txt' was created, Put channel id and restart!");

        }

        {
            try {
                scan = new Scanner(file1).useDelimiter("\\Z");
                scan1 = new Scanner(file2).useDelimiter("\\Z");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new Main().setupFiles();
        if(args.length < 1){
            System.out.println("Please Enter Token As Pamar!");
            System.exit(1);
        }
        JDABuilder.createDefault(new Main().scan.next(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .addEventListeners(new Main())
        ;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        Message message = e.getMessage();
        String[] mes = e.getMessage().getContentRaw().split(" ");
        if(message.getContentRaw().equals("!ping")){
            MessageAction reply = message.reply("Pong! " + e.getJDA().getGatewayPing());
            reply.queue();
        }else if(mes[0].equals("!log")){
            MessageEmbed builder = new EmbedBuilder()
                    .setTitle("AntiCheat Log!")
                    .addField("Cheater: ", mes[1], true)
                    .addField("Failed: ", mes[2], true)
                    .setFooter("Made By daysling#0651 !!!")
                    .build();

            MessageChannel messageChannel = e.getJDA().getTextChannelById(scan1.next());
            messageChannel.sendMessage(builder);
        }
    }
}
