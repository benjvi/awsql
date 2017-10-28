package com.benjvi.awsql.repositories;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.AmazonEC2AsyncClientBuilder;
import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.queryfilters.AwsVpcFilter;
import com.benjvi.awsql.types.AwsEc2Instance;
import com.benjvi.awsql.types.AwsVpc;

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

    public List<AwsVpc> vpcs(AwsVpcFilter filter) {
        if (filter == null) {
            filter = new AwsVpcFilter();
        }

        List<AwsVpc> filteredVpcs = filter.getFilteredResources(this.ec2Client);

        return filteredVpcs;
    }

    public List<AwsEc2Instance> ec2Instances(AwsEc2InstanceFilter filter) {
        if (filter == null) {
            filter = new AwsEc2InstanceFilter();
        }

        List<AwsEc2Instance> filteredInstances = filter.getFilteredResources(this.ec2Client);

        return filteredInstances;
    }



}
