package it.pers.aoc23.model;

import it.pers.aoc23.model.days.Day;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.*;

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
        Set<Position> checkedDigits = new HashSet<>();
        positionsToCheck.forEach((position -> digitsToCheck.addAll(getAdjacent(position))));
        List<Integer> numbersToSum = new ArrayList<>();
        Set<Position> alreadyComposedPositions = new HashSet<>();
        for(Position digit: digitsToCheck.stream().sorted().toList()){
            if(checkedDigits.add(digit)){
                var found = getSymbol(digit);
                System.out.println("found "+found+" at position "+digit);
                Set<Position> positionsToCompose = new HashSet<>();
                positionsToCompose.add(digit);
                var inlinePrev = digit.getInLinePrev();
                while (getSymbol(inlinePrev) != null && isDigit(getSymbol(inlinePrev))){
                    positionsToCompose.add(inlinePrev);
                    inlinePrev=inlinePrev.getInLinePrev();
                }
                var inLineNext = digit.getInLineNext();
                while (getSymbol(inLineNext) != null && isDigit(getSymbol(inLineNext))){
                    positionsToCompose.add(inLineNext);
                    inLineNext=inLineNext.getInLineNext();
                }
                var firstPos = positionsToCompose.stream().sorted().toList().get(0);
                System.out.println("firstPos: "+firstPos);
                if(alreadyComposedPositions.add(firstPos)){
                    var string = positionsToCompose.stream().sorted().map(this::getSymbol).reduce("", (s, str) -> s != null ? s.concat(str) : null);
                    System.out.println("Number: "+string);
                    numbersToSum.add(Integer.parseInt(string));
                }
            }
        }
        var sum = numbersToSum.stream().reduce(0,Integer::sum);
        System.out.println("Somma: "+sum);

    }

    @Override
    public void partTwo() {

    }

    private boolean isDigit(String str){
        return str.replaceAll("\\d","").equalsIgnoreCase("");
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

    private String getSymbol(Position position){
        try{
            return schematic[position.row][position.col];
        }catch (IndexOutOfBoundsException e){
            return null;
        }
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
                        var npos = new Position(i + dx, j + dy);
                        v.add(npos);

                    }
                }
            }
        }

        // Returning the vector array
        return v;
    }

    private static class Position implements Comparable<Position> {
        int row;
        int col;

        public Position(int row, int col){
            this.col=col;
            this.row=row;
        }

        private Position getInLinePrev(){
            return new Position(this.row, this.col-1);
        }
        private Position getInLineNext(){
            return new Position(this.row, this.col+1);
        }
        @Override
        public String toString(){
            return "(x="+row+",y="+col+")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Position)) {
                return false;
            }
            Position other = (Position) obj;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int compareTo(@NonNull Position other) {
            if (this.row == other.row) {
                return Integer.compare(this.col, other.col);
            }
            return Integer.compare(this.row, other.row);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + row;
            result = 31 * result + col;
            return result;
        }
    }
}
