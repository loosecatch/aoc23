package it.pers.aoc23.utils;



import it.pers.aoc23.Aoc23Application;

import java.io.*;

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
}
