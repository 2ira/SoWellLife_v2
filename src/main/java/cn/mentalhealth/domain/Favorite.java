package cn.mentalhealth.domain;

public class Favorite {
    private int Uid;
    private int Rid;
    public Favorite() {
    }

    // 生成getter和setter方法
    public int getUid() {
        return Uid;
    }

    public void setUid(int Uid) {
        this.Uid = Uid;
    }

    public int getRid() {
        return Rid;
    }

    public void setRid(int Rid) {
        this.Rid = Rid;
    }
}