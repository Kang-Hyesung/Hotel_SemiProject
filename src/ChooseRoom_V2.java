import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;

public class ChooseRoom_V2 {
    int sukbak;       // 숙박일수
    int inwon;        // 인원
    int roomNum;   // 방 호수

    int column;            // 층
    int row;               // 호수
    Hashtable<String, Room> roomMap;        // 예약번호, 객실 객체 자료 구조
    Calendar today = Calendar.getInstance();
    int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean[][] roomCheck = new boolean[4][10];

    ChooseRoom_V2(Hashtable<String, Room> roomMap){                 // 생성자로 자료구조 받아오기
        this.roomMap = roomMap;
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
                            roomCheck[i][j] = true;
                            flag = true;
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

    public void inputRoomNum(){
        try {
            System.out.print("객실번호를 선택하세요 : ");
            roomNum = Integer.parseInt(br.readLine());
            column = roomNum / 100;               // 층
            row    = roomNum % 100;               // 호수

            if(roomCheck[column][row]){
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
        String ran = "example";
        if(column == 1)
            if(row <= 5)
                roomMap.put(ran, new Deluxe(roomNum, "Twin", sukbak));
            else
                roomMap.put(ran, new Deluxe(roomNum, "Double", sukbak));
        else if(column == 2)
            if(row <= 5)
                roomMap.put(ran, new Superior(roomNum, "Twin + Double", sukbak));
            else
                roomMap.put(ran, new Superior(roomNum, "3 Twin", sukbak));
        else if(column == 3)
            roomMap.put(ran, new Family(roomNum, "2 King", sukbak));
        else if(column == 4)
            if(row <= 5)
                roomMap.put(ran, new Superior(roomNum, "King", sukbak));
            else
                roomMap.put(ran, new Superior(roomNum, "2 Double", sukbak));

    }


}
