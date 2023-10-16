import java.awt.color.ICC_ColorSpace;
import java.util.*;
import java.io.*;


public class Pay {
    public Hashtable<String, Room> roomMap;
    public Hashtable<String, int[]> cusArray;
    int[] stockArray;
    private int[] cusAmenity;
    String reNum;
    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int totalPriceR, totalPriceA, totalPriceAll;

    public Pay(Hashtable<String, Room> roomMap, Hashtable<String, int[]>cusArray, int[] stockArray, String reNum) {
        this.roomMap = roomMap;
        this.cusArray = cusArray;
        this.stockArray = stockArray;
        this.reNum = reNum;
        if(cusArray.get(reNum) == null){
            this.cusAmenity = new int[6];
        };
    }

    public void payRun()
    {

        totalPriceAll = totalPriceR + totalPriceA;
        printLast();
        payment();
    }

    // 객실 구매 내역 정보
    public void printCartRoom()
    {
        Room room = this.roomMap.get(reNum);
        System.out.println("\n\t[객실 구매 정보]");
        System.out.println("\t* 객실 등급 : " + room.getGrade());
        System.out.println("\t* 침대 타입 : " + room.getBedType());
        System.out.println("\t* 객실 가격 : " + room.getRoomPrice() + "원");
        totalPriceR = room.getRoomPrice();
    }

    // 어메니티 및 식사 구매 내역 정보
    public void printCartAmenity()
    {
        System.out.println("\n\n\t[어메니티 및 식사 구매 정보]");
        for (int i=0; i<6 ;i++)
        {
            if (cusArray.get(reNum)[i] != 0)
            {
                if (i == 0)
                    System.out.println("\t* 칫솔&치약 : " + cusArray.get(reNum)[i] * 1000 + "원");
                if (i == 1)
                    System.out.println("\t* 면도기 : " + cusArray.get(reNum)[i] * 1000 + "원");
                if (i == 2)
                    System.out.println("\t* 샤워 타월 : " + cusArray.get(reNum)[i] * 1000 + "원");
                if (i == 3)
                    System.out.println("\t* 객실 슬리퍼 : " + cusArray.get(reNum)[i] * 1000 + "원");
                if (i == 4)
                    System.out.println("\t* 조식 이용권 : " + cusArray.get(reNum)[i] * 50000 + "원");
                if (i == 5)
                    System.out.println("\t* 디너&바 이용권 : " + cusArray.get(reNum)[i]  * 80000 + "원");
            }
        }
        totalPriceA = ((cusArray.get(reNum)[0] * 1000) + (cusArray.get(reNum)[1] * 1000) + (cusArray.get(reNum)[2] * 1000) + (cusArray.get(reNum)[3] * 1000)
                + (cusArray.get(reNum)[4] * 50000) + (cusArray.get(reNum)[5]  * 80000));
    }

    public void printFirst()
    {
        System.out.println("\n\n\t========== 최종 결제 내역 확인 ==========");
    }

    public void printLast()
    {
        System.out.println("\n\t========================================");
        System.out.println("\t● 최종 결제 금액 : " + totalPriceAll + "원");
    }


    public void payment()
    {
        System.out.print("\n\t● 카드사를 입력해주세요 (ex. 우리카드 → 우리) : ");
        try
        {
            String cardCompany = br.readLine();
        }
        catch (Exception e) {
            System.out.println("\n\t*** 문자를 입력해주세요. ***");
        }

        System.out.print("\n\t● 카드 번호 뒷 4자리를 입력해주세요. : ");
        try
        {
            int cardBackNumber = Integer.parseInt(br.readLine());
        }
        catch (Exception e)
        {
            System.out.println("\n\t정수 형태로 입력해주세요.");
        }
        System.out.println("\n\t*** 결제가 완료되었습니다. 감사합니다 :) ***");
        System.out.println("\t*** 예약 번호는 [ " + reNum + " ] 입니다. ***");
        System.out.println(roomMap.get(reNum));

    }

    public void getCusArrayList(){
        for (int i=0; i<6 ;i++)
        {
            if (cusArray.get(reNum)[i] != 0)
            {
                if (i == 0)
                    stockArray[i] -= cusArray.get(reNum)[i];
                if (i == 1)
                    stockArray[i] -= cusArray.get(reNum)[i];
                if (i == 2)
                    stockArray[i] -= cusArray.get(reNum)[i];
                if (i == 3)
                    stockArray[i] -= cusArray.get(reNum)[i];
            }
        }
    }

    public void payRoom(){
        printFirst();
        printCartRoom();
        printCartAmenity();
        payRun();
    }
    public void payAmnity(){
        printFirst();
        printCartAmenity();
        payRun();
        getCusArrayList();
    }

    public void payRoomOnly(){
        printFirst();
        printCartRoom();
        payRun();
    }
}