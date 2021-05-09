package com.codecool.language_school.model.attendance;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

//@Annotation
public class AttendanceEntry {

    //@Annotation
    private long id;

    //@Annotation
    private LocalDate date;

    //@Annotation
    private AttendanceStatus status;

    //@Annotation
    private Attendance attendance;

    public AttendanceEntry(LocalDate date, AttendanceStatus status) {
        this.date = date;
        this.status = status;
    }

    public AttendanceEntry() {}

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setId(long attendanceEntryId) {
        this.id = attendanceEntryId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
