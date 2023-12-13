package it.pers.aoc23.model.boatrace;

import it.pers.aoc23.model.days.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class RaceGame implements Day {

    private final List<BoatRace> races;
    public RaceGame(String filename){
        this.races=new ArrayList<>();
        var times = getFileLines(filename).get(0);
        var dists = getFileLines(filename).get(1);
        var tList = Arrays.stream(times.replaceAll("[a-zA-Z]|:","").split(" ")).filter(s -> !Objects.equals(s, "")).toList();
        var dList = Arrays.stream(dists.replaceAll("[a-zA-Z]|:","").split(" ")).filter(s -> !Objects.equals(s, "")).toList();
        if (tList.size() == dList.size()){

            for (int i=0;i<tList.size();i++) {
                var b = new BoatRace(Long.parseLong(tList.get(i)),Long.parseLong(dList.get(i)));
                System.out.println(b);
                this.races.add(b);
            }
        }
    }
    @Override
    public void partOne() {
        var res = this.races.stream().map(BoatRace::countWaysToWin).reduce(1, (a,b)->a*b);
        System.out.println(res);
    }

    @Override
    public void partTwo() {
        var duration = Long.parseLong(this.races.stream().map(BoatRace::getDuration).map(Object::toString).reduce(String::concat).get());
        var record = Long.parseLong(this.races.stream().map(BoatRace::getRecord).map(Object::toString).reduce(String::concat).get());
        var onlyOne = new BoatRace(duration,record);
        System.out.println(onlyOne+" has "+onlyOne.countWaysToWin()+" ways");
    }
}
