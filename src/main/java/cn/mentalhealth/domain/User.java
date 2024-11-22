package cn.mentalhealth.domain;

public class User {
    private int Uid;
    private String Email;
    private String UName;
    private String AvatarUser;

    public User() {
    }

    // 生成getter和setter方法
    public int getUid() {
        return Uid;
    }

    public void setUid(int Uid) {
        this.Uid = Uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getAvatarUser() {
        return AvatarUser;
    }

    public void setAvatarUser(String AvatarUser) {
        this.AvatarUser = AvatarUser;
    }
}