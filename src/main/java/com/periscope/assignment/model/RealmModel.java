package com.periscope.assignment.model;


import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement (name = "realm")
@XmlAccessorType(XmlAccessType.NONE)
public class RealmModel implements Serializable {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String key;

    public RealmModel(long id, String name, String description, String key) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public RealmModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RealmModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

