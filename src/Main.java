import java.util.Hashtable;
//
public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        //Hashtable<String, Room> roomMap = inOut.fileIn1();       // 역직렬화로 Hashtable 가져오기
        //int[] stockArray = inOut.filein2();

        Hashtable<String, Room> roomMap = new Hashtable<>();
        int[] stockArray = {0, 0, 0, 0};        // 직렬화 해야함

        // 관리자모드 나와서 회원이냐 묻는 메소드 반복해야 함
        SemiAdmin admin = new SemiAdmin(stockArray);

        int S = admin.AdminRun(roomMap);                // SemiAdmin custommode에서 선택결과
        String reNum;                                   // 객실 선택 후 부여된 예약 번호

        switch (S){
            case 1: Reserve_list reList = new Reserve_list(roomMap);
                    reList.reserve(); break;
            case 2: ReChooseRoom rechoose = new ReChooseRoom(roomMap);
                    reNum = rechoose.ReChooseRoomRun(); break;

        }


        //각각 분리하고 배열을 빼고 넣어주는 식



        inOut.fileOut1(roomMap);                                 // 추가한 Map 직렬화
        inOut.fileOut2(stockArray);
        inOut = null;
    }
}