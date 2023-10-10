import java.io.*;
import java.util.Hashtable;
//
public class FileInputOutput {
    Hashtable<String, Room> roomMap;

    public Hashtable<String, Room> fileIn1() throws IOException, ClassNotFoundException {
        //Hashtable<String, Room> roomMap = new Hashtable<>();            // 해쉬테이블은 중복허용 안하기 때문에 예약명단 어차피 하나만 들어감!!!!

        FileInputStream fis = new FileInputStream("test.ser1");
        ObjectInputStream in = new ObjectInputStream(fis);



        Hashtable<String, Room> roomMap = (Hashtable<String, Room>) in.readObject();
        //System.out.println(roomMap);
        in.close();
        fis.close();

        return roomMap;
    }

    public int[] fileIn2() throws IOException, ClassNotFoundException {
        //Hashtable<String, Room> roomMap = new Hashtable<>();            // 해쉬테이블은 중복허용 안하기 때문에 예약명단 어차피 하나만 들어감!!!!

        FileInputStream fis = new FileInputStream("test.ser2");
        ObjectInputStream in = new ObjectInputStream(fis);



        int[] stockArray = (int[]) in.readObject();
        //System.out.println(roomMap);
        in.close();
        fis.close();

        return stockArray;
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
}
