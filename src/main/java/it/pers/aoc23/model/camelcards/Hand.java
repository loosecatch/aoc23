package it.pers.aoc23.model.camelcards;

import java.util.*;

public class Hand implements Comparable<Hand>{
    public final static int HAND_LENGTH = 5;
    private final List<CardLabelDomain> cards;
    private final int bid;
    private final String toString;

    public Hand(String line) {
        toString=line;
        var cardString = line.split(" ")[0];
        this.bid = Integer.parseInt(line.split(" ")[1]);
        cards = new ArrayList<>(Arrays.stream(cardString.split("")).map(CardLabelDomain::getEnumByLabel).toList());
    }

    public Integer getType(){
        Map<CardLabelDomain,Integer> map = new HashMap<>();
        var diffCardList = this.cards.stream().distinct().toList();
        diffCardList.forEach(card -> map.put(card,getOccurencesNumber(card)));
        var minMaxDiff = minMaxDiff(map);
        switch (map.size()){
            case 1:
                return 7;
            case 2:
               if (minMaxDiff>2) return 6;
               else return 5;
            case 3:
                if (minMaxDiff>1) return 4;
                else return 3;
            case 4:
                return 2;
            case 5:
                return 1;
            default:
                return -1;
        }
    }

    private int getOccurencesNumber(CardLabelDomain card){
        return this.cards.stream().filter(cardLabelDomain -> cardLabelDomain.equals(card)).toList().size();
    }

    private int minMaxDiff(Map<CardLabelDomain,Integer>  map){
        var max = map.values().stream().reduce(Integer::max).get();
        var min = map.values().stream().reduce(Integer::min).get();
        return max-min;
    }
    @Override
    public String toString(){
        return "Hand: "+toString.split(" ")[0]+" Bid: "+bid+" Type: "+getType();
    }

    @Override
    public int compareTo(Hand o) {
        var cmp = this.getType().compareTo(o.getType());
        if (cmp == 0){
            for(int i=0;i<HAND_LENGTH;i++){
                var cCmp= o.cards.get(i).compareTo(this.cards.get(i));
                if (cCmp !=  0) return cCmp;
            }
            return 0;
        }else return cmp;
    }

    public int getBid() {
        return bid;
    }
}
