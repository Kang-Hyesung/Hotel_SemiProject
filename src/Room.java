public class Room {
    protected String roomNum;     // 객실 호수
    protected String grade;       // 객실의 등급
    protected String bedType;     // 침대 타입
    protected int num;            // 객실의 사용가능 인원
    protected int roomPrice;      // 객실 가격
    protected boolean roomRe;     // 예약 여부

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
    }

    public void checkCon(int num){
        if(this.num >= num)
            System.out.println("인원수 : 가능");
        else
            System.out.println("인원수 : 불가능");
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
