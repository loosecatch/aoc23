package it.pers.aoc23.model.camelcards;

import lombok.Getter;

import java.util.*;

@Getter
public class Hand implements Comparable<Hand>{
    public final static int HAND_LENGTH = 5;
    private final List<CardLabelDomain> cards;
    private final int bid;
    private final String toString;
    private final boolean isJokerVariant;

//    private final static Map<String,Integer> types = new HashMap<>();
//    static {
//
////        XXXXX {5} && 0 J                //Five of a kind:
////        XXXXJ {4, 1} && 1 J             //Five of a kind:
////        XXXJJ {3, 2} && 2 J             //Five of a kind:
////        XXJJJ {2, 3} && 3 J             //Five of a kind:
////        XJJJJ {1, 4} && 4 J             //Five of a kind:
////        XXXXY {4, 1} && 0 J             //Four of a kind:
////        XXXYY {3, 2} && 0 J             //Full house:
////        XXXJY {3, 1, 1} && 1 J          //Four of a kind:
////        XXJJY {2, 2, 1} && 2 J          //Four of a kind:
////        XJJJY {1, 3, 1} && 3 J          //Four of a kind:
////        XXJYY {2, 1, 2} && 1 J          //Full house:
////        XXXYZ {3, 1, 1} && 0 J          //Three of a kind:
////        XXYYZ {2, 2, 1} && 0 J          //Two pair:
////        XXJYZ {2, 1, 1, 1} && 1 J       //Three of a kind:
////        XJJYZ {1, 2, 1, 1} && 2 J       //Three of a kind:
////        XXYZW {2, 1, 1, 1} && 0 J       //One pair:
////        XJYZW {1, 1, 1, 1, 1} && 1 J    //One pair:
////        XYZWU {1, 1, 1, 1, 1} && 0 J    //High card:
//    }
    public Hand(String line, boolean isJokerVariant) {
        this.isJokerVariant=isJokerVariant;
        toString=line;
        var cardString = line.split(" ")[0];
        this.bid = Integer.parseInt(line.split(" ")[1]);
        cards = new ArrayList<>(Arrays.stream(cardString.split("")).map(CardLabelDomain::getEnumByLabel).toList());
    }
    public Hand(String line) {
        this(line,false);
    }

    public Integer getType(){
        Map<CardLabelDomain,Integer> map = new HashMap<>();
        this.cards.stream().distinct().forEach(card -> map.put(card,getOccurencesNumber(card)));
        int jollyOccurrences = Objects.requireNonNullElse(map.get(CardLabelDomain.JOKER),0);
        int max = map.values().stream().reduce(Integer::max).get();
        switch (map.size()) {
            case 1 -> {
                return 7;
            }
            case 2 -> {
                if (jollyOccurrences > 0) return 7;
                else if (max == 4) return 6;
                else if (max == 3) return 5;
            }
            case 3 -> {
                if (jollyOccurrences > 1) return 6;
                else if (jollyOccurrences == 1){
                    if (max == 3) return 6;
                    else return 5;
                }
                else{
                    if (max == 3) return 4;
                    else return 3;
                }
            }
            case 4 -> {
                if (jollyOccurrences > 0) return 4;
                else return 2;
            }
            case 5 -> {
                if (jollyOccurrences == 0) return 1;
                else return 2;
            }
        }
        System.out.println("Attenzione! type undefined!");
        return -1;
    }

    private int getOccurencesNumber(CardLabelDomain card){
        return this.cards.stream().filter(cardLabelDomain -> cardLabelDomain.equals(card)).toList().size();
    }

    @Override
    public String toString(){
        var string = toString;
        return "Hand: "+string.split(" ")[0]+" Bid: "+bid+" Type: "+getType()+"\n";
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
