import java.util.*;
import java.io.*;

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
        if (cusArray.get(reNum) == null){
            this.cusAmenity = new int [6];
        }
        else{
            this.cusAmenity = cusArray.get(reNum);
        }
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

    private int[] cusAmenity; //소비자가 담은 변수

    // [0] = 치약칫솔
    // [1] = 샤워타월
    // [2] = 면도기
    // [3] = 슬리퍼




    private  String itemName; // 상품 입력

    //Pay ppay = new Pay(roomMap, cusArray, stockArray, reNum);


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
            System.out.println("\t● 숫자형태로 입력해주세용");
            menuDisp();
            menuSelect();
        }
    }

    // 메뉴 출력 메소드
    public void menuDisp() {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃===========어메니티 및 식사 구매===========┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ 1. 추가 어메니티 구매                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ 2. 조식 이용권 구매                       ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ 3. 디너 & 바 이용권 구매                  ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ 4. 선택한 항목 및 수량 확인               ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ 5. 어메니티 및 식사 구매 종료             ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t● 이동하고자 하는 메뉴 번호를 입력해주세요 : ");
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
    // String[] validItems = {"칫솔치약", "샤워타올", "면도기", "객실 슬리퍼"};
    public boolean isValidAmenity(String itemName) {
        // 유효한 항목 리스트
        String[] validItems = {"칫솔치약", "샤워타올", "면도기", "객실 슬리퍼"};

        // 입력값이 유효한 항목인지 확인
        for(String item : validItems) {
            if(item.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void addAmenity() throws IOException {

        do {
            System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃ [칫솔치약, 샤워타올, 면도기, 객실 슬리퍼] ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃ 위 4가지 항목 중                          ┃");
            System.out.println("\t┃ 구매를 원하는 제품명 하나를 입력해 주세요.┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("\t● 제품명 입력 : ");
            itemName = br.readLine();

            if (itemName.equals("V")) {
                break;
            }
            //입력값이 유효한 지 확인
            if(isValidAmenity(itemName)) {
                System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("\t┃                                  SS HOTEL ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.printf("\t┃ ▷ 선택한 어메니티 : %7s          ┃\n", itemName);
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃ ▷ 구매를 원하는 수량을 입력해 주세요.    ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.print("\t● 수량 입력 (숫자 형태) : ");

                try {
                    num = Integer.parseInt(br.readLine());
                    if (itemName.equals("칫솔치약") && stockArray[0] - (num + cusAmenity[0]) <= 0 ){
                        System.out.println("\n\t*** 재고가 부족하여 구매가 불가합니다. 데스크에 문의 바랍니다. ***");
                        continue;
                    }
                    if (itemName.equals("샤워타월") && stockArray[1] - (num + cusAmenity[1]) <= 0 ){
                        System.out.println("\n\t*** 재고가 부족하여 구매가 불가합니다. 데스크에 문의 바랍니다. ***");
                        continue;
                    }
                    if (itemName.equals("면도기") && stockArray[2] - (num + cusAmenity[2]) <= 0 ){
                        System.out.println("\n\t*** 재고가 부족하여 구매가 불가합니다. 데스크에 문의 바랍니다. ***");
                        continue;
                    }
                    if (itemName.equals("객실 슬리퍼") && stockArray[3] - (num + cusAmenity[3]) <= 0 ){
                        System.out.println("\n\t*** 재고가 부족하여 구매가 불가합니다. 데스크에 문의 바랍니다. ***");
                        continue;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\n\t*** 숫자형태로 입력해 주세요. ***");
                    addAmenity();
                }

                boolean itemTrue = false;


                for (Amenity_kinds amenity : vt) {
                    if (amenity.name.equalsIgnoreCase(itemName)) {
                        amenity.Amenity_Number += num;
                        itemTrue = true;
                        break;
                    }
                }


                if (itemName.equals("칫솔치약") && stockArray[0] - (num + cusAmenity[0])>=0){
                    cusAmenity[0] += num;
                    stockArray[0] -= num;
                } else if (itemName.equals("샤워타올")&& stockArray[1] - (num + cusAmenity[1])>=0){
                    cusAmenity[1] += num;
                    stockArray[1] -= num;
                } else if (itemName.equals("면도기")&& stockArray[2] - (num + cusAmenity[2])>=0){
                    cusAmenity[2] += num;
                    stockArray[2] -= num;
                } else if (itemName.equals("객실 슬리퍼")&& stockArray[3] - (num + cusAmenity[3])>=0){
                    cusAmenity[3] += num;
                    stockArray[3] -= num;
                }
                else {
                    System.out.println("\t*** 재고가 부족합니다. ***");
                }

                if (!itemTrue) {
                    Amenity_kinds amenity = new Amenity_kinds(itemName, num);
                    vt.add(amenity);
                }

                System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("\t┃                                  SS HOTEL ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.printf("\t┃ ▷ 선택한 어메니티 : %7s          ┃\n", itemName);
                System.out.println("\t┃                                           ┃");
                System.out.printf("\t┃ ▷ 입력한 수량 : %3d개                    ┃\n", num);
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃ ▷ 추가 구매를 진행하시겠습니까?          ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.print("\t● 추가 구매 진행 여부 (Y/N) : ");
                con = br.readLine().toUpperCase();
            }
            else {
                System.out.println("\t*** 제품명을 다시 한번 확인해 주세요. ***");
                addAmenity();
            }

        }while(con.equals("Y"));
    }

    public void ChBreakfast() throws IOException {
        if (cusAmenity[4] == inwon*roomMap.get(reNum).getDays()) {
            System.out.println("\n\t*** 이미 모든 인원이 조식을 선택하셨습니다. ***");
            return;
        }

        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃    ▷ 조식 이용권을 구매하시겠습니까?     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃       (조식 이용권 1매 : 30,000원)        ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t● 조식 구매 여부 (Y/N) : : ");
        do {
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {

                total = inwon*roomMap.get(reNum).getDays();

                System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("\t┃                                  SS HOTEL ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃ ▷ 구매 원하시는 조식 이용권의            ┃");
                System.out.println("\t┃    수량을 입력해 주세요.                  ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.printf("\t┃    * 구매 가능한 최대 수량 : %2d           ┃\n", total);
                System.out.println("\t┃      ( 숙박 인원 * 숙박 일 수 )           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.print("\t● 조식 이용권 구매 수량 : : ");

                dayMeal = Integer.parseInt(br.readLine()); // 원하는 일 수

                if (dayMeal + cusAmenity[4]> inwon*roomMap.get(reNum).getDays()) {
                    System.out.println("\n\t*** 입력하신 수량은 구매 가능한 최대 수량을 초과하였습니다. ***");
                }  else {
                    breakfastcheck = "조식 이용권을 추가하였습니다.";
                    cusAmenity[4] += dayMeal;
                    hasBreakfast = true;
                    //System.out.println("조식 사용권 : " +dayMeal);
                }
            } else if (con.equals("V")) {
                break;
            } else {
                breakfastcheck = "\n\t** 조식을 선택하지 않았습니다. 이전 메뉴로 돌아갑니다. ***";
                System.out.println(breakfastcheck);
            }
        } while (con.length() <= 0);
    }


    public void ChDinner_bar() throws IOException {
        if (cusAmenity[5] == inwon*roomMap.get(reNum).getDays()) {
            System.out.println("\t● 이미 모든 인원이 디너 & 바를 선택하셨습니다.");
            return;
        }

        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  ▷ 디너 & 바 이용권을 구매하시겠습니까?  ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃     (디너 & 바 이용권 1매 : 50,000원)     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t● 디너 & 바 구매 여부 (Y/N) : : ");

        do {
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                total = inwon*roomMap.get(reNum).getDays();

                System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("\t┃                                  SS HOTEL ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃ ▷ 구매 원하시는 디너 & 바 이용권의       ┃");
                System.out.println("\t┃    수량을 입력해 주세요.                  ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.printf("\t┃    * 구매 가능한 최대 수량 : %2d        ┃\n", total);
                System.out.println("\t┃      ( 숙박 인원 * 숙박 일 수 )           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┃                                           ┃");
                System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.print("\t● 디너 & 바 이용권 구매 수량 : : ");

                dayMeal = Integer.parseInt(br.readLine()); // 원하는 일 수

                if (dayMeal +cusAmenity[5] > inwon*roomMap.get(reNum).getDays()) {
                    System.out.println("\n\t*** 입력하신 수량은 구매 가능한 최대 수량을 초과하였습니다. ***");
                }  else {
                    dinner_bar = "디너 & 바 이용권을 추가하였습니다.";
                    cusAmenity[5] += dayMeal;
                    hasdinner_bar = true;
                    //System.out.println("디너 & 바 사용권 : " +dayMeal);
                }
            } else if (con.equals("V")) {
                break;
            } else {
                dinner_bar = "\n\t*** 디너 & 바를 선택하지 않았습니다. 이전 메뉴로 돌아갑니다. ***";
                System.out.println(dinner_bar);
            }
        } while (con.length() <= 0);
    }

    public void strCheck_Menu() throws IOException {

        if (!vt.isEmpty()){
            System.out.print("\t● 장바구니 내역 중 삭제하고 싶은 항목이 있습니까? (Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("\n\t● 제품명을 입력해 주세요 : ");
                itemName = br.readLine();

                for (int i = 0; i < vt.size(); i++) {
                    Amenity_kinds amenity = vt.get(i);
                    if (amenity.name.equals(itemName)) {
                        System.out.print("\n\t● 취소하고 싶은 수량을 입력해 주세요. : ");
                        count = Integer.parseInt(br.readLine());

                        // 뺄 수량 확인
                        if (count > 0 && count <= amenity.Amenity_Number) {
                            // 저장되어 있는 수량에서 입력한 수만큼 빼기
                            amenity.Amenity_Number -= count;
                            // if (itemName.equals("칫솔치약")){ // 칫솔치약 제거
                            //     cusAmenity[0] += count;
                            //} else if (itemName.equals("샤워타올")){ // 샤워타올 제거
                            //		cusAmenity[1] += count;
                            //} else if (itemName.equals("면도기")){ // 면도기 제거
                            //		cusAmenity[2] += count;
                            //} else if (itemName.equals("슬리퍼")){ // 슬리퍼 제거
                            //		cusAmenity[3] += count;
                            //}
                            if (itemName.equals("칫솔치약")){
                                cusAmenity[0] -= count;
                                stockArray[0] += count;
                            } else if (itemName.equals("샤워타올")){
                                cusAmenity[1] -= count;
                                stockArray[1] += count;
                            } else if (itemName.equals("면도기")){
                                cusAmenity[2] -= count;
                                stockArray[2] += count;
                            } else if (itemName.equals("객실 슬리퍼")){
                                cusAmenity[3] -= count;
                                stockArray[3] += count;
                            }
                        } else {
                            System.out.println("\n\t*** 입력하신 수량은 장바구니에 담긴 수량을 초과하였습니다. ***");
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
                    System.out.println("\t● 선택하신 상품이 아닙니다.");
                }
            }
        }
        food_Menu();
    }

    public void food_Menu() throws IOException {
        if (hasBreakfast) {
            System.out.print("\n\t● 조식 이용권의 내역 취소를 원하십니까? (Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("\n\t● 장바구니 내에서 원하시는 취소 수량을 입력해 주세요. : ");
                dayMeal = Integer.parseInt(br.readLine());

                if (dayMeal <= cusAmenity[4]) {
                    cusAmenity[4] -= dayMeal; //하루동안 먹은 조식 총 인원
                    System.out.printf("\n\t*** 조식 이용권의 수량을 %d개 만큼 취소했습니다. ***",dayMeal);

                    if (cusAmenity[4] == 0){
                        System.out.println("\n\t*** 조식 이용권 수량이 전부 취소 되었습니다. ***");
                        breakfastcheck = "\t조식을 선택하지 않았습니다.";
                        hasBreakfast = false;
                    }

                } else {
                    System.out.println("\n\t*** 입력하신 수량은 장바구니에 담긴 수량을 초과하였습니다. ***");
                    food_Menu();
                }
            }
        }

        if (hasdinner_bar) {
            System.out.print("\n\t● 디너 & 바 이용권의 내역 취소를 원하십니까? (Y/N) : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y")) {
                System.out.print("\n\t● 장바구니 내에서 원하시는 취소 수량을 입력해 주세요. : ");
                dayMeal = Integer.parseInt(br.readLine());

                if (dayMeal <= cusAmenity[5]) {
                    cusAmenity[5] -= dayMeal; //하루동안 디너 & 바 먹은 총 인원
                    System.out.printf("\n\t*** 디너 & 바 이용권의 수량을 %d개 만큼 취소했습니다. ***",dayMeal);

                    if (cusAmenity[5] == 0){
                        System.out.println("\n\t*** 디너 & 바 이용권 수량이 전부 취소 되었습니다. ***");
                        dinner_bar = "\t  디너 & 바를 신청하지 않았습니다.";
                        hasdinner_bar = false;
                    }

                } else {
                    System.out.println("\n\t*** 입력하신 수량은 장바구니에 담긴 수량을 초과하였습니다. ***");
                }
            }
        }
    }

    public void Check_Menu() throws IOException { // 장바구니 확인 메서드
        System.out.println("\n\n\t========== 장바구니 내역 확인 ==========");
        System.out.println("\n\t[ 어메니티 ]\n");

        if (vt.isEmpty()) {
            System.out.println("\n\t* 선택한 상품이 없습니다.");
        }

        for (Amenity_kinds str : vt) {
            System.out.println("\t* "+str);
        }

        System.out.println("\n\n\t[ 식사권 ]");
        System.out.println("\n\t* 조식 이용권 : "+ cusAmenity[4] + " 매");
        System.out.println("\t* 디너 & 바 이용권: " + cusAmenity[5] + " 매");
        System.out.println("\n\t========================================");

    }

    public void Check_finalMenu() throws IOException {

        Check_Menu();
        strCheck_Menu();

    }
    public void exit() {
        // System.out.print("\n프로그램 종료");
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