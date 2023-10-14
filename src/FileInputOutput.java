import java.io.*;
import java.util.Hashtable;
//
public class FileInputOutput {

public Hashtable<String, Room> fileIn1() throws IOException, ClassNotFoundException {
    String appDir = System.getProperty("user.dir");
    File f0 = new File(appDir, "test1.ser");
    Hashtable<String, Room> roomMap;

    if(!f0.exists()){
        roomMap = new Hashtable<>();
    }

    else{
        FileInputStream fis = new FileInputStream("test1.ser");
        ObjectInputStream in = new ObjectInputStream(fis);

        roomMap = (Hashtable<String, Room>) in.readObject();
        in.close();
        fis.close();
    }
    return roomMap;
}

//    public int[] fileIn2() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("test.ser2");
//        ObjectInputStream in = new ObjectInputStream(fis);
//        int[] stockArray = (int[]) in.readObject();
//        in.close();
//        fis.close();
//        return stockArray;
//    }
    public int[] fileIn2() throws IOException, ClassNotFoundException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test2.ser");
        int[] stockArray;

        if(!f0.exists()){
            stockArray = new int[4];
        }

        else{
            FileInputStream fis = new FileInputStream("test2.ser");
            ObjectInputStream in = new ObjectInputStream(fis);

            stockArray = (int[]) in.readObject();
            in.close();
            fis.close();
        }
        return stockArray;
    }

//    public Hashtable<String, Reserves> fileIn3() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("test.ser3");
//        ObjectInputStream in = new ObjectInputStream(fis);
//        Hashtable<String, Reserves> reGuest = (Hashtable<String, Reserves>) in.readObject();
//        in.close();
//        fis.close();
//        return reGuest;
//    }

    public Hashtable<String, Reserves> fileIn3() throws IOException, ClassNotFoundException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test3.ser");
        Hashtable<String, Reserves> reGuest;

        if(!f0.exists()){
            reGuest = new Hashtable<>();
        }

        else{
            FileInputStream fis = new FileInputStream("test3.ser");
            ObjectInputStream in = new ObjectInputStream(fis);

            reGuest = (Hashtable<String, Reserves>) in.readObject();
            in.close();
            fis.close();
        }
        return reGuest;
    }

    public Hashtable<String, int[]> fileIn4() throws IOException, ClassNotFoundException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test4.ser");
        Hashtable<String, int[]> cusArray;

        if(!f0.exists()){
            cusArray = new Hashtable<>();
        }

        else{
            FileInputStream fis = new FileInputStream("test4.ser");
            ObjectInputStream in = new ObjectInputStream(fis);

            cusArray = (Hashtable<String, int[]>) in.readObject();
            in.close();
            fis.close();
        }
        return cusArray;
    }

    public void fileOut1(Hashtable<String, Room> roomMap) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test1.ser");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(roomMap);
        oos.close();
        fos.close();
    }

    public void fileOut2(int[] stockArray) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test2.ser");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(stockArray);
        oos.close();
        fos.close();
    }

    public void fileOut3(Hashtable<String, Reserves> reGuest) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test3.ser");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(reGuest);
        oos.close();
        fos.close();
    }

    public void fileOut4(Hashtable<String, int[]> cusArray) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test4.ser");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cusArray);
        oos.close();
        fos.close();
    }
}