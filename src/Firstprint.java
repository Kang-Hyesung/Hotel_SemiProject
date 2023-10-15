import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Firstprint {
    private int password;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void FirstRun(){
        adminPasswordDisp();
        adminPassword();
    }
    public void adminPassword()
    {
        try
        {
            do
            {
                System.out.print("\t▶ 관리자 비밀번호를 입력하세요 : ");

                password = Integer.parseInt(br.readLine());
                System.out.println();


                if (password != 1234){
                    if(password == 0){
                        System.out.println("프로그램을 종료합니다");
                        Main.CODE = 1;
                        return;
                    }
                    System.out.println("\t*** 관리자 비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요. ***");
                    System.out.println();
                    adminPassword();
                }
            }
            while (password != 1234);
        }
        catch (Exception e)
        {
            System.out.println("\n\t*** 숫자 형태를 입력해주세요. ***");
            System.out.println();
            adminPassword();
        }
    }
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
}