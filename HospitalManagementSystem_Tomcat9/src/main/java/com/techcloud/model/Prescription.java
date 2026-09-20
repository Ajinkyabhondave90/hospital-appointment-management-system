package com.techcloud.model;

public class Prescription {
    private int prescriptionId;
    private int consultationId;
    private String medicineName;
    private String dosage;
    private String duration;
    private String instructions;

    public int getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(int prescriptionId) { this.prescriptionId = prescriptionId; }

    public int getConsultationId() { return consultationId; }
    public void setConsultationId(int consultationId) { this.consultationId = consultationId; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
