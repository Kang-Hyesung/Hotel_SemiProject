import java.util.Hashtable;

public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        Hashtable<String, Room> roomMap = inOut.fileIn();      // 역직렬화로 Hasttable 가져오기

        //Hashtable<String, Room> roomMap = new Hashtable<>();

        ChooseRoom chooseRoom = new ChooseRoom();

        chooseRoom.buyRoom(roomMap);
        // 날짜 비교해 빈 방 만들고 구매된 방 채운 후 방 구매

        inOut.fileOut(roomMap);                               // 추가한 Map 직렬화
        inOut = null;
    }
}

//{객실 번호=103, 등급=Deluxe, 침대 타입=Double, 최대인원수=2, 객실 가격=150000}
//숙박 시작 : 2023년 08월 24일
//숙박 종료 : 2023년 08월 28일false
//{객실 번호=103, 등급=Deluxe, 침대 타입=Twin, 최대인원수=2, 객실 가격=150000}
//숙박 시작 : 2023년 08월 15일
//숙박 종료 : 2023년 08월 18일false
//{객실 번호=101, 등급=Deluxe, 침대 타입=Twin, 최대인원수=2, 객실 가격=150000}
//숙박 시작 : 2023년 08월 19일
//숙박 종료 : 2023년 08월 27일false
//몇 박? : 1
//객실번호를 선택하세요 : 103
//침대 타입을 선택하세요 : Double
//{객실 번호=103, 등급=Deluxe, 침대 타입=Double, 최대인원수=2, 객실 가격=150000}
//숙박 시작 : 2023년 09월 03일
//숙박 종료 : 2023년 09월 04일true