import java.sql.*;

public class StudentDAOImpl implements StudentDAO {

    Connection con = DBConnection.getConnection();

    @Override
public void addStudent(Student student) {

    String sql = "INSERT INTO students(name,email,branch,phone) VALUES(?,?,?,?)";

    try {

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getBranch());
        ps.setString(4, student.getPhone());

        int rows = ps.executeUpdate();

        if(rows > 0){
            System.out.println("Student Added Successfully");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
public void viewStudents() {

    String sql = "SELECT * FROM students";

    try {

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            System.out.println("----------------------------");

            System.out.println("ID : " + rs.getInt("id"));
            System.out.println("Name : " + rs.getString("name"));
            System.out.println("Email : " + rs.getString("email"));
            System.out.println("Branch : " + rs.getString("branch"));
            System.out.println("Phone : " + rs.getString("phone"));

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
public void searchStudent(int id) {

    String sql = "SELECT * FROM students WHERE id=?";

    try {

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("ID : " + rs.getInt("id"));
            System.out.println("Name : " + rs.getString("name"));
            System.out.println("Email : " + rs.getString("email"));
            System.out.println("Branch : " + rs.getString("branch"));
            System.out.println("Phone : " + rs.getString("phone"));

        } else {

            System.out.println("Student Not Found");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}

    @Override
public void updateStudent(Student student) {

    String sql = "UPDATE students SET name=?,email=?,branch=?,phone=? WHERE id=?";

    try {

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getBranch());
        ps.setString(4, student.getPhone());
        ps.setInt(5, student.getId());

        int rows = ps.executeUpdate();

        if(rows>0){

            System.out.println("Student Updated Successfully");

        }else{

            System.out.println("Student Not Found");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}

    @Override
public void deleteStudent(int id) {

    String sql = "DELETE FROM students WHERE id=?";

    try {

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if(rows>0){

            System.out.println("Student Deleted Successfully");

        }else{

            System.out.println("Student Not Found");

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

}
}