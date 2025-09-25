package com.music;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicManager musicManager = new MusicManager(scanner);
        musicManager.run();
        scanner.close();
    }
}


