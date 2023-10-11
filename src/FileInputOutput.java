import java.io.*;
import java.util.Hashtable;
//
public class FileInputOutput {
    Hashtable<String, Room> roomMap;

    public Hashtable<String, Room> fileIn1() throws IOException, ClassNotFoundException {
//        String appDir = System.getProperty("user.dir");
//        File f0 = new File(appDir, "test.ser1");
//
//        if(!f0.exists()){
//
//        }     파일이 없으면 만드는 과정 진행중..

        FileInputStream fis = new FileInputStream("test.ser1");
        ObjectInputStream in = new ObjectInputStream(fis);


        Hashtable<String, Room> roomMap = (Hashtable<String, Room>) in.readObject();
        in.close();
        fis.close();
        return roomMap;
    }

    public int[] fileIn2() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("test.ser2");
        ObjectInputStream in = new ObjectInputStream(fis);
        int[] stockArray = (int[]) in.readObject();
        in.close();
        fis.close();
        return stockArray;
    }

    public Hashtable<String, Reserves> fileIn3() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("test.ser3");
        ObjectInputStream in = new ObjectInputStream(fis);
        Hashtable<String, Reserves> reGuest = (Hashtable<String, Reserves>) in.readObject();
        in.close();
        fis.close();
        return reGuest;
    }

    public void fileOut1(Hashtable<String, Room> roomMap) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test.ser1");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(roomMap);
        oos.close();
        fos.close();
    }

    public void fileOut2(int[] stockArray) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test.ser2");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(stockArray);
        oos.close();
        fos.close();
    }

    public void fileOut3(Hashtable<String, Reserves> reGuest) throws IOException {
        String appDir = System.getProperty("user.dir");
        File f0 = new File(appDir, "test.ser3");
        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(reGuest);
        oos.close();
        fos.close();
    }
}
