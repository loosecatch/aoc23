package it.pers.aoc23.model.oasis;


import it.pers.aoc23.model.days.Day;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static it.pers.aoc23.utils.Utils.getFileLines;

public class Oasis implements Day {
    private final List<Sequence> seqs;

    public Oasis(String filename){
        this.seqs=getFileLines(filename).stream().map(Sequence::new).toList();
    }
    @Override
    public void partOne() {
        var res = this.seqs.stream().map(Sequence::getPrediction).reduce(Integer::sum).orElse(0);
        System.out.println("Result: "+res);
    }

    @Override
    public void partTwo() {
        var res = this.seqs.stream().map(Sequence::getHistory).reduce(Integer::sum).orElse(0);
        System.out.println("Result: "+res);
    }



}
