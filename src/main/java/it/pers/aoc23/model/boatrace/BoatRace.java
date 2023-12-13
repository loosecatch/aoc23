package it.pers.aoc23.model.boatrace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class BoatRace {
    long duration;
    long record;

    public int countWaysToWin(){
        double discriminant = duration * duration - 4 * record;

        if (discriminant > 0) {
            double root1 = Math.abs((duration - Math.sqrt(discriminant)) / (2));
            double root2 = Math.abs((duration + Math.sqrt(discriminant)) / (2));
            double dec1 = root1 % 1;
            double dec2 = root2 % 1;
            int adj = 1;
            int a = dec1 != 0 ? (int) Math.ceil(root1) : (int) root1+1;
            int b = dec2 != 0 ? (int) Math.floor(root2) : (int) root2-1;
            int res = (adj + b - a);
            System.out.println("The solutions are x < " + a + " or x > " + b);
            System.out.println("There are " + res + " ways");
            return res;
        } else if (discriminant == 0) {
            double root = (double) -duration / (2);
            System.out.println("The solution is x = " + root);
            return 1;
        } else {
            System.out.println("The inequality has no real solutions.");
            return 0;
        }
    }
}
