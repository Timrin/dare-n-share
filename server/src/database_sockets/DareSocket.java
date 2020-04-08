package database_sockets;

/**
 * @author Timothy, Julia and Kamilla - XP pair programming
 *
 * */

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class DareSocket {

    private static String[] files = {"server/resources/dare.txt", "files/dare2.txt"};


    /**
     * This methods reads the dares from dare.txt and returns
     */

    public static String readDare(int id) {

        String path = files[id];

        StringBuilder stringBuilder = new StringBuilder();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        try (InputStream is = classLoader.getResourceAsStream(path)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {
                String str = br.readLine();
                while (str != null) {
                    System.out.println(str);
                    stringBuilder.append(str); // Reads dare.txt
                    str = br.readLine();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

        public static void main (String[]args) throws IOException {
         readDare(0);
        }

    }
