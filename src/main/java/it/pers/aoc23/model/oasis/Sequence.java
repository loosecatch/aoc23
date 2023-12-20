package it.pers.aoc23.model.oasis;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
public class Sequence {
    final List<Integer> values;
    final int length;

    public Sequence(String line){
        this.values = new ArrayList<>();
        values.addAll(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
        length=values.size();
    }
    public Sequence(List<Integer> ints){
        this.values=new ArrayList<>(ints);
        length=values.size();
    }

    public List<Integer> getDifferences(){
        var list = new ArrayList<Integer>();
        for (int i=0;i<length-1;i++){
            list.add(Math.abs(values.get(i+1)-values.get(i)));
        }
        return list;
    }
    public Integer getPrediction(){
        Sequence initialSeq = new Sequence(this.values);
        ArrayList<Integer> resultList = new ArrayList<>();
        return sumSequence(initialSeq, resultList,true);
    }

    public Integer getHistory(){
        Sequence initialSeq = new Sequence(this.values);
        ArrayList<Integer> resultList = new ArrayList<>();
        return sumSequence(initialSeq, resultList,false);
    }


    public boolean isAllZeros(){
        return this.values.stream().allMatch(integer -> integer.equals(0));
    }
    public int sumSequence(Sequence sequence, ArrayList<Integer> integers, boolean isPartOne) {
        System.out.println(sequence+" "+integers); // Printing the sequence (optional)

        if (sequence.isAllZeros()) {
            if (isPartOne) return integers.stream().reduce(Integer::sum).orElse(0); // Return the sum;
            else{
                System.out.println(integers);
                int curr = 0;
                for(int i=integers.size()-1;i>0;i--){
                    System.out.print("\n#"+i+" integers.get(i)-curr = "+integers.get(i)+" - "+curr);
                    curr=integers.get(i)-curr;
                    System.out.print(" = "+curr+"\n");
                }
                System.out.println();
                return integers.get(0)-curr;
            }

        } else {
            var nextIndex = isPartOne ? sequence.length - 1 : 0;
            Sequence newSeq = new Sequence(sequence.getDifferences());
                integers.add(sequence.values.get(nextIndex));
                return sumSequence(newSeq, integers, isPartOne); // Recursive call with the updated sequence and integers list}

        }
    }

}
