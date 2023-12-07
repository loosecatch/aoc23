package it.pers.aoc23.model.cardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ScratchCard implements Comparable<ScratchCard>{
    private final int ID;
    private final List<String> winningNumbers;
    private final List<String> haveNumbers;
    private final List<String> matches;

    private final static String DELIMITATOR = " \\| ";

    public ScratchCard(String cardString) {
        var parts = cardString.split(DELIMITATOR);
        var header = parts[0].split(": ");
        this.winningNumbers = Stream.of(header[1].split(" ")).filter((s -> s.length()!=0)).toList();
        this.ID = Integer.parseInt(header[0].replaceAll("[a-zA-Z]","").trim());
        this.haveNumbers = Stream.of(parts[1].split(" ")).filter((s -> s.length()!=0)).toList();
        this.matches = new ArrayList<>();
        winningNumbers.forEach(winningNumber -> {
            if (haveNumbers.contains(winningNumber)){
                this.matches.add(winningNumber);
            }
        });
    }

    public double getPoints(){
        return this.matches.size() > 0 ? Math.pow(2, this.matches.size()-1 ) : 0;
    }
    @Override
    public String toString(){
        return "G "+ID+" winning: "+winningNumbers+" have: "+haveNumbers;
    }

    public List<String> getMatches() {
        return this.matches;
    }

    @Override
    public int compareTo(ScratchCard o) {
        return Integer;
    }
}
