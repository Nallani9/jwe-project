package com.nallani.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class AdditionalClaims {

    private String key;
    private List<String> value;

    public String getKey() {
        return key;
    }
    @JsonIgnoreProperties(value = { "sub", "aud", "token_level", "target_url", "target_scope", "iss", "exp", "iat"})

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    @JsonAnySetter
    public void ignored(String name, List<String> value) {
        //System.out.println(name + " : " + value);
        setKey(name);
        setValue(value);
    }

    @Override
    public String toString() {
        return "AdditionalClaims{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
