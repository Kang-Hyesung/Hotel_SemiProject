//import java.util.*;
//import java.io.*;
//import java.util.Enumeration;
//
//class Amenity_kinds {
//
//    String name;
//    int Amenity_Number;
//
//    public Amenity_kinds(String name, int Amenity_Number) {
//        this.name = name;
//        this.Amenity_Number = Amenity_Number;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAmenity_Number() {
//        return Amenity_Number;
//    }
//
//    public void setAmenity_Number(int Amenity_Number) {
//        this.Amenity_Number = Amenity_Number;
//    }
//
//    public String toString() {
//
//        return "상품명: " + name + ", 수량: " + Amenity_Number;
//    }
//}
//
//class plusMenus {
//
//    public static final int E_Amenity=1;	//어메니티
//    public static final int E_Breakfast=2;		// 조식 여부
//    public static final int E_Dinner_bar=3;		// 저녁 & 바
//    public static final int E_Check_Menu=4;		// 메뉴 확인
//    public static final int E_purchase_Menu=5;		// 장바구니에서 사기
//
//}
//
//public class BuyAmenity {
//
//    // 주요 속성 구성 --> 완료
//    private static final Vector<Amenity_kinds> vt;   //어메니티 벡터
//    private static BufferedReader br;				//입력시 활용
//    private static Integer sel;						// 선택 값
//    private static String con;						// 계속 진행여부
//    private static Integer count;					// 빼는 수량의 수
//
//    private static String breakfastcheck;			// 조식 여부
//    private static boolean hasBreakfast;			// 조식을 이미 선택했는지 여부
//
//    private static String dinner_bar;				// 디너 & 바 여부
//    private static boolean hasdinner_bar;			// 디너 & 바를 이미 선택했는지 여부
//    private static boolean itemFound;				// 벡터가 안에 있는지 없는지
//    private static int num;							// 몇개를 고를건지에 대한 수량
//
//    private static String itemName;					// 상품 입력
//
//    // Static 초기화 블럭
//    static {
//        //Vector 자료구조 생성
//        vt = new Vector<Amenity_kinds>(); //백터 생성
//        br = new BufferedReader(new InputStreamReader(System.in));
//
//        // 사용자 입력값 초기화
//        count =1;
//        sel =1;
//        con=" ";
//        itemName = " ";
//        hasBreakfast = false;
//        breakfastcheck = "조식 안먹는걸로 할게용";
//        num = 1;
//
//        dinner_bar = "디너 안먹는걸로 할게용";
//        hasdinner_bar = false;
//
//        itemFound = false;
//    }
//
//    public static void menuSelect() throws IOException {
//
//        try {
//            do {
//                sel = Integer.parseInt(br.readLine());
//                return;
//            } while (sel < 1 || sel > 6);
//        } catch (NumberFormatException e) {
//            System.out.println("숫자형태로 입력해주세용");
//            menuDisp();
//            menuSelect();
//
//        }
//    }
//
//    // 메뉴 출력 메소드
//    public static void menuDisp() {
//        System.out.println("[장바구니]================");
//        System.out.println("1.어메니티 추가");
//        System.out.println("2.조식 여부");
//        System.out.println("3.디너 & Bar 이용 여부");
//        System.out.println("4.선택한 장바구니 확인하기");
//        System.out.println("5.구매하기");
//        System.out.println("6.종료");
//        System.out.println("========================");
//        System.out.print("메뉴선택 (1~6) : ");
//    }
//
//    // 선택된 메뉴 실행에 따른 기능 호출 메소드
//    public static void menuRun() throws IOException{
//
//        switch (sel){
//            case plusMenus.E_Amenity : addAmenity(); break;
//            case plusMenus.E_Breakfast : ChBreakfast(); break;
//            case plusMenus.E_Dinner_bar : ChDinner_bar(); break;
//            case plusMenus.E_Check_Menu : Check_finalMenu(); break;
//            case plusMenus.E_purchase_Menu : purchaseMenu(); break;
//            case 6 : exit(); break;
//        }
//    }
//
//    public static boolean isValidAmenity(String itemName) {
//        // 유효한 항목 리스트
//        String[] validItems = {"a", "b", "c", "d"};
//
//        // 입력값이 유효한 항목인지 확인
//        for(String item : validItems) {
//            if(item.equalsIgnoreCase(itemName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void addAmenity() throws IOException {
//
//        do {
//            System.out.println("칫솔, 치약, 면도기, 샤워타월, 실내슬리퍼 중 하나를 입력하세요.");
//            System.out.print("원하시는 상품을 입력하세요. : ");
//
//            itemName = br.readLine();
//
//            //입력값이 유효한 지 확인
//            if(isValidAmenity(itemName)) {
//
//                System.out.print("원하는 수량을 입력하세요 : ");
//
//                try {
//                    num = Integer.parseInt(br.readLine());
//
//                } catch (NumberFormatException e) {
//
//                    System.out.println("숫자형태로 입력해주세용");
//                    addAmenity();
//                }
//
//                Amenity_kinds amenity = new Amenity_kinds(itemName, num);
//                vt.add(amenity);
//
//                System.out.print("계속 할거임?(Y/N) : ");
//                con = br.readLine().toUpperCase();
//            }
//
//            else {
//                System.out.println("그런 상품은 없는데용");
//            }
//
//        }while(con.equals("Y"));
//    }
//
//    public static void ChBreakfast() throws IOException { //조식 먹을지 말지에 대한 메서드
//
//        //인원수에 따라 제한해야되기 때문에 일단 이렇게 설정
//        if (hasBreakfast) {
//            System.out.println("이미 조식 먹는다고 했는딩");
//            return;
//        }
//
//        System.out.print("조식을 먹을건가용? (Y/N): ");
//        con = br.readLine().toUpperCase();
//
//        if (con.equals("Y")) {
//            breakfastcheck = "조식 먹음";
//            hasBreakfast = true;
//            System.out.println("조식 먹음!");
//        } else {
//            breakfastcheck = "조식 안먹음";
//            System.out.println("조식 안먹음!");
//        }
//    }
//
//    public static void ChDinner_bar() throws IOException { //저녁을 먹을건지 말건지에 대한 메서드
//
//        //인원수에 따라 제한해야되기 때문에 일단 이렇게 설정
//        if (hasdinner_bar) {
//            System.out.println("이미 디너 & 바 먹는다고 했는딩");
//            return;
//        }
//        System.out.print("디너&바를 먹을건가용? (Y/N): ");
//        con = br.readLine().toUpperCase();
//
//        if (con.equals("Y")) {
//            dinner_bar = "디너 & 바 먹음";
//            hasdinner_bar = true;
//            System.out.println("디너 & 바 먹음!");
//        } else {
//            dinner_bar = "디너 & 바 안먹음";
//            System.out.println("디너 & 바 안먹음!");
//        }
//    }
//
//    public static void strCheck_Menu() throws IOException {
//
//        if (!vt.isEmpty()){
//            System.out.print("빼고 싶은 물품이 있나용?(Y/N): ");
//            con = br.readLine().toUpperCase();
//
//            if (con.equals("Y")) {
//                System.out.print("물품 이름을 입력하세요: ");
//                itemName = br.readLine();
//
//                for (int i = 0; i < vt.size(); i++) {
//                    Amenity_kinds amenity = vt.get(i);
//                    if (amenity.name.equals(itemName)) {
//                        System.out.print("몇개 뺄건뎅??: ");
//                        count = Integer.parseInt(br.readLine());
//
//                        // 뺄 수량 확인
//                        if (count > 0 && count <= amenity.Amenity_Number) {
//                            // 저장되어 있는 수량에서 입력한 수만큼 빼기
//                            amenity.Amenity_Number -= count;
//                        } else {
//                            System.out.println("우린 그만큼 없는데용.");
//                        }
//
//                        if (amenity.Amenity_Number == 0) {
//                            vt.remove(i);
//                            i--; // 제거했으므로 남은 공간 없애기
//                        }
//                        itemFound = true;
//                        break;
//                    }
//                }
//            }
//        }
//        food_Menu();
//    }
//
//    public static void food_Menu() throws IOException {
//
//        if (hasBreakfast == true) {
//            System.out.println("조식도 뺄까용?(Y/N)");
//            con = br.readLine().toUpperCase();
//
//            if (con.equals("Y")) {
//                hasBreakfast = false;
//                System.out.println("조식 뺏어용");
//                breakfastcheck = "조식 안먹음";
//            }
//        }
//
//        if (hasdinner_bar == true) {
//            System.out.println("디너 & 바도 뺄까용?(Y/N)");
//            con = br.readLine().toUpperCase();
//
//            if (con.equals("Y")) {
//                hasdinner_bar = false;
//                System.out.println("디너 & 바 뺏어용");
//                dinner_bar = "디너 & 바 안먹음";
//            }
//        }
//    }
//
//    public static void Check_Menu() throws IOException { // 장바구니 확인 메서드
//        System.out.println("선택한 장바구니 내역:");
//
//        if (vt.isEmpty()) {
//            System.out.println("선택한 상품이 없는데용");
//        }
//
//        for (Amenity_kinds str : vt) {
//            System.out.println(str);
//        }
//
//        System.out.println("선택한 조식 여부: " + breakfastcheck);
//        System.out.println("선택한 디너 & 바 여부: " + dinner_bar);
//
//        if (!itemFound && !vt.isEmpty()) {
//            System.out.println("그런 물품은 없는데용?");
//        }
//    }
//
//    public static void Check_finalMenu() throws IOException {
//
//        Check_Menu();
//        strCheck_Menu();
//
//    }
//
//    public static void purchaseMenu() throws IOException{
//        System.out.println("구매하시는 물품이 아래와 같이 같습니까?");
//
//        if (vt.isEmpty() && hasBreakfast == false && hasdinner_bar == false) {
//            System.out.println("선택한 메뉴 없음");
//            return;
//        } else {
//
//            for (Amenity_kinds str : vt) {
//                System.out.println(str);
//                itemName = str.name; // 아이템 이름 가져오기
//
//                // 'a'인 경우
//                if (itemName.equals("a")) {
//                    if (SemiAdmin.roomSlipper > 0) {
//                        System.out.printf("a를 %d개 구매합니다.",str.Amenity_Number);
//
//                        System.out.print("구매하시겠습니까?(Y/N) : ");
//                        con = br.readLine().toUpperCase();
//
//                        SemiAdmin.roomSlipper -= str.Amenity_Number; // 아이템 수량만큼 뺌
//                    } else {
//                        System.out.println("a 재고가 없어 구매할 수 없습니다.");
//                    }
//                }
//                // 'b'인 경우
//                else if (itemName.equals("b")) {
//                    if (SemiAdmin.teethBrushPaste > 0) {
//                        System.out.printf("b를 %d개 구매합니다.",str.Amenity_Number);
//
//                        System.out.print("구매하시겠습니까?(Y/N) : ");
//                        con = br.readLine().toUpperCase();
//
//                        SemiAdmin.teethBrushPaste -= str.Amenity_Number; // 아이템 수량만큼 뺌
//
//                    } else {
//                        System.out.println("b 재고가 없어 구매할 수 없습니다.");
//                    }
//                }
//            }
//
//            if (hasBreakfast == true) {
//                System.out.println("선택한 조식 여부: " + breakfastcheck);
//                System.out.print("구매하시겠습니까?(Y/N) : ");
//                con = br.readLine().toUpperCase();
//            }
//            if (hasdinner_bar == true) {
//                System.out.println("선택한 디너 & 바 여부: " + dinner_bar);
//                System.out.print("구매하시겠습니까?(Y/N) : ");
//                con = br.readLine().toUpperCase();
//            }
//        }
//    }
//
//    // 프로그램 종료 메소드 => 지금은 그렇지만 합칠때는 돌아가기로 구현 할 것
//    public static void exit() {
//        System.out.print("\n프로그램 종료");
//        System.exit(-1);
//    }
//
//    public static void main(String[] args) throws IOException, NumberFormatException {
//
//        do{
//            menuDisp();
//            menuSelect();
//            menuRun();
//        } while (true);
//
//    }
//}