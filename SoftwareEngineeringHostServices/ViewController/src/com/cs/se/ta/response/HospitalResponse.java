package com.cs.se.ta.response;

import com.cs.se.ta.resobj.Hospital;

public class HospitalResponse {
    private Hospital[] hospitals;
    public HospitalResponse() {
        super();
    }

    public void setHospitals(Hospital[] hospitals) {
        this.hospitals = hospitals;
    }

    public Hospital[] getHospitals() {
        return hospitals;
    }
}
