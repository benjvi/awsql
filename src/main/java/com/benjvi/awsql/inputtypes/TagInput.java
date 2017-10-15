package com.benjvi.awsql.inputtypes;

import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Vpc;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by benjamin on 20/09/2017.
 */
public class TagInput {


    private String key;
    private String value;

    public TagInput() {}

    public TagInput(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
