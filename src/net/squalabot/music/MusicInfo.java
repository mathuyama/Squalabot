package net.squalabot.music;

import net.dv8tion.jda.core.entities.Member;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class MusicInfo {
	
	private AudioTrack track;
	private Member author;
	
	MusicInfo(AudioTrack track, Member author){
		this.track = track;
		this.author = author;
	}
	
	public AudioTrack getTrack() {
        return track;
    }

    public Member getAuthor() {
        return author;
}
	
}
