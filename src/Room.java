public class Room {
    private String roomNum;     // 객실 호수
    private String grade;       // 객실의 등급
    private String bedType;     // 침대 타입
    private int num;            // 객실의 사용가능 인원
    private int roomPrice;      // 객실 가격
    private boolean roomRe;     // 예약 여부

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
        setGrade("Deluxe");
        setBedType("twin");
        setNum(2);
        setRoomPrice(150000);
        setRoomRe(false);
    }

    Deluxe(String roomNum, String bedType)
    {
        setRoomNum(roomNum);
        setBedType(bedType);
    }
}
