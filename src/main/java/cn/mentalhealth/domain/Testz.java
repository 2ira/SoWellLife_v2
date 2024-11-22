package cn.mentalhealth.domain;

import java.time.LocalDateTime;

public class Testz {
    private int Tid;
    private int Uid;
    private LocalDateTime Time;
    private String Tag;
    private String Result;

    public Testz() {
    }

    // 生成getter和setter方法
    public int getTid() {
        return Tid;
    }

    public void setTid(int Tid) {
        this.Tid = Tid;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int Uid) {
        this.Uid = Uid;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime Time) {
        this.Time = Time;
    }

    public String getTag() {
        if (Tag == null) {
            Tag = "";
        }
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public String getResult() {
        if (Result == null) {
            Result = "";
        }
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
}