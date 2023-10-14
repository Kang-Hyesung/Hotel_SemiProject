import java.io.Serializable;

public class Reserves implements Serializable
{
    private static final long serialVersionUID = -5632753118030590391l;
    private String reName;
    private String reBirth;
    private String reTel;
    private char gender;
    private String reNum;

    public Reserves(String reName, String reBirth, String reTel)
    {
        this.reName = reName;
        this.reBirth = reBirth;
        this.reTel = reTel;
        setGender();
    }

    public String getReName()
    {
        return reName;
    }

    public void setReName(String reName)
    {
        this.reName = reName;
    }

    public String getReBirth()
    {
        return reBirth;
    }

    public void setReBirth(String reBirth)
    {
        this.reBirth = reBirth;
    }

    public String getReTel()
    {
        return reTel;
    }

    public void setReTel(String reTel)
    {
        this.reTel = reTel;
    }

    public void setGender()
    {
        if (reBirth.charAt(7) == '1' || reBirth.charAt(7) == '3')
        {
            gender = 'M';
        }
        else
            gender = 'F';
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString()
    {
        return "\n\t┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "\t┃                                  SS HOTEL ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃   [ 회원 정보 확인 ]                      ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 이름         : " + reName + "                  ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 주민등록번호 : " + reBirth + "          ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃ ▷ 전화번호     : " + reTel + "           ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┃                                           ┃\n" +
                "\t┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";

        //"\n이    름 : "+reName+"\n생년월일 : "+reBirth+"\n전화번호 : "+reTel+"\n";
    }
}