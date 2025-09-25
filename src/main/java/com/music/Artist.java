package com.music;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String artistId;
    private String name;
    private String genre;
    private List<Song> songs;

    public Artist(String artistId, String name, String genre) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
        this.songs = new ArrayList<>();
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void displayArtistDetails() {
        System.out.println("Artist ID: " + artistId);
        System.out.println("Name: " + name);
        System.out.println("Genre: " + genre);
        if (!songs.isEmpty()) {
            System.out.println("Songs by " + name + ":");
            for (Song song : songs) {
                System.out.println("  - " + song.getTitle() + " (" + song.getDuration() + "s)");
            }
        }
    }
}


