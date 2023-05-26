package jpa.dao;

import jpa.entitymodels.Course;

import java.util.List;

public interface CourseDAO {

    // course interface method
    List<Course> getAllCourses();
}
