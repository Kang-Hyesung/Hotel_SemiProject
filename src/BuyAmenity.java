import java.util.*;
import java.io.*;
import java.util.Enumeration;

class Amenity_kinds {

    String name;
    int Amenity_Number;

    public Amenity_kinds(String name, int Amenity_Number) {
        this.name = name;
        this.Amenity_Number = Amenity_Number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmenity_Number() {
        return Amenity_Number;
    }

    public void setAmenity_Number(int Amenity_Number) {
        this.Amenity_Number = Amenity_Number;
    }

    public String toString() {

        return "상품명: " + name + ", 수량: " + Amenity_Number;
    }
}

class plusMenus {

    public static final int E_Amenity=1;	//어메니티
    public static final int E_Breakfast=2;		// 조식 여부
    public static final int E_Dinner_bar=3;		// 저녁 & 바
    public static final int E_Check_Menu=4;		// 메뉴 확인
    public static final int E_purchase_Menu=5;		// 장바구니에서 사기

}

public class BuyAmenity {
    int[] stockArray;
    String reNum;
    int inwon;
    Hashtable<String, Room> roomMap;

    Hashtable<String,int[]> cusArray;

    public BuyAmenity(Hashtable<String, Room> roomMap, Hashtable<String,int[]> cusArray,int[] stockArray ,String reNum) {
        this.stockArray = stockArray; //호텔 재고
        this.reNum = reNum;
        this.roomMap = roomMap;
        inwon = Integer.parseInt(roomMap.get(reNum).getInwon()); // 숙박 인원
        this.cusArray = cusArray; //소비자가 담은 재고량
    }

    // 주요 속성 구성 --> 완료
    private Vector<Amenity_kinds> vt;   //어메니티 벡터
    private BufferedReader br;			//입력시 활용
    private Integer sel;					// 선택 값
    private String con;					// 계속 진행여부
    private String back;					// 뒤로갈건지에 대한 여부
    private Integer count; // 빼는 수량의 수

    private String breakfastcheck;		// 조식 여부
    private boolean hasBreakfast; // 조식을 이미 선택했는지 여부

    private String dinner_bar;		// 디너 & 바 여부
    private boolean hasdinner_bar; // 디너 & 바를 이미 선택했는지 여부

    private boolean itemFound; // 벡터가 안에 있는지 없는지

    private int num; // 몇개를 고를건지에 대한 수량

    private int dayMeal; // 날 수를 입력 받는 변수
    private int people; // 몇 명이서 먹을지에 대한 변수

    private int total; // 식권을 총 사용할 수 있는 개수

    private int n;

    // private int subitem; // 식사권을 몇개를 뺄 건지

    //private int[] brinwontotal = new int [10]; // 하루 다녀간 아침 총인원 아니면 날짜별로 기록할지?
    //private int[] deinwontotal = new int [10]; // 하루 다녀간 저녁 총인원 아니면 날짜별로 기록할지는 물어봐야알듯!

    private int[] cusAmenity = new int[6]; //소비자가 담은 변수

    // [0] = 치약 칫솔
    // [1] = 샤워타올
    // [2] = 면도기
    // [3] = 슬리퍼




    private  String itemName; // 상품 입력

    // Static 초기화 블럭
    {
        //Vector 자료구조 생성
        vt = new Vector<Amenity_kinds>(); //백터 생성
        br = new BufferedReader(new InputStreamReader(System.in));

        // 사용자 입력값 초기화
        count =1;
        sel =1;
        con=" ";
        itemName = " ";
        hasBreakfast = false;
        breakfastcheck = "조식을 선택하지 않았습니다.";
        num = 1;

        dinner_bar = "디너 & 바를 신청하지 않았습니다.";
        hasdinner_bar = false;

        itemFound = false;

    }

    public void menuSelect() throws IOException {

        try {
            do {
                sel = Integer.parseInt(br.readLine());
                return;
            } while (sel < 1 || sel > 6);
        } catch (NumberFormatException e) {
            System.out.println("숫자형태로 입력해주세용");
            menuDisp();
            menuSelect();
        }
    }

    // 메뉴 출력 메소드
    public void menuDisp() {
        System.out.println("[장바구니]================");
        System.out.println("1.어메니티 추가");
        System.out.println("2.조식 여부");
        System.out.println("3.디너 & Bar 이용 여부");
        System.out.println("4.선택한 장바구니 확인하기");
        System.out.println("5.종료");
        System.out.println("========================");
        System.out.print("메뉴선택 (1~5) : ");
    }

    // 선택된 메뉴 실행에 따른 기능 호출 메소드
    public void menuRun() throws IOException{

        switch (sel){
            case plusMenus.E_Amenity : addAmenity(); break;
            case plusMenus.E_Breakfast : ChBreakfast(); break;
            case plusMenus.E_Dinner_bar : ChDinner_bar(); break;
            case plusMenus.E_Check_Menu : Check_finalMenu(); break;
            case 5 : exit(); break;
        }
    }
    // String[] validItems = {"칫솔치약", "샤워타올", "면도기", "슬리퍼"};
    public boolean isValidAmenity(String itemName) {
        // 유효한 항목 리스트
        String[] validItems = {"칫솔치약", "샤워타올", "면도기", "슬리퍼"};

        // 입력값이 유효한 항목인지 확인
        for(String item : validItems) {
            if(item.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

//    public void addAmenity() throws IOException {
//
//        do {
//            System.out.print("칫솔치약, 샤워타올, 면도기, 슬리퍼 중 하나를 입력하세요 : ");
//            itemName = br.readLine();
//
//            if (itemName.equals("V")) {
//                break;
//            }
//            //입력값이 유효한 지 확인
//            if(isValidAmenity(itemName)) {
//                System.out.print("원하는 수량을 입력하세요 : ");
//                try {
//                    num = Integer.parseInt(br.readLine());
//                } catch (NumberFormatException e) {
//                    System.out.println("숫자형태로 입력해주세요");
//                    addAmenity();
//                }
//
//                boolean itemTrue = false;
//
//                for (Amenity_kinds amenity : vt) {
//                    if (amenity.name.equalsIgnoreCase(itemName)) {
//                        amenity.Amenity_Number += num;
//
//                        if (itemName.equals("칫솔치약")){
//                            cusAmenity[0] += num;
//                        } else if (itemName.equals("샤워타올")){
//                            cusAmenity[1] += num;
//                        } else if (itemName.equals("면도기")){
//                            cusAmenity[2] += num;
//                        } else if (itemName.equals("슬리퍼")){
//                            cusAmenity[3] += num;
//                        }
//                        itemTrue = true;
//                        break;
//                    }
//                }
//
//                if (!itemTrue) {
//                    Amenity_kinds amenity = new Amenity_kinds(itemName, num);
//                    vt.add(amenity);
//                }
//                System.out.print("계속 추가하시겠습니까?(Y/N) : ");
//                con = br.readLine().toUpperCase();
//            }
//            else {
//                System.out.println("선택하신 상품이 없습니다.");
//                return;
//            }
//
//        }while(con.equals("Y"));
//    }

    public void addAmenity2() throws IOException{
        System.out.print("칫솔치약, 샤워타올, 면도기, 슬리퍼 중 하나를 입력하세요 : ");
        System.out.println("0 1 2 3");
        int i = Integer.parseInt(br.readLine());
        System.out.print("추가할 숫자를 입력하세요");
        int j = Integer.parseInt(br.readLine());

        switch (i){
            case 0: cusAmenity[0] += j;
            case 1: cusAmenity[1] += j;
            case 2: cusAmenity[2] += j;
            case 3: cusAmenity[3] += j;
        }
    }
    public void ChBreakfast() throws IOException {
        if (cusAmenity[4] == inwon*roomMap.get(reNum).getDays()) {
            System.out.println("이미 모든 인원이 조식을 선택하셨습니다.");
            return;
        }

        System.out.print("조식 식권을 신청하시겠습니까? (Y/N) : ");
        do {
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {

                total = inwon*roomMap.get(reNum).getDays();

                System.out.printf("조식 식권을 몇개 살건지 입력하세요 [최대 %d]: ", total);
                dayMeal = Integer.parseInt(br.readLine()); // 원하는 일 수

                if (dayMeal + cusAmenity[4]> inwon*roomMap.get(reNum).getDays()) {
                    System.out.println("그 만큼의 식권을 추가 할 수 없습니다.");
                }  else {
                    breakfastcheck = "조식을 선택했습니다.";
                    cusAmenity[4] += dayMeal;
                    hasBreakfast = true;
                    System.out.println("조식 사용권 : " +dayMeal);
                }
            } else if (con.equals("V")) {
                break;
            } else {
                breakfastcheck = "조식을 선택하지 않았습니다.";
                System.out.println(breakfastcheck);
            }
        } while (con.length() <= 0);
    }


    public void ChDinner_bar() throws IOException {
        if (cusAmenity[5] == inwon*roomMap.get(reNum).getDays()) {
            System.out.println("이미 모든 인원이 디너 & 바를 선택하셨습니다.");
            return;
        }

        System.out.print("디너 & 바 식권을 신청하시겠습니까? (Y/N) : ");
        do {
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                total = inwon*roomMap.get(reNum).getDays();
                System.out.printf("디너 & 바 식권을 몇개 살건지 입력하세요 [최대 %d]: ",total);

                dayMeal = Integer.parseInt(br.readLine()); // 원하는 일 수

                if (dayMeal +cusAmenity[5] > inwon*roomMap.get(reNum).getDays()) {
                    System.out.println("그 만큼의 식권을 추가 할 수 없습니다.");
                }  else {
                    dinner_bar = "디너 & 바를 선택했습니다.";
                    cusAmenity[5] += dayMeal;
                    hasdinner_bar = true;
                    System.out.println("디너 & 바 사용권 : " +dayMeal);
                }
            } else if (con.equals("V")) {
                break;
            } else {
                dinner_bar = "디너 & 바를 선택하지 않았습니다.";
                System.out.println(dinner_bar);
            }
        } while (con.length() <= 0);
    }

    public void strCheck_Menu() throws IOException {

        if (!vt.isEmpty()){
            System.out.print("빼고 싶은 물품이 있습니까?(Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("물품 이름을 입력하세요 : ");
                itemName = br.readLine();

                for (int i = 0; i < vt.size(); i++) {
                    Amenity_kinds amenity = vt.get(i);
                    if (amenity.name.equals(itemName)) {
                        System.out.print("몇개를 빼시겠습니까? : ");
                        count = Integer.parseInt(br.readLine());

                        // 뺄 수량 확인
                        if (count > 0 && count <= amenity.Amenity_Number) {
                            // 저장되어 있는 수량에서 입력한 수만큼 빼기
                            amenity.Amenity_Number -= count;
                            // if (itemName.equals("치약칫솔")){ // 치약 칫솔 제거
                            //     stockArray[0] += count;
                            //} else if (itemName.equals("샤워타올")){ // 샤워타올 제거
                            //  stockArray[1] += count;
                            //} else if (itemName.equals("면도기")){ // 면도기 제거
                            //  stockArray[2] += count;
                            //} else if (itemName.equals("슬리퍼")){ // 슬리퍼 제거
                            //  stockArray[3] += count;
                            //}
                        } else {
                            System.out.println("그 만큼의 수량을 선택하지 않았습니다.");
                        }

                        if (amenity.Amenity_Number == 0) {
                            vt.remove(i);
                            i--; // 제거했으므로 남은 공간 없애기
                        }
                        itemFound = true;
                        break;
                    }
                }

                if (!itemFound && !vt.isEmpty()) {
                    System.out.println("선택하신 상품이 아닙니다.");
                }
            }
        }
        food_Menu();
    }

    public void food_Menu() throws IOException {
        if (hasBreakfast) {
            System.out.print("조식을 제외하시겠습니까? (Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("조식 사용권 몇개를 삭제하시겠습니까? : ");
                dayMeal = Integer.parseInt(br.readLine());

                if (dayMeal <= cusAmenity[4]) {
                    cusAmenity[4] -= dayMeal; //하루동안 먹은 조식 총 인원
                    System.out.printf("조식을 %d만큼 제외했습니다.\n",dayMeal);

                    if (cusAmenity[4] == 0){
                        System.out.println("조식을 전부 제외했습니다.");
                        breakfastcheck = "조식을 선택하지 않았습니다";
                        hasBreakfast = false;
                    }

                } else {
                    System.out.println("그 이상 만큼 제외 할 수 없습니다.");
                    return;
                }
            }
        }

        if (hasdinner_bar) {
            System.out.print("디너 & 바를 제외하시겠습니까? (Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("며칠 분을 제외하시겠습니까? : ");
                dayMeal = Integer.parseInt(br.readLine());

                if (dayMeal <= cusAmenity[5]) {
                    cusAmenity[5] -= dayMeal; //하루동안 디너 & 바 먹은 총 인원
                    System.out.printf("디너 & 바를 %d만큼 제외했습니다.\n",dayMeal);

                    if (cusAmenity[5] == 0){
                        System.out.println("디너 & 바를 전부 제외했습니다.");
                        dinner_bar = "디너 & 바를 신청하지 않았습니다.";
                        hasdinner_bar = false;
                    }

                } else {
                    System.out.println("그 이상 만큼 제외 할 수 없습니다.");
                }
            }
        }
    }

    public void Check_Menu() throws IOException { // 장바구니 확인 메서드
        System.out.println("선택한 장바구니 내역:");

        if (vt.isEmpty()) {
            System.out.println("선택한 상품이 없습니다.");
        }

        for (Amenity_kinds str : vt) {
            System.out.println(str);
        }

        System.out.println("선택한 조식 여부: " + breakfastcheck + "조식 식권 수 : "+ cusAmenity[4]);
        System.out.println("선택한 디너 & 바 여부: " + dinner_bar+ "디너 & 바 식권 수 : "+ cusAmenity[5]);

    }

    public void Check_finalMenu() throws IOException {

        Check_Menu();
        strCheck_Menu();

    }
    // 프로그램 종료 메소드 => 지금은 그렇지만 합칠때는 돌아가기로 구현 할 것
    public void exit() {
        System.out.print("\n프로그램 종료");
        putAmenity();
        n=1;
    }

    public void startProgram() throws IOException, NumberFormatException {

        do{
            menuDisp();
            menuSelect();
            menuRun();
        } while (n==0);
    }
    public void putAmenity() {
        cusArray.put(reNum,cusAmenity);
    }
}