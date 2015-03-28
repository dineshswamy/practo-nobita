package com.application.nobita;



/**
 * Created by dinesh on 28/3/15.
 */
public class ClinicToken {
    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_phone_no() {
        return patient_phone_no;
    }

    public void setPatient_phone_no(String patient_phone_no) {
        this.patient_phone_no = patient_phone_no;
    }

    public String getToken_serial_no() {
        return token_serial_no;
    }

    public void setToken_serial_no(String token_serial_no) {
        this.token_serial_no = token_serial_no;
    }

    public String getToken_status() {
        return token_status;
    }

    public void setToken_status(String token_status) {
        this.token_status = token_status;
    }

    public String getPatient_reason() {
        return patient_reason;
    }

    public void setPatient_reason(String patient_reason) {
        this.patient_reason = patient_reason;
    }

    private String patient_name;
    private String patient_phone_no;
    private String token_serial_no;
    private String token_status;
    private String patient_reason;
    private String token_id;

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    private String patient_id;
}
