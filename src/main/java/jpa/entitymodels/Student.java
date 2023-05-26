package jpa.entitymodels;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Id;


@Entity
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    // fields
    @Id
    @Column(nullable = false, length = 50, name = "email")
    private String sEmail;
    @Column(nullable = false, length = 50, name = "name")
    private String sName;
    @Column(nullable = false, length = 50, name = "password")
    private String sPass;

    // ORM mapping
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Course> sCourses;

    // constructor
    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = new ArrayList<>();
    }

    // empty constructor
    public Student() {
    }

// getters and setters
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }

    public void addCourse(Course course) {
        this.sCourses.add(course);
    }

    @Override
    public String toString() {
        return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses=" + sCourses + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(sCourses, sEmail, sName, sPass);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(sCourses, other.sCourses) && Objects.equals(sEmail, other.sEmail)
                && Objects.equals(sName, other.sName) && Objects.equals(sPass, other.sPass);
    }


}