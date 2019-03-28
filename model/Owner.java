package model;

public class Owner {
    private String firstName;
    private String lastName;
    private int age;

    public Owner(String first, String last, int age){
        this.firstName = first;
        this.lastName = last;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String toString(){
        return String.format("Owner Name: " + getFirstName() + " " + getLastName() + "%n Owner Age: " + getAge());
    }
}
