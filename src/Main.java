import java.util.Hashtable;
//
public class Main{
    public static void main(String[] args) throws Exception {
        FileInputOutput inOut = new FileInputOutput();
        Hashtable<String, Room> roomMap = inOut.fileIn();       // 역직렬화로 Hashtable 가져오기

        //Hashtable<String, Room> roomMap = new Hashtable<>();
        SemiAdmin admin = new SemiAdmin(roomMap);
        admin.menuDisp();
        admin.adminPassword();
        admin.adminSel();
        admin.adminRun(roomMap);

        inOut.fileOut(roomMap);                                 // 추가한 Map 직렬화
        inOut = null;
    }
}


