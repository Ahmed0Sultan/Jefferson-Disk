import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ahmed on 10/11/2016.
 */
public class Sector {

    public static Sector[] secList;
    public static ArrayList<ArrayList> sectors = new ArrayList<>();
    char alphabet [] = {' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    ArrayList ind_numbers = new ArrayList();
    ArrayList sector_alpha = new ArrayList();
    public Sector(int method) throws IOException {

        if(method ==1){
            Create_Write();
        }else if(method==2){

        }
    }
    public void Create_Write()throws IOException{
        while(ind_numbers.size() != 27){
            sector_alpha.add(alphabet[randInt()]);
        }
        for(int i =0;i<27;i++){
            System.out.println(sector_alpha.get(i)+" ==> "+sector_alpha.indexOf(sector_alpha.get(i)));
        }
        sectors.add(sector_alpha);
        FileWriter writer = new FileWriter("sector_key.csv");
        for(ArrayList arr: sectors) {
            String appender = "";
            for(Object s : arr){
                writer.write(appender + s);
                appender = ",";
            }
            writer.write("\n");
            writer.flush();
        }
        writer.close();
        System.out.println("*********************");
    }
    public Sector[] restoreSectors(File file,int[] key) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        secList = new Sector[key.length];
        for(int i=0;i<key.length;i++){
            secList[i] = new Sector(2);
        }try{
//            while ((nextLine = reader.readNext()) != null) {
////                System.out.println("test");
//                // nextLine[] is an array of values from the line
//                for(int i=0;i<nextLine.length;i++){
//                    sector_alpha.add(nextLine[i]);
//                }
////                System.out.println(sector_alpha);
////                secList[0].sector_alpha = sector_alpha;
//                if(number<limit) {
//                    secList[number].sector_alpha = sector_alpha;
//                    System.out.println(secList[number].sector_alpha);
//                    number++;
//                    sector_alpha.clear();
//                }
//
//            }
            List l = reader.readAll();
            String[][] matrix=new String[l.size()][];
            matrix= (String[][]) l.toArray(matrix);
//            System.out.println(secList[0].sector_alpha);
//            System.out.println(matrix[0][0]);
            for(int x=0;x<key.length;x++){
                for(int z=0;z<27;z++){
                    secList[x].sector_alpha.add(matrix[x][z].charAt(0));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(secList[0].sector_alpha);
        return secList;
    }

    public char encrypt_letter(char letter, int row_shift){
//        System.out.println("Plain Letter is "+letter);
        int plain_index = sector_alpha.indexOf(letter);
//        System.out.println("Plain Index is "+plain_index);
        int new_index = plain_index+row_shift;
        if((plain_index+row_shift)>27){
            new_index = new_index-27;
        }
//        System.out.println(sector_alpha.get(new_index));
        char cipher_letter = (char) sector_alpha.get(new_index);
        return cipher_letter;
    }
    public char decrypt_letter(char letter, int row_shift){
//        System.out.println("Cipher Letter is "+letter);
        int cipher_index = sector_alpha.indexOf(letter);
//        System.out.println("Cipher Index is "+cipher_index);
        int new_index = cipher_index-row_shift;
//        System.out.println("Error is here ==> "+new_index);
        if((cipher_index+row_shift)>27){
            new_index = new_index-27;
        }else if((cipher_index-row_shift)<0){
            new_index = new_index+27;
        }
//        System.out.println("Error is here ==> "+ sector_alpha);
//        System.out.println(sector_alpha.get(new_index));
        char plain_letter = (char) sector_alpha.get(new_index);
        return plain_letter;
    }
    public int randInt() {

        Random rand = new Random();

        int randomNum = rand.nextInt((26 - 0) + 1) + 0;
        while(ind_numbers.contains(randomNum)){
            randomNum = rand.nextInt((26 - 0) + 1) + 0;
        }

        ind_numbers.add(randomNum);

        return randomNum;
    }
//    public static void main (String[] args){
//        Sector s1 = new Sector();
//        Sector s2 = new Sector();
//        Sector s3 = new Sector();
//    }
}
