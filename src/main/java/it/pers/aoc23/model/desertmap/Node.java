package it.pers.aoc23.model.desertmap;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Node implements Comparable<Node>{
    private final String label;
    private final String left;
    private final String right;

    public Node(String line) {
        line=line.replaceAll(" ","");
        var s1 = line.split("=");
        var s2 = s1[1].replaceAll("[(]|[)]","").split(",");
        right = s2[1];
        left = s2[0];
        label = s1[0];
    }

    public String getDirection(DirectionCharacters direction){
        switch (direction){

            case RIGHT -> {
                return right;
            }
            case LEFT -> {
                return left;
            }
        }
        return null;
    }
    @Override
    public int compareTo(Node other){
        return this.label.compareTo(other.label);
    }
}
