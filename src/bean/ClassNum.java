package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

    private String class_num;
    private String old_class_num;
    private School school;
    private int c_count;
    private String message;     // 更新結果メッセージ
    private boolean success;    // 成功フラグ

    // ゲッターとセッター
    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    public String getOld_class_num() {
        return old_class_num;
    }

    public void setOld_class_num(String old_class_num) {
        this.old_class_num = old_class_num;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getC_count() {
        return c_count;
    }

    public void setC_count(int c_count) {
        this.c_count = c_count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
