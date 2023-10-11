import java.util.Hashtable;

public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        // 역직렬화로 Hashtable 가져오기
        Hashtable<String, Room> roomMap = inOut.fileIn1();                // 예약번호, 객실 객체
        int[] stockArray = inOut.fileIn2();                               // 호텔의 재고를 int형 배열로 나타냄
        Hashtable<String, Reserves> reGuest = inOut.fileIn3();            // 예약번호, 예약자 객체
        Hashtable<String, int[]> cusArray = inOut.fileIn4();              // 예약변호, 고객이 구매한 물건 수량을 표현한 int형 배열

        // stockArray에서는 빼야 함(구매되면)
        // 인원 * 숙박 일수 만큼 조식 디너 구매가능(마지막 날은 조식만 구매가능)
        // cusAmenity 에는 더해야 함(구매되면)



        // 자료구조 초기화해주기
//        Hashtable<String, Room> roomMap = new Hashtable<>();
//        Hashtable<String, Reserves> reGuest = new Hashtable<>();
//        int[] stockArray = {0, 0, 0, 0};        // 직렬화 해야함



        // 관리자모드 나와서 회원이냐 묻는 메소드 반복해야 함
        SemiAdmin admin = new SemiAdmin(stockArray,roomMap,reGuest);

        int S = admin.AdminRun(roomMap);                                 // SemiAdmin custommode에서 선택결과(1 = 키오스크 모드, 2 = 예약 시작)
        int R = 0;                                                       // reList.reserve() 반환값 담을 변수
        String reNum = "";                                               // 객실 선택 후 부여된 예약 번호

        // 각 클래스의 객체 생성
        Reserve_list reList = new Reserve_list(roomMap,reGuest);
        ChooseRoom choose = new ChooseRoom(roomMap);
        ReChooseRoom rechoose = new ReChooseRoom(roomMap);


        switch (S){  // SemiAdmin custommode에서 선택결과에 따라
            case 1: R = reList.reserve();                                // 1번이면 회원인지 예약인지 확인
                reNum = choose.ChooseRoomRun();     break;               // 확인한 후에 객실 구매로 이동 후 예약번호 받아옴
            case 2: reNum = choose.ChooseRoomRun(); break;           // 2번이면 예약 시작으로 이동 후 예약번호 받아옴
        }

        reList.setReNum(reNum);                                          // 예약번호 reList객체에 넣어주기
        reList.putInfo();                                                // 예약번호, 예약자 객체 생성

        System.out.println(reGuest.get(reNum));



        BuyAmenity buy = new BuyAmenity(roomMap, cusArray, stockArray ,reNum);
        buy.startProgram();






        // 파일 직렬화
        inOut.fileOut1(roomMap);
        inOut.fileOut2(stockArray);
        inOut.fileOut3(reGuest);
        inOut.fileOut4(cusArray);
        inOut = null;
    }
}