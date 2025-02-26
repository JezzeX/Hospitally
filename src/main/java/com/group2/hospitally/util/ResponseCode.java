package com.group2.hospitally.util;

public enum ResponseCode {

    SUCCESS("00", "Successfully Processed"),
    CREATED("01", "Record Successfully Created"),
    UPDATED("02", "Record Successfully Updated"),
    DELETED("03", "Record Successfully Deleted"),
    DISCHARGED("04", "Patient Successfully Discharged"),
    PAYMENT_SUCCESS("05", "Payment Processed Successfully"),
    APPOINTMENT_CONFIRMED("06", "Appointment Successfully Confirmed"),

    AD_REQUEST("40", "Invalid Request Format"),
    UNAUTHORIZED("41", "Unauthorized Access"),
    FORBIDDEN("42", "Access Forbidden"),
    NOT_FOUND("43", "Record Not Found"),
    DUPLICATE_ENTRY("44", "Duplicate Record Exists"),
    INVALID_INPUT("45", "Invalid Input Data"),
    APPOINTMENT_CONFLICT("46", "Appointment Slot Already Taken"),
    PATIENT_NOT_REGISTERED("47", "Patient Not Registered"),

    INTERNAL_SERVER_ERROR("50", "An Unexpected Error Occurred"),
    SERVICE_UNAVAILABLE("51", "Service Temporarily Unavailable"),
    DATABASE_ERROR("52", "Database Error Occurred"),
    TIMEOUT("53", "Request Timed Out"),
    EXTERNAL_SERVICE_FAILURE("54", "External Service Error"),
    PAYMENT_FAILED("55", "Payment Processing Failed");



    private String code;
    private String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
