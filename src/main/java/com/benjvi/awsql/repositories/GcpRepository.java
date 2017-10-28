package com.benjvi.awsql.repositories;

import com.benjvi.awsql.types.GcpComputeInstance;
import com.benjvi.awsql.types.Utils;
import com.google.api.gax.paging.Page;
import com.google.cloud.compute.Compute;
import com.google.cloud.compute.Instance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 27/10/2017.
 */
public class GcpRepository {

    private Compute compute;

    public GcpRepository() {
        this.compute = com.google.cloud.compute.ComputeOptions.getDefaultInstance().getService();
    }

    public List<GcpComputeInstance> instances() {
        List<GcpComputeInstance> result = new ArrayList<>();
        Page<Instance> instancesPage = compute.listInstances();
        Iterable<Instance> it = instancesPage.iterateAll();
        it.forEach(i -> result.add((GcpComputeInstance)Utils.copyFlatProperties(i, GcpComputeInstance.class)));
        return result;
    }
}
