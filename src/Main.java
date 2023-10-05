import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Calendar;

public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        Hashtable<String, Room> roomMap = inOut.fileIn();       // 역직렬화로 Hashtable 가져오기

        //Hashtable<String, Room> roomMap = new Hashtable<>();

        ChooseRoom chooseRoom = new ChooseRoom();

        roomMap = chooseRoom.buyRoom(roomMap);					// void 형으로 바꿔도 될 듯
        // 날짜 비교해 빈 방 만들고 구매된 방 채운 후 방 구매

        inOut.fileOut(roomMap);                                 // 추가한 Map 직렬화
        inOut = null;

    }
}

/*
{객실 번호=103, 등급=Deluxe, 침대 타입=Twin, 최대인원수=2, 객실 가격=150000}
숙박 시작 : 2023년 07월 13일
숙박 종료 : 2023년 07월 17일false

{객실 번호=102, 등급=Deluxe, 침대 타입=Double, 최대인원수=2, 객실 가격=150000}
숙박 시작 : 2023년 07월 04일
숙박 종료 : 2023년 07월 06일false

{객실 번호=104, 등급=Deluxe, 침대 타입=Twin, 최대인원수=2, 객실 가격=150000}
숙박 시작 : 2023년 08월 13일
숙박 종료 : 2023년 08월 17일false

{객실 번호=101, 등급=Deluxe, 침대 타입=Twin, 최대인원수=2, 객실 가격=150000}
숙박 시작 : 2023년 09월 04일
숙박 종료 : 2023년 09월 05일true
*/