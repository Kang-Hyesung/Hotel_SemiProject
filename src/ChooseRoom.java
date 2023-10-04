import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChooseRoom implements Purchase{
    @Override
    public Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toChangeRoomEmpty(roomMap);

        for (int i = 1; i < 11; i++)
        {
            Iterator<String> it = roomMap.keySet().iterator();
            Calendar today = Calendar.getInstance();
            int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE)));
            while(it.hasNext())
            {
                String key = it.next();
                if(todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate <= (roomMap.get(key).getIntEndDate()))
                {
                    if(Integer.parseInt(roomMap.get(key).getRoomNum()) == 100 + i)
                        System.out.print(" ■ ");
                    else
                        System.out.print(" □ ");
                }
            }
        }
        System.out.println();

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
            reNum = String.format("%d%02d%02d%03d", year, month, day,i);						// 고쳐야 함
        }
        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days)) != null);

        roomMap.get(reNum).setRoomRe(true);
        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인

        return roomMap;
    } // buyRoom end


    // 방 예약
    @Override
    public Hashtable<String, Room> reRoom(Hashtable<String, Room> roomMap) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toChangeRoomEmpty(roomMap);

        System.out.print("며칠 뒤부터 시작?");
        int afDay = Integer.parseInt(br.readLine());
        System.out.print("몇 박? : ");
        int days = Integer.parseInt(br.readLine());
        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 선택하세요 : ");
        String bedType = br.readLine();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, afDay);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        Random rd = new Random();

        String reNum;
        do {
            int i = rd.nextInt(8999) + 1000;
            reNum = String.format("%d%02d%02d%03d", year, month, day,i);
        }
        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days, afDay)) != null);

        roomMap.get(reNum).setRoomRe(true);
        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인

        return roomMap;
    } // reRoom end
    @Override
    public void toChangeRoomEmpty(Hashtable<String, Room> roomMap){
        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE)));
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate > (roomMap.get(key).getIntEndDate())){
                roomMap.get(key).setRoomRe(false);
            }
            if(todayDate == (roomMap.get(key).getIntStartDate())) {
                roomMap.get(key).setRoomRe(true);
            }
            System.out.println(roomMap.get(key));
            System.out.println();
        }
    } //toChangeRoomEmpty end

}

interface Purchase {
    public abstract Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException;
    public abstract Hashtable<String, Room> reRoom(Hashtable<String, Room> roomMap) throws IOException;
    public abstract void toChangeRoomEmpty(Hashtable<String, Room> roomMap) throws IOException;
}