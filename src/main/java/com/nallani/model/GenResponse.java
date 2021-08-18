package com.nallani.model;

public class GenResponse {

    private String jweString;
    private String sub;
    private String assertopnTtype;

    public String getJweString() {
        return jweString;
    }

    public void setJweString(String jweString) {
        this.jweString = jweString;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAssertopnTtype() {
        return assertopnTtype;
    }

    public void setAssertopnTtype(String assertopnTtype) {
        this.assertopnTtype = assertopnTtype;
    }

    @Override
    public String toString() {
        return "GenResponse{" +
                "jweString='" + jweString + '\'' +
                ", sub='" + sub + '\'' +
                ", assertopnTtype='" + assertopnTtype + '\'' +
                '}';
    }
}
