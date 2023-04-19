package com.example.adventofcode202203;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AdventOfCode202203Application {

    public static void main(String[] args)
            throws IOException {

        // load data from file
        BufferedReader bf = new BufferedReader(new FileReader("AoC.txt"));
        String str;
        List<String> itemsInBackPack = new ArrayList<>();
        while ((str = bf.readLine()) != null) {
            itemsInBackPack.add(str);

        }

        int score1 = 0;
        for (String itemBackPack : itemsInBackPack) {
            int lengthItem = itemBackPack.length();
            String firstPart = itemBackPack.substring(0, (lengthItem / 2));
            String secondPart = itemBackPack.substring((lengthItem / 2));

            for (int i = 0; i < firstPart.length(); i++) {
                // Check for common char
                if (firstPart.contains(Character.toString(secondPart.charAt(i)))) {
                    // If contains, then calculate priority number of specific char, and add to score
                    score1 += priorityScoreChar(secondPart.charAt(i));
                    break;
                }
            }

        }

        System.out.println("score 1: " + score1);


        // deel 2, vergelijk de eerste 3 met elkaar

        int score2 = 0;
        for (int i = 0; i < itemsInBackPack.size(); i += 3) {
            int shortestLength = 1000000;
            List<String> threeBackPacks = Arrays.asList(itemsInBackPack.get(i), itemsInBackPack.get(i + 1), itemsInBackPack.get(i + 2));
            List<String> threeBackPacksSortedByLength = new ArrayList<>();
            //create list waarbij de kortste string als eerste komt, zodat je door die characters kunt loopen en zeker weten dat je niks mist.
            for (String s : threeBackPacks) {
                if (s.length() < shortestLength) {
                    shortestLength = s.length();
                    threeBackPacksSortedByLength.add(0, s);
                } else {
                    threeBackPacksSortedByLength.add(s);
                }
            }
            for (int j = 0; j < shortestLength; j++) {
                if (threeBackPacksSortedByLength.get(1).contains(Character.toString(threeBackPacksSortedByLength.get(0).charAt(j)))) {
                    // If contains then check if next string also contains
                    if (threeBackPacksSortedByLength.get(2).contains(Character.toString(threeBackPacksSortedByLength.get(0).charAt(j)))) {
                        //if next string also contains that char, calculate the priority number and add it to score2
                        score2 += priorityScoreChar(threeBackPacksSortedByLength.get(0).charAt(j));
                        break;
                    }
                }
            }
        }
        System.out.println("score 2: " + score2);

    }

    public static int priorityScoreChar(char ch){
        int result = ch;
        if (Character.isLowerCase(ch)) {
            result -= 96;
        } else {
            result -= 38;
        }
        return result;
    }
}




