import java.io.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        Map<String, Room> roomMap = new HashMap<>();
//
//
//        FileInputStream fis = new FileInputStream("test.ser");
//        ObjectInputStream in = new ObjectInputStream(fis);
//
//        roomMap = (HashMap) in.readObject();
////        Filein("test.ser", roomMap);
//        System.out.println(roomMap);
//
//        System.out.print("객실번호를 선택하세요 : ");
//        String roomNum = br.readLine();
//        System.out.print("침대 타입을 입력하세요 : ");
//        String bedType = br.readLine();
//
//        // 예약 변호 만들어서 map에 roomNum 대신 넣어줄 것(마지막에 결제되면 실행되게 해야 함)
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DATE);
//        int i = 1;
//
//        String reNum = String.valueOf(year * 10000 + month * 100 + day * 10 + i++);
//        System.out.println(reNum);
//
//        roomMap.put(reNum, new Deluxe(roomNum, bedType));
//
//        roomMap.get(reNum).reservationFin();
//        System.out.println(roomMap.get(reNum));
//
//        String appDir = System.getProperty("user.dir");
//
//        System.out.println(appDir);
//        File f0 = new File(appDir, "test.ser");
//
//        FileOutputStream fos = new FileOutputStream(f0);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(roomMap);
        chooseRoom();
    }


//    public static void Filein(String filename,Map mapName) throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream(filename);
//        ObjectInputStream in = new ObjectInputStream(fis);
//        mapName = (HashMap) in.readObject();
//    } 안됨..

    public static void chooseRoom() throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Room> roomMap = new HashMap<>();

        FileInputStream fis = new FileInputStream("test.ser");
        ObjectInputStream in = new ObjectInputStream(fis);

        roomMap = (HashMap) in.readObject();
//        Filein("test.ser", roomMap);
        System.out.println(roomMap);

        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 입력하세요 : ");
        String bedType = br.readLine();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        int i = 1;

        String reNum = String.format("%d년 %02d월 %02d일", year, month, day);
        System.out.println(reNum);

        roomMap.put(reNum, new Deluxe(roomNum, bedType));

        roomMap.get(reNum).reservationFin();
        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인

        String appDir = System.getProperty("user.dir");

        // System.out.println(appDir); 저장 장소 확인
        File f0 = new File(appDir, "test.ser");

        FileOutputStream fos = new FileOutputStream(f0);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(roomMap);
    }


}