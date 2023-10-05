import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class ReChooseRoom implements Purchase{
    int startYear,startMonth,startDay;

    // 방 예약
    @Override
    public Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        toChangeRoomEmpty(roomMap);
        int afDay = afterDay();


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
        cal.add(Calendar.DATE, afDay);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
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
    public void printRoom(Hashtable<String, Room> roomMap, int days, int num){
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

                while(it.hasNext())
                {
                    String key = it.next();
                    if(Integer.parseInt(roomMap.get(key).getRoomNum()) == 100 * i + j)
                    {
                        if(todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate())) {
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

    public int afterDay() throws IOException {
        int afDay = 0;
        Calendar now = Calendar.getInstance();
        startYear = now.get(Calendar.YEAR);
        startMonth = now.get(Calendar.MONTH) + 1;
        startDay = now.get(Calendar.DATE);
        int todayDate = Integer.parseInt(String.format("%d%02d%02d", now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE)));


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("체크인 날짜를 입력하세요");
        System.out.println("예)2000년 08월 04일");
        String afDate = br.readLine();
        int afterYear = Integer.parseInt(afDate.substring(0, 4));
        int afterMonth = Integer.parseInt(afDate.substring(6, 8));
        int afterDay = Integer.parseInt(afDate.substring(10, 12));
        int afterDate = Integer.parseInt(String.format("%d%02d%02d", afterYear, afterMonth, afterDay));

        for(int i = 1; i <= 30; i++){
            now.add(Calendar.DATE, 1);
            startYear = now.get(Calendar.YEAR);
            startMonth = now.get(Calendar.MONTH);
            startDay = now.get(Calendar.DATE);
            todayDate = Integer.parseInt(String.format("%d%02d%02d", now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DATE)));

            if (todayDate == afterDate) {
                afDay = i;
            }
        }
        return afDay;
    }


}
