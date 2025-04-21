package de.tum.in.ase.eist;

public abstract class Person {
	// TODO: implement according to problem statement
    private String name;
    private int age;
    private String tumId;

    public Person(String name, int age, String tumId) {
        this.name = name;
        this.age = age;
        this.tumId = tumId;
    }
    // final bc: this methode should not be overwritten
    public void say(String tx) {
        System.out.println(name + " said: " + tx);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTumId() {
        return tumId;
    }
}
