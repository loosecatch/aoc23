package it.pers.aoc23;

import it.pers.aoc23.model.Trebuchet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aoc23Application {

    public static void main(String[] args) {
        SpringApplication.run(Aoc23Application.class, args);

    }

    public static void dayTeo(){

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
