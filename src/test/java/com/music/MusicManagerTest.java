package com.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MusicManagerTest {

    private MusicManager musicManager;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        musicManager = new MusicManager(new Scanner(System.in));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    void testAddArtist() {
        provideInput("A001\nArtist Name\nPop\n");
        musicManager.addArtist();
        Artist foundArtist = musicManager.searchArtist("A001");
        assertNotNull(foundArtist);
        assertEquals("Artist Name", foundArtist.getName());
        assertEquals("Pop", foundArtist.getGenre());
        assertTrue(getOutput().contains("Artist added successfully!"));
    }

    @Test
    void testSearchArtist_Found() {
        provideInput("A002\nAnother Artist\nRock\n");
        musicManager.addArtist();
        Artist foundArtist = musicManager.searchArtist("A002");
        assertNotNull(foundArtist);
        assertEquals("A002", foundArtist.getArtistId());
    }

    @Test
    void testSearchArtist_NotFound() {
        provideInput("A003\nThird Artist\nJazz\n");
        musicManager.addArtist();
        Artist foundArtist = musicManager.searchArtist("A004");
        assertNull(foundArtist);
    }

    @Test
    void testAddSong() {
        provideInput("A005\nSong Artist\nElectronic\nS001\nSong Title\nA005\n180\n");
        musicManager.addArtist();
        testOut.reset(); // Clear output for song addition
        musicManager.addSong();
        Song foundSong = musicManager.searchSong("S001");
        assertNotNull(foundSong);
        assertEquals("Song Title", foundSong.getTitle());
        assertEquals("A005", foundSong.getArtistId());
        assertEquals(180, foundSong.getDuration());
        assertTrue(getOutput().contains("Song added successfully!"));

        Artist artist = musicManager.searchArtist("A005");
        assertTrue(artist.getSongs().contains(foundSong));
    }

    @Test
    void testAddSong_ArtistNotFound() {
        provideInput("S002\nAnother Song\nA999\n200\n");
        musicManager.addSong();
        Song foundSong = musicManager.searchSong("S002");
        assertNull(foundSong);
        assertTrue(getOutput().contains("Artist not found. Please add the artist first."));
    }

    @Test
    void testSearchSong_Found() {
        provideInput("A006\nArtist For Song\nClassical\nS003\nClassical Piece\nA006\n300\n");
        musicManager.addArtist();
        musicManager.addSong();
        Song foundSong = musicManager.searchSong("S003");
        assertNotNull(foundSong);
        assertEquals("S003", foundSong.getSongId());
    }

    @Test
    void testSearchSong_NotFound() {
        provideInput("A007\nArtist For Song 2\nBlues\nS004\nBlues Song\nA007\n240\n");
        musicManager.addArtist();
        musicManager.addSong();
        Song foundSong = musicManager.searchSong("S005");
        assertNull(foundSong);
    }
}


