package com.techcloud.model;

import java.sql.Timestamp;

public class Consultation {
    private int consultationId;
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private String patientName;  // populated via JOIN for display
    private String doctorName;   // populated via JOIN for display
    private String symptoms;
    private String diagnosis;
    private String doctorNotes;
    private Timestamp consultationDate;

    public int getConsultationId() { return consultationId; }
    public void setConsultationId(int consultationId) { this.consultationId = consultationId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getDoctorNotes() { return doctorNotes; }
    public void setDoctorNotes(String doctorNotes) { this.doctorNotes = doctorNotes; }

    public Timestamp getConsultationDate() { return consultationDate; }
    public void setConsultationDate(Timestamp consultationDate) { this.consultationDate = consultationDate; }
}
