package com.music;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicManager {
    private List<Artist> artists;
    private List<Song> songs;
    private Scanner scanner;

    public MusicManager(Scanner scanner) {
        this.artists = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.scanner = scanner;
    }

    public void addArtist() {
        System.out.println("\n--- Add New Artist ---");
        System.out.print("Enter Artist ID: ");
        String artistId = scanner.nextLine();
        System.out.print("Enter Artist Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        Artist newArtist = new Artist(artistId, name, genre);
        artists.add(newArtist);
        System.out.println("Artist added successfully!");
    }

    public Artist searchArtist(String artistId) {
        for (Artist artist : artists) {
            if (artist.getArtistId().equalsIgnoreCase(artistId)) {
                return artist;
            }
        }
        return null;
    }

    public void addSong() {
        System.out.println("\n--- Add New Song ---");
        System.out.print("Enter Song ID: ");
        String songId = scanner.nextLine();
        System.out.print("Enter Song Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Artist ID for the song: ");
        String artistId = scanner.nextLine();
        Artist artist = searchArtist(artistId);

        if (artist == null) {
            System.out.println("Artist not found. Please add the artist first.");
            return;
        }

        System.out.print("Enter Song Duration (seconds): ");
        int duration = Integer.parseInt(scanner.nextLine());

        Song newSong = new Song(songId, title, artistId, duration);
        songs.add(newSong);
        artist.addSong(newSong);
        System.out.println("Song added successfully!");
    }

    public Song searchSong(String songId) {
        for (Song song : songs) {
            if (song.getSongId().equalsIgnoreCase(songId)) {
                return song;
            }
        }
        return null;
    }

    public void displayAllArtists() {
        System.out.println("\n--- All Artists ---");
        if (artists.isEmpty()) {
            System.out.println("No artists in the system.");
            return;
        }
        for (Artist artist : artists) {
            artist.displayArtistDetails();
            System.out.println("--------------------");
        }
    }

    public void displayAllSongs() {
        System.out.println("\n--- All Songs ---");
        if (songs.isEmpty()) {
            System.out.println("No songs in the system.");
            return;
        }
        for (Song song : songs) {
            song.displaySongDetails();
            System.out.println("--------------------");
        }
    }

    public void displayMenu() {
        System.out.println("\n--- Music Management Menu ---");
        System.out.println("1. Add New Artist");
        System.out.println("2. Search Artist");
        System.out.println("3. Add New Song");
        System.out.println("4. Search Song");
        System.out.println("5. Display All Artists");
        System.out.println("6. Display All Songs");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        int choice;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addArtist();
                        break;
                    case 2:
                        System.out.print("Enter Artist ID to search: ");
                        String artistId = scanner.nextLine();
                        Artist artist = searchArtist(artistId);
                        if (artist != null) {
                            artist.displayArtistDetails();
                        } else {
                            System.out.println("Artist not found.");
                        }
                        break;
                    case 3:
                        addSong();
                        break;
                    case 4:
                        System.out.print("Enter Song ID to search: ");
                        String songId = scanner.nextLine();
                        Song song = searchSong(songId);
                        if (song != null) {
                            song.displaySongDetails();
                        } else {
                            System.out.println("Song not found.");
                        }
                        break;
                    case 5:
                        displayAllArtists();
                        break;
                    case 6:
                        displayAllSongs();
                        break;
                    case 7:
                        System.out.println("Exiting Music Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0; // To keep the loop running
            }
        } while (choice != 7);
    }
}


