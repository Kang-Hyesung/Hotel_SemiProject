// Reserve_list에서 임의적으로 put함
public class Members
{
    protected String memberName;
    protected String memberBirth;
    protected String memberTel;
    protected int memberNum;


    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public String getMemberBirth()
    {
        return memberBirth;
    }

    public void setMemberBirth(String memberBirth)
    {
        this.memberBirth = memberBirth;
    }

    public String getMemberTel()
    {
        return memberTel;
    }

    public void setMemberTel(String memberTel)
    {
        this.memberTel = memberTel;
    }


    public int getMemberNum()
    {
        return memberNum;
    }

    public void setMemberNum(int memberNum)
    {
        this.memberNum = memberNum;
    }


    public Members()
    {
        memberName = "";
        memberBirth = "";
        memberTel = "";
        memberNum = 0;
    }

    public Members(String memberName, String memberBirth, String memberTel, int memberNum)
    {
        this.memberName = memberName;
        this.memberBirth = memberBirth;
        this.memberTel = memberTel;
        this.memberNum = memberNum;
    }

    @Override
    public String toString()
    {
        return "\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "\t┃                                  SS HOTEL ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃             [ 회원 정보 확인 ]            ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 회원 번호 : " + memberNum + "                     ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 이름      : " + memberName + "                     ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 생년월일  : " + memberBirth + "             ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 전화번호  : " + memberTel + "              ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";







        //"\n회원번호 : "+memberNum+"\n이    름 : "+memberName+"\n생년월일 : "+memberBirth+"\n전화번호 : "+memberTel+"\n";
    }

}