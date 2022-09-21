package com.kenzie.app;

// import necessary libraries


import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CluesDTO;

import java.util.Scanner;

public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */

    public static void main(String[] args) {
        try {
            int userScore = 0;
            int incorrectInputCounter = 0;
            boolean exitFlag = false;
            Scanner scanner = new Scanner(System.in);
            String cluesURL = "https://jservice.kenzie.academy/api/clues";
            String response = CustomHttpClient.sendGET(cluesURL);
            ObjectMapper objectMapper = new ObjectMapper();
            CluesDTO cluesDTOObj = objectMapper.readValue(response, CluesDTO.class);

            while (!exitFlag) {

                int randomIndex = (int) (Math.random() * 100);

                StartOfGame.startCatQuestOutput(cluesDTOObj, randomIndex);

                String playerInput = scanner.nextLine();

                if (playerInput.isBlank()) {
                    System.out.println("You have entered incorrect input.  Please don't enter empty or blank space.  \nGetting new question...\n");
                    incorrectInputCounter++;
                } else if (playerInput.equalsIgnoreCase(cluesDTOObj.getClues().get(randomIndex).getAnswer())) {
                    userScore++;
                    System.out.println("Correct answer!  Great job! Your current score is: " + userScore + "\n");
                } else {
                    System.out.println("Sorry, that is incorrect.  The correct answer is " + cluesDTOObj.getClues().get(randomIndex).getAnswer() + "\n");
                    incorrectInputCounter++;
                }

                if (userScore + incorrectInputCounter == 10) {
                    System.out.println("\nGame over!  Your total score is: " + userScore);
                    System.out.println("Would you like to play again?  Type 'y' for yes and 'n' for no");
                    String continueGame = scanner.nextLine();

                    if (continueGame.equalsIgnoreCase("n")) {
                        System.out.println("Thank you for playing!");
                        exitFlag = true;
                    } else if (continueGame.equalsIgnoreCase("y")) {
                        System.out.println("Starting new game...");
                        userScore = 0;
                        incorrectInputCounter = 0;
                    } else {
                        System.out.println("Incorrect input.  Ending game...");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
        }
    }
}

