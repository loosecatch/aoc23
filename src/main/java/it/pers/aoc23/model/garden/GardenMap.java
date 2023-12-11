package it.pers.aoc23.model.garden;

import java.awt.font.NumericShaper;
import java.math.BigInteger;
import java.util.*;

public class GardenMap {
    public final String name;
    public final Map<Range,Range> map;
    public final List<String> lines;


    public GardenMap(String name) {
        this.name = name;
        lines = new ArrayList<>();
        map = new HashMap<>();
    }


    public void initialize(List<String> lines){
        lines.forEach(this::lineConsumer);
        System.out.println(this.name+" -> "+map);
    }

    public Long getValue(Long key){
        for (Map.Entry<Range, Range> entry: map.entrySet()) {
            if (entry.getKey().isIncluded(key)){
                long offset = key-entry.getKey().getStart();
                return entry.getValue().getStart()+offset;
            }
        }
        return key;
    }
    public void addLine(String line){
        lines.add(line);
    }

    public void initialize(){
        System.out.println(name);
        lines.forEach(this::lineConsumer);
        System.out.println(this.name+" -> "+map);
    }

     static void test(){
        var test = new GardenMap("prova");
        var lines = new ArrayList<String>();
        lines.add("50 98 2");
        lines.add("52 50 48");
        test.initialize(lines);
        System.out.println(test.getValue(63L));
        System.out.println(test.getValue(0L));
         System.out.println(test.getValue(50L));
         System.out.println(test.getValue(98L));

    }
    private void lineConsumer(String line){
        try {
            var values = line.split(" ");
            long destStart = Long.parseLong(values[0]);
            long srcStart = Long.parseLong(values[1]);
            long length = Long.parseLong(values[2]);
            var sourceRange = new Range(srcStart,srcStart+length-1);
            var destRange = new Range(destStart,destStart+length-1);
            map.put(sourceRange,destRange);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
