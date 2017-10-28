package com.benjvi.awsql;

import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.queryfilters.AwsVpcFilter;
import com.benjvi.awsql.repositories.AwsRepository;
import com.benjvi.awsql.repositories.GcpRepository;
import com.benjvi.awsql.types.AwsEc2Instance;
import com.benjvi.awsql.types.AwsVpc;
import com.benjvi.awsql.types.GcpComputeInstance;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

/**
 * Created by benjamin on 20/09/2017.
 */
public class Query implements GraphQLQueryResolver {

    private final AwsRepository awsRepository;
    private final GcpRepository gcpRepository;

    public Query(AwsRepository awsRepository, GcpRepository gcpRepository) {
        this.awsRepository = awsRepository;
        this.gcpRepository = gcpRepository;
    }

    public List<AwsVpc> awsVpcs(AwsVpcFilter filter) { return awsRepository.vpcs(filter); }

    public List<AwsEc2Instance> awsEc2Instances(AwsEc2InstanceFilter filter) { return awsRepository.ec2Instances(filter); }

    public List<GcpComputeInstance> gcpInstances() { return gcpRepository.instances(); }
}
