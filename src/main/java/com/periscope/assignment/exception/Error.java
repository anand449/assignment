package com.periscope.assignment.exception;

import javax.xml.bind.annotation.*;;
import java.io.Serializable;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.NONE)
public class Error implements Serializable {
    @XmlElement
    private  String code;

    public Error(String code) {
        super();
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
