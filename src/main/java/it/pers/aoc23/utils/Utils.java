package it.pers.aoc23.utils;



import it.pers.aoc23.Aoc23Application;
import it.pers.aoc23.model.cardgame.ScratchCard;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {

    public interface DayLoader<T>{
        default T load(BufferedReader bufferedReader) throws IOException {
            return null;
        };
        default void load(BufferedReader bufferedReader, T param) throws IOException {
            return;
        };
    }

    public static <T> T loadFile(String filepath,DayLoader<T> dayLoader){
        T result = null;
        try {
            InputStream is = Aoc23Application.class.getClassLoader().getResourceAsStream(filepath);
            assert is != null;
            var bufferedReader = new BufferedReader(new InputStreamReader(is));
            result = dayLoader.load(bufferedReader);
        } catch (FileNotFoundException e) {
            System.out.println("File "+filepath+" not found: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }

        return result;
    }
    public static <T> void loadFile(String filepath,DayLoader<T> dayLoader,T param){
        try {
            InputStream is = Aoc23Application.class.getClassLoader().getResourceAsStream(filepath);
            assert is != null;
            var bufferedReader = new BufferedReader(new InputStreamReader(is));
            dayLoader.load(bufferedReader,param);
        } catch (FileNotFoundException e) {
            System.out.println("File "+filepath+" not found: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }

    public static BufferedReader getFile(String filepath){
        InputStream is = Aoc23Application.class.getClassLoader().getResourceAsStream(filepath);
        assert is != null;
        return new BufferedReader(new InputStreamReader(is));
    }

    public static List<String> getFileLines(String filename){
        try {
            var is = Aoc23Application.class.getClassLoader().getResource(filename);
            assert is != null;
            return Files.readAllLines(Paths.get(is.toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    public static long gcd(long a, long b) {
        if (a == 0 && b == 0) {
            return 0;
        } else if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return Math.abs(a * b) / gcd(a, b);
    }
}
