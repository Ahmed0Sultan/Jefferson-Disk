import java.io.IOException;

/**
 * Created by Ahmed on 10/12/2016.
 */
public class MainClass {
    public MainClass(){

    }
    public static void main(String[] args) throws IOException {
        int key[] = {4,1,3,2};
        //1 to encrypt
        //2 to decrypt
        Cylinder c = new Cylinder(2,key);
//        c.encrypt(key,3,"fire");
        c.decrypt(key,3,"tbgl");
    }
}
//ihch

