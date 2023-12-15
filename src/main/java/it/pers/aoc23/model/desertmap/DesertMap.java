package it.pers.aoc23.model.desertmap;

import it.pers.aoc23.model.days.Day;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class DesertMap implements Day {
    private final List<Node> nodes;
    private final String directions;

    public DesertMap(String filename) {
        var lines = getFileLines(filename);
        this.directions = lines.get(0);
        lines.remove(0);
        nodes = lines.stream().map(Node::new).sorted().toList();
    }

    @Override
    public void partOne() {
        int counter = 0;
        boolean found = false;
        var node = nodes.get(0);
        System.out.print("First ");
        for (int i=0; i<directions.length() && !found; i=(i+1)%directions.length()){
            System.out.print("Node: "+node+"\n");
            if(node.getLabel().equals("ZZZ")){
                found=true;
            }else{
                var dir = DirectionCharacters.getEnumByLabel(String.valueOf(directions.charAt(i)));
                System.out.println("Direction #"+i+": "+dir.name());
                var nextLabel = node.getDirection(dir);
                if (nextLabel == null) {
                    System.out.println("No node!");
                    return;
                }else{
                    counter++;
                    node = nodes.stream().filter(n -> n.getLabel().equals(nextLabel)).toList().get(0);
                }
            }
        }
        System.out.println("Found after "+counter+" steps");
    }

    @Override
    public void partTwo() {


        int counter = 0;
        boolean found = false;
        var currentNodes = nodes.stream().filter(node -> getLastChar(node.getLabel()).equals("A")).toList();
        for (int i=0; i<directions.length() && !found; i=(i+1)%directions.length()) {
            if (currentNodes.stream().allMatch(node -> getLastChar(node.getLabel()).equals("Z"))) found=true;
            else{
                counter++;
                var dir = DirectionCharacters.getEnumByLabel(String.valueOf(directions.charAt(i)));
                var nextLabels = currentNodes.stream().map(node -> node.getDirection(dir));
                currentNodes = nextLabels.map( label -> nodes.stream().filter(n -> n.getLabel().equals(label)).toList().get(0)).toList();
            }
        }
        System.out.println("Found after "+counter+" steps");

    }

    private String getLastChar(String string){
        return String.valueOf(string.charAt(string.length()-1));
    }
}
