package model;

import Services.Functions;

import java.util.List;
import java.util.Random;

public class CCCD {
    private String id;
    private String placeOfBirth;
    private String gender;
    private int dob;
    private int randomNumber;

    public CCCD(String placeOfBirth, String gender, int dob) {
        setRandomNumber();
        setId(placeOfBirth, gender, dob, randomNumber);
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
        this.dob = dob;
    }

    public CCCD() {
    }

    public String getId() {
        return id;
    }


    private void setId(String placeOfBirth, String gender, int dob, int randomNumber) {
        String idNumber = "";
        Functions func = new Functions();
        List<Province> provinces = func.getDefaultProvince();
//        append province code to CCCD
        for (Province pro : provinces) {
            if (placeOfBirth.equalsIgnoreCase(pro.getName())) {
                idNumber += pro.getCode();
                break;
            }
        }

//        append gender code to CCCD
        idNumber += func.renderGenderToCode(gender, dob);
//        appending code of dob
        idNumber += String.valueOf(dob).substring(2);
//        appending code randomNumber
        idNumber += randomNumber;
        this.id = idNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        this.randomNumber = Integer.parseInt(String.format("%06d", number));
    }

    @Override
    public String toString() {
        return "Gioi tinh: " + gender + " | " + dob + "\nNoi Sinh: " + placeOfBirth + "\nSo ngau nhien: " + randomNumber;
    }

    public void displayPob() {
        System.out.println("Noi Sinh: " + placeOfBirth);
    }

    public void displayAgeAndGender() {
        System.out.println("Gioi tinh: " + gender + " | " + dob);
    }

    public void displayRandomNumber() {
        System.out.println("So ngau nhien: " + randomNumber);
    }
}
