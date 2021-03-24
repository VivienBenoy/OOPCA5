package com.dkit.oopca5.server.dto;

public class StudentChoices {
    private int caoNumber;
    private String courseId;

    public StudentChoices(int caoNumber, String courseId) {
        this.caoNumber = caoNumber;
        this.courseId = courseId;
    }

    public int getCaoNumber() {
        return caoNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "StudentChoices{" +
                "caoNumber=" + caoNumber +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
