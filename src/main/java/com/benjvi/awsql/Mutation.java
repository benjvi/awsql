package com.benjvi.awsql;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Vpc;
import com.benjvi.awsql.queryfilters.AwsVpcFilter;
import com.benjvi.awsql.mutationactions.AwsAddTagsAction;
import com.benjvi.awsql.queryfilters.AwsEc2InstanceFilter;
import com.benjvi.awsql.repositories.AwsRepository;
import com.coxautodev.graphql.tools.GraphQLRootResolver;

import java.util.List;

/**
 * Created by benjamin on 20/09/2017.
 */
public class Mutation implements GraphQLRootResolver {

    private final AwsRepository awsRepository;

    public Mutation(AwsRepository awsRepository) {
        this.awsRepository = awsRepository;
    }

    public List<Vpc> vpcs(AwsVpcFilter filter, AwsAddTagsAction addTags) {
        // get info for running mutation
        List<Vpc> vpcs = awsRepository.vpcs(filter);
        vpcs.stream().map(v -> v.getVpcId()).forEach(addTags::perform);
        // get updated info
        vpcs = awsRepository.vpcs(filter);
        return vpcs;
    }

    public List<Instance> ec2Instances(AwsEc2InstanceFilter filter, AwsAddTagsAction addTags) {
        // get info for running mutation
        List<Instance> instances = awsRepository.ec2Instances(filter);
        instances.stream().map(i -> i.getInstanceId()).forEach(addTags::perform);
        // get updated info
        instances = awsRepository.ec2Instances(filter);
        return instances;
    }
}
