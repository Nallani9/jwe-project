package com.nallani.model;

import java.util.List;

public class GenRequest {

    public String assertion_type;
    public String target_clientid;
    public String target_url;
    public List<String> target_scope;
    public AdditionalClaims additionalClaims;

    public String getAssertion_type() {
        return assertion_type;
    }

    public void setAssertion_type(String assertion_type) {
        this.assertion_type = assertion_type;
    }

    public String getTarget_clientid() {
        return target_clientid;
    }

    public void setTarget_clientid(String target_clientid) {
        this.target_clientid = target_clientid;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public List<String> getTarget_scope() {
        return target_scope;
    }

    public void setTarget_scope(List<String> target_scope) {
        this.target_scope = target_scope;
    }

    public AdditionalClaims getAdditionalClaims() {
        return additionalClaims;
    }

    public void setAdditionalClaims(AdditionalClaims additionalClaims) {
        this.additionalClaims = additionalClaims;
    }

    @Override
    public String toString() {
        return "GenRequest{" +
                "assertion_type='" + assertion_type + '\'' +
                ", target_clientid='" + target_clientid + '\'' +
                ", target_url='" + target_url + '\'' +
                ", target_scope=" + target_scope +
                ", additionalClaims=" + additionalClaims +
                '}';
    }
}
