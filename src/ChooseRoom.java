//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class ChooseRoom {
//
//    public Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        toChangeRoomEmpty(roomMap);
//
//        System.out.print("몇 박? : ");
//        int days = Integer.parseInt(br.readLine());
//
//        System.out.printf("인원수? : ");
//        int num = Integer.parseInt(br.readLine());
//
//        printRoom(roomMap, days, num);                                // 객실 출력 □ ■
//        System.out.print("객실번호를 선택하세요 : ");
//        String roomNum = br.readLine();
//
//
//
//        if(roomNum.charAt(0) =='1')
//            if(num > 2)
//                System.out.println("인원수 초과");
//            else if(roomNum.charAt(0) =='2')
//                if(num > 3)
//                    System.out.println("인원수 초과");
//                else if(roomNum.charAt(0) =='3')
//                    if(num > 6)
//                        System.out.println("인원수 초과");
//                    else if(roomNum.charAt(0) =='4')
//                        if(num > 3)
//                            System.out.println("인원수 초과");
//
//        checkRoomCondition(roomMap, days, roomNum);
//
//        System.out.print("침대 타입을 선택하세요 : ");
//        String bedType = br.readLine();
//
//        //putRoom(roomMap, roomNum, days);
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int day = cal.get(Calendar.DATE);
//        Random rd = new Random();
//
//        String reNum;
//        do {
//            int i = rd.nextInt(8999) + 1000;
//            reNum = String.format("%d%02d%02d%04d", year, month, day,i);						// 고쳐야 함
//        }
//        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days)) != null);
//
//        roomMap.get(reNum).setRoomRe(true);
//        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인
//
//        return roomMap;
//    } // buyRoom end
//
//    public void toChangeRoomEmpty(Hashtable<String, Room> roomMap){
//        Iterator<String> it = roomMap.keySet().iterator();
//        Calendar today = Calendar.getInstance();
//        int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//        while(it.hasNext())
//        {
//            String key = it.next();
//            if(todayDate >= (roomMap.get(key).getIntEndDate())){
//                roomMap.get(key).setRoomRe(false);
//            }
//            if(todayDate == (roomMap.get(key).getIntStartDate())) {
//                roomMap.get(key).setRoomRe(true);
//            }
//            System.out.println(roomMap.get(key));
//            System.out.println();
//        }
//    } //toChangeRoomEmpty end
//
//
//    public void printRoom(Hashtable<String, Room> roomMap, int days, int num){  // 자료구조, 몇 박, 인원 수
//        for (int i = 1; i <= 4; i++){
//
//
////            if(num > 2)
////                if(i == 1)
////                    i++;
////            if(num >= 4)
////                if(i == 2)
////                    i++;
//
//            if(i == 1 && num <= 2)
//                System.out.println("Deluxe (2인 기준)");
//            if(i == 2 && num <= 3)
//                System.out.println("Superior (2 ~ 3인 기준)");
//            if(i == 3 && num <= 7)
//                System.out.println("Family (4 ~ 6인 기준)");
//            if(i == 4 && num <= 3)
//                System.out.println("Suite (2 ~ 3인 기준)");
//
//            for(int j = 1; j <= 10; j++){
//
//                boolean flag = false;
//                Iterator<String> it = roomMap.keySet().iterator();
//                Calendar today = Calendar.getInstance();
//                int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//                today.add(Calendar.DATE, days);
//                int endDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//
//                while(it.hasNext())
//                {
//                    String key = it.next();
//                    if(Integer.parseInt(roomMap.get(key).getRoomNum()) == 100 * i + j)
//                    {
//                        if( (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate())) ||
//                                (endDate >= (roomMap.get(key).getIntStartDate()) && endDate < (roomMap.get(key).getIntEndDate()))) {
//                            System.out.print(" ■  ");
//                            flag = true;
//                            continue;
//                        }
//                        System.out.println();
//                    }
//                }
//
//                if(!flag)
//                    System.out.print(" □  ");
//            }
//
//            System.out.println();
//
//            for (int j = 1; j <= 10; j++){
//                System.out.printf("%d ", 100 * i + j);
//            }
//            System.out.println("\n");
//
////            if(num >= 4)
////                if(i == 3)
////                    break;
//        }
//    }
//
//    public void checkRoomCondition(Hashtable<String, Room> roomMap, int days, String roomNum) throws IOException{  // 해쉬 테이블, 몇 박, 방 호수
//        for (int i = 1; i <= 4; i++){
//            for(int j = 1; j <= 10; j++){
//                Iterator<String> it = roomMap.keySet().iterator();
//                Calendar today = Calendar.getInstance();
//                int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//                today.add(Calendar.DATE, days);
//                int endDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//
//                while(it.hasNext())
//                {
//                    String key = it.next();
//                    if(roomMap.get(key).getRoomNum() == Integer.parseInt(roomNum))
//                    {
//                        if( (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate())) ||
//                                (endDate >= (roomMap.get(key).getIntStartDate()) && endDate < (roomMap.get(key).getIntEndDate()))) {
//                            System.out.println("선택이 불가합니다.");
//                            buyRoom(roomMap);
//                        }
//                    }
//                }
//
//            }
//        }
//
//    }
//
//
//    public void putRoom(Hashtable<String, Room> roomMap, String roomNum, int days){
//        String ran = "example";
//        if(roomNum.charAt(0) == '1')
//            if(roomNum.charAt(2) <= '5')
//                roomMap.put(ran, new Deluxe(101, "Twin", days));
//            else
//                roomMap.put(ran, new Deluxe(101, "Double", days));
//        else if(roomNum.charAt(0) == '2')
//            if(roomNum.charAt(2) <= '5')
//                roomMap.put(ran, new Superior(101, "Twin + Double", days));
//            else
//                roomMap.put(ran, new Superior(101, "3 Twin", days));
//        else if(roomNum.charAt(0) == '3')
//            roomMap.put(ran, new Family(101, "2 King", days));
//        else if(roomNum.charAt(0) == '4')
//            if(roomNum.charAt(2) <= '5')
//                roomMap.put(ran, new Superior(101, "King", days));
//            else
//                roomMap.put(ran, new Superior(101, "2 Double", days));
//
//    }
//
//
//}