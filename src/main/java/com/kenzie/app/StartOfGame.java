package com.kenzie.app;

import dto.CluesDTO;

public class
StartOfGame {
    public static void startCatQuestOutput(CluesDTO dtoObj, int index) {
        System.out.println("Category: " + dtoObj.getClues().get(index).getCategory().getTitle());
        System.out.println("Question: " + dtoObj.getClues().get(index).getQuestion());
    }
}
