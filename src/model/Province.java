package model;

public class Province {
    private String code;
    private String name;

    public Province(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public Province() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
