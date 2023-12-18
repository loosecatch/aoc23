package it.pers.aoc23.model.desertmap;

import it.pers.aoc23.model.days.Day;
import it.pers.aoc23.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
        int counter = stepsToDestination(nodes.get(0), "ZZZ", true);
        System.out.println("Found after " + counter + " steps");
    }

    @Override
    public void partTwo() {
        var currentNodes = nodes.stream().filter(node -> getLastChar(node.getLabel()).equals("A")).toList();
        var counter = calculateStepsForNodes(currentNodes, "Z").stream().reduce(1L, Utils::lcm);
        System.out.println("Found after " + counter + " steps");
    }

    private int stepsToDestination(Node node, String destination, boolean isPartOne) {
        int counter = 0;
        boolean found = false;
        System.out.print("First ");
        for (int i = 0; i < directions.length() && !found; i = (i + 1) % directions.length()) {
            System.out.print("Node: " + node + "\n");
            String label = isPartOne ? node.getLabel() : getLastChar(node.getLabel());
            if (label.equals(destination)) {
                found = true;
            } else {
                var dir = DirectionCharacters.getEnumByLabel(String.valueOf(directions.charAt(i)));
                System.out.println("Direction #" + i + ": " + dir.name());
                var nextLabel = node.getDirection(dir);
                if (nextLabel == null) {
                    System.out.println("No node!");
                } else {
                    counter++;
                    node = nodes.stream().filter(n -> n.getLabel().equals(nextLabel)).toList().get(0);
                }
            }
        }
        return counter;
    }

    public List<Long> calculateStepsForNodes(List<Node> nodeList, String destination) {
        List<Long> resultList = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<Integer>> futures = new ArrayList<>();

        for (Node node : nodeList) {
            Future<Integer> future = executor.submit(() -> stepsToDestination(node,destination,false));
            futures.add(future);
        }

        for (Future<Integer> future : futures) {
            try {
                int result = future.get();
                resultList.add((long) result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        return resultList;
    }


    private String getLastChar(String string){
        return String.valueOf(string.charAt(string.length()-1));
    }
}