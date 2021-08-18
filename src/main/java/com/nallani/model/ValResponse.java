package com.nallani.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValResponse {

    private String sub;
    private List<String> aud;
    private String token_level;
    private String target_url;
    private List<String> target_scope;
    private AdditionalClaims additionalClaims;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<String> getAud() {
        return aud;
    }

    public void setAud(List<String> aud) {
        this.aud = aud;
    }

    public String getToken_level() {
        return token_level;
    }

    public void setToken_level(String token_level) {
        this.token_level = token_level;
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
}
