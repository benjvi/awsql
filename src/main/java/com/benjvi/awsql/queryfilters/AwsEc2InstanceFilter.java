package com.benjvi.awsql.queryfilters;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.benjvi.awsql.inputtypes.TagInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 20/09/2017.
 */
public class AwsEc2InstanceFilter implements AwsEc2Filter<Instance> {

    private String idContains;
    private List<TagInput> tagsContains;

    @JsonProperty("idContains") //the name must match the schema
    public String getInstanceIdContains() {
        return idContains;
    }

    public void setInstanceIdContains(String idContains) {
        this.idContains = idContains;
    }

    @JsonProperty("tagsContains")
    public List<TagInput> getTagsContains() { return tagsContains; }

    public void setTagsContains(List<TagInput> tagsContains) { this.tagsContains = tagsContains; }

    public Boolean testInstanceIdContains(Instance instance) {
        if (idContains != null) {
            return instance.getInstanceId().contains(idContains);
        } else {
            return true;
        }
    }

    public Boolean testTagsContains(Instance instance) {
        if (tagsContains != null) {
            return instance.getTags().containsAll(tagsContains.stream().map(
                    t -> new Tag(t.getKey(), t.getValue())).collect(Collectors.toList()));
        } else {
            return true;
        }
    }

    @Override
    public List<Predicate<Instance>> buildPredicates() {
        List<Predicate<Instance>> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(this.getInstanceIdContains())) {
            predicates.add(this::testInstanceIdContains);
        }
        if (this.getTagsContains() != null && this.getTagsContains().size() > 0) {
            predicates.add(this::testTagsContains);
        }
        return predicates;
    }

    @Override
    public List<Instance> applyPredicates(AmazonEC2Async ec2Client, List<Predicate<Instance>> predicates) {
        List<Instance> instances = ec2Client.describeInstances().getReservations()
                .stream().flatMap(r -> r.getInstances().stream()).collect(Collectors.toList());

        instances = instances.stream().filter(i -> predicates.stream().allMatch(p -> p.test(i))).collect(Collectors.toList());
        return instances;
    }

}
