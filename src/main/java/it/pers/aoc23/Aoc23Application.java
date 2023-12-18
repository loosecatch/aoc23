package it.pers.aoc23;

import it.pers.aoc23.model.DiceGame;
import it.pers.aoc23.model.Engine;
import it.pers.aoc23.model.Trebuchet;
import it.pers.aoc23.model.boatrace.BoatRace;
import it.pers.aoc23.model.boatrace.RaceGame;
import it.pers.aoc23.model.camelcards.CamelCardsGame;
import it.pers.aoc23.model.camelcards.Hand;
import it.pers.aoc23.model.cardgame.CardGame;
import it.pers.aoc23.model.cardgame.ScratchCard;
import it.pers.aoc23.model.desertmap.DesertMap;
import it.pers.aoc23.model.desertmap.Node;
import it.pers.aoc23.model.garden.Garden;
import it.pers.aoc23.model.garden.GardenMap;
import it.pers.aoc23.model.oasis.Oasis;
import it.pers.aoc23.model.oasis.Sequence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class Aoc23Application {

    public static void main(String[] args) {
        SpringApplication.run(Aoc23Application.class, args);
        var test = new Oasis("9/input.txt");
        test.partTwo();
    }



    public static void dayThree(){
        var test = new Engine("3/input.txt");
        test.partOne();
        test.partTwo();
    }

    public static void dayTwo(){
        var map = new HashMap<String,Integer>();
        map.put("blue",14);
        map.put("green",13);
        map.put("red",12);
        var example = new DiceGame("2/input.txt",map);
        example.partOne();
        example.partTwo();
    }

    public static void dayOne(){
        var example1 = new Trebuchet("1/example1.txt");
        var example2 = new Trebuchet("1/example2.txt");
        var input = new Trebuchet("1/input.txt");
        example1.partOne();
        input.partOne();
        example2.partTwo();
        input.partTwo();
    }

}
