package org.example;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;


public class Person{
    private String fName;
    private String lName;
    private LocalDate bDate;

    public Person(String fName, String lName, LocalDate bDate) {
        this.fName = fName;
        this.lName = lName;
        this.bDate = bDate;
    }

    public Person(){

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(fName)
                .append(lName)
                .append(getAge() + "y.o.")
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(fName).append(lName).append(bDate).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder()
                .append(this, obj)
                .isEquals();
    }

    public void writeJSON(String path) throws IOException {
        new JsonWriter(new FileWriter(path))
                .beginObject().name("fName").value(fName)
                .name("lName").value(lName)
                .name("bDate").value(String.valueOf(bDate.getLong(ChronoField.EPOCH_DAY)))
                .endObject().flush();
    }

    public Person readJSON(String path) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(path));
        String fname = null;
        String lname = null;
        long day = -1;
        reader.beginObject();
        while (reader.hasNext()){
            String name = reader.nextName();
            if (name.equals("fName")){
                fname = reader.nextString();
            }
            else if (name.equals("lName")){
                lname = reader.nextString();
            }
            else if (name.equals("bDate")){
                day = reader.nextLong();
            }
            else {
                reader.skipValue();
            }


        }
        reader.endObject();
        LocalDate date = LocalDate.ofEpochDay(day);
        return new Person(fname, lname, date);
    }

    public long getAge(){
        return ChronoUnit.YEARS.between(bDate, LocalDate.now());
    }


}
