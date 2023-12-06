package it.pers.aoc23.model;

import it.pers.aoc23.Aoc23Application;
import it.pers.aoc23.model.days.Day;
import it.pers.aoc23.utils.Utils;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import static it.pers.aoc23.utils.Utils.getFile;

public class DiceGame implements Day {
    private final String filename;
    private final Map<String,Integer> diceNumbers;


    public DiceGame(String filename, Map<String, Integer> diceNumbers){
        this.filename= filename;
        this.diceNumbers = diceNumbers;
    }



    @Override
    public void partOne() {
        var bufferedReader = getFile(this.filename);
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int sum = 0;
        while (line != null){
            System.out.println(line);
            var subLines = line.split(":");
            var gameId = subLines[0].split(" ")[1];
            System.out.println("id: "+gameId);
            var turns = subLines[1].split(";");
            var ref = new Object() {
                int maxBlue = 0;
                int maxGreen = 0;
                int maxRed =0;
            };
            for (String turn : turns) {
                //System.out.println(turn);
                var picks = turn.replaceAll(" ","").split(",");
                Arrays.stream(picks).forEach((pick) -> {
                    //System.out.println(pick);
                    if(pick.contains("blue")){
                        int val = Integer.parseInt(pick.replaceAll("blue",""));
                        //System.out.println(val+">"+ ref.maxBlue+"? "+(val> ref.maxBlue));
                        if  (val> ref.maxBlue) ref.maxBlue =val;
                    }
                    if(pick.contains("green")){
                        int val = Integer.parseInt(pick.replaceAll("green",""));
                        if  (val> ref.maxGreen) ref.maxGreen =val;
                    }
                    if(pick.contains("red")){
                        int val = Integer.parseInt(pick.replaceAll("red",""));
                        if  (val> ref.maxRed) ref.maxRed =val;
                    }
                });
            }
            sum += diceNumbers.get("blue")>=ref.maxBlue && diceNumbers.get("green")>=ref.maxGreen && diceNumbers.get("red")>=ref.maxRed  ? Integer.parseInt(gameId) : 0;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getName(filename)+" part one: "+sum);
    }

    @Override
    public void partTwo() {
        var bufferedReader = getFile(this.filename);
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int sum = 0;
        while (line != null){
            System.out.println(line);
            var subLines = line.split(":");
            var gameId = subLines[0].split(" ")[1];
            System.out.println("id: "+gameId);
            var turns = subLines[1].split(";");
            var ref = new Object() {
                int maxBlue = 0;
                int maxGreen = 0;
                int maxRed =0;
            };
            for (String turn : turns) {
                //System.out.println(turn);
                var picks = turn.replaceAll(" ","").split(",");
                Arrays.stream(picks).forEach((pick) -> {
                    //System.out.println(pick);
                    if(pick.contains("blue")){
                        int val = Integer.parseInt(pick.replaceAll("blue",""));
                        //System.out.println(val+">"+ ref.maxBlue+"? "+(val> ref.maxBlue));
                        if  (val> ref.maxBlue) ref.maxBlue =val;
                    }
                    if(pick.contains("green")){
                        int val = Integer.parseInt(pick.replaceAll("green",""));
                        if  (val> ref.maxGreen) ref.maxGreen =val;
                    }
                    if(pick.contains("red")){
                        int val = Integer.parseInt(pick.replaceAll("red",""));
                        if  (val> ref.maxRed) ref.maxRed =val;
                    }
                });
            }
            sum += ref.maxBlue * ref.maxGreen * ref.maxRed;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getName(filename)+" part two: "+sum);
    }


}
