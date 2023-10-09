import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Calendar;


class AdminMenus
{
    public static final int ADMIN_STOCK = 1;
    public static final int ADMIN_RESERVATION = 2;
    public static final int ADMIN_SELL = 3;
}

public class SemiAdmin
{
    static int adminSel;
    static int teethBrushPaste;
    static int roomSlipper;
    static int totalPrice;
    static int password;
    static int countReRoom;				// 당일 예약된 방을 보여주는 변수
    public Hashtable<String, Room> roomMap;

    public SemiAdmin(Hashtable<String, Room> roomMap) {
        this.roomMap = roomMap;
    }


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);

    // 메뉴 출력 메소드
    public static void menuDisp()
    {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                                  SS HOTEL ┃");
        System.out.println("┃ ┌────────────────────┐                    ┃");
        System.out.println("┃ │ administrator mode │                    ┃");
        System.out.println("┃ └────────────────────┘                    ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   1. 재고 관리                            ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   2. 예약 현황                            ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   3. 매출 현황                            ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    // 관리자 비밀번호 입력 메소드
    public void adminPassword()
    {
        try
        {
            do
            {
                System.out.print("관리자 비밀번호를 입력하세요 : ");

                password = Integer.parseInt(br.readLine());

                if (password != 1234)
                    System.out.println("관리자 비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
            }

            while (password != 1234);

        }
        catch (Exception e)
        {
            System.out.println("숫자 형태를 입력해주세요.");
            adminPassword();
            System.out.println();
        }
    }


    // 관리자 메뉴 선택 메소드
    public void adminSel()
    {
        try
        {
            do
            {
                System.out.print("이동하고자 하는 메뉴 번호를 입력해주세요 : ");
                adminSel = Integer.parseInt(br.readLine());
                System.out.println();
                if (adminSel < 1 || adminSel > 3)
                {
                    System.out.println("=== 1 ~ 3에 해당하는 번호를 입력해주세요 ===");
                    System.out.println();
                }
            }
            while (adminSel < 1 || adminSel > 3);
        }
        catch (Exception e)
        {
            System.out.println("숫자 형태를 입력해주세요.");
            adminSel();
        }

    }


    // 관리자 메뉴 선택에 따른 기능 호출 메소드

    public void adminRun(Hashtable<String, Room> roomMap) throws IOException
    {
        switch (adminSel)
        {
            case AdminMenus.ADMIN_STOCK : adminStock(); break;
            case AdminMenus.ADMIN_RESERVATION : adminReservation(roomMap); break;
            case AdminMenus.ADMIN_SELL : adminSell(roomMap); break;
        }
    }


    // 재고 관리 메소드
    public void adminStock()
    {

        stockTeeth();
        stockSlipper();
    }

    public void stockTeeth()
    {
        teethBrushPaste = roomSlipper = 0;

        System.out.printf("현재 치약&칫솔 재고 수량 : %d\n", teethBrushPaste);
        System.out.print("치약&칫솔의 재고를 보충하시겠습니까?(Y/N) : ");
        String a = sc.next();

        if (a.equals("Y") || a.equals("y"))
        {
            teethBrushPaste += 30;
            System.out.println();
            System.out.println("\n**** 치약&칫솔의 재고가 30개 보충되었습니다. ****");
            System.out.printf("보충 후 치약&칫솔의 재고 수량 : %d\n", teethBrushPaste);
            System.out.println();
        }

        else
            System.out.println("\n**** 치약&칫솔의 재고를 보충하지 않습니다. ****\n");
    }


    public void stockSlipper()
    {
        System.out.printf("현재 객실 슬리퍼의 재고 수량 : %d\n", roomSlipper);
        System.out.print("객실 슬리퍼의 재고를 보충하시겠습니까?(Y/N) : ");
        String b = sc.next();

        if (b.equals("Y") || b.equals("y"))
        {
            roomSlipper += 30;
            System.out.println();
            System.out.println("**** 객실 슬리퍼의 재고가 30개 보충되었습니다. ****");
            System.out.printf("보충 후 객실 슬리퍼의 재고 수량 : %d\n\n", roomSlipper);
            System.out.println();
        }
        else
            System.out.println("\n**** 객실 슬리퍼의 재고를 보충하지 않습니다. ****\n");

        System.out.println("**** 재고 셋팅이 완료되었습니다. ****");
        System.out.println();

        customerMode();
    }

    public void adminReservation(Hashtable<String, Room> roomMap) throws IOException
    {

        Iterator<String> it = roomMap.keySet().iterator();
        Calendar today = Calendar.getInstance();
        int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DATE)));

        while (it.hasNext())
        {
            String key = it.next();

            if (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate()))
            {
                countReRoom += 1 ;
            }
        }

        if (countReRoom == 0)
            System.out.print("당일 예약된 방이 없습니다.\n");
        else
            System.out.println("당일 예약된 객실의 수");
        System.out.println(countReRoom);

        System.out.println("관리자 모드 초기 화면으로 돌아가시겠습니까?(Y/N)");

        String a = sc.next();

        if (a.equals("Y") || a.equals("y"))
        {
            menuDisp();
            adminPassword();
            adminSel();
            adminRun(roomMap);
        }
        else
            System.out.println("프로그램을 종료합니다.");


    }

    public void adminSell(Hashtable<String, Room> roomMap) throws IOException
    {
        Iterator<String> it = roomMap.keySet().iterator();
        while(it.hasNext())
        {
            String key = it.next();
            totalPrice += roomMap.get(key).getRoomPrice();
        }
        System.out.println("현재 총 객실 매출");
        System.out.printf("%d원\n",totalPrice);

        System.out.println("관리자 모드 초기 화면으로 돌아가시겠습니까?(Y/N)");

        String a = sc.next();

        if (a.equals("Y") || a.equals("y"))
        {
            menuDisp();
            adminPassword();
            adminSel();
            adminRun(roomMap);
        }
        else
            System.out.println("프로그램을 종료합니다.");
    }

    public void customerMode()
    {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                                  SS HOTEL ┃");
        System.out.println("┃ ┌────────────────────┐                    ┃");
        System.out.println("┃ │ administrator mode │                    ┃");
        System.out.println("┃ └────────────────────┘                    ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   1. 키오스크 모드 전환                   ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   2. 예약 시스템 모드 전환                ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃   3. 관리자 모드 전환                     ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┃                                           ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

        try
        {
            int customerModeSel = Integer.parseInt(br.readLine());

            if (customerModeSel == 3)
            {
                menuDisp();
            }
            if(customerModeSel == 1){
                Reserve_list abc = new Reserve_list(roomMap);
                abc.reserve();
            }

        }
        catch (Exception e)
        {
            System.out.println("숫자 형태를 입력해주세요.");
        }

    }
}