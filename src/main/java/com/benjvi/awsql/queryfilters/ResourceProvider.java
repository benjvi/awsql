package com.benjvi.awsql.queryfilters;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by benjamin on 27/10/2017.
 */
public interface ResourceProvider<P, R> {

    List<R> getFilteredResources(P cloudProvider);

}
