import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Room> roomMap = new HashMap<>();

        FileInputStream fis = new FileInputStream("test.ser");
        ObjectInputStream in = new ObjectInputStream(fis);

        roomMap = (HashMap) in.readObject();
//        System.out.println(roomMap);


        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 입력하세요 : ");
        String bedType = br.readLine();

        // 예약 변호 만들어서 map에 roomNum 대신 넣어줄 것(마지막에 결제되면 실행되게 해야 함)

        roomMap.put(roomNum, new Deluxe(roomNum, bedType));

        roomMap.get(roomNum).reservationFin();
        System.out.println(roomMap.get(roomNum));

        String appDir = System.getProperty("user.dir");

        System.out.println(appDir);
        File f0 = new File(appDir, "test.ser");

        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(roomMap);

    }
}