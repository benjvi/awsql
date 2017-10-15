package com.benjvi.awsql.queryfilters;

import com.amazonaws.services.ec2.AmazonEC2Async;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Vpc;
import com.google.common.base.Strings;
import com.benjvi.awsql.inputtypes.TagInput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by benjamin on 20/09/2017.
 */
public class AwsVpcFilter implements AwsEc2Filter<Vpc> {

    private String idContains;
    private String cidrContains;
    private List<TagInput> tagsContains;

    @JsonProperty("idContains") //the name must match the schema
    public String getVpcIdContains() {
        return idContains;
    }

    public void setVpcIdContains(String idContains) {
        this.idContains = idContains;
    }

    @JsonProperty("cidrContains")
    public String getCidrBlockContains() {
        return cidrContains;
    }

    public void setCidrBlockContains(String cidrContains) {
        this.cidrContains = cidrContains;
    }

    @JsonProperty("tagsContains")
    public List<TagInput> getTagsContains() { return tagsContains; }

    public void setTagsContains(List<TagInput> tagsContains) { this.tagsContains = tagsContains; }

    public Boolean testVpcIdContains(Vpc vpc) {
        if (idContains != null) {
            return vpc.getVpcId().contains(idContains);
        } else {
            return true;
        }
    }

    public Boolean testCidrBlockContains(Vpc vpc) {
        if (cidrContains != null) {
            return vpc.getCidrBlock().contains(cidrContains);
        } else {
            return true;
        }
    }

    public Boolean testTagsContains(Vpc vpc) {
        if (tagsContains != null) {
            return vpc.getTags().containsAll(tagsContains.stream().map(
                    t -> new Tag(t.getKey(), t.getValue())).collect(Collectors.toList()));
        } else {
            return true;
        }
    }

    @Override
    public List<Predicate<Vpc>> buildPredicates() {
        List<Predicate<Vpc>> predicates = new ArrayList<>();
        if (!Strings.isNullOrEmpty(this.getCidrBlockContains())) {
            predicates.add(this::testCidrBlockContains);
        }
        if (!Strings.isNullOrEmpty(this.getVpcIdContains())) {
            predicates.add(this::testVpcIdContains);
        }
        if (this.getTagsContains() != null && this.getTagsContains().size() > 0) {
            predicates.add(this::testTagsContains);
        }
        return predicates;
    }

    @Override
    public List<Vpc> applyPredicates(AmazonEC2Async ec2Client, List<Predicate<Vpc>> predicates) {
        List<Vpc> vpcs = ec2Client.describeVpcs().getVpcs();

        vpcs = vpcs.stream().filter(v -> predicates.stream().allMatch(p -> p.test(v))).collect(Collectors.toList());
        return vpcs;
    }

}
