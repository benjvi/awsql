package com.benjvi.awsql.repositories;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.AmazonEC2AsyncClientBuilder;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Vpc;
import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.queryfilters.AwsVpcFilter;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by benjamin on 20/09/2017.
 */
public class AwsRepository {

    private AmazonEC2Async ec2Client;

    public AwsRepository() {
        this.ec2Client = AmazonEC2AsyncClientBuilder.defaultClient();
    }

    public List<Vpc> vpcs(AwsVpcFilter filter) {
        List<Predicate<Vpc>> validVpcPredicates = filter.buildPredicates();

        List<Vpc> filteredVpcs = filter.applyPredicates(this.ec2Client, validVpcPredicates);

        return filteredVpcs;
    }

    public List<Instance> ec2Instances(AwsEc2InstanceFilter filter) {
        List<Predicate<Instance>> validInstancePredicates = filter.buildPredicates();

        List<Instance> filteredInstances = filter.applyPredicates(this.ec2Client, validInstancePredicates);

        return filteredInstances;
    }



}
