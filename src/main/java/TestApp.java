import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestApp {
    public static void main(String[] args){
        SessionFactory sessionFactory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(entities.Student.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Instructor ramesh = new Instructor("ramesh.com","123dsad","Ramesh","Sippi",null);
        Course it = new Course("IT",ramesh);
        it.add(new Review("Great course..Loved it"));
        Course cs = new Course("CS",ramesh);
        cs.add(new Review("Cool course..Good job"));
        cs.add(new Review("Very Good course"));
        Instructor suresh = new Instructor("suresh.com","123dsad","Suresh","Sippi",null);
        Course mc = new Course("mc",suresh);
        mc.add(new Review("New concepts"));

        ramesh.add(it);
        ramesh.add(cs);
        suresh.add(mc);

        entities.Student abhishek = new entities.Student("abhishek","pratap","ab_pt.com");
        entities.Student sidhesh = new entities.Student("sidhesh","singh","ss.com");
        entities.Student dinesh = new entities.Student("dinesh","singh","ds.com");

        abhishek.add(new Book("it book"));
        sidhesh.add(new Book("cs book"));
        sidhesh.add(new Book("it book"));
        dinesh.add(new Book("mc book"));

        session.persist(ramesh);
        session.persist(suresh);

        session.persist(abhishek);
        session.persist(sidhesh);
        session.persist(dinesh);
        session.getTransaction().commit();

        Session getSession = sessionFactory.getCurrentSession();
        getSession.beginTransaction();
        entities.Student student = getSession.get(entities.Student.class,Long.valueOf(1));
        entities.Student student2 = getSession.get(entities.Student.class,Long.valueOf(2));
        entities.Student student3 = getSession.get(entities.Student.class,Long.valueOf(3));
        System.out.println(student.getBooks());
        System.out.println(student2.getBooks());
        System.out.println(student3.getBooks());
        getSession.delete(student);
        getSession.getTransaction().commit();

    }
}
