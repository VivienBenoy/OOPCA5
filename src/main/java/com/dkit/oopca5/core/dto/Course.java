package com.dkit.oopca5.core.dto;

import com.dkit.oopca5.core.CAOService;

import java.util.Comparator;
import java.util.Objects;

public class Course {
    private String courseId;
    private int level;
    private String title;
    private String institution;

    public Course(String courseId, int level, String title, String institution) {
        this.courseId = courseId;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getInstitution() {
        return institution;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", level=" + level +
                ", title='" + title + '\'' +
                ", institution='" + institution + '\'' +
                '}';
    }
    public String format()
    {
        return this.courseId+ CAOService.BREAKING_CHARACTER+this.level+CAOService.BREAKING_CHARACTER+this.title+CAOService.BREAKING_CHARACTER+this.institution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return level == course.level &&
                Objects.equals(courseId, course.courseId) &&
                Objects.equals(title, course.title) &&
                Objects.equals(institution, course.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, level, title, institution);
    }


}