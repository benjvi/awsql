package com.benjvi.awsql.queryfilters;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.model.Vpc;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by benjamin on 15/10/2017.
 */
public interface AwsEc2Filter<T> {

    List<T> applyPredicates(AmazonEC2Async ec2Client, List<Predicate<T>> predicates);

    List<Predicate<T>> buildPredicates();
}
