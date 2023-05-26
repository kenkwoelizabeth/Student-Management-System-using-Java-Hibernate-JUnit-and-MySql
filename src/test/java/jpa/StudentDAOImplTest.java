package jpa;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.service.StudentDAOImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class StudentDAOImplTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    StudentDAOImpl student;


    @Before("")
    public void setup() {

            // Perform setup actions here
            // Initialize Hibernate session factory or any other resources needed for the tests
            Configuration configuration = new Configuration();
            // Load Hibernate configuration file
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();


    }


        @Test
        public void getAllStudents(){
            // TO INITIALIZE THE CONNECTION
            StudentDAOImpl service = new StudentDAOImpl();

            // when
            List<Student> student = service.getAllStudents();

            // then
            Assertions.assertThat(student.isEmpty());
            System.out.println(" Test Passed");
        }
    @Test
    public void getStudentByEmail() {
        StudentDAO service1 = new StudentDAOImpl();

            // Provide the email address for the student you want to retrieve
            String email = "hluckham0@google.ru";
            // Call the getStudentByEmail() method with the email address
            Student student1 = service1.getStudentByEmail(email);
            // Perform assertions on the returned student object
            Assertions.assertThat(student1.getsEmail()).isEqualTo(email);
        System.out.println(" Test Passed");
        }




    @After("")
    public void teardown() {
        transaction.rollback();
        session.close();
        sessionFactory.close();
    }

}
