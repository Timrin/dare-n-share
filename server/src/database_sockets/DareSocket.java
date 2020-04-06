package database_sockets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DareSocket {

    private static String [] files = {"files/dare.txt", "files/dare2.txt"};



    /**
     * This methods reads the dares from dare.txt and returns
     *
     * */

    public static String readDare(int id){

        String path = files[id];

        StringBuilder stringBuilder = new StringBuilder();

        try(FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr)){

            String str = br.readLine();


            while (str!=null){

                stringBuilder.append(str); // Reads dare.txt

                str= br.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
