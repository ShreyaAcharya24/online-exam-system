package com.roima.exam.online_exam_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.roima.exam.online_exam_system.enums.Result;
import com.roima.exam.online_exam_system.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;

//  Each Enrollment belongs to a single Exam, but exam can have multiple enrolllments
    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "examId" ,nullable = false)
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "studentId",referencedColumnName = "studentId")
    private  Student student;

    private LocalDateTime attemptedOn;

    private double totalMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.NotAttempted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Result result = Result.NA;

    @ManyToOne
    @JoinColumn(name = "created_by",referencedColumnName = "admin_id",nullable = false,updatable = false)
    @JsonManagedReference
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by",referencedColumnName = "admin_id")
    @JsonManagedReference
    private Admin updatedBy;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getAttemptedOn() {
        return attemptedOn;
    }

    public void setAttemptedOn(LocalDateTime attemptedOn) {
        this.attemptedOn = attemptedOn;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Admin createdBy) {
        this.createdBy = createdBy;
    }

    public Admin getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Admin updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
