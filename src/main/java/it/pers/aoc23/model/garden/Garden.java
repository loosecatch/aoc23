package it.pers.aoc23.model.garden;

import it.pers.aoc23.model.days.Day;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class Garden implements Day {
    private final static Pattern pattern = Pattern.compile("[a-zA-Z\\-]+");

    private final Map<String,GardenMap> map;
    private final List<String> seeds;

    public Garden(String filename){
        var lines = getFileLines(filename);
        this.map=new HashMap<>();
        var seedLine = lines.get(0);
        this.seeds = Arrays.stream(lines.get(0).replaceAll("[a-zA-Z]|:","").split(" ")).filter(s -> !Objects.equals(s, "")).toList();
        System.out.println("Seeds: "+seeds);
        lines.remove(seedLine);
        AtomicReference<String> lastMap = new AtomicReference<>("");
        lines.stream().filter(s -> !Objects.equals(s,"")).forEach(line -> {
            //var processedLine = line.replaceAll(" map","").replaceAll()
            var matcher = pattern.matcher(line);
            if (matcher.find()){
                var name = matcher.group();
                map.put(name, new GardenMap(name));
                lastMap.set(name);
            }else{
                var currentMap = map.get(lastMap.get());
                currentMap.addLine(line);
                System.out.printf("Add %s to map %s\n",line,currentMap.name);
            }
        });
        map.forEach((key, value) -> value.initialize());

    }
    @Override
    public void partOne() {
        //GardenMap.test();
        var locations = new ArrayList<Long>();
        this.seeds.forEach(seed -> {
            var soil = this.map.get("seed-to-soil").getValue(Long.valueOf(seed));
            var fer = this.map.get("soil-to-fertilizer").getValue(soil);
            var water = this.map.get("fertilizer-to-water").getValue(fer);
            var light = this.map.get("water-to-light").getValue(water);
            var temp = this.map.get("light-to-temperature").getValue(light);
            var hum = this.map.get("temperature-to-humidity").getValue(temp);
            var loc = this.map.get("humidity-to-location").getValue(hum);
            locations.add(loc);
            System.out.printf("Seed %s, soil %s, fertilizer %s, water %s, light %s, temperature %s, humidity %s, location %s.\n",seed,soil,fer,water,light,temp,hum,loc);
        });
        var min = locations.stream().reduce(Long::min).get();
        System.out.println("Closest location: "+min);
    }

    @Override
    public void partTwo() {

    }
}
