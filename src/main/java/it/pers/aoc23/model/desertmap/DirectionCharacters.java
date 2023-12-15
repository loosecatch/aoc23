package it.pers.aoc23.model.desertmap;

import it.pers.aoc23.model.camelcards.CardLabelDomain;

public enum DirectionCharacters {
    RIGHT("R"),
    LEFT("L");
    private final String id;

    DirectionCharacters(String id){
        this.id=id;
    }

    public static DirectionCharacters getEnumByLabel(String label) {
        for (DirectionCharacters directionCharacters : DirectionCharacters.values()) {
            if (directionCharacters.getId().equals(label)) {
                return directionCharacters;
            }
        }
        throw new IllegalArgumentException();
    }

    private String getId() {
        return id;
    }
}
