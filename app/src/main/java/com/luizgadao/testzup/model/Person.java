package com.luizgadao.testzup.model;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class Person {

    private String name;
    private int age;

    public Person( String name, int age ) {

        if ( age < 0 )
            throw new IllegalArgumentException( "age less than 0 is not valid." );

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public boolean canVote(){
        return age >= 16;
    }
}
