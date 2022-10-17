package Services;

import Utils.Constant;
import model.CCCD;
import model.Province;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Processor {
    /*      Start Screen      */
    public void selectionScreen() {
        System.out.println("+---------------+-----------------------------+----------------+");
        System.out.println("| NGAN HANG SO  |  " + Constant.AUTHOR + Constant.VERSION + "                             |");
        System.out.println("| 1. Nhap CCCD                                                 |");
        System.out.println("| 0. Thoat                                                     |");
        System.out.println("+---------------+-----------------------------+----------------+");
        System.out.print("Chuc nang: ");
    }

    public void checkInfoScreen() {
        System.out.println("    | 1. Kiem tra noi sinh");
        System.out.println("    | 2. Kiem tra tuoi, gioi tinh");
        System.out.println("    | 3. Kiem tra so ngau nhien");
        System.out.println("    | 0. Thoat");
        System.out.print("Chuc nang: ");
    }
    /*      End Screen      */

    /*      Start Adding default value to model      */
    List<CCCD> list;

    public List<Province> getDefaultProvince() {
        List<Province> provinces = new ArrayList<>();
        provinces.add(new Province("Hà Nội", "001"));
        provinces.add(new Province("Hà Giang", "002"));
        provinces.add(new Province("Ninh Bình", "037"));
        provinces.add(new Province("Đà Nẵng", "048"));
        provinces.add(new Province("Hồ Chí Minh", "079"));

        return provinces;
    }

    public List<CCCD> getDefaultCCCD() {
        List<CCCD> CCCDs = new ArrayList<>();
        CCCDs.add(new CCCD("Hà Nội", "Nam", 2015));
        CCCDs.add(new CCCD("Hồ Chí Minh", "Nam", 1925));
        CCCDs.add(new CCCD("Ninh Bình", "Nu", 1953));
        CCCDs.add(new CCCD("Hà Giang", "Nu", 1993));
        CCCDs.add(new CCCD("Đà Nẵng", "Nam", 2099));

        if (list == null) {
            list = CCCDs;
        } else {
            return list;
        }
        return list;
    }

    /*      End Adding default value to model*/

    /*      start main menu       */
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            selectionScreen();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    list = getDefaultCCCD();
                    inputVerifyCode();
                    inputCccd();
                    break;
                case 2:
                    System.out.println("ok 2");
                    break;
                default:
                    System.out.println("Vui long nhap dung cac chuc nang hien co.");
                    break;
            }


        } while (choice != 0);
    }
    /*      End main menu       */

    /*      The Second menu*/
    public void checkInfo(CCCD cccd) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            checkInfoScreen();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    cccd.displayPob();
                    break;
                case 2:
                    cccd.displayAgeAndGender();
                    break;
                case 3:
                    cccd.displayRandomNumber();
                    break;
                default:
                    System.out.println("Vui long nhap dung chuc nang!");
                    break;
            }

        } while (choice != 0);
    }
/*      End the second menu     */

    /**
     * Return the CCCD is searched by id
     * @param id the CCCD code is inputted
     * @param cccds the default CCCD list
     */
    public CCCD getCccdById(String id, List<CCCD> cccds) {
        for (CCCD cccd : cccds) {
            if (id.equals(cccd.getId()))
                return cccd;
        }
        return null;
    }

    /*      The feature input CCCD and validation       */
    public void inputCccd() {
        Scanner sc = new Scanner(System.in);
        for (CCCD cd : list) {
            System.out.println(cd.getId());
        }
        System.out.print("Vui long nhap so CCCD: ");
        String id = sc.nextLine();
        if (!id.matches("\\d{12}")) {
            System.out.println("------ Value " + id + " is a valid CCCD!   ------");
            inputCccd();
        }
        else {
            System.out.println("Invalid CCCD.");
            CCCD newCccd = getCccdById(id, list);
            if (newCccd == null) {
                System.out.println("-------- Khong tim thay CCCD! --------");
                inputCccd();
            } else {
                checkInfo(newCccd);

            }
        }

    }

    /*      The feature input verify code and validation        */
    public void inputVerifyCode() {
        Scanner sc = new Scanner(System.in);
        int choice;
        String newVerifyCode;
        String inputCode;
        boolean boo;

        do {
            boo = false;
            System.out.println("    Lua chon muc do xac minh:");
            System.out.println("        1. EASY");
            System.out.println("        2. HARD");
            System.out.print("Lua chon: ");
            choice = sc.nextInt();
            Scanner scanner = new Scanner(System.in);
            switch (choice) {
                case 1:
                    newVerifyCode = verifyCode(3);
                    System.out.println("Nhap ma xac thuc: " + newVerifyCode);
                    inputCode = scanner.nextLine();
                    if (inputCode.equals(newVerifyCode))
                        boo = true;
                    else
                        System.out.println("Vui long nhap lai!");
                    break;
                case 2:
                    newVerifyCode = verifyCode(6);
                    System.out.println("Nhap ma xac thuc: " + newVerifyCode);
                    inputCode = scanner.nextLine();
                    if (inputCode.equals(newVerifyCode))
                        boo = true;
                    else
                        System.out.println("Vui long nhap lai!");
                    break;
                default:
                    break;
            }
        } while (!boo);
    }

    /**
    * Return String verify code
    * @param num is digit of verify code
    */
    public String verifyCode(int num) {
        StringBuilder verifyCode = new StringBuilder();
        int number = 0; // 0-9 : '0'-'9'
        int lower = 10; // 10-35: [a-z]
        int upper = 36; // 36-61 [A-Z]
        int max = upper + 25;

        for (int i = 0; i < num; i++) {
            int ran = (new Random()).nextInt(max);
            char c;

            if (ran >= upper) {
                c = (char) ('A' + (ran - upper));
            } else if (ran >= lower) {
                c = (char) ('a' + (ran - lower));
            } else {
                c = (char) ('0' + (ran - number));
            }
            verifyCode.append(c);
        }
        return String.valueOf(verifyCode);
    }

    /**
    * Return the String code reference with gender
     * @param gender the gender is inputted
     * @param dob the date of birth inputted
    */
    public String renderGenderToCode(String gender, int dob) {
        if (dob >= 1900 && dob <= 1999) {
            if (gender.equalsIgnoreCase("nam"))
                return "0";
            else if (gender.equalsIgnoreCase("nu"))
                return "1";
        } else if (dob >= 2000 && dob <= 2099) {
            if (gender.equalsIgnoreCase("nam"))
                return "2";
            else if (gender.equalsIgnoreCase("nu"))
                return "3";
        } else if (dob >= 2100 && dob <= 2199) {
            if (gender.equalsIgnoreCase("nam"))
                return "4";
            else if (gender.equalsIgnoreCase("nu"))
                return "5";
        } else if (dob >= 2200 && dob <= 2299) {
            if (gender.equalsIgnoreCase("nam"))
                return "6";
            else if (gender.equalsIgnoreCase("nu"))
                return "7";
        } else if (dob >= 2300 && dob <= 2399) {
            if (gender.equalsIgnoreCase("nam"))
                return "8";
            else if (gender.equalsIgnoreCase("nu"))
                return "9";
        }
        return null;
    }

}
