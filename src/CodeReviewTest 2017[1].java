import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// Imagine you are a peer of the developer who committed this (syntactically correct) Java code and asked you to review
// their pull request. You work on the same product but are not familiar with this piece of work or its associated
// requirements.
//
// Please use Java comments for your review feedback, putting them on separate lines around the code. Do not modify the
// code itself.

public class CodeReviewTest {

    //Review Comment: Please define Purpose of the class in one or two liner summary
    volatile Integer totalAge = 0;

    /*Review Comment:: Please use the constructor only for initializing the class, there shouldn't be any 
       logic manipulation except assigning values to few default internal variables
       Also please remove dependecy from consutructor by removing personPersonDatabase paramter fromn constructor 
       and create getter and setter methods for this purpose, otherwise it'd be waste of one resource(Object creation) 
       in case change of value of attribute
    */
    CodeReviewTest(PersonDatabase<Person> personPersonDatabase) {
        Person[] persons = null;
        try {
            persons = personPersonDatabase.getAllPersons();
        } catch (IOException e) {

            //Review Comment: Please don't leave the catch body unattended, use the logger to record the exception
        }

        List<Person> personsList = new LinkedList();

        /*
          Review Comment: 
          1. Altough nothing wrong in this approach but it'd be better if use 'for-each' loop here,
          because for each loop is efficient in all cases as its uses Iterator specific for the collection plus
          I dont see index has been used anywhere else.
          2. Also populating personList here should be done in sepearate method named 'populatePersonList'
             so that code could satisfy single responsibility principles
        */
        for (int i = 0; i <= persons.length; i++) {
            personsList.add(persons[i]);
        }

        personsList.parallelStream().forEach(person -> {
            totalAge += person.getAge();
        });

        List<Person> males = new LinkedList<>();

        //Review Comment:: Create separate method named it 'populateMalePersonsList'
        for (Person person : personsList) {
            switch (person.gender) {
                case "Female": personsList.remove(person);
                case "Male"  : males.add(person);
            }
        }

        System.out.println("Total age =" + totalAge);
        System.out.println("Total number of females =" + personsList.size());
        System.out.println("Total number of males =" + males.size());
    }

}


//Review Comment: Please define Purpose of the class in one or two liner summary
class Person {

    private int age;
    private String firstName;
    private String lastName;
    String gender;

    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        return this.lastName == ((Person)obj).lastName;
    }

}

//Review Comment: Please define Purpose of the Interface in one or two liner summary
interface PersonDatabase<E> {

    Person[] getAllPersons() throws IOException;

}
