import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable {
    public Room() {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        day = now.get(Calendar.DATE);
        System.out.println(year);
    }
    protected String roomNum;     // 객실 호수
    protected String grade;       // 객실의 등급
    protected String bedType;     // 침대 타입
    protected int num;            // 객실의 사용가능 인원
    protected int roomPrice;      // 객실 가격
    protected boolean roomRe;     // 예약 여부
    int year,month,day;

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isRoomRe() {
        return roomRe;
    }

    public void setRoomRe(boolean roomRe) {
        this.roomRe = roomRe;
    }     // 게터세터

    public void checkCon(int num){
        if(this.num >= num)
            System.out.println("인원수 : 가능");
        else
            System.out.println("인원수 : 불가능");
    }

    public void reservationFin(){
        roomRe = true;
    }

    @Override
    public String toString() {
//        Calendar now = Calendar.getInstance();
//        int year = now.get(Calendar.YEAR);
//        int month = now.get(Calendar.MONTH) + 1;
//        int day = now.get(Calendar.DATE);

        return "{" +
                "객실 번호=" + roomNum +
                ", 등급=" + grade +
                ", 침대 타입=" + bedType +
                ", 최대인원수=" + num +
                ", 객실 가격=" + roomPrice +
                '}' + "\n" + String.format("%d년 %02d월 %02d일", year, month, day);
    }


}

class Deluxe extends Room{
    {
        grade = "Deluxe";
        bedType = "twin";
        num = 2;
        roomPrice = 150000;
        roomRe = false;
    }

    Deluxe(String roomNum, String bedType) {
        super();
        this.roomNum = roomNum;
        this.bedType = bedType;
    }
}

class Superior extends Room{
    {
        grade = "Superior";
        bedType = "twin & Double";
        num = 3;
        roomPrice = 180000;
        roomRe = false;
    }

    Superior(String roomNum, String bedType) {
        this.roomNum = roomNum;
        this.bedType = bedType;
    }
}

class Family extends Room{

    {
        grade = "Family";
        bedType = "King2";
        num = 6;
        roomPrice = 250000;
        roomRe = false;
    }

    Family(String roomNum, String bedType) {
        this.roomNum = roomNum;
        this.bedType = bedType;
    }
}

class Suite extends Room{

    {
        grade = "Suite";
        bedType = "King";
        num = 3;
        roomPrice = 300000;
        roomRe = false;
    }

    Suite(String roomNum, String bedType) {
        this.roomNum = roomNum;
        this.bedType = bedType;
    }
}
