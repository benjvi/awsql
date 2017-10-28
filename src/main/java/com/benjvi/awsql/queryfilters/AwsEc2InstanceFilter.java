package com.benjvi.awsql.queryfilters;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.benjvi.awsql.inputtypes.InputAwsEc2Instance;
import com.benjvi.awsql.types.AwsEc2Instance;
import com.benjvi.awsql.types.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 20/09/2017.
 */
public class AwsEc2InstanceFilter implements ResourceProvider<AmazonEC2Async, AwsEc2Instance> {

    private InputAwsEc2Instance contains;

    public InputAwsEc2Instance getContains() {
        return contains;
    }

    public void setContains(InputAwsEc2Instance contains) {
        this.contains = contains;
    }

    @Override
    public List<AwsEc2Instance> getFilteredResources(AmazonEC2Async ec2Client) {
        List<Instance> instances = ec2Client.describeInstances().getReservations()
                .stream().flatMap(r -> r.getInstances().stream()).collect(Collectors.toList());

        List<Predicate<AwsEc2Instance>> predicates = new ArrayList<>();
        if (contains!=null) {
            ContainsFilter<AwsEc2Instance> containsFilter = new ContainsFilter();
            predicates.addAll(containsFilter.buildPredicates(contains));
            if (contains.getInputTags() != null && !contains.getInputTags().isEmpty())
                predicates.add(containsFilter.getListPropertyPredicate("tags",
                        contains.getInputTags().stream()
                                .map(t -> Utils.copyProperties(t, Tag.class))
                                .collect(Collectors.toList())));
        }

        return instances.stream()
                .map(i -> (AwsEc2Instance)Utils.copyProperties(i, AwsEc2Instance.class))
                .filter(i -> predicates.stream().allMatch(p -> p.test(i)))
                .collect(Collectors.toList());
    }
}
