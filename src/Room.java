import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable {

    private static final long serialVersionUID = -3406591124049958401l;
    public Room(int days) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, afDay);
        startYear = now.get(Calendar.YEAR);
        startMonth = now.get(Calendar.MONTH) + 1;
        startDay = now.get(Calendar.DATE);

        now.add(Calendar.DATE, days);
        endYear = now.get(Calendar.YEAR);
        endMonth = now.get(Calendar.MONTH) + 1;
        endDay = now.get(Calendar.DATE);
    }

    public Room(int days, int afDay) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, afDay);
        startYear = now.get(Calendar.YEAR);
        startMonth = now.get(Calendar.MONTH) + 1;
        startDay = now.get(Calendar.DATE);

        now.add(Calendar.DATE, days);
        endYear = now.get(Calendar.YEAR);
        endMonth = now.get(Calendar.MONTH) + 1;
        endDay = now.get(Calendar.DATE);
    }


    protected int roomNum;     // 객실 호수
    protected String grade;       // 객실의 등급
    protected String bedType;     // 침대 타입
    protected int num;            // 객실의 사용가능 인원
    protected int roomPrice;      // 객실 가격
    protected boolean roomRe;     // 예약 여부
    protected int days;           // 몇박??
    protected int afDay;          // 예약이 며칠 뒤 부터?
    protected String inwon;       // 숙박 인원(사용가능 인원x)
    int startYear, startMonth, startDay, endYear, endMonth, endDay;     // 숙박 시작 날짜, 숙박 종료 날짜

    public String getEndDate() {
        return String.format("%d%02d%02d",endYear,endMonth,endDay);
    }

    public int getIntEndDate() {
        return Integer.parseInt(String.format("%d%02d%02d",endYear,endMonth,endDay));
    }

    public String getStartDate() {
        return String.format("%d%02d%02d", startYear, startMonth, startDay);
    }

    public int getIntStartDate() {
        return Integer.parseInt(String.format("%d%02d%02d", startYear, startMonth, startDay));
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
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
        return roomPrice * days;
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

    public String getInwon() {
        return inwon;
    }

    public int getDays() {
        return days;
    }

    public void checkCon(int num){
        if(this.num >= num)
            System.out.println("인원수 : 가능");
        else
            System.out.println("인원수 : 불가능");
    }

    @Override
    public String toString() {
        return "\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "\t┃                                  SS HOTEL ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃             [ 예약 정보 확인 ]            ┃\n" +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 객실 번호     : %3d호                  ┃\n",roomNum) +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 객실 등급     : %8s               ┃\n",grade) +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 침대 타입     : %8s               ┃\n",bedType) +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 숙박 인원     : %3s인                  ┃\n",inwon) +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 객실 가격     : %7d원              ┃\n",roomPrice) +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 체크인 일자   : %d년 %02d월 %02d일       ┃\n", startYear, startMonth, startDay)  +
                "\t┃                                           ┃\n" +
                String.format("\t┃ ▷ 체크아웃 일자 : %d년 %02d월 %02d일       ┃\n", endYear, endMonth, endDay)  +
                "\t┃                                           ┃\n" +
                "\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";

			/*
		"{" +
                "객실 번호=" + roomNum +
                ", 등급=" + grade +
                ", 침대 타입=" + bedType +
                ", 최대인원수=" + num +
                ", 객실 가격=" + roomPrice +
                '}' + "\n" + String.format("숙박 시작 : %d년 %02d월 %02d일\n", startYear, startMonth, startDay)
                + String.format("숙박 종료 : %d년 %02d월 %02d일", endYear, endMonth, endDay)
                ;
				*/
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

    Deluxe(int roomNum, String bedType, int days, String inwon) {
        super(days);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.inwon = inwon;
    }

    Deluxe(int roomNum, String bedType, int days, int afDay, String inwon) {
        super(days, afDay);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.afDay = afDay;
        this.inwon = inwon;
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

    Superior(int roomNum, String bedType, int days, String inwon) {
        super(days);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.inwon = inwon;
    }

    Superior(int roomNum, String bedType, int days, int afDay, String inwon) {
        super(days, afDay);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.afDay = afDay;
        this.inwon = inwon;
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

    Family(int roomNum, String bedType, int days, String inwon) {
        super(days);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.inwon = inwon;
    }

    Family(int roomNum, String bedType, int days, int afDay, String inwon) {
        super(days, afDay);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.afDay = afDay;
        this.inwon = inwon;
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

    Suite(int roomNum, String bedType, int days, String inwon) {
        super(days);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.inwon = inwon;
    }

    Suite(int roomNum, String bedType, int days, int afDay, String inwon) {
        super(days, afDay);
        this.roomNum = roomNum;
        this.bedType = bedType;
        this.days = days;
        this.afDay = afDay;
        this.inwon = inwon;
    }
}