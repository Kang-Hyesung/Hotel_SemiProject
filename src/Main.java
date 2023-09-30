import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("객실번호를 선택하세요 : ");
        String roomNum = br.readLine();
        System.out.print("침대 타입을 입력하세요 : ");
        String bedType = br.readLine();

        Map<String, Room> n = new HashMap<>();
        n.put(roomNum, new Deluxe(roomNum, bedType));

        n.get(roomNum).reservationFin();
        System.out.println(n.get(roomNum));
    }
}