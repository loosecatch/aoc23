package it.pers.aoc23.model.garden;

public class Range {
    private final Long start;
    private final Long end;

    private final static String ERROR1 = "illegal start and end of range";

    Range(Long start, Long end){
        if (start>end) throw new IllegalArgumentException(ERROR1);
        this.start = start;
        this.end = end;
    }

    boolean isIncluded(Long value){
        return value >= start && value<= end;
    }

    boolean isBefore(Long value){
        return value<start;
    }
    boolean isAfter(Long value){
        return value>end;
    }

    @Override
    public String toString(){
        return "["+this.start+" -> "+this.end+"]";
    }

    public Long getStart() {
        return start;
    }
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = Math.toIntExact(31 * result + start);
//        result = Math.toIntExact(31L * result + end);
//        return result;
//    }
}
