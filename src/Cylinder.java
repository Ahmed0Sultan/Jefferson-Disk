import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ahmed on 10/11/2016.
 */
public class Cylinder {
    static Sector[] sectorArray;
    ArrayList<ArrayList> secList = new ArrayList<>();

    public Cylinder(int method,int[] key) throws IOException {
        if(method ==1){
            initialize(4);
        }else if(method==2){
            FileChooserDialog fc = new FileChooserDialog();
            File file = fc.chooseFile();
            restore(file,key);
        }
    }
    public void initialize(int sectorNum) throws IOException {
        sectorArray = new Sector[sectorNum];
        for(int i=0;i<sectorNum;i++){
            sectorArray[i] = new Sector(1);
        }
    }
    public void restore(File file,int[] key) throws IOException {

        Sector s = new Sector(2);
        sectorArray = new Sector[key.length];
        for(int i=0;i<key.length;i++){
            sectorArray[i] = new Sector(2);
        }
        sectorArray = s.restoreSectors(file,key);
    }

    public void encrypt(int[] key,int row_shift,String plain_text){
        if(plain_text.length() > key.length){
            System.out.println("Please make sure that the plain text letters number is NOT bigger than sectors number.");
            return;
        }
        char[] cipehr_letters = new char[plain_text.length()];
        char[] plain_letters = new char[plain_text.length()];
        System.out.print("Cipher Text ==> ");
        for(int i =0;i < plain_text.length() ;i++){
            plain_letters[i] = plain_text.charAt(i);
//            System.out.println((key[i]-1));
            int secNum = (key[i]-1);
//            System.out.println("Test is "+sectorArray[secNum].sector_alpha);
            cipehr_letters[i] = sectorArray[secNum].encrypt_letter(plain_letters[i],row_shift);
            System.out.print(cipehr_letters[i]);
        }
    }
    public void decrypt(int[] key,int row_shift,String cipher_text){
        if(cipher_text.length() > key.length){
            System.out.println("Please make sure that the plain text letters number is NOT bigger than sectors number.");
            return;
        }
        char[] cipehr_letters = new char[cipher_text.length()];
        char[] plain_letters = new char[cipher_text.length()];
        System.out.print("Plain Text ==> ");
        for(int i =0;i < cipher_text.length() ;i++){
            cipehr_letters[i] = cipher_text.charAt(i);
//            System.out.println((key[i]-1));
            int secNum = (key[i]-1);
//            System.out.println("Test is "+sectorArray[secNum].sector_alpha);
            plain_letters[i] = sectorArray[secNum].decrypt_letter(cipehr_letters[i],row_shift);
            System.out.print(plain_letters[i]);
        }
    }

//    public static void main (String[] args) throws IOException {
//        Cylinder demo = new Cylinder(4);
//        int key[] = {3,4,1,2};
//        demo.encrypt(key,27,"fire");
//    }
}
class FileChooserDialog {

    public File chooseFile(){
        JFileChooser fileopen = new JFileChooser();
        File file = null;
        FileFilter filter = new FileNameExtensionFilter("CSV", "csv");
        fileopen.addChoosableFileFilter(filter);

        int ret = fileopen.showDialog(null, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileopen.getSelectedFile();
            System.out.println(file);
        }
        return file;
    }
}
