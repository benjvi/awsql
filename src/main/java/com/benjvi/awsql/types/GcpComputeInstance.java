package com.benjvi.awsql.types;

import com.google.cloud.compute.*;
import com.google.common.base.Function;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.List;

/**
 * Created by benjamin on 27/10/2017.
 */
public class GcpComputeInstance  {

    private static final long serialVersionUID = -6601223112628977168L;
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = ISODateTimeFormat.dateTime();
    private String generatedId;
    private InstanceId instanceId;
    private Long creationTimestamp;
    private String description;
    private InstanceInfo.Status status;
    private String statusMessage;
    private Tags tags;
    private MachineTypeId machineType;
    private Boolean canIpForward;
    private List<NetworkInterface> networkInterfaces;
    private List<AttachedDisk> attachedDisks;
    private Metadata metadata;
    private List<ServiceAccount> serviceAccounts;
    private SchedulingOptions schedulingOptions;
    private String cpuPlatform;

    public String getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }

    public InstanceId getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(InstanceId instanceId) {
        this.instanceId = instanceId;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InstanceInfo.Status getStatus() {
        return status;
    }

    public void setStatus(InstanceInfo.Status status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public MachineTypeId getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineTypeId machineType) {
        this.machineType = machineType;
    }

    public Boolean getCanIpForward() {
        return canIpForward;
    }

    public void setCanIpForward(Boolean canIpForward) {
        this.canIpForward = canIpForward;
    }

    public List<NetworkInterface> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }

    public List<AttachedDisk> getAttachedDisks() {
        return attachedDisks;
    }

    public void setAttachedDisks(List<AttachedDisk> attachedDisks) {
        this.attachedDisks = attachedDisks;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<ServiceAccount> getServiceAccounts() {
        return serviceAccounts;
    }

    public void setServiceAccounts(List<ServiceAccount> serviceAccounts) {
        this.serviceAccounts = serviceAccounts;
    }

    public SchedulingOptions getSchedulingOptions() {
        return schedulingOptions;
    }

    public void setSchedulingOptions(SchedulingOptions schedulingOptions) {
        this.schedulingOptions = schedulingOptions;
    }

    public String getCpuPlatform() {
        return cpuPlatform;
    }

    public void setCpuPlatform(String cpuPlatform) {
        this.cpuPlatform = cpuPlatform;
    }

    public GcpComputeInstance() {

    }

}
