package org.example;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    //TODO
//     * commons.lang
//     *  toString
//     *  equals
//     *  hashCode
//     * gson
//     *  Serialize
//     * class Person:
//     *  fname
//     *  lname
//     *  age
//     *
//     * @param args

    public static void main(String[] args) throws IOException {
        String path = "person.json";
        new Person("Ivan", "Malyarchuck", LocalDate.of(1990, 7, 7))
                .writeJSON(path);


        System.out.println(new Person().readJSON(path).toString());



    }
}