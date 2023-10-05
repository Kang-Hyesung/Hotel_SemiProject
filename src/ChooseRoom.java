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

        System.out.printf("인원수? : ");
        int num = Integer.parseInt(br.readLine());

        printRoom(roomMap, days, num);                                // 객실 출력 □ ■


        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 선택하세요 : ");
        String bedType = br.readLine();

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
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

    @Override
    public void toChangeRoomEmpty(Hashtable<String, Room> roomMap){
        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate >= (roomMap.get(key).getIntEndDate())){
                roomMap.get(key).setRoomRe(false);
            }
            if(todayDate == (roomMap.get(key).getIntStartDate())) {
                roomMap.get(key).setRoomRe(true);
            }
            System.out.println(roomMap.get(key));
            System.out.println();
        }
    } //toChangeRoomEmpty end

    @Override
    public void printRoom(Hashtable<String, Room> roomMap, int days, int num){  // 자료구조, 몇 박, 인원 수
        for (int i = 1; i <= 4; i++){
            if(i == 1)
                System.out.println("Deluxe");
            if(i == 2)
                System.out.println("Superior");
            if(i == 3)
                System.out.println("Family");
            if(i == 4)
                System.out.println("Suite");

            for(int j = 1; j <= 10; j++){
                boolean flag = false;
                Iterator<String> it = roomMap.keySet().iterator();
                Calendar today = Calendar.getInstance();
                int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
                today.add(Calendar.DATE, days);
                int endDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));

                while(it.hasNext())
                {
                    String key = it.next();
                    if(Integer.parseInt(roomMap.get(key).getRoomNum()) == 100 * i + j)
                    {
                        if( (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate())) &&
                                (endDate >= (roomMap.get(key).getIntStartDate()) && endDate < (roomMap.get(key).getIntEndDate()))) {
                            System.out.print(" ■  ");
                            flag = true;
                            continue;
                        }
                        System.out.println();
                    }
                }

                if(!flag)
                    System.out.print(" □  ");
            }

            System.out.println();

            for (int j = 1; j <= 10; j++){
                System.out.printf("%d ", 100 * i + j);
            }
            System.out.println("\n");
        }
    }
}

interface Purchase {
    public abstract Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException;
    public abstract void toChangeRoomEmpty(Hashtable<String, Room> roomMap) throws IOException;
    public abstract void printRoom(Hashtable<String, Room> roomMap, int days, int num);
}