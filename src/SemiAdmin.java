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
    public static final int ADMIN_EXIT = 4;
}


public class SemiAdmin
{
    private int adminSel;
    private int teethBrushPaste, roomSlipper, razor, showerTowel;
    private int totalPrice;
    private int password;
    private int countReRoomDeluxe;           // 당일 예약된 방을 보여주는 변수 (디럭스)
    private int countReRoomSuperior;            // 당일 예약된 방을 보여주는 변수 (슈페리어)
    private int countReRoomFamily;              // 당일 예약된 방을 보여주는 변수 (패밀리)
    private int countReRoomSuite;               // 당일 예약된 방을 보여주는 변수 (스위트)
    private int[] stockArray;      // 어메니티 재고를 담아둘 int형 배열
    // int 형 배열에 들어가는 재고 수량.
    // stockArray[0] = teethBrushPaste;
    // stockArray[1] = razor;
    // stockArray[2] = showerTowel;
    // stockArray[3] = roomSlipper;
    private int i;          // 메소드 이동을 위한 임의 변수
    private Calendar today = Calendar.getInstance();
    private int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DATE)));


    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private Scanner sc = new Scanner(System.in);
    private Hashtable<String, Room> roomMap;
    private Hashtable<String, Reserves> reGuest;

    public SemiAdmin(int[] stockArray, Hashtable<String, Room> roomMap, Hashtable<String, Reserves> reGuest)
    {
        this.stockArray = stockArray;
        this.roomMap = roomMap;
        this.reGuest = reGuest;
    }

    public int AdminRun(Hashtable<String, Room> roomMap) throws IOException
    {
        menuDisp();
        adminSel();
        adminRun(roomMap);

        return i;
    }

    // 관리자 비밀번호 입력창 출력 메소드 (초기 화면)
	/*
    public void adminPasswordDisp()
    {
        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ administrator mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃         *** 관리자 모드 실행 ***          ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃      관리자 비밀번호를 입력해주세요.      ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

    }
    public void password(){
        adminPasswordDisp();
        adminPassword();
    }
	*/
    // 관리자 비밀번호 입력 메소드
	/*
    public void adminPassword()
    {
        try
        {
            do
            {
                System.out.print("\t▶ 관리자 비밀번호를 입력하세요 : ");

                password = Integer.parseInt(br.readLine());
                //System.out.println();


                if (password != 1234)
					{
                    System.out.println("\t*** 관리자 비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요. ***\n");
					System.out.println();
                    adminPassword();
					}
                //System.out.println();
            }
            while (password != 1234);
        }
        catch (Exception e)
        {
            System.out.println("\t\t*** 숫자 형태를 입력해주세요.***\n");
            adminPassword();
        }
    }
	*/

    // 메뉴 출력 메소드
    public static void menuDisp()
    {
        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ administrator mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   1. 재고 관리                            ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   2. 객실 이용 현황                       ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   3. 매출 현황                            ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   4. 프로그램 종료                        ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    // 프로그램 종료 메소드
    public void adminExit()
    {
        System.exit(-1);
    }



    // 관리자 메뉴 선택 메소드
    public void adminSel()
    {
        try
        {
            do
            {
                System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");
                adminSel = Integer.parseInt(br.readLine());
                System.out.println();
                if (adminSel < 1 || adminSel > 4)
                {
                    System.out.println("\t*** 1 ~ 3에 해당하는 번호를 입력해주세요 ***");
                    System.out.println();
                }
            }
            while (adminSel < 1 || adminSel > 4);
        }
        catch (Exception e)
        {
            System.out.println("\n\t*** 숫자 형태를 입력해주세요 ***\n");
            adminSel();
        }
    }



    // 관리자 메뉴 선택에 따른 기능 호출 메소드


    public void adminRun(Hashtable<String, Room> roomMap) throws IOException
    {
        switch (adminSel)
        {
            case AdminMenus.ADMIN_STOCK : adminStock(); break;
            case AdminMenus.ADMIN_RESERVATION : adminReservationDisp(); break;
            case AdminMenus.ADMIN_SELL : adminSellDisp(); break;
            case AdminMenus.ADMIN_EXIT : adminExit(); break;
        }
    }


    // 재고 관리 메소드
    public int[] adminStock()
    {
        stockTeeth();
        return stockArray;
    }


    public void stockTeeth()
    {

        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃  ※ 현재 칫솔&치약 재고 수량 : %2d개       ┃\n" , stockArray[0]);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃    칫솔&치약의 재고를 보충하시겠습니까?   ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 칫솔&치약 재고 보충 여부 : ");
        String a = sc.next();
        if (a.equals("Y") || a.equals("y"))
        {
            stockTeethY();
        }
        else
            stockTeethN();
    }

    public void stockTeethY()
    {
        System.out.print("\t▶ 칫솔&치약 재고 보충 수량을 입력하세요. : ");
        try
        {
            int res = Integer.parseInt(br.readLine());
            stockArray[0] += res;
            System.out.println();

            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃ ┌────────────────────┐                    ┃");
            System.out.println("\t┃ │ stock setting mode │                    ┃");
            System.out.println("\t┃ └────────────────────┘                    ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃  [ 칫솔&치약 재고가 %2d개 보충되었습니다. ]┃\n" , res);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃  ※ 보충 후 칫솔&치약의 재고 수량 : %2d개  ┃\n", stockArray[0]);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println();
            stockRazor();
        }
        catch (Exception e)
        {
            System.out.println("\n\t*** 숫자 형태로 입력해주세요. ***\n");
            stockTeethY();
        }
    }

    public void stockTeethN()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ ※ 칫솔&치약의 재고를 보충하지 않습니다.  ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();

        stockRazor();
    }

    public void stockRazor() {
        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃  ※ 현재 면도기 재고 수량 : %2d개          ┃\n" , stockArray[1]);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃    면도기의 재고를 보충하시겠습니까?      ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 면도기 재고 보충 여부 : ");
        String a = sc.next();
        if (a.equals("Y") || a.equals("y"))
        {
            stockRazorY();
        }
        else
            stockRazorN();
    }

    public void stockRazorY()
    {
        System.out.print("\t▶ 면도기 재고 보충 수량을 입력하세요. : ");
        try
        {
            int res = Integer.parseInt(br.readLine());
            stockArray[1] += res;
            System.out.println();

            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃ ┌────────────────────┐                    ┃");
            System.out.println("\t┃ │ stock setting mode │                    ┃");
            System.out.println("\t┃ └────────────────────┘                    ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃   [ 면도기 재고가 %2d개 보충되었습니다. ]  ┃\n" , res);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃   ※ 보충 후 면도기의 재고 수량 : %2d개    ┃\n", stockArray[1]);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println();
            stockShowerTowel();
        }
        catch (Exception e)
        {
            System.out.println("\n\t*** 숫자 형태로 입력해주세요. ***\n");
            stockRazorY();
        }
    }

    public void stockRazorN()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  ※ 면도기의 재고를 보충하지 않습니다.    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();
        stockShowerTowel();
    }

    public void stockShowerTowel()
    {
        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃  ※ 현재 샤워타올 재고 수량 : %2d개        ┃\n" , stockArray[2]);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃    샤워타올의 재고를 보충하시겠습니까?    ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 샤워타올 재고 보충 여부 : ");
        String a = sc.next();
        if (a.equals("Y") || a.equals("y"))
        {
            stockShowerTowelY();
        }
        else
            stockShowerTowelN();
    }
    public void stockShowerTowelY()
    {
        System.out.print("\t▶ 샤워타올 재고 보충 수량을 입력하세요. : ");
        try
        {
            int res = Integer.parseInt(br.readLine());
            stockArray[2] += res;
            System.out.println();

            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃ ┌────────────────────┐                    ┃");
            System.out.println("\t┃ │ stock setting mode │                    ┃");
            System.out.println("\t┃ └────────────────────┘                    ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃  [ 샤워타올 재고가 %2d개 보충되었습니다. ] ┃\n" , res);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃  ※ 보충 후 샤워타올의 재고 수량 : %2d개   ┃\n", stockArray[2]);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println();
            stockSlipper();
        }
        catch (Exception e)
        {
            System.out.println("\n\t *** 숫자 형태로 입력해주세요. ***\n");
            stockShowerTowelY();
        }
    }
    public void stockShowerTowelN()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  ※ 샤워타올의 재고를 보충하지 않습니다.  ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();
        stockSlipper();
    }

    public void stockSlipper()
    {
        System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃  ※ 현재 객실 슬리퍼 재고 수량 : %2d개     ┃\n" , stockArray[3]);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   객실 슬리퍼의 재고를 보충하시겠습니까?  ┃");
        System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
        System.out.println("\t┃         │   Y  │        │   N  │          ┃");
        System.out.println("\t┃         └──────┘        └──────┘          ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 객실 슬리퍼 재고 보충 여부 : ");
        String a = sc.next();
        if (a.equals("Y") || a.equals("y"))
        {
            stockSlipperY();
        }
        else
            stockSlipperN();
    }

    public void stockSlipperY()
    {
        System.out.print("\t▶ 객실 슬리퍼 재고 보충 수량을 입력하세요. : ");
        try
        {
            int res = Integer.parseInt(br.readLine());
            stockArray[3] += res;
            System.out.println();

            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃ ┌────────────────────┐                    ┃");
            System.out.println("\t┃ │ stock setting mode │                    ┃");
            System.out.println("\t┃ └────────────────────┘                    ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃ [ 객실 슬리퍼 재고가 %2d개 보충되었습니다 ]┃\n" , res);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.printf("\t┃ ※ 보충 후 객실 슬리퍼의 재고 수량 : %2d개 ┃\n", stockArray[3]);
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println();
            stockDone();
        }
        catch (Exception e)
        {
            System.out.println("\n\t*** 숫자 형태로 입력해주세요. ***\n");
            stockSlipperY();
        }
    }
    public void stockSlipperN()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ ※ 객실 슬리퍼의 재고를 보충하지 않습니다.┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.println();
        stockDone();
    }

    public void stockDone()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ stock setting mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃    *** 재고 세팅이 완료되었습니다. ***    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
        customerMode();
    }

    public void toChangeRoomEmpty()
    {
        // 오늘 날짜와 객실 객체의 숙박시작 ~ 종료날짜를 비교하여 방이 비어 있는지 여부를 true false로 변경
        Iterator<String> it = roomMap.keySet().iterator();
        while(it.hasNext())
        {
            String key = it.next();
            if(todayDate >= (roomMap.get(key).getIntEndDate()))
                roomMap.get(key).setRoomRe(false);
            if(todayDate == (roomMap.get(key).getIntStartDate()))
                roomMap.get(key).setRoomRe(true);
        }
    } //toChangeRoomEmpty end

    // 객실 이용 현황 메뉴 선택 메소드
    public void adminReservationDisp()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────────┐                     ┃");
        System.out.println("\t┃ │ Room usage status │                     ┃");
        System.out.println("\t┃ └───────────────────┘                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   1. 남녀별 객실 이용 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   2. 연령별 객실 이용 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   3. 객실 타입별 이용 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");

        try
        {
            int res = Integer.parseInt(br.readLine());
            if (res == 1)
                adminReservationGender(roomMap);
            else if (res == 2)
                adminReservationAge(roomMap);
            else if (res == 3)
                adminReservationGrade(roomMap);
        }
        catch (Exception e)
        {
            System.out.println("\t*** 숫자 형태로 입력해주세요. ***");
        }
    } //adminReservationDisp end

    // 남녀별 객실 이용 현황 메소드
    public void adminReservationGender(Hashtable<String, Room> roomMap) throws IOException
    {
        toChangeRoomEmpty();
        Iterator<String> it = roomMap.keySet().iterator();

        int M=0, F=0, all=0;
        double MR, FR;
        while(it.hasNext())
        {
            String key = it.next();
            if(roomMap.get(key).isRoomRe())
            {
                if (reGuest.get(key).getGender() == 'M')
                    M += 1;
                else if (reGuest.get(key).getGender() == 'F')
                    F += 1;
            }
        }
        all = M + F;
        MR = ((double)M / all)*100 ;
        FR = ((double)F / all)*100 ;
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────────┐                     ┃");
        System.out.println("\t┃ │ Room usage status │                     ┃");
        System.out.println("\t┃ └───────────────────┘                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  [ 남녀별 당일 이용 현황 ]  * 구매자 기준 ┃ ");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * 남성 : %2d명, %5.1f%%                   ┃\n",M,MR);
        System.out.printf("\t┃   * 여성 : %2d명, %5.1f%%                   ┃\n",F,FR);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 이용 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");
        int a = Integer.parseInt(br.readLine());
        if (a == 1)
        {
            AdminRun(roomMap);
        }
        else if (a == 2)
        {
            adminReservationDisp();
        }
    } //adminReservationGender end

    // 연령별 객실 이용 현황 메소드
    public void adminReservationAge(Hashtable<String, Room> roomMap)
    {
        int n10=0, n20=0, n30=0, n40=0, n50=0, n60=0;

        Iterator<String> it = roomMap.keySet().iterator();

        while (it.hasNext())
        {
            String key = it.next();
            int memberDate = Integer.parseInt(reGuest.get(key).getReBirth().substring(0,2));
            int todayYear = Integer.parseInt(String.format("%d", today.get(Calendar.YEAR)));

            if(Integer.parseInt(reGuest.get(key).getReBirth().substring(7,8)) == 3 || Integer.parseInt(reGuest.get(key).getReBirth().substring(7,8)) == 4)
                memberDate += 2000;
            else
                memberDate += 1900;

            if (todayYear - memberDate >= 19 || todayYear - memberDate > 100)
            {
                if (todayYear - memberDate == 19)
                    n10++;
                else if (todayYear - memberDate >= 20 && todayYear - memberDate <= 29)
                    n20++;
                else if (todayYear - memberDate >= 30 && todayYear - memberDate <= 39)
                    n30++;
                else if (todayYear - memberDate >= 40 && todayYear - memberDate <= 49)
                    n40++;
                else if (todayYear - memberDate >= 50 && todayYear - memberDate <= 59)
                    n50++;
                else if (todayYear - memberDate >= 60)
                    n60++;
            }
        }
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────────┐                     ┃");
        System.out.println("\t┃ │ Room usage status │                     ┃");
        System.out.println("\t┃ └───────────────────┘                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   [ 연령별 당일 이용 현황 ]               ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * 20세 미만   : %2d건                    ┃\n",n10);
        System.out.printf("\t┃   * 20세 ~ 29세 : %2d건                    ┃\n",n20);
        System.out.printf("\t┃   * 30세 ~ 39세 : %2d건                    ┃\n",n30);
        System.out.printf("\t┃   * 40세 ~ 49세 : %2d건                    ┃\n",n40);
        System.out.printf("\t┃   * 50세 ~ 59세 : %2d건                    ┃\n",n50);
        System.out.printf("\t┃   * 60세 이상   : %2d건                    ┃\n",n60);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 이용 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");

        try
        {
            int a = Integer.parseInt(br.readLine());

            if (a == 1)
                AdminRun(roomMap);
            else if (a == 2)
                adminReservationDisp();
        }
        catch (Exception e)
        {
            System.out.println("숫자 형태로 입력해주세요.");
        }

    } //adminReservationAge end

    // 객실 타입별 이용 현황 메소드
    public void adminReservationGrade(Hashtable<String, Room> roomMap)
    {
        Iterator<String> it = roomMap.keySet().iterator();
        //Calendar today = Calendar.getInstance();
        //int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DATE)));

        while (it.hasNext())
        {
            String key = it.next();

            if (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate()))
            {
                if ("Deluxe".equals(roomMap.get(key).getGrade()))
                    countReRoomDeluxe += 1;
                else if ("Superior".equals(roomMap.get(key).getGrade()))
                    countReRoomSuperior += 1;
                else if ("Family".equals(roomMap.get(key).getGrade()))
                    countReRoomFamily += 1;
                else if ("Suite".equals(roomMap.get(key).getGrade()))
                    countReRoomSuite += 1;
                int countReRoom = countReRoomDeluxe + countReRoomSuperior + countReRoomFamily + countReRoomSuite;
            }
        }

        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────────┐                     ┃");
        System.out.println("\t┃ │ Room usage status │                     ┃");
        System.out.println("\t┃ └───────────────────┘                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   [ 객실 타입별 당일 이용 현황 ]          ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * Deluxe 객실   : %2d건 / 10건           ┃\n",countReRoomDeluxe);
        System.out.printf("\t┃   * Superior 객실 : %2d건 / 10건           ┃\n",countReRoomSuperior);
        System.out.printf("\t┃   * Family 객실   : %2d건 / 10건           ┃\n",countReRoomFamily);
        System.out.printf("\t┃   * Suite 객실    : %2d건 / 10건           ┃\n",countReRoomSuite);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 이용 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");

        try
        {
            int a = Integer.parseInt(br.readLine());

            if (a == 1)
                AdminRun(roomMap);
            else if (a == 2)
                adminReservationDisp();
        }
        catch (Exception e)
        {
            System.out.println("숫자 형태로 입력해주세요.");
        }
    } //adminReservationGrade end

    // 객실 매출 현황 메뉴 선택
    public void adminSellDisp()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────┐                         ┃");
        System.out.println("\t┃ │  Sales Status │                         ┃");
        System.out.println("\t┃ └───────────────┘                         ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   1. 객실 총 매출 현황                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   2. 남녀별 객실 매출 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   3. 연령별 객실 매출 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   4. 객실 타입별 매출 현황                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");

        try
        {
            int res = Integer.parseInt(br.readLine());
            if (res == 1)
                adminSellRoomTotal(roomMap);
            else if (res == 2)
                adminSellRoomGender(roomMap);
            else if (res == 3)
                adminSellRoomAge(roomMap);
            else if (res == 4)
                adminSellRoomGrade(roomMap);
        }
        catch (Exception e)
        {
            System.out.println("\t*** 숫자 형태로 입력해주세요. ***");
            adminSellDisp();
        }
    }// adminSellRoomDisp end

    // 객실 총 매출 현황 메소드
    public void adminSellRoomTotal(Hashtable<String, Room> roomMap) throws IOException
    {
        int totalPrice=0;
        Iterator<String> it = roomMap.keySet().iterator();

        while(it.hasNext())
        {
            String key = it.next();
            totalPrice += roomMap.get(key).getRoomPrice();
        }

        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────┐                         ┃");
        System.out.println("\t┃ │  Sales Status │                         ┃");
        System.out.println("\t┃ └───────────────┘                         ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  [ 객실 총 매출 현황 ]                    ┃ ");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * 객실 매출 : %7d원                 ┃\n",totalPrice);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 매출 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");
        int a = Integer.parseInt(br.readLine());

        if (a == 1)
        {
            AdminRun(roomMap);
        }
        else if (a == 2)
        {
            adminSellDisp();
        }

    }


    // 남녀별 객실 매출 현황 메소드
    public void adminSellRoomGender(Hashtable<String, Room> roomMap) throws IOException
    {
        toChangeRoomEmpty();
        Iterator<String> it = roomMap.keySet().iterator();

        int M=0, F=0;
        while(it.hasNext())
        {
            String key = it.next();
            if(roomMap.get(key).isRoomRe())
            {
                if (reGuest.get(key).getGender() == 'M')
                    M += roomMap.get(key).getRoomPrice();
                else if (reGuest.get(key).getGender() == 'F')
                    F += roomMap.get(key).getRoomPrice();
            }
        }
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────┐                         ┃");
        System.out.println("\t┃ │  Sales Status │                         ┃");
        System.out.println("\t┃ └───────────────┘                         ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  [ 남녀별 객실 매출 현황 ]  * 구매자 기준 ┃ ");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * 남성 : %7d원                      ┃\n",M);
        System.out.printf("\t┃   * 여성 : %7d원                      ┃\n",F);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 매출 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해주세요 : ");
        int a = Integer.parseInt(br.readLine());

        if (a == 1)
        {
            AdminRun(roomMap);
        }
        else if (a == 2)
        {
            adminSellDisp();
        }
    }// adminSellRoomGender end

    // 연령별 객실 매출 현황 메소드
    public void adminSellRoomAge(Hashtable<String, Room> roomMap) throws IOException
    {
        int n10=0, n20=0, n30=0, n40=0, n50=0, n60=0;
        Iterator<String> it = roomMap.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            int memberDate = Integer.parseInt(reGuest.get(key).getReBirth().substring(0,2));
            int todayYear = Integer.parseInt(String.format("%d", today.get(Calendar.YEAR)));

            if(Integer.parseInt(reGuest.get(key).getReBirth().substring(7,8)) == 3 || Integer.parseInt(reGuest.get(key).getReBirth().substring(7,8)) == 4)
                memberDate += 2000;
            else
                memberDate += 1900;

            if (todayYear - memberDate >= 19 || todayYear - memberDate > 100)
            {
                if (todayYear - memberDate == 19)
                    n10 += roomMap.get(key).getRoomPrice();
                else if (todayYear - memberDate >= 20 && todayYear - memberDate <= 29)
                    n20 += roomMap.get(key).getRoomPrice();
                else if (todayYear - memberDate >= 30 && todayYear - memberDate <= 39)
                    n30 = roomMap.get(key).getRoomPrice();
                else if (todayYear - memberDate >= 40 && todayYear - memberDate <= 49)
                    n40 += roomMap.get(key).getRoomPrice();
                else if (todayYear - memberDate >= 50 && todayYear - memberDate <= 59)
                    n50 += roomMap.get(key).getRoomPrice();
                else if (todayYear - memberDate >= 60)
                    n60 += roomMap.get(key).getRoomPrice();
            }
        }
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────────┐                     ┃");
        System.out.println("\t┃ │ Room usage status │                     ┃");
        System.out.println("\t┃ └───────────────────┘                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   [ 연령별 객실 매출 현황 ]               ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * 20세 미만   : %7d원               ┃\n",n10);
        System.out.printf("\t┃   * 20세 ~ 29세 : %7d원               ┃\n",n20);
        System.out.printf("\t┃   * 30세 ~ 39세 : %7d원               ┃\n",n30);
        System.out.printf("\t┃   * 40세 ~ 49세 : %7d원               ┃\n",n40);
        System.out.printf("\t┃   * 50세 ~ 59세 : %7d원               ┃\n",n50);
        System.out.printf("\t┃   * 60세 이상   : %7d원               ┃\n",n60);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 매출 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해 주세요 : ");

        int a = Integer.parseInt(br.readLine());

        if (a == 1)
        {
            AdminRun(roomMap);
        }
        else if (a == 2)
        {
            adminSellDisp();
        }

    }

    // 객실 타입별 매출 현황 메소드
    public void adminSellRoomGrade(Hashtable<String, Room> roomMap) throws IOException
    {
        Iterator<String> it = roomMap.keySet().iterator();
        //Calendar today = Calendar.getInstance();
        //int todayDate = Integer.parseInt(String.format("%d%02d%02d", today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DATE)));
        int d=0,s=0,f=0,suite=0;
        while (it.hasNext())
        {
            String key = it.next();

            if (todayDate >= (roomMap.get(key).getIntStartDate()) && todayDate < (roomMap.get(key).getIntEndDate()))
            {
                if ("Deluxe".equals(roomMap.get(key).getGrade()))
                    d += roomMap.get(key).getRoomPrice();
                else if ("Superior".equals(roomMap.get(key).getGrade()))
                    s += roomMap.get(key).getRoomPrice();
                else if ("Family".equals(roomMap.get(key).getGrade()))
                    f += roomMap.get(key).getRoomPrice();
                else if ("Suite".equals(roomMap.get(key).getGrade()))
                    suite += roomMap.get(key).getRoomPrice();
            }
        }
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌───────────────┐                         ┃");
        System.out.println("\t┃ │  Sales Status │                         ┃");
        System.out.println("\t┃ └───────────────┘                         ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   [ 객실 타입별 매출 현황 ]               ┃");
        System.out.println("\t┃                                           ┃");
        System.out.printf("\t┃   * Deluxe 객실   : %7d원             ┃\n",d);
        System.out.printf("\t┃   * Superior 객실 : %7d원             ┃\n",s);
        System.out.printf("\t┃   * Family 객실   : %7d원             ┃\n",f);
        System.out.printf("\t┃   * Suite 객실    : %7d원             ┃\n",suite);
        System.out.println("\t┃   ┌─────────────┐       ┌─────────────┐   ┃");
        System.out.println("\t┃   │ 1. 관리자 홈│       │ 2. 구매 현황│   ┃");
        System.out.println("\t┃   └─────────────┘       └─────────────┘   ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해 주세요 : ");

        try
        {
            int a = Integer.parseInt(br.readLine());

            if (a == 1)
                AdminRun(roomMap);
            else if (a == 2)
                adminSellDisp();
        }
        catch (Exception e)
        {
            System.out.println("*** 숫자 형태로 입력해 주세요. ***");
        }
    }


    // 고객용 화면으로 전환할 수 있는 메소드
    public void customerMode()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃ ┌────────────────────┐                    ┃");
        System.out.println("\t┃ │ administrator mode │                    ┃");
        System.out.println("\t┃ └────────────────────┘                    ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   1. 키오스크 모드 전환                   ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   2. 예약 시스템 모드 전환                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   3. 관리자 모드 전환                     ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t▶ 이동하고자 하는 메뉴 번호를 입력해 주세요 : ");
        try
        {
            int customerModeSel = Integer.parseInt(br.readLine());

            if (customerModeSel == 1)
            {
                System.out.println("\n\t============================================");
                System.out.println("\n\t        키오스크 모드로 전환됩니다.         ");
                System.out.println("\n\t============================================");
            }
            else if (customerModeSel == 2)
                resersvationSystem();
            else if (customerModeSel == 3)
                AdminRun(roomMap);
        }
        catch (Exception e)
        {
            System.out.println("\t\t*** 숫자 형태를 입력해주세요. ***");
        }

    }// customerMode end

    // 키오스크 모드 전환
    public int kioskMode()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("\t┃  %d년 %d월 %d일                SS HOTEL ┃\n", todayDate/10000, (todayDate%10000)/100, todayDate%100);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃ ┌───────────────┐                         ┃");
        System.out.println("\t┃ │ SS Hotel Kiosk┃                         ┃");
        System.out.println("\t┃ └───────────────┘                         ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃           Welcome to SS Hotel :)          ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   1. 객실 예약 조회 및 현장 구매          ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃   2. 어메니티 및 식사 구매                ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃     ※현금 결제 & 미성년자 사용 불가※    ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("\t● 이동하고자 하는 메뉴 번호를 입력해 주세요. : ");

        try
        {
            int reserveModeSel = Integer.parseInt(br.readLine());

            if (reserveModeSel == 1)
                return 1;
            else if (reserveModeSel == 2)
            {
                buyAmenityMeal();
                return 2;
            }
            else if(reserveModeSel == 0){
                Main.CODE = 1;
            }
        }
        catch (Exception e)
        {
            System.out.println("\t*** 숫자 형태를 입력해 주세요. ***");
        }
        return 0;
    } //reserveMode end


    // 예약 시스템 모드 전환 (아고다, 호텔스컴바인 같은,,,,)
    public void resersvationSystem()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("\t┃                                  SS HOTEL ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃           Welcome to SS Hotel :)          ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃		                            ┃");
        System.out.println("\t┃  안녕하세요!                              ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃  SS HOTEL 온라인 예약 페이지입니다.       ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        i = 3;
    } //resersvationSystem

    // 어메니티 및 식사 구매 모드 전환
    public void buyAmenityMeal()
    {
        System.out.println("\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("\t┃   %d년 %d월 %d일               SS HOTEL ┃\n", todayDate/10000, (todayDate%10000)/100, todayDate%100);
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃※ 해당 메뉴는 투숙객만 이용 가능합니다 ※ ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃     ==============================        ┃");
        System.out.println("\t┃        예약 번호를 입력해 주세요.         ┃");
        System.out.println("\t┃     ==============================        ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┃                                           ┃");
        System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}