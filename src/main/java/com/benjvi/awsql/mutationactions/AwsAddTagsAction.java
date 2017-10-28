package com.benjvi.awsql.mutationactions;


import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.AmazonEC2AsyncClientBuilder;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.benjvi.awsql.inputtypes.InputAwsTag;
import com.benjvi.awsql.types.Utils;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 15/10/2017.
 */
public class AwsAddTagsAction {

    private AmazonEC2Async client;
    private List<Tag> tags;

    public AwsAddTagsAction() {
        this(new ArrayList<>());
    }

    @JsonProperty("tags")
    public List<InputAwsTag> getTags() {
        return tags.stream().map(t -> (InputAwsTag)Utils.copyProperties(t, InputAwsTag.class)).collect(Collectors.toList());
    }

    public void setTags(List<InputAwsTag> tags) {
        this.tags = tags.stream().collect(Collectors.toList());
    }

    public AwsAddTagsAction(List<InputAwsTag> tags) {
        this.client = AmazonEC2AsyncClientBuilder.defaultClient();
        this.tags = tags.stream().collect(Collectors.toList());
    }

    public void perform(String resourceId) {
        client.createTags(new CreateTagsRequest()
                .withResources(resourceId)
                .withTags(tags));
    }

}
