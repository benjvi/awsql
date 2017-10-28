package com.benjvi.awsql.inputtypes;

import com.amazonaws.services.ec2.model.Tag;
import com.benjvi.awsql.types.AwsVpc;
import com.benjvi.awsql.types.Utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 28/10/2017.
 */
public class InputAwsVpc extends AwsVpc {

    public List<InputAwsTag> getInputTags() {
        return getTags().stream()
                .map(t -> (InputAwsTag)Utils.copyProperties(t, InputAwsTag.class))
                .collect(Collectors.toList());
    }

    public void setInputTags(List<InputAwsTag> inputTags) {
        setTags(inputTags.stream()
                .map(t -> (InputAwsTag) Utils.copyProperties(t, Tag.class))
                .collect(Collectors.toList()));
    }

}
