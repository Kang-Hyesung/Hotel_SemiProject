import java.awt.color.ICC_ColorSpace;
import java.util.*;
import java.io.*;


import static java.lang.Integer.parseInt;


public class Pay {
    public Hashtable<String, Room> roomMap;
    String reNum;
    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public Pay(Hashtable<String, Room> roomMap, String reNum) {
        this.roomMap = roomMap;
        this.reNum = reNum;
    }


    public void PayRun()
    {
        printCartTotal();
        payment();
    }




    public void printCartTotal()                // 장바구니 내역 출력 (객실 구매 + 부가 서비스  예약 구매고객일 경우 객실 구매는 결제 x)
    {
        System.out.println(reNum);
        System.out.println("테스트 : " + parseInt(reNum.substring(8,9)) );
        System.out.println("\n\n========= 최종 결제 내역 확인 =========");
        System.out.println("[객실 구매 정보]");


        Room room = this.roomMap.get(reNum);


        if (parseInt(reNum.substring(8,9)) < 5 ) {


            if (roomMap.containsKey(reNum)) {
                System.out.println("객실 등급 : " + room.getGrade());
                System.out.println("침대 타입 : " + room.getBedType());
                System.out.println("객실 가격 : " + room.getRoomPrice() + "원");
            } else
                System.out.println("해당 예약번호를 찾을 수 없습니다.");
        }
        else if (parseInt(reNum.substring(8,9)) > 4)
        {
            System.out.println("장바구니 내역만 출력할게요~");
        }


        System.out.println("==================================");




        System.out.print("장바구니 내역의 결제를 진행 하시겠습니까? (Y/N) ");
        String answer = sc.next();


        if (answer.equals("Y")) {
            System.out.println("결제를 진행합니다.");
        } else
            System.out.println("객실 구매 창으로 이동합니다.");
    }


    public void payment()
    {
        System.out.print("카드사를 입력해주세요 (ex. 우리카드 → 우리) : ");
        try
        {
            String cardCompany = br.readLine();
        }
        catch (Exception e) {
            System.out.println("문자를 입력해주세요.");
        }


        System.out.print("\n카드 번호 뒷 4자리를 입력해주세요. : ");
        try
        {
            int cardBackNumber = parseInt(br.readLine());
        }
        catch (Exception e)
        {
            System.out.println("정수 형태로 입력해주세요.");
        }


        System.out.println("결제가 완료되었습니다. 체크인 시간은 오후 3시부터 입니다 :)");
    }
}


