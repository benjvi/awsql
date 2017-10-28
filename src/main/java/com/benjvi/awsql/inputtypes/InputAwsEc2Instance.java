package com.benjvi.awsql.inputtypes;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.benjvi.awsql.types.AwsEc2Instance;
import com.benjvi.awsql.types.Utils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 27/10/2017.
 */
public class InputAwsEc2Instance extends AwsEc2Instance {

    public List<InputAwsTag> getInputTags() {
        return getTags().stream()
                .map(t -> (InputAwsTag)Utils.copyProperties(t, InputAwsTag.class))
                .collect(Collectors.toList());
    }

    public void setInputTags(List<InputAwsTag> inputTags) {
        setTags(inputTags.stream()
                .map(t -> (Tag)Utils.copyProperties(t, Tag.class))
                .collect(Collectors.toList()));
    }
}
