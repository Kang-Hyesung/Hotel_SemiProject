import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable {
    public Room() {
//        Calendar now = Calendar.getInstance();
//        startYear = now.get(Calendar.YEAR);
//        startMonth = now.get(Calendar.MONTH);
//        startDay = now.get(Calendar.DATE);
//        System.out.println(startDay);
//        now.add(Calendar.DATE,days);
//        endYear = now.get(Calendar.YEAR);
//        endMonth = now.get(Calendar.MONTH);
//        endDay = now.get(Calendar.DATE);
//        System.out.println(endDay);
    }
    protected String roomNum;     // 객실 호수
    protected String grade;       // 객실의 등급
    protected String bedType;     // 침대 타입
    protected int num;            // 객실의 사용가능 인원
    protected int roomPrice;      // 객실 가격
    protected boolean roomRe = false;     // 예약 여부
    protected int days;           // 몇박??
    int startYear, startMonth, startDay, endYear, endMonth, endDay;     // 숙박 시작 날짜, 숙박 종료 날짜

    public String getEndDate() {
        return String.format("%d%d%d",endYear,endMonth,endDay);
    }

    public String getStartDate() {
        return String.format("%d%d%d",startYear,startMonth,startDay);
    }

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

    @Override
    public String toString() {
        Calendar now = Calendar.getInstance();
        startYear = now.get(Calendar.YEAR);
        startMonth = now.get(Calendar.MONTH);
        startDay = now.get(Calendar.DATE);

        now.add(Calendar.DATE,days);
        endYear = now.get(Calendar.YEAR);
        endMonth = now.get(Calendar.MONTH);
        endDay = now.get(Calendar.DATE);

        return "{" +
                "객실 번호=" + roomNum +
                ", 등급=" + grade +
                ", 침대 타입=" + bedType +
                ", 최대인원수=" + num +
                ", 객실 가격=" + roomPrice +
                '}' + "\n" + String.format("숙박 시작 : %d년 %02d월 %02d일\n", startYear, startMonth, startDay)
                + String.format("숙박 종료 : %d년 %02d월 %02d일", endYear, endMonth, endDay)
                + roomRe;
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

    Deluxe(String roomNum, String bedType, int days) {
        super();
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
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

    Superior(String roomNum, String bedType, int days) {
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
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

    Family(String roomNum, String bedType, int days) {
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
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

    Suite(String roomNum, String bedType, int days) {
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
    }
}
