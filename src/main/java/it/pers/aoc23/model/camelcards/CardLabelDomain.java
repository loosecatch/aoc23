package it.pers.aoc23.model.camelcards;

public enum CardLabelDomain implements Comparable<CardLabelDomain>{
    A("A",14),
    K("K",13),
    Q("Q",12),
    J("J",11),
    T("T",10),
    NINE("9",9),
    EIGHT("8",8),
    SEVEN("7",7),
    SIX("6",6),
    FIVE("5",5),
    FOUR("4",4),
    THREE("3",3),
    TWO("2",2),
    JOKER("N",1);
    private final String label;
    private final Integer strength;

    CardLabelDomain(String label, int strength) {
        this.label=label;
        this.strength=strength;
    }

    public static CardLabelDomain getEnumByLabel(String label) {
        for (CardLabelDomain cardLabelDomain : CardLabelDomain.values()) {
            if (cardLabelDomain.getLabel().equals(label)) {
                return cardLabelDomain;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getLabel() {
        return label;
    }
    public Integer getStrength() { return strength; }

    @Override
    public String toString(){
        return label;
    }
}
