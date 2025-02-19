package Nhom_11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class sinhVien {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String classID;
    private double oop;
    private double qlda;
    private double hm;
    private double csdl;
    private double mobileApp;

    public sinhVien(String firstName, String lastName, String birthDate, String address, String classID,
            double oop, double qlda, double hm, double csdl, double mobileApp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.classID = classID;
        this.oop = oop;
        this.qlda = qlda;
        this.hm = hm;
        this.csdl = csdl;
        this.mobileApp = mobileApp;
    }

    public double getDiemTB() {
        return (oop + qlda + hm + csdl + mobileApp) / 5.0;
    }

    public String xepHang() {
        double xh = getDiemTB();
        if (xh >= 8.5)
            return "A";
        else if (xh >= 7.0)
            return "B";
        else if (xh >= 5.5)
            return "C";
        else if (xh >= 4.0)
            return "D";
        else
            return "<D";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + classID + " - Rank: " + xepHang();
    }
}

class Lop {
    private String tenLop;
    private List<sinhVien> sv;

    public Lop(String tenLop) {
        this.tenLop = tenLop;
        this.sv = new ArrayList<>();
    }

    // them sinh vien moi vao danh sach sinh vien
    public void themSV(sinhVien sinhvien) {
        sv.add(sinhvien);
    }

    public void hienThi() {
        for (sinhVien x : sv) {
            System.out.println(x);
        }
    }

    public int tongXepHang(String diem) {
        int count = 0;
        for (sinhVien x : sv) {
            if (x.xepHang().equals(diem)) {
                count++;
            }
        }
        return count;
    }

    public String hienThiLop() {
        return tenLop;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Lop> Lop = new ArrayList<>();

        // Sample data
        Lop class1 = new Lop("CNTT1");
        class1.themSV(new sinhVien("Nguyen", "Van A", "01/01/2000", "Hanoi", "CNTT1", 8.0, 7.5, 9.0, 8.5, 7.0));
        class1.themSV(new sinhVien("Tran", "Thi B", "02/02/2000", "Hanoi", "CNTT1", 6.0, 5.5, 7.0, 6.5, 5.0));
        Lop.add(class1);

        Lop class2 = new Lop("CNTT2");
        class2.themSV(new sinhVien("Le", "Van C", "03/03/2000", "Hanoi", "CNTT2", 9.0, 8.5, 9.5, 9.0, 8.0));
        Lop.add(class2);

        System.out.println("Danh sach cac lop: ");
        for (Lop x : Lop) {
            System.out.println(x.hienThiLop());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNhap ma lop: ");
        String index = scanner.nextLine();

        for (Lop x : Lop) {
            if (x.hienThiLop().equals(index)) {
                System.out.println("\nDanh sach sinh vien trong lop " + index + ":");
                x.hienThi();

                System.out.println("\nTong cac sinh vien theo rank:");
                System.out.println("A: " + x.tongXepHang("A"));
                System.out.println("B: " + x.tongXepHang("B"));
                System.out.println("C: " + x.tongXepHang("C"));
                System.out.println("D: " + x.tongXepHang("D"));
                System.out.println("<D: " + x.tongXepHang("<D"));
                return;
            }
            scanner.close();
        }

        System.out.println("Khong tim thay lop " + index);

    }
}
