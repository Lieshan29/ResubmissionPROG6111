package com.music;

public class Song {
    private String songId;
    private String title;
    private String artistId;
    private int duration;

    public Song(String songId, String title, String artistId, int duration) {
        this.songId = songId;
        this.title = title;
        this.artistId = artistId;
        this.duration = duration;
    }

    public String getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistId() {
        return artistId;
    }

    public int getDuration() {
        return duration;
    }

    public void displaySongDetails() {
        System.out.println("Song ID: " + songId);
        System.out.println("Title: " + title);
        System.out.println("Artist ID: " + artistId);
        System.out.println("Duration: " + duration + " seconds");
    }
}


