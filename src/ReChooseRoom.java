import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReChooseRoom implements BuyRoom{
    private int sukbak;                                  // 숙박일수
    private String inwon;                                // 인원
    private int roomNum;                                 // 방 호수
    private int afDay;                                   // 며칠뒤부터 시작?
    private String afDate;                               // afDay 스트링형
    private int column;                                  // 층
    private int row;                                     // 호수
    private String reNum;                                // 예약 번호
    Hashtable<String, Room> roomMap;                     // 예약번호, 객실 객체 자료 구조
    Set<String> reNumList = new HashSet<>();             // 예약번호 담을 자료구조
    Calendar today = Calendar.getInstance();
    int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean[][] roomCheck = new boolean[4][10];

    ReChooseRoom(Hashtable<String, Room> roomMap){                 // 생성자로 자료구조 받아오기
        this.roomMap = roomMap;
    }

    public String ReChooseRoomRun(){
        today = Calendar.getInstance();
        afterDay();
        inputSukbakInwon();
        toChangeRoomEmpty();
        printRoom();
        inputRoomNum();

        return putRoom();
    }

    public String getReNum() {
        return reNum;
    }

    public void inputSukbakInwon(){                             // 숙박일수와 인원수 받는 메소드
        try {
            System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("\t┃   %d년 %d월 %d일               SS HOTEL ┃\n", todayDate/10000, (todayDate%10000)/100, todayDate%100);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃================객실 구매==================┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃ ▷ 숙박하실 일 수를 입력해 주세요.        ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("\t● 숙박 일 수 입력(숫자) : ");

            sukbak = Integer.parseInt(br.readLine());

            if(afDay + sukbak >= 29){
                System.out.println("\t*** 28일 이상의 장기 숙박은 프론트로 문의해 주세요. ***");
                System.out.println("\t*** 숙박하실 일 수를 다시 입력해 주세요. ***");
                inputSukbakInwon();
            }

            System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("\t┃   %d년 %d월 %d일               SS HOTEL ┃\n", todayDate/10000, (todayDate%10000)/100, todayDate%100);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃================객실 구매==================┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃ ▷ 숙박 일 수 : %2d일                      ┃\n",sukbak);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃ ▷ 숙박 인원 수를 입력해 주세요.          ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.printf("\t● 숙박 인원 입력(숫자) : " );

            inwon = br.readLine();
        }
        catch (Exception e){
            System.out.println("*** 정수 형태로 입력해 주세요. ***");
            inputSukbakInwon();
        }
    }

    public void toChangeRoomEmpty(){
        // 오늘 날짜와 객실 객체의 숙박시작 ~ 종료날짜를 비교하여 방이 비어 있는지 여부를 true false로 변경
        Iterator<String> it = roomMap.keySet().iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                String key = it.next();
                if (todayDate >= (roomMap.get(key).getIntEndDate())) {
                    roomMap.get(key).setRoomRe(false);
                }
                if (todayDate == (roomMap.get(key).getIntStartDate())) {
                    roomMap.get(key).setRoomRe(true);
                }
                System.out.println();
            }
        }
    } //toChangeRoomEmpty end

    public void printRoom(){  // 자료구조, 몇 박, 인원 수
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("\t┃   %d년 %d월 %d일                         SS HOTEL ┃\n", todayDate/10000, (todayDate%10000)/100, todayDate%100);
        System.out.println("\t┃                                                     ┃");
        System.out.println("\t┃======================객실 선택======================┃");
        System.out.println("\t┃                                                     ┃");

        for (int i = 1; i <= 4; i++)
        {
            if(i == 1)
                System.out.println("\t┃   Deluxe (2인 기준)                                 ┃ ");
            if(i == 2)
                System.out.println("\t┃   Superior (2 ~ 3인 기준)                           ┃");
            if(i == 3)
                System.out.println("\t┃   Family (4 ~ 6인 기준)                             ┃");
            if(i == 4)
                System.out.println("\t┃   Suite (2 ~ 3인 기준)                              ┃");

            for(int j = 1; j <= 10; j++){
                boolean flag = false;
                Iterator<String> it = roomMap.keySet().iterator();
                Calendar start = Calendar.getInstance();
                start.add(Calendar.DATE,afDay);
                int startDate = Integer.parseInt(String.format("%d%02d%02d", start.get(Calendar.YEAR), start.get(Calendar.MONTH) + 1, start.get(Calendar.DATE)));
                start.add(Calendar.DATE, sukbak);
                int endDate = Integer.parseInt(String.format("%d%02d%02d", start.get(Calendar.YEAR), start.get(Calendar.MONTH) + 1, start.get(Calendar.DATE)));

                if (j == 1)
                    System.out.print("\t┃  ");

                while(it.hasNext())
                {
                    String key = it.next();
                    if(roomMap.get(key).getRoomNum() == 100 * i + j)
                    {
                        if( (startDate >= (roomMap.get(key).getIntStartDate()) && startDate < (roomMap.get(key).getIntEndDate())) ||
                                (endDate > (roomMap.get(key).getIntStartDate()) && endDate <= (roomMap.get(key).getIntEndDate()))) {
                            System.out.print(" ■  ");
                            roomCheck[i - 1][j - 1] = true;
                            flag = true;
                        }
                    }
                }

                if(!flag) {
                    System.out.print(" □  ");
                    if (j == 10)
                        System.out.print(" ┃");
                }
            }
            System.out.println();

            for (int j = 1; j <= 10; j++)
            {
                if (j == 1)
                    System.out.printf("\t┃  ");
                System.out.printf("%d  ", 100 * i + j);
                if (j == 10)
                    System.out.print(" ┃");
            }
            System.out.println();
            System.out.println("\t┃                                                     ┃");
        }
        System.out.println("\t┃ ▷ 원하시는 객실의 호수를 입력해 주세요.            ┃");
        System.out.println("\t┃                                                     ┃");
        System.out.println("\t└━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┘");
    }

    public void inputRoomNum(){
        try {
            System.out.print("\t● 객실 호수 입력 (숫자 형태) : ");
            roomNum = Integer.parseInt(br.readLine());
            column = roomNum / 100;               // 층
            row    = roomNum % 100;               // 호수

            if(roomCheck[column - 1][row - 1]){
                System.out.println("\t\n=====이미 선택된 객실입니다. 다시 입력해 주세요.====");
                inputRoomNum();
            }

            if((roomNum / 100 == 1) &&  Integer.parseInt(inwon) > 2){
                System.out.println("\t\n=====Deluxe등급의 객실은 2인까지 숙박이 가능합니다.=====");
                inputRoomNum();
            }
            else if((roomNum / 100 == 2) &&  Integer.parseInt(inwon) > 3){
                System.out.println("\t\n=====Superior등급의 객실은 3인까지 숙박이 가능합니다.=====");
                inputRoomNum();
            }
            else if((roomNum / 100 == 3) &&  Integer.parseInt(inwon) > 6){
                System.out.println("\t\n=====Family등급의 객실은 6인까지 숙박이 가능합니다.=====");
                inputRoomNum();
            }
            else if((roomNum / 100 == 4) &&  Integer.parseInt(inwon) > 3){
                System.out.println("\t\n=====Suite등급의 객실은 3인까지 숙박이 가능합니다.=====");
                inputRoomNum();
            }
        }
        catch (Exception e){
            System.out.println("*** 정수 형태로 입력해 주세요. ***");
            inputRoomNum();
        }
    }

    public String putRoom(){
        Random rd = new Random();
        do {// 예약의 경우 5000 ~ 9999 부여
            int i = rd.nextInt(5000) + 5000;
            reNum = String.format("%d%02d%02d%04d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE),i);
        }
        while (!reNumList.add(reNum));


        if(column == 1)
            if(row <= 5)
                roomMap.put(reNum, new Deluxe(roomNum, "Twin", sukbak, afDay, inwon));
            else
                roomMap.put(reNum, new Deluxe(roomNum, "Double", sukbak, afDay, inwon));
        else if(column == 2)
            if(row <= 5)
                roomMap.put(reNum, new Superior(roomNum, "Twin + Double", sukbak, afDay, inwon));
            else
                roomMap.put(reNum, new Superior(roomNum, "3 Twin", sukbak, afDay, inwon));
        else if(column == 3)
            roomMap.put(reNum, new Family(roomNum, "2 King", sukbak, afDay, inwon));
        else if(column == 4)
            if(row <= 5)
                roomMap.put(reNum, new Superior(roomNum, "King", sukbak, afDay, inwon));
            else
                roomMap.put(reNum, new Superior(roomNum, "2 Double", sukbak, afDay, inwon));


        return reNum;

    }

    public void afterDay() {
        Calendar today = Calendar.getInstance();
        int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));

        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  ▷ 체크인 하실 날짜를 입력해 주세요.     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃      * 예) 2023-08-04                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t● 날짜 입력 : ");
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

        for(int i = 1; i <= 28; i++){
            if (todayDate == afterDate) {
                afDay = i;
                break;
            }
            today.add(Calendar.DATE, 1);
            int todayYear = today.get(Calendar.YEAR);
            int todayMonth = today.get(Calendar.MONTH) + 1;
            int todayDay = today.get(Calendar.DATE) - 1;
            todayDate = Integer.parseInt(String.format("%d%02d%02d", todayYear, todayMonth, todayDay));

            if(i == 28){
                System.out.println("체크인 날짜는 27일 후 까지 가능");
                afterDay();
            }
        }
    }

    public void removeRoom(){
        roomMap.remove(reNum);
        reNumList.remove(reNum);
    }
}