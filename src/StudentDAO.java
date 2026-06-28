import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);

    void viewStudents();

    void searchStudent(int id);

    void updateStudent(Student student);

    void deleteStudent(int id);

}