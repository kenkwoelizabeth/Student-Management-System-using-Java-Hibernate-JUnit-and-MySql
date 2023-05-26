package jpa;


import jpa.entitymodels.Course;
import jpa.service.CourseDAOImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;



public class CourseDAOImplTest {


    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
CourseDAOImpl courseDAO;




    @Before("0")
    public void setup() {
        // Perform setup actions here
        // Initialize Hibernate session factory or any other resources needed for the tests
        Configuration configuration = new Configuration();
        // Load Hibernate configuration file
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();

    }

        @Test
        public void getAllCoursesTest() {

        // TO INITIALIZE THE CONNECTION
            courseDAO = new CourseDAOImpl();

            // when
            List<Course> courses = courseDAO.getAllCourses();

            // then
            Assertions.assertEquals(courses.size(), 10);
            System.out.println(" Test Passed");
        }


    @After("")
    public void teardown() {
        transaction.rollback();
        session.close();
        sessionFactory.close();
    }
}
