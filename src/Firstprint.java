import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Firstprint {
    int password;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void password(){
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
                    System.out.println("\t*** 관리자 비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요. ***");
                    adminPassword();
                }
                System.out.println();
            }
            while (password != 1234);
        }
        catch (Exception e)
        {
            System.out.println("\t▶ 숫자 형태를 입력해주세요.");
            adminPassword();
            System.out.println();
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
