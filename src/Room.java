public class Room {
    String roomNum;     // 객실 호수
    String grade;       // 객실의 등급
    String bedType;     // 침대 타입
    int num;            // 객실의 사용가능 인원
    int roomPrice;      // 객실 가격
    boolean roomRe;     // 예약 여부

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
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
}

class Deluxe extends Room{
    {
        roomNum = "101";
        grade = "deluxe";
        bedType = "twin";
        num = 2;
        roomPrice = 150000;
        roomRe = false;
    }

}
