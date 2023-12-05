package it.pers.aoc23.model.days;

public interface Day {
    void partOne();
    void partTwo();

    default String getName(String filename){
        var string = this.getClass().getName().split("\\.");
        return string[string.length-1]+" "+filename.replaceAll("[0-9/.]+|txt","")+" ";
    }
}
