package it.pers.aoc23.model;

import it.pers.aoc23.model.days.Day;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

import static it.pers.aoc23.utils.Utils.getFile;


public class Engine implements Day {
    private String[][] schematic;
    private final String filename;
    private final Set<String> symbols;

    public Engine(String filename){
        this.filename=filename;
        this.symbols= new HashSet<>();
        var bufferedReader = getFile(filename);
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i=0;
        List<String[]> rows = new ArrayList<>();
        while (line != null){
            var row = line.split("(?!^)");
            System.out.println(Arrays.toString(row));
            rows.add(row);
            var symbols=line.replaceAll("\\d","").replaceAll("\\.","").split("(?!^)");
            this.symbols.addAll(Arrays.stream(symbols).filter((sym)->!sym.equals("")).toList());
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.schematic= new String[rows.size()][rows.get(0).length];
        for (String[] row: rows) {
            schematic[i]=row;
            i++;
        }
        System.out.println(this.symbols);
    }
    @Override
    public void partOne() {
        List<Position> positionsToCheck = new ArrayList<>();
        this.symbols.forEach((s -> positionsToCheck.addAll(getPosition(s))));
        System.out.println(positionsToCheck);
        List<Position> digitsToCheck = new ArrayList<>();
        positionsToCheck.forEach((position -> digitsToCheck.addAll(getAdjacent(position))));
        for(Position digit: positionsToCheck){
            //TODO add digit to set if successful check if its part of a number, reconstruct the numbers and sum
            // important: implement .equals for position
        }
    }

    @Override
    public void partTwo() {

    }


    private List<Position> getPosition(String symbol){
        int i = 0;
        var list = new ArrayList<Position>();
        for (String[] row: this.schematic) {
            int j=0;
            for (String col: row) {
                if (col.equals(symbol)) {
                    var pos = new Position(i,j);
                    list.add(pos);
                }
                j++;
            }
            i++;
        }
        return list;
    }

    private List<Position> getAdjacent(Position pos){
        var arr = this.schematic;
        var i = pos.row;
        var j= pos.col;

        // Size of given 2d array
        int n = arr.length;
        int m = arr[0].length;

        // Initialising a vector array where
        // adjacent elements will be stored
        List<Position> v = new ArrayList<>();

        // Checking for adjacent elements
        // and adding them to array

        // Deviation of row that gets adjusted
        // according to the provided position
        for (int dx = (i > 0 ? -1 : 0); dx <= (i < n ? 1 : 0);
             ++dx) {

            // Deviation of the column that
            // gets adjusted according to
            // the provided position
            for (int dy = (j > 0 ? -1 : 0);
                 dy <= (j < m ? 1 : 0); ++dy) {
                if (dx != 0 || dy != 0) {
                    var found=arr[i + dx][j + dy];
                    if (!found.equals(".")){
                        v.add(new Position(i + dx, j + dy));
                        System.out.println(found);
                    }
                }
            }
        }

        // Returning the vector array
        return v;
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col){
            this.col=col;
            this.row=row;
        }

        @Override
        public String toString(){
            return "(x="+row+",y="+col+")";
        }
    }
}
