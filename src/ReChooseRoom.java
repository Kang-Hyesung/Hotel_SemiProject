import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReChooseRoom{
    int sukbak;             // 숙박일수
    int inwon;              // 인원
    int roomNum;            // 방 호수
    int afDay;              // 며칠뒤부터 시작?
    String afDate;          // afDay 스트링형
    int column;             // 층
    int row;                // 호수
    static String reNum;           // 예약 번호
    static Hashtable<String, Room> roomMap;                     // 예약번호, 객실 객체 자료 구조
    Set<String> reNumList = new HashSet<>();             // 예약번호 담을 자료구조
    Calendar today;
    int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean[][] roomCheck = new boolean[4][10];

    ReChooseRoom(Hashtable<String, Room> roomMap){                 // 생성자로 자료구조 받아오기
        ReChooseRoom.roomMap = roomMap;
    }

    public void ReChooseRoomRun(){
        today = Calendar.getInstance();
        afterDay();
        inputSukbakInwon();
        toChangeRoomEmpty();
        printRoom();
        inputRoomNum();
        putRoom();
    }

    public void inputSukbakInwon(){                             // 숙박일수와 인원수 받는 메소드
        try {
            System.out.print("몇 박? : ");
            sukbak = Integer.parseInt(br.readLine());

            System.out.printf("인원수? : ");
            inwon = Integer.parseInt(br.readLine());
        }
        catch (Exception e){
            System.out.println("정수 형태로 입력해 주세요");
            inputSukbakInwon();
        }
    }

    public void toChangeRoomEmpty(){
        Iterator<String> it = roomMap.keySet().iterator();
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate >= (roomMap.get(key).getIntEndDate())){
                roomMap.get(key).setRoomRe(false);
            }
            if(todayDate == (roomMap.get(key).getIntStartDate())) {
                roomMap.get(key).setRoomRe(true);
            }
            System.out.println();
        }
    } //toChangeRoomEmpty end

    public void printRoom(){  // 자료구조, 몇 박, 인원 수
        for (int i = 1; i <= 4; i++){
            if(i == 1)
                System.out.println("Deluxe (2인 기준)");
            if(i == 2)
                System.out.println("Superior (2 ~ 3인 기준)");
            if(i == 3)
                System.out.println("Family (4 ~ 6인 기준)");
            if(i == 4)
                System.out.println("Suite (2 ~ 3인 기준)");

            for(int j = 1; j <= 10; j++){
                boolean flag = false;
                Iterator<String> it = roomMap.keySet().iterator();
                Calendar start = Calendar.getInstance();
                int startDate = Integer.parseInt(String.format("%d%02d%02d", start.get(Calendar.YEAR), start.get(Calendar.MONTH) + 1, start.get(Calendar.DATE)));
                start.add(Calendar.DATE, sukbak);
                int endDate = Integer.parseInt(String.format("%d%02d%02d", start.get(Calendar.YEAR), start.get(Calendar.MONTH) + 1, start.get(Calendar.DATE)));

                while(it.hasNext())
                {
                    String key = it.next();
                    if(roomMap.get(key).getRoomNum() == 100 * i + j)
                    {
                        if( (startDate >= (roomMap.get(key).getIntStartDate()) && startDate < (roomMap.get(key).getIntEndDate())) ||
                                (endDate >= (roomMap.get(key).getIntStartDate()) && endDate < (roomMap.get(key).getIntEndDate()))) {
                            System.out.print(" ■  ");
                            roomCheck[i - 1][j - 1] = true;
                            flag = true;
                        }
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

    public void inputRoomNum(){
        try {
            System.out.print("객실번호를 선택하세요 : ");
            roomNum = Integer.parseInt(br.readLine());
            column = roomNum / 100;               // 층
            row    = roomNum % 100;               // 호수

            if(roomCheck[column - 1][row - 1]){
                System.out.println("이미 선택된 객실입니다 다시 선택하세요");
                inputRoomNum();
            }

            if((roomNum / 100 == 1) &&  inwon > 2){
                System.out.println("Deluxe등급의 객실은 2인까지 가능");
                inputRoomNum();
            }
            else if((roomNum / 100 == 2) &&  inwon > 3){
                System.out.println("Superior등급의 객실은 3인까지 가능");
                inputRoomNum();
            }
            else if((roomNum / 100 == 3) &&  inwon > 6){
                System.out.println("Family등급의 객실은 6인까지 가능");
                inputRoomNum();
            }
            else if((roomNum / 100 == 4) &&  inwon > 3){
                System.out.println("Suite등급의 객실은 3인까지 가능");
                inputRoomNum();
            }
        }
        catch (Exception e){
            System.out.println("정수 형태로 입력해 주세요");
            inputRoomNum();
        }
    }

    public void putRoom(){
        Random rd = new Random();
        do {// 예약의 경우 5000 ~ 9999 부여
            int i = rd.nextInt(5000) + 5000;
            reNum = String.format("%d%02d%02d%04d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE),i);
        }
        while (!reNumList.add(reNum));


        if(column == 1)
            if(row <= 5)
                roomMap.put(reNum, new Deluxe(roomNum, "Twin", sukbak, afDay));
            else
                roomMap.put(reNum, new Deluxe(roomNum, "Double", sukbak, afDay));
        else if(column == 2)
            if(row <= 5)
                roomMap.put(reNum, new Superior(roomNum, "Twin + Double", sukbak, afDay));
            else
                roomMap.put(reNum, new Superior(roomNum, "3 Twin", sukbak, afDay));
        else if(column == 3)
            roomMap.put(reNum, new Family(roomNum, "2 King", sukbak, afDay));
        else if(column == 4)
            if(row <= 5)
                roomMap.put(reNum, new Superior(roomNum, "King", sukbak, afDay));
            else
                roomMap.put(reNum, new Superior(roomNum, "2 Double", sukbak, afDay));

    }

    public void afterDay() {
        System.out.println("체크인 날짜를 입력하세요");
        System.out.println("예)2000-08-04");

        try {
        afDate = br.readLine();
        }
        catch (Exception e){
            afterDay();
        }


        int afterYear = Integer.parseInt(afDate.substring(0, 4));
        int afterMonth = Integer.parseInt(afDate.substring(5, 7));
        int afterDay = Integer.parseInt(afDate.substring(8, 10));
        int afterDate = Integer.parseInt(String.format("%d%02d%02d", afterYear, afterMonth, afterDay));

        for(int i = 1; i <= 30; i++){
            today.add(Calendar.DATE, 1);
            int todayYear = today.get(Calendar.YEAR);
            int todayMonth = today.get(Calendar.MONTH) + 1;
            int todayDay = today.get(Calendar.DATE);
            todayDate = Integer.parseInt(String.format("%d%02d%02d", todayYear, todayMonth, todayDay));

            if (todayDate == afterDate) {
                afDay = i;
                break;
            }
        }
    }

    public static void getInformation(){
        System.out.println(roomMap.get(reNum));
    }

    public void removeRoom(){
        roomMap.remove(reNum);
        reNumList.remove(reNum);
    }



//    int startYear,startMonth,startDay;
//
//    // 방 예약
//
//    public Hashtable<String, Room> buyRoom(Hashtable<String, Room> roomMap) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        toChangeRoomEmpty(roomMap);
//        int afDay = afterDay();
//
//
//        System.out.print("몇 박? : ");
//        int days = Integer.parseInt(br.readLine());
//
//        System.out.print("인원수? : ");
//        int num = Integer.parseInt(br.readLine());
//
//        printRoom(roomMap, days, num);                                // 객실 출력 □ ■
//        System.out.print("객실번호를 선택하세요 : ");
//        String roomNum = br.readLine();
//        checkRoomCondition(roomMap, days, roomNum);
//
//        System.out.print("침대 타입을 선택하세요 : ");
//        String bedType = br.readLine();
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, afDay);
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH) + 1;
//        int day = cal.get(Calendar.DATE);
//        Random rd = new Random();
//
//        String reNum;
//        do {
//            int i = rd.nextInt(8999) + 1000;
//            reNum = String.format("%d%02d%02d%03d", year, month, day,i);
//        }
//        while (roomMap.put(reNum, new Deluxe(roomNum, bedType, days, afDay)) != null);
//
//        roomMap.get(reNum).setRoomRe(true);
//        System.out.println(roomMap.get(reNum));     // 객체 정보(애약/구매 내역) 확인
//
//        return roomMap;
//    } // reRoom end
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
//    public void printRoom(Hashtable<String, Room> roomMap, int days, int num){
//        for (int i = 1; i <= 4; i++){
//            if(i == 1)
//                System.out.println("Deluxe");
//            if(i == 2)
//                System.out.println("Superior");
//            if(i == 3)
//                System.out.println("Family");
//            if(i == 4)
//                System.out.println("Suite");
//
//            for(int j = 1; j <= 10; j++){
//                boolean flag = false;
//                Iterator<String> it = roomMap.keySet().iterator();
//                Calendar today = Calendar.getInstance();
//                int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
//
//                while(it.hasNext())
//                {
//                    String key = it.next();
//                    if(Integer.parseInt(roomMap.get(key).getRoomNum()) == 100 * i + j)
//                    {
//                        if(todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate())) {
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
//        }
//    }
//
//    public int afterDay() throws IOException {
//        int afDay = 0;
//        Calendar now = Calendar.getInstance();
//        startYear = now.get(Calendar.YEAR);
//        startMonth = now.get(Calendar.MONTH) + 1;
//        startDay = now.get(Calendar.DATE);
//        int todayDate = Integer.parseInt(String.format("%d%02d%02d", now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE)));
//
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("체크인 날짜를 입력하세요");
//        System.out.println("예)2000년 08월 04일");
//        String afDate = br.readLine();
//        int afterYear = Integer.parseInt(afDate.substring(0, 4));
//        int afterMonth = Integer.parseInt(afDate.substring(6, 8));
//        int afterDay = Integer.parseInt(afDate.substring(10, 12));
//        int afterDate = Integer.parseInt(String.format("%d%02d%02d", afterYear, afterMonth, afterDay));
//
//        for(int i = 1; i <= 30; i++){
//            now.add(Calendar.DATE, 1);
//            startYear = now.get(Calendar.YEAR);
//            startMonth = now.get(Calendar.MONTH);
//            startDay = now.get(Calendar.DATE);
//            todayDate = Integer.parseInt(String.format("%d%02d%02d", now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DATE)));
//
//            if (todayDate == afterDate) {
//                afDay = i;
//            }
//        }
//        return afDay;
//    }
//
//    public void checkRoomCondition(Hashtable<String, Room> roomMap, int days, String roomNum) throws IOException{  // 해쉬 테이블, 몇 박, 방 호수
//        for (int i = 1; i <= 4; i++){
//            for(int j = 1; j <= 10; j++){
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
//                    if(roomMap.get(key).getRoomNum() == roomNum)
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
}
