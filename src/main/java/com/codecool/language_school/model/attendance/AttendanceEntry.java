package com.codecool.language_school.model.attendance;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AttendanceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @NotNull
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AttendanceStatus status;

    @ManyToOne
    @JoinTable(name="attendence_attendenceentry", joinColumns = @JoinColumn(name = "attendence_entry_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attendence_id", referencedColumnName = "id"))
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
