public class Student {

    private int id;
    private String name;
    private String email;
    private String branch;
    private String phone;

    public Student() {
    }

    public Student(String name, String email, String branch, String phone) {
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.phone = phone;
    }

    public Student(int id, String name, String email, String branch, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}