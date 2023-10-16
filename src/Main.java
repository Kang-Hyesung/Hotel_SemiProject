import java.util.Hashtable;

public class Main{
    static int CODE;
    public static void main(String[] args) throws Exception {
        while (true) {
            FileInputOutput inOut = new FileInputOutput();
            // 역직렬화로 데이터 가져오기
            Hashtable<String, Room> roomMap = inOut.fileIn1();                // 예약번호, 객실 객체
            int[] stockArray = inOut.fileIn2();                               // 호텔의 재고를 int형 배열로 나타냄
            Hashtable<String, Reserves> reGuest = inOut.fileIn3();            // 예약번호, 예약자 객체
            Hashtable<String, int[]> cusArray = inOut.fileIn4();              // 예약변호, 고객이 구매한 물건 수량을 표현한 int형 배열


            // 관리자모드 나와서 회원이냐 묻는 메소드 반복해야 함
            Firstprint first = new Firstprint();
            first.FirstRun();
            SemiAdmin admin = new SemiAdmin(stockArray, roomMap, reGuest);

            int S = admin.AdminRun(roomMap);                                 // SemiAdmin custommode에서 선택결과(1 = 예약 조회 2 = 현장 구매 3= 어매니티 식사 구매)
            inOut.fileOut1(roomMap);
            inOut.fileOut2(stockArray);
            inOut.fileOut3(reGuest);
            inOut.fileOut4(cusArray);
            CODE = 0;

            while (CODE == 0) {

                roomMap = inOut.fileIn1();                // 예약번호, 객실 객체
                stockArray = inOut.fileIn2();                               // 호텔의 재고를 int형 배열로 나타냄
                reGuest = inOut.fileIn3();            // 예약번호, 예약자 객체
                cusArray = inOut.fileIn4();              // 예약변호, 고객이 구매한 물건 수량을 표현한 int형 배열

                int R = 0;                                                       // reList.reserve() 반환값 담을 변수
                String reNum = "";                                               // 객실 선택 후 부여된 예약 번호

                if (S != 3)
                    S = admin.kioskMode();                                       // SemiAdmin custommode에서 선택결과(1 = 예약 조회 및 현장 구매 2= 어매니티 식사 구매)

                // 각 클래스의 객체 생성
                Reserve_list reList = new Reserve_list(roomMap, reGuest);
                ChooseRoom choose = new ChooseRoom(roomMap);
                ReChooseRoom rechoose = new ReChooseRoom(roomMap);
                BuyAmenity buy;
                Pay pay;
                //reserve에서 회원이면 회원정보를 예약자 객체로 넘겨야 함. and 결제할 때 5%할인


                switch (S) {  // SemiAdmin custommode에서 선택결과에 따라
                    case 1:
                        String[] outRenum = reList.reserve();               // S가 1번이면 예약인지 확인 후 어매니티 구매로 이동
                        R = Integer.parseInt(outRenum[0]);
                        reNum = outRenum[1];
                        // 예약 없을 때만 chooseRoom가도록 변수 받아와야함
                        if (R == 1) {
                            buy = new BuyAmenity(roomMap, cusArray, stockArray, reNum);
                            buy.startProgram();                              // 확인하고 어매니티 구매로 이동
                            pay = new Pay(roomMap, cusArray, stockArray, reNum);
                            pay.payAmnity();
                            break;

                        } else if (R == 2) {
                            reNum = choose.ChooseRoomRun();                         // 확인한 후에 객실 구매로 이동 후 예약번호 받아옴
                            buy = new BuyAmenity(roomMap, cusArray, stockArray, reNum);
                            buy.startProgram();                                          // 확인하고 어매니티 구매로 이동
                            reList.setReNum(reNum);
                            reList.putMemberInfo(reNum);                             // 회원인 경우) 예약번호, 예약자 객체 생성
                            pay = new Pay(roomMap, cusArray, stockArray, reNum);
                            pay.payRoom();
                            break;

                        } else if (R == 3) {
                            reNum = choose.ChooseRoomRun();                         // 확인한 후에 객실 구매로 이동 후 예약번호 받아옴
                            buy = new BuyAmenity(roomMap, cusArray, stockArray, reNum);
                            buy.startProgram();                                          // 확인하고 어매니티 구매로 이동
                            reList.setReNum(reNum);
                            reList.putInfo();                                               // 비회원인 경우) 예약번호, 예약자 객체 생성
                            pay = new Pay(roomMap, cusArray, stockArray, reNum);
                            pay.payRoom();
                            break;
                        }

                    case 2:
                        reNum =  reList.reserveCheck();                              // 예약번호 확인
                        buy = new BuyAmenity(roomMap, cusArray, stockArray, reNum);
                        buy.startProgram();
                        pay = new Pay(roomMap, cusArray, stockArray, reNum);
                        pay.payAmnity();
                        break;          // 확인하고 어매니티 구매로 이동

                    // 예약하는 경우
                    case 3:
                        R = reList.member();                                // 회원인지 확인 후 객실 예약으로 이동
                        reNum = rechoose.ReChooseRoomRun();                 // 예약으로 이동
                        if (R == 2) {
                            reList.setReNum(reNum);
                            reList.putMemberInfo(reNum);                        // 회원인 경우) 예약번호, 예약자 객체 생성
                            pay = new Pay(roomMap, cusArray, stockArray, reNum);
                            pay.payRoomOnly();
                            S = 0;
                            break;
                        } else if (R == 3) {
                            reList.setReNum(reNum);
                            reList.putInfo();                                  // 비회원인 경우) 예약번호, 예약자 객체 생성
                            pay = new Pay(roomMap, cusArray, stockArray, reNum);
                            pay.payRoomOnly();
                            S = 0;
                            break;
                        }
                }

                // 파일 직렬화
                inOut.fileOut1(roomMap);
                inOut.fileOut2(stockArray);
                inOut.fileOut3(reGuest);
                inOut.fileOut4(cusArray);
            }
            inOut.fileOut1(roomMap);
            inOut.fileOut2(stockArray);
            inOut.fileOut3(reGuest);
            inOut.fileOut4(cusArray);
        }
    }
}