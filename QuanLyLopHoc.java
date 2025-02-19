import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Student {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String address;
    private String className;
    // Điểm của 5 môn học
    private double scoreOOP;      // Lập trình hướng đối tượng
    private double scoreQLDA;     // Quản lý dự án
    private double scoreML;       // Học Máy
    private double scoreCSDL;     // Cơ sở dữ liệu
    private double scoreLTUD;     // Lập trình ứng dụng cho TBDĐ

    public Student(String firstName, String lastName, String birthdate, String address, String className,
                   double scoreOOP, double scoreQLDA, double scoreML, double scoreCSDL, double scoreLTUD) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
        this.className = className;
        this.scoreOOP = scoreOOP;
        this.scoreQLDA = scoreQLDA;
        this.scoreML = scoreML;
        this.scoreCSDL = scoreCSDL;
        this.scoreLTUD = scoreLTUD;
    }

    // Tính điểm trung bình của sinh viên
    public double tinhDiemTrungBinh() {
        return (scoreOOP + scoreQLDA + scoreML + scoreCSDL + scoreLTUD) / 5.0;
    }

    // Xếp loại dựa trên điểm trung bình
    public String xepLoai() {
        double avg = tinhDiemTrungBinh();
        if (avg >= 8.5)
            return "A";
        else if (avg >= 7.0)
            return "B";
        else if (avg >= 5.5)
            return "C";
        else if (avg >= 4.0)
            return "D";
        else
            return "F";
    }

    @Override
    public String toString() {
        return String.format("Tên: %s %s, Ngày sinh: %s, Địa chỉ: %s, Lớp: %s, Điểm TB: %.2f, Xếp loại: %s",
                firstName, lastName, birthdate, address, className, tinhDiemTrungBinh(), xepLoai());
    }
}

// Lớp biểu diễn một lớp học với danh sách sinh viên
class LopHoc {
    private String maLop;
    private List<Student> danhSachSinhVien;

    public LopHoc(String maLop) {
        this.maLop = maLop;
        danhSachSinhVien = new ArrayList<>();
    }

    public String getMaLop() {
        return maLop;
    }

    public void addStudent(Student s) {
        danhSachSinhVien.add(s);
    }

    // Hiển thị danh sách sinh viên của lớp
    public void hienThiDanhSachSinhVien() {
        for (Student s : danhSachSinhVien) {
            System.out.println(s);
        }
    }

    // Đếm số lượng sinh viên theo xếp loại: A, B, C, D và <D (F)
    public Map<String, Integer> demSoLuongTheoHang() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 0);
        map.put("B", 0);
        map.put("C", 0);
        map.put("D", 0);
        map.put("F", 0);  // F tương đương với <D
        for (Student s : danhSachSinhVien) {
            String rank = s.xepLoai();
            map.put(rank, map.get(rank) + 1);
        }
        return map;
    }
}

public class QuanLyLopHoc {
    public static void main(String[] args) {
        // Tạo danh sách các lớp học
        List<LopHoc> danhSachLop = new ArrayList<>();

        // Tạo lớp CNTT1 và thêm sinh viên
        LopHoc lop1 = new LopHoc("CNTT1");
        lop1.addStudent(new Student("Nguyen", "A", "1999-01-01", "123 ABC", "CNTT1",
                9.0, 8.5, 8.0, 7.5, 8.0));
        lop1.addStudent(new Student("Tran", "B", "1998-05-05", "456 DEF", "CNTT1",
                6.0, 7.0, 6.5, 7.0, 7.0));
        lop1.addStudent(new Student("Pham", "C", "2000-03-10", "789 XYZ", "CNTT1",
                3.0, 4.0, 4.5, 3.5, 4.0));
        danhSachLop.add(lop1);

        // Tạo lớp CNTT2 và thêm sinh viên
        LopHoc lop2 = new LopHoc("CNTT2");
        lop2.addStudent(new Student("Le", "D", "2000-02-02", "321 QWE", "CNTT2",
                8.0, 7.5, 8.5, 8.0, 8.5));
        lop2.addStudent(new Student("Do", "E", "1999-11-11", "654 RTY", "CNTT2",
                4.0, 4.5, 5.0, 4.0, 3.5));
        danhSachLop.add(lop2);

        // Hiển thị danh sách các lớp
        System.out.println("Danh sách các lớp:");
        for (LopHoc lop : danhSachLop) {
            System.out.println("- " + lop.getMaLop());
        }

        // Nhập mã lớp cần xem thông tin
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã lớp cần xem: ");
        String maLopNhap = sc.nextLine();

        boolean timThay = false;
        for (LopHoc lop : danhSachLop) {
            if (lop.getMaLop().equalsIgnoreCase(maLopNhap)) {
                timThay = true;
                System.out.println("\nThông tin sinh viên lớp " + lop.getMaLop() + ":");
                lop.hienThiDanhSachSinhVien();

                // Hiển thị tổng kết số sinh viên theo xếp loại
                Map<String, Integer> bangXepLoai = lop.demSoLuongTheoHang();
                System.out.println("\nTổng kết số sinh viên theo xếp loại:");
                System.out.println("A: " + bangXepLoai.get("A") + " sinh viên") ;
                System.out.println("B: " + bangXepLoai.get("B") +" sinh viên" );
                System.out.println("C: " + bangXepLoai.get("C") +" sinh viên" );
                System.out.println("D: " + bangXepLoai.get("D") +" sinh viên" );
                System.out.println("<D: " + bangXepLoai.get("F") +" sinh viên");
                break;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy lớp có mã: " + maLopNhap);
        }

        sc.close();
    }
}
