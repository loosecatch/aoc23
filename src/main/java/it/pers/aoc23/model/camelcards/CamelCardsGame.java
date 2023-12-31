package it.pers.aoc23.model.camelcards;

import it.pers.aoc23.model.days.Day;

import java.util.ArrayList;
import java.util.List;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class CamelCardsGame implements Day {
    private final List<Hand> hands;

    public CamelCardsGame(String filename) {
        List<Hand> tmp = new ArrayList<>();
        getFileLines(filename).forEach(line -> tmp.add(new Hand(line)));
        this.hands=tmp.stream().sorted().toList();
    }

    @Override
    public void partOne() {
        var winnings= hands.stream().map(hand -> hand.getBid()*(hands.indexOf(hand)+1)).reduce(Integer::sum).get();
        System.out.println(this);
        System.out.println("Winnings: "+winnings);
    }

    @Override
    public void partTwo() {
        var newHands= hands.stream().map(hand -> new Hand(hand.getToString().replaceAll("J","N"),true)).sorted().toList();
        var winnings= newHands.stream().map(hand -> hand.getBid()*(newHands.indexOf(hand)+1)).reduce(Integer::sum).get();
        System.out.println(newHands);
        System.out.println("Winnings: "+winnings);
    }

    @Override
    public String toString(){
        return this.hands.toString();
    }
}
