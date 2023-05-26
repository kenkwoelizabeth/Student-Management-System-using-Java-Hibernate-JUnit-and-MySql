package jpa.entitymodels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Id;


@Entity
@Table(name = "Course")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
//course fields
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;
    @Column(nullable = false, length = 50, name = "name")
    private String cName;
    @Column(nullable = false, length = 50, name = "instructor")
    private String cInstructor;

    // constructor
    public Course(int cId, String cName, String cInstructor) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructor = cInstructor;
    }


    // empty constructor
    public Course() {
    }



    // getters and setters
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructor() {
        return cInstructor;
    }

    public void setcInstructor(String cInstructor) {
        this.cInstructor = cInstructor;
    }

    @Override
    public String toString() {
        return "Course [cId=" + cId + ", cName=" + cName + ", cInstructor=" + cInstructor + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cInstructor, cName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return cId == course.cId &&
                Objects.equals(cName, course.cName) &&
                Objects.equals(cInstructor, course.cInstructor);
    }


}