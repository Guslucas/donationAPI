package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class BankBill {

    long id;
    float value;
    String emitterBankName;
    String emitterAgencyNumber;
    float otherValues;
    float finalValue;
    String emitterReceiver;
    Date emissionDate;
    Date dueDate;
    BankBillStatus status;

    public BankBill(long id, float value, String emitterBankName, String emitterAgencyNumber, float otherValues, float finalValue, String emitterReceiver, Date emissionDate, Date dueDate, BankBillStatus status) {
        this.id = id;
        this.value = value;
        this.emitterBankName = emitterBankName;
        this.emitterAgencyNumber = emitterAgencyNumber;
        this.otherValues = otherValues;
        this.finalValue = finalValue;
        this.emitterReceiver = emitterReceiver;
        this.emissionDate = emissionDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }
    public float getValue() {
        return value;
    }
    public String getEmitterBankName() {
        return emitterBankName;
    }
    public String getEmitterAgencyNumber() {
        return emitterAgencyNumber;
    }
    public float getOtherValues() {
        return otherValues;
    }
    public float getFinalValue() {
        return finalValue;
    }
    public String getEmitterReceiver() {
        return emitterReceiver;
    }
    public Date getEmissionDate() {
        return emissionDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public BankBillStatus getStatus() {
        return status;
    }
}
