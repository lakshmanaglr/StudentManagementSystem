import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAOImpl();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Branch: ");
                    String branch = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    Student student = new Student(name, email, branch, phone);
                    dao.addStudent(student);
                    break;

                case 2:
                    dao.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int searchId = sc.nextInt();
                    dao.searchStudent(searchId);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter New Branch: ");
                    String newBranch = sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();

                    Student updatedStudent = new Student(id, newName, newEmail, newBranch, newPhone);
                    dao.updateStudent(updatedStudent);
                    break;

                case 5:
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = sc.nextInt();
                    dao.deleteStudent(deleteId);
                    break;

                case 6:
                    System.out.println("Thank you for using Student Management System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}