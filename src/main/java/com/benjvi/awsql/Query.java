package com.benjvi.awsql;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Vpc;
import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.queryfilters.AwsVpcFilter;
import com.benjvi.awsql.repositories.AwsRepository;
import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.List;

/**
 * Created by benjamin on 20/09/2017.
 */
public class Query implements GraphQLRootResolver {

    private final AwsRepository awsRepository;

    public Query(AwsRepository awsRepository) {
        this.awsRepository = awsRepository;
    }

    public List<Vpc> vpcs(AwsVpcFilter filter) { return awsRepository.vpcs(filter); }

    public List<Instance> ec2Instances(AwsEc2InstanceFilter filter) { return awsRepository.ec2Instances(filter); }
}
