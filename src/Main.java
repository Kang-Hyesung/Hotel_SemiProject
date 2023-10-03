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