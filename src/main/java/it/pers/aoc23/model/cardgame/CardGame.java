package it.pers.aoc23.model.cardgame;

import it.pers.aoc23.model.days.Day;

import java.util.ArrayList;
import java.util.List;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class CardGame implements Day {

    List<ScratchCard> cards;


    public CardGame(String filename){
        this.cards=getFileLines(filename).stream().map(ScratchCard::new).sorted().toList();
    }

    @Override
    public void partOne() {
        this.cards.forEach(scratchCard -> System.out.println(scratchCard+" points: "+scratchCard.getPoints()));
        var sum = this.cards.stream().map(ScratchCard::getPoints).reduce((double) 0,Double::sum);
        System.out.println("Somma: "+sum);
    }

    @Override
    public void partTwo() {
        List<ScratchCard> newCards = new ArrayList<>(this.cards);
        //System.out.println("Initial new ");
        //newCards.stream().sorted().forEach(System.out::println);
        this.cards.forEach(scratchCard -> newCards.addAll(getCards(scratchCard)));
        //newCards.stream().sorted().forEach( c -> System.out.println(c.getID()));
        System.out.println(newCards.size());
    }

    private List<ScratchCard> getCards(ScratchCard card){
        List<ScratchCard> res = new ArrayList<>();
        try{
            var n = card.getMatches().size();
            //System.out.println("Computing card: " + card.getID());
            for (int i=0;i<n;i++){
                var c = this.cards.get(i+card.getID());
                //System.out.println("adding card: " + c.getID());
                res.add(c);
                res.addAll(getCards(c));
            }
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return res;
    }
    @Override
    public String toString(){
        return cards.toString();
    }
}
