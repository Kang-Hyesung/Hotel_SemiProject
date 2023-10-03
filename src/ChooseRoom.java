import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChooseRoom implements Purchase{
    @Override
    public Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toChangeRoomEmpty(roomMap);

        System.out.print("몇 박? : ");
        int days = Integer.parseInt(br.readLine());
        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 선택하세요 : ");
        String bedType = br.readLine();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        Random rd = new Random();

        String reNum;
        do {
            int i = rd.nextInt(8999) + 1000;
            reNum = String.format("%d%02d%02d%03d", year, month, day,i);
        }
        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days)) != null);

        roomMap.get(reNum).setRoomRe(true);
        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인

        return roomMap;
    } // buyRoom end
//    public static void printInput(){
//        System.out.println("객실을 선택하세요\n 1층 : 디럭스 \n 2층 : 슈페리어 \n 3층 : 패밀리 \n 4층 스위트");
//    }
    @Override
    public void toChangeRoomEmpty(Hashtable<String, Room> roomMap){
        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        String todayDate = String.format("%d%d%d", today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate.compareTo(roomMap.get(key).getEndDate()) > 0){
                roomMap.get(key).setRoomRe(false);
            }
            if(todayDate.compareTo(roomMap.get(key).getStartDate()) == 0) {
                roomMap.get(key).setRoomRe(true);
            }
            System.out.println(roomMap.get(key));
        }
    } //toEmpty end

//    public void toReserved(Hashtable<String, Room> roomMap){
//        Iterator<String> it = roomMap.keySet().iterator();
//        Calendar today = Calendar.getInstance();
//        String todayDate = String.format("%d%d%d",today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));
//        while(it.hasNext())
//        {
//            String key = it.next();
//            String startDate = roomMap.get(key).getStartDate();
//            System.out.println(roomMap.get(key));
//            if(todayDate.compareTo(startDate) <= 0){
//                roomMap.get(key).setRoomRe(true);
//                System.out.println("예약되었습니다");
//            }
//        }
//    }//toReserved end
}

interface Purchase {
    public abstract Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException;
    public abstract void toChangeRoomEmpty(Hashtable<String, Room> roomMap) throws IOException;
}
