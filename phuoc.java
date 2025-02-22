import java.util.*;

class Student {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String address;
    private String className;
    private double scoreOOP, scoreQLDA, scoreML, scoreCSDL, scoreLTUD;

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

    public double tinhDiemTrungBinh() {
        return (scoreOOP + scoreQLDA + scoreML + scoreCSDL + scoreLTUD) / 5.0;
    }

    public String xepLoai() {
        double avg = tinhDiemTrungBinh();
        if (avg >= 8.5) return "A";
        else if (avg >= 7.0) return "B";
        else if (avg >= 5.5) return "C";
        else if (avg >= 4.0) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return String.format("Tên: %s %s, Ngày sinh: %s, Địa chỉ: %s, Lớp: %s, Điểm TB: %.2f, Xếp loại: %s",
                firstName, lastName, birthdate, address, className, tinhDiemTrungBinh(), xepLoai());
    }
}

class LopHoc {
    private String maLop;
    private List<Student> danhSachSinhVien;

    public LopHoc(String maLop) {
        this.maLop = maLop;
        this.danhSachSinhVien = new ArrayList<>();
    }

    public String getMaLop() {
        return maLop;
    }

    public void addStudent(Student s) {
        danhSachSinhVien.add(s);
    }

    public void hienThiDanhSachSinhVien() {
        for (Student s : danhSachSinhVien) {
            System.out.println(s);
        }
    }

    public Map<String, Integer> demSoLuongTheoHang() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 0);
        map.put("B", 0);
        map.put("C", 0);
        map.put("D", 0);
        map.put("F", 0);
        for (Student s : danhSachSinhVien) {
            String rank = s.xepLoai();
            map.put(rank, map.get(rank) + 1);
        }
        return map;
    }
}

public class QuanLyLopHoc {
    public static void main(String[] args) {
        List<LopHoc> danhSachLop = new ArrayList<>();

        LopHoc lop1 = new LopHoc("CNTT1");
        lop1.addStudent(new Student("Nguyen", "A", "1999-01-01", "123 ABC", "CNTT1", 9.0, 8.5, 8.0, 7.5, 8.0));
        lop1.addStudent(new Student("Tran", "B", "1998-05-05", "456 DEF", "CNTT1", 6.0, 7.0, 6.5, 7.0, 7.0));
        lop1.addStudent(new Student("Pham", "C", "2000-03-10", "789 XYZ", "CNTT1", 3.0, 4.0, 4.5, 3.5, 4.0));
        danhSachLop.add(lop1);

        LopHoc lop2 = new LopHoc("CNTT2");
        lop2.addStudent(new Student("Le", "D", "2000-02-02", "321 QWE", "CNTT2", 8.0, 7.5, 8.5, 8.0, 8.5));
        lop2.addStudent(new Student("Do", "E", "1999-11-11", "654 RTY", "CNTT2", 4.0, 4.5, 5.0, 4.0, 3.5));
        danhSachLop.add(lop2);

        System.out.println("Danh sách các lớp:");
        for (LopHoc lop : danhSachLop) {
            System.out.println("- " + lop.getMaLop());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nNhập mã lớp cần xem: ");
        String maLopNhap = sc.nextLine().trim();  // Xóa khoảng trắng thừa

        boolean timThay = false;
        for (LopHoc lop : danhSachLop) {
            if (lop.getMaLop().equalsIgnoreCase(maLopNhap)) {
                timThay = true;
                System.out.println("\nThông tin sinh viên lớp " + lop.getMaLop() + ":");
                lop.hienThiDanhSachSinhVien();

                Map<String, Integer> bangXepLoai = lop.demSoLuongTheoHang();
                System.out.println("\nTổng kết số sinh viên theo xếp loại:");
                System.out.printf("A: %d sinh viên\n", bangXepLoai.get("A"));
                System.out.printf("B: %d sinh viên\n", bangXepLoai.get("B"));
                System.out.printf("C: %d sinh viên\n", bangXepLoai.get("C"));
                System.out.printf("D: %d sinh viên\n", bangXepLoai.get("D"));
                System.out.printf("<D: %d sinh viên\n", bangXepLoai.get("F"));
                break;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy lớp có mã: " + maLopNhap);
        }

        sc.close();
    }
}
