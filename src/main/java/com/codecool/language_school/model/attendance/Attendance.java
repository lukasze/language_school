package com.codecool.language_school.model.attendance;

import com.codecool.language_school.model.user.Student;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Annotation
public class Attendance {

    //@Annotation
    private long id;

    //@Annotation
    private Set<AttendanceEntry> attendanceHistory;

    //@Annotation
    private Student student;

    public Attendance(Student student) {
        this.attendanceHistory = new HashSet<>();
        this.student = student;
    }

    public Attendance() {}

    public long getId() {
        return id;
    }

    public Set<AttendanceEntry> getAttendanceHistory() {
        return attendanceHistory;
    }

    public Student getStudent() {
        return student;
    }

    public void addAttendanceEntry(AttendanceEntry attendanceEntry) {
        attendanceHistory.add(attendanceEntry);
    }

    public void setId(long attendanceId) {
        this.id = attendanceId;
    }

    public void setAttendanceHistory(Set<AttendanceEntry> attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
