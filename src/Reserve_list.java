import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Scanner;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;


public class Reserve_list
{
    // BufferedReader 인스턴스 생성
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private String reNum;												//-- 예약번호
    private String res;													//-- 응답확인 변수 Y/N
    private String name, birth, tel;									//-- (비회원모드) 이름, 주민번호, 전화번호



    int n = 0;													// 다시 입력, 돌아가기 값 받는 곳
    int i;

    public Hashtable<String,Room> roomMap;
    public Hashtable<String,Reserves> reGuest;
    HashMap<String, Members> map = new HashMap<String, Members>();
    String str;

    Reserve_list(Hashtable<String,Room> roomMap, Hashtable<String,Reserves> reGuest)
    {
        this.roomMap = roomMap;
        this.reGuest = reGuest;
    }

    // 예약 여부(Y/N) 확인
    public int reserve() throws IOException
    {
        do
        {
            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃            예약을 하셨습니까?             ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
            System.out.println("\t┃         │   Y  │        │   N  │          ┃");
            System.out.println("\t┃         └──────┘        └──────┘          ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            res = br.readLine().toUpperCase();						//-- 입력한 답이 대문자로 비교될 수 있도록...
            if (!res.equals("Y") && !res.equals("N"))
                System.out.println("\n\t=======Y/N 중 선택해주세요.=======\n");

            if (res.equals("Y"))									//-- Y (예약했을 때)
            {
                reserveCheck();										//-- 예약자이므로 예약번호확인으로 이동
            }
            else if (res.equals("N"))								//-- N (예약하지 않았을 때)
            {
                member();											//-- 예약자가 아니므로 객실 구매창(회원여부확인)으로 이동
            }
        }
        while (!res.equals("Y") && !res.equals("N"));				//-- Y나 N이 아닌 다른 문자나 숫자를 입력했을 경우..

        return i;

    } // end reserve()


    // 예약 번호 확인
    public void reserveCheck() throws IOException
    {
        do
        {
            System.out.print("\n\t▶예약 번호(숫자 8자리)를 입력해주세요 : ");
            String inputNum = br.readLine();						//-- 사용자가 입력한 예약번호

            if (inputNum.equals(reNum))
            {
                System.out.println("\t예약번호 : "+reNum);
                System.out.println("\t예약자정보 : "+reGuest.get(reNum));
                System.out.println("\t객실정보 : "+roomMap.get(reNum));
                do
                {
                    System.out.println("\t출력된 예약정보가 맞습니까?(Y/N) : ");
                    res = br.readLine().toUpperCase();
                    if (!res.equals("Y") && !res.equals("N"))
                        System.out.println("\n\t=======Y/N 중 선택해주세요.=======\n");

                    if (res.equals("Y"))
                    {
                        System.out.println("\t어메니티구매창으로..");
                    }
                    else if (res.equals("N"))
                    {
                        member();
                        i = 10;
                    }
                }
                while (!res.equals("Y") && !res.equals("N"));
            }

            else
            {
                System.out.println("\n\t=======일치하는 예약번호가 없습니다.=======\n");
                try
                {
                    do
                    {
                        System.out.println("\t1. 다시 입력\t 2. 이전 메뉴\n");
                        System.out.print("\t원하는 번호를 입력해주세요. : ");
                        n = Integer.parseInt(br.readLine());
                        if (n == 2)									//-- 이전 메뉴(2)를 클릭했을 때 메인홈으로 돌아가기
                            reserve();
                        if(n != 1 && n!= 2)
                        {
                            System.out.println("\n\t=======1. 다시 입력 2. 이전 메뉴 중 선택해주세요.=======\n");
                        }
                    }
                    while (n != 1 && n!= 2);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("\n\t=======숫자를 입력해주세요.=======\n");
                }
            }
        }
        while (n == 1);												//-- 다시 입력(1)을 클릭했을 때 예약번호입력으로 돌아가기

    } // end reserveCheck()

    // 회원 여부(Y/N) 확인
    public void member() throws IOException
    {
        do
        {
            System.out.println("\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("\t┃                                  SS HOTEL ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                회원이십니까?              ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃                                           ┃");
            System.out.println("\t┃         ┌──────┐        ┌──────┐          ┃");
            System.out.println("\t┃         │   Y  │        │   N  │          ┃");
            System.out.println("\t┃         └──────┘        └──────┘          ┃");
            System.out.println("\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            res = br.readLine().toUpperCase();						//-- 입력한 답이 대문자로 비교될 수 있도록...
            if (!res.equals("Y") && !res.equals("N"))
                System.out.println("\n\t=======Y/N 중 선택해주세요.=======\n");

            if (res.equals("Y"))									//-- Y (회원일 때)
            {
                memberCheck();										//-- 회원모드로 이동
            }
            else if (res.equals("N"))								//-- N (비회원일 때)
            {
                nonMemberCheck();									//-- 비회원모드로 이동
            }
        }
        while (!res.equals("Y") && !res.equals("N"));				//-- Y나 N이 아닌 다른 문자나 숫자를 입력했을 경우..
    } // end member()


    // 회원모드
    public void memberCheck() throws IOException
    {

        // 회원명단
        map.put("110101", new Members("채다선","960101-2440198","010-1234-5678", 110101));
        map.put("121225", new Members("이윤수","951225-1385624","010-2345-6789", 121225));
        map.put("131225", new Members("최혜인","971225-2426900","010-3456-6789", 131225));
        map.put("141010", new Members("강혜성","971010-1898442","010-4567-8901", 141010));
        map.put("150224", new Members("길현욱","990224-1873336","010-3456-6789", 150224));
        map.put("161111", new Members("김수환","951111-1068870","010-4567-8901", 161111));


        try
        {
            System.out.print("\n\t▶회원번호(숫자 6자리)를 입력하세요 : ");
            str = br.readLine();
            int inputNum = Integer.parseInt(str);

            if (map.get(str).getMemberNum() == inputNum)
            {
                System.out.print("\n\t▶주민번호 뒷자리(숫자 7자리)를 입력하세요 : ");
                String rnum = br.readLine();

                if (rnum.equals(map.get(str).getMemberBirth().substring(7)))
                {
                    System.out.println(map.get(str));						//-- 회원정보출력
                    System.out.println("\n\t객실 구매창으로 이동합니다.");	//-- 회원이므로 객실 구매창(객실 선택)으로 이동**삭제해야함
                    i = 1;
                }

            }
        }
        catch (Exception ee)
        {
            System.out.println("\n\t=======일치하는 회원번호가 없습니다.=======\n");
            do
            {
                try
                {
                    System.out.println("\t1. 다시 입력\t 2. 이전 메뉴\n");
                    System.out.print("\t원하는 번호를 입력해주세요. : ");
                    n = Integer.parseInt(br.readLine());
                    if (n == 2)											//-- 이전 메뉴(2)를 클릭했을 때
                        member();
                    else if (n == 1)
                        memberCheck();
                    else if(n != 1 && n!= 2)
                        System.out.println("\n\t=======1. 다시 입력 2. 이전 메뉴 중 선택해주세요.=======\n");
                }
                catch (Exception e)
                {
                    System.out.println("\n\t=======숫자를 입력해주세요.=======\n");
                }
            }
            while (n != 1 && n!= 2);
        }

    } // end memberCheck()


    // 비회원모드
    public void nonMemberCheck() throws IOException
    {
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.print("\n\t▶이름 전화번호(010-xxxx-xxxx)를 입력해주세요(공백구분) : ");
            name = sc.next();
            tel = sc.next();

            try
            {
                System.out.println("\n\t1. 다시 입력\t 2. 이전 메뉴\t 3. 다음 메뉴\n");
                System.out.print("\t원하는 번호를 입력해주세요 : ");
                n = Integer.parseInt(br.readLine());
                if (n==2)										//-- 이전 메뉴(2)를 클릭했을 때 예약여부확인으로 돌아가기**변경해야함
                {
                    reserve();									// 메인화면으로 가는게 좋음
                    break;
                }
                else if (n==3)									//-- 다음 메뉴(3)를 클릭했을 때 주민번호입력(성인인증)으로 이동
                {
                    jumin();
                    break;
                }
                else if (n !=1)
                {
                    System.out.print("\n\t=======1. 다시 입력 2. 이전 메뉴 3. 다음 메뉴 중 선택해주세요.=======\n");
                }
            }
            catch (NumberFormatException o)
            {
                System.out.println("\n\t=======숫자를 입력해주세요.=======\n");
            }
        }
        while (n == 1);

    } // end nonMemberCheck()


    // 성인인증 판별
    public void jumin() throws IOException
    {
        // 주민번호 유효성 검사
        int[] bir = {2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
        int tot = 0;
        System.out.print("\n\t▶주민번호(xxxxxx-xxxxxx)를 입력해주세요 : ");
        birth = br.readLine();

        if (birth.length() != 14)
        {
            do
            {
                try
                {
                    System.out.println("\n\t=======주민번호를 다시 확인해주세요.=======\n");

                    System.out.println("\t1. 다시 입력\t 2. 이전 메뉴\n");
                    System.out.print("\t원하는 번호를 입력해주세요 : ");
                    n = Integer.parseInt(br.readLine());
                    if (n==1)										//-- 다시 입력(1)을 클릭했을 때 주민번호입력(성인인증)으로 돌아가기
                    {
                        jumin();
                    }
                    else if (n==2)									//-- 이전 메뉴(2)를 클릭했을 때 예약여부확인으로 돌아가기
                    {
                        reserve();
                    }
                    else
                        System.out.print("\n\t=======1. 다시 입력 2. 이전 메뉴 중 선택해주세요.=======\n");
                }
                catch (NumberFormatException u)
                {
                    System.out.println("\n\t=======숫자를 입력해주세요.=======\n");
                }
            }
            while (n != 1 && n!= 2);
        }
        else
        {
            for (int i=0;i<13 ;i++ )
            {
                if (i==6)
                {
                    continue;
                }
                tot += bir[i]*Integer.parseInt(birth.substring(i,(i+1)));
            }

            int su = (11 - tot % 11)%10;

            // 유효하지 않은 주민번호일 경우
            if (su != Integer.parseInt(birth.substring(13)))
            {
                System.out.println("\n\t=======잘못된 주민번호입니다.=======\n");

                do
                {
                    try
                    {
                        System.out.println("\n\t1. 다시 입력\t 2. 이전 메뉴");
                        System.out.print("\t원하는 번호를 입력해주세요 : ");
                        n = Integer.parseInt(br.readLine());
                        if (n==2)									//-- 이전 메뉴(2)를 클릭했을 때 메인화면으로 돌아가기
                        {
                            System.out.println("\t메인화면으로...");								// 메인화면으로 가는게 좋음
                        }
                        else
                            System.out.print("\n\t=======1. 다시 입력 2. 이전 메뉴 중 선택해주세요.=======\n");
                    }
                    catch (NumberFormatException a)
                    {
                        System.out.println("\n\t=======숫자를 입력해주세요.=======\n");
                    }
                }
                while (n != 1 && n!= 2);
            }

            // 유효한 주민번호일 경우
            else if (su == Integer.parseInt(birth.substring(13)))
            {
                // 성인인증
                int b = Integer.parseInt(birth.substring(0,6));

                // 2000년대생은 주민번호 뒷자리가 3 또는 4이므로...
                if (Integer.parseInt(birth.substring(7,8))==3 || Integer.parseInt(birth.substring(7,8))==4)
                    b += 20000000;
                else
                    b += 19000000;

                int memberY = b / 10000;
                int memberM = (b%10000) / 100;
                int memberD = b % 100;


                Calendar calMember = Calendar.getInstance();	// 회원생년월일
                calMember.set(memberY,memberM,memberD);
                calMember.add(Calendar.YEAR,19);
                Calendar cal = Calendar.getInstance();			// 시스템 날짜

                int todayY = cal.get(Calendar.YEAR);
                int todayM = cal.get(Calendar.MONTH);
                int todayD = cal.get(Calendar.DATE);

                // 성인일 경우
                if (cal.compareTo(calMember) == 1 || cal.compareTo(calMember) == 0)
                {
                    System.out.println("\n\t객실 정보 출력창으로 이동합니다.");	//-- 성인이므로 객실 구매창(객실 선택)으로 이동
                    i = 1;
                }

                // 미성년자일 경우
                else
                {
                    System.out.println("\n\t미성년자이므로 객실 예약이 불가합니다.\n데스크에 문의 바랍니다.\n");
                    System.out.println("\t메인화면으로..");
                    //**
                }
            }
        }
    } // end jumin()

    public void putInfo()
    {
        reGuest.put(reNum, new Reserves(name, birth, tel));	// **객실 구매 후 예약번호 생성 시 받아와야함
    } // end putInfo()
    public void putMemberInfo(String reNum){
        reGuest.put(reNum, new Reserves(map.get(str).getMemberName(), map.get(str).getMemberBirth(), map.get(str).getMemberTel()));
    }

    public void setReNum(String reNum){
        this.reNum = reNum;
    }


} // end class