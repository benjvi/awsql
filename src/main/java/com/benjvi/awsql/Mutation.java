package com.benjvi.awsql;

import com.benjvi.awsql.queryfilters.AwsVpcFilter;
import com.benjvi.awsql.mutationactions.AwsAddTagsAction;
import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.repositories.AwsRepository;
import com.benjvi.awsql.types.AwsEc2Instance;
import com.benjvi.awsql.types.AwsVpc;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import java.util.List;

/**
 * Created by benjamin on 20/09/2017.
 */
public class Mutation implements GraphQLMutationResolver {

    private final AwsRepository awsRepository;

    public Mutation(AwsRepository awsRepository) {
        this.awsRepository = awsRepository;
    }

    public List<AwsVpc> vpcs(AwsVpcFilter filter, AwsAddTagsAction addTags) {
        // get info for running mutation
        List<AwsVpc> vpcs = awsRepository.vpcs(filter);
        vpcs.stream().map(v -> v.getVpcId()).forEach(addTags::perform);
        // get updated info
        vpcs = awsRepository.vpcs(filter);
        return vpcs;
    }

    public List<AwsEc2Instance> ec2Instances(AwsEc2InstanceFilter filter, AwsAddTagsAction addTags) {
        // get info for running mutation
        List<AwsEc2Instance> instances = awsRepository.ec2Instances(filter);
        instances.stream().map(i -> i.getInstanceId()).forEach(addTags::perform);
        // get updated info
        instances = awsRepository.ec2Instances(filter);
        return instances;
    }
}
