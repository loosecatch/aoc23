package it.pers.aoc23.model.garden;

import java.util.Objects;

public class Range implements Comparable<Range>{
    private final Long start;
    private final Long end;

    private final static String ERROR1 = "illegal start and end of range";

    public Range(Long start, Long end){
        if (start>end) throw new IllegalArgumentException(ERROR1+" ["+start+", "+end+"]");
        this.start = start;
        this.end = end;
    }

    public boolean isIncluded(Long value){
        return value >= start && value<= end;
    }

    public boolean isBefore(Long value){
        return value<start;
    }
    public boolean isAfter(Long value){
        return value>end;
    }

    @Override
    public String toString(){
        return "["+this.start+" -> "+this.end+"]";
    }

    public Long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Range range = (Range) obj;
        return Objects.equals(start, range.start) &&
                Objects.equals(end, range.end);
    }

    @Override
    public int compareTo(Range o) {
        return Long.compare(this.start,o.start);
    }

    public long getOffset() {
        return end-start;
    }
    public static Range min(Range a, Range b){
        if (a.compareTo(b) <= 0) return a;
        else return b;
    }
}
