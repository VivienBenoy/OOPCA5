
package com.dkit.oopca5.core.dto;

import com.dkit.oopca5.core.CAOService;

import java.util.Objects;

public class Student {
    private int caoNumber;
    private String dateOfBirth;
    private String password;

    public Student(int caoNumber, String dateOfBirth, String password) {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public int getCaoNumber() {
        return caoNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public String format()
    {
        return this.caoNumber+ CAOService.BREAKING_CHARACTER+this.dateOfBirth+CAOService.BREAKING_CHARACTER+this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return caoNumber == student.caoNumber &&
                Objects.equals(dateOfBirth, student.dateOfBirth) &&
                Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caoNumber, dateOfBirth, password);
    }
}