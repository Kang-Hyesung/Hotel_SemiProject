import java.io.*;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        Hashtable<String, Room> roomMap = inOut.fileIn();

        toEmpty(roomMap);                                     // 객체의 숙박종료날짜와 오늘 날짜를 비교하여 빈방으로 바꾸기
        toReserved(roomMap);                                  // 객체의 예약 날짜와 오늘 날짜를 비교하여 입실완료로 바꾸기
        chooseRoom(roomMap);                                  // 객실 고르고 Map에 예약명단과 객실정보 추가

        inOut.fileOut(roomMap);                               // 추가한 Map 직렬화
        inOut = null;
    }

    public static void chooseRoom(Hashtable<String, Room> roomMap) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.print("몇 박 묵으실거에요? : ");
        int days = Integer.parseInt(br.readLine());
        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 입력하세요 : ");
        String bedType = br.readLine();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        Random rd = new Random();
        //int i = rd.nextInt(8999) + 1000;
        String reNum;
        do {
            int i = rd.nextInt(8999) + 1000;
            reNum = String.format("%d%02d%02d%03d", year, month, day,i);
        }
        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days)) != null);
        //int i = rd.nextInt(8999) + 1000;

        //String reNum = String.format("%d%02d%02d%03d", year, month, day,i);
        //System.out.println(reNum);

        //roomMap.put(reNum, new Deluxe(roomNum, bedType, days));

        roomMap.get(reNum).reservationFin();
        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인
    }

//    public static void printInput(){
//        System.out.println("객실을 선택하세요\n 1층 : 디럭스 \n 2층 : 슈페리어 \n 3층 : 패밀리 \n 4층 스위트");
//    }
    public static void toEmpty(Hashtable<String, Room> roomMap){
        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        String todayDate = String.format("%d%d%d",Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate.compareTo(roomMap.get(key).getEndDate()) >= 0){
                roomMap.get(key).setRoomRe(false);
            }
        }
    }

    public static void toReserved(Hashtable<String, Room> roomMap){
        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        String todayDate = String.format("%d%d%d",Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate.compareTo(roomMap.get(key).getStartDate()) == 0){
                roomMap.get(key).setRoomRe(true);
            }
        }
    }




}