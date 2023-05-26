package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public class StudentDAOImpl implements StudentDAO {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public StudentDAOImpl() {
    }

    @Override
    public List<Student> getAllStudents() {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "from Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        List<Student> students = query.list();
        transaction.commit();
        session.close();
        factory.close();
        return students;

    }

    @Override
    public Student getStudentByEmail(String sEmail) {

        Student student = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, sEmail);
            transaction.commit();
            factory.close();
            session.close();

        } catch (Error e) {
            System.out.println("User not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
        return student;
    }

    @Override
    public boolean validateStudent(String sEmail, String sPass) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("select s from Student s where email=:email && password=:password", Student.class);
            query.setParameter("email", sEmail);
            query.setParameter("password", sPass);
            List<Student> students = query.getResultList();
            if (students.isEmpty()) {
                System.out.println("User not found");
                transaction.commit();
                session.close();
                factory.close();
                return false;
            }
            for (Student student : students) {
                System.out.println(student);
            }
            transaction.commit();
            session.close();
            factory.close();
            return true;
        } catch (Exception e) {
            System.out.println("User not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
            transaction.commit();
            session.close();
            factory.close();
            return false;
        }


    }

    @Override
    public boolean registerStudentToCourse(String sEmail, int cId) {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        // Check if the student is already registered for the course
        Query<Student> query = session.createQuery("from Student where sEmail = :email", Student.class);
        query.setParameter("email", sEmail);
        Student student = query.getSingleResult();

        if (student == null) {

            transaction.rollback();
            session.close();
            factory.close();
            return false;
        }

        Query<Course> query2 = session.createQuery("from Course where id= :id", Course.class);
        query2.setParameter("id", cId);
        Course course = query2.getSingleResult();

        if (course == null) {
            transaction.rollback();
            session.close();
            factory.close();
            return false;
        }
        if (student.getsCourses().contains(course)) {
            return false;
        }
        student.addCourse(course);
        transaction.commit();
        session.close();

        factory.close();
        return true;
    }


    @Override
    public List<Course> getStudentCourses(String sEmail) {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "From Student Where sEmail=:email";

        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", sEmail);
        Student student = query.getSingleResult();
        Hibernate.initialize(student.getsCourses());
        List<Course> courses = student.getsCourses();

        transaction.commit();
        session.close();
        factory.close();

        return courses;
    }


}

