package com.benjvi.awsql.queryfilters;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Vpc;
import com.benjvi.awsql.inputtypes.InputAwsVpc;
import com.benjvi.awsql.types.AwsVpc;
import com.benjvi.awsql.types.Utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 20/09/2017.
 */
public class AwsVpcFilter implements ResourceProvider<AmazonEC2Async,AwsVpc> {

    @Override
    public List<AwsVpc> getFilteredResources(AmazonEC2Async ec2Client) {
        List<Vpc> vpcs = ec2Client.describeVpcs().getVpcs();

        ContainsFilter<AwsVpc> containsFilter = new ContainsFilter();
        List<Predicate<AwsVpc>> predicates = containsFilter.buildPredicates(contains);
        if (contains.getInputTags() != null && !contains.getInputTags().isEmpty())
            predicates.add(containsFilter.getListPropertyPredicate("tags",
                    contains.getInputTags().stream()
                            .map(t -> Utils.copyProperties(t, Tag.class))
                            .collect(Collectors.toList())));

        return vpcs.stream()
                .map(v -> (AwsVpc) Utils.copyProperties(v, AwsVpc.class))
                .filter(v -> predicates.stream().allMatch(p -> p.test(v))).collect(Collectors.toList());
    }

    private InputAwsVpc contains;
    //private InputAwsVpc equals;
    //private InputAwsVpc notEquals;


    public InputAwsVpc getContains() {
        return contains;
    }

    public void setContains(InputAwsVpc contains) {
        this.contains = contains;
    }

}
