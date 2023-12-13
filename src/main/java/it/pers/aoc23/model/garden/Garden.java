package it.pers.aoc23.model.garden;

import it.pers.aoc23.model.days.Day;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class Garden implements Day {
    private final static Pattern pattern = Pattern.compile("[a-zA-Z\\-]+");

    private final Map<String,GardenMap> map;
    private final List<Range> seeds;

    public Garden(String filename){
        var lines = getFileLines(filename);
        this.map=new HashMap<>();
        this.seeds = new ArrayList<>();
        var seedLine = lines.get(0);
        var seedString = new ArrayList<>(Arrays.stream(seedLine.replaceAll("[a-zA-Z]|:","").split(" ")).filter(s -> !Objects.equals(s, "")).toList());
        while (seedString.size()>=2){
            var startString = seedString.remove(0);
            var lengthString = seedString.remove(0);
            this.seeds.add(new Range(Long.valueOf(startString),Long.parseLong(startString)+Long.parseLong(lengthString)));
        }
        System.out.println("Seeds: "+seeds.stream().sorted().toList());
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
            }
        });
        map.forEach((key, value) -> value.initialize());
        System.out.println(map.get("seed-to-soil"));
        System.out.println(map.get("soil-to-fertilizer"));
        System.out.println(map.get("fertilizer-to-water"));
        System.out.println(map.get("water-to-light"));
        System.out.println(map.get("light-to-temperature"));
        System.out.println(map.get("temperature-to-humidity"));
        System.out.println(map.get("humidity-to-location"));

    }
    @Override
    public void partOne() {
        //GardenMap.test();
        var locations = new ArrayList<Long>();
        this.seeds.forEach(seed -> {
            extractLocation(locations, seed.getStart());
            extractLocation(locations, seed.getEnd()-seed.getStart());
        });
        var min = locations.stream().reduce(Long::min).get();
        System.out.println("Closest location: "+min);
    }

    private void extractLocation(List<Long> locations,Long seed){
        var soil = this.map.get("seed-to-soil").getValue(seed);
        var fer = this.map.get("soil-to-fertilizer").getValue(soil);
        var water = this.map.get("fertilizer-to-water").getValue(fer);
        var light = this.map.get("water-to-light").getValue(water);
        var temp = this.map.get("light-to-temperature").getValue(light);
        var hum = this.map.get("temperature-to-humidity").getValue(temp);
        var loc = this.map.get("humidity-to-location").getValue(hum);
        locations.add(loc);
        System.out.printf("Seed %s, soil %s, fertilizer %s, water %s, light %s, temperature %s, humidity %s, location %s.\n",seed,soil,fer,water,light,temp,hum,loc);
    }

    @Override
    public void partTwo() {
        partTwoBrute();
    }

    private Range getRange(String mapName, Range src) {
        var range = this.map.get(mapName).getRange(src);
        System.out.println(mapName+" results : "+range);
        return range;
    }

    private void partTwoBrute(){
        var locations = new ArrayList<Long>();
        this.seeds.forEach(seed -> {
            for (long i= seed.getStart();i<+seed.getEnd();i++)
                extractLocation(locations, i);
        });
        var min = locations.stream().reduce(Long::min).get();
        System.out.println("Closest location: "+min);
    }
}
