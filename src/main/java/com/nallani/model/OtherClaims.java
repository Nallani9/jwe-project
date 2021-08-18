package com.nallani.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = { "sub", "aud", "token_level", "iss", "exp", "iat", "additionalClaims"})
public class OtherClaims {

    public String target_url;
    public List<String> target_scope;

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

    @Override
    public String toString() {
        return "OtherClaims{" +
                "target_url='" + target_url + '\'' +
                ", target_scope=" + target_scope +
                '}';
    }
}
