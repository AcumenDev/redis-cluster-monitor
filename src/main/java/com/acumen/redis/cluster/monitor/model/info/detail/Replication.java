package com.acumen.redis.cluster.monitor.model.info.detail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Replication implements Serializable {
    private String role;
    private String connectedSlaves;
    private Map<String, String> slaves = new HashMap<>();
    private String masterReplOffset;
    private String replBacklogActive;
    private String replBacklogSize;
    private String replBacklogFirstByteOffset;
    private String replBacklogHistlen;

    public String getRole() {
        return role;
    }

    public Replication setRole(String role) {
        this.role = role;
        return this;
    }

    public String getConnectedSlaves() {
        return connectedSlaves;
    }

    public Replication setConnectedSlaves(String connectedSlaves) {
        this.connectedSlaves = connectedSlaves;
        return this;
    }

    public Map<String, String> getSlaves() {
        return slaves;
    }

    public Replication setSlaves(Map<String, String> slaves) {
         this.slaves = slaves;
       // this.slaves.put("slave" + slaves.size(), slave)
        return this;
    }

    public String getMasterReplOffset() {
        return masterReplOffset;
    }

    public Replication setMasterReplOffset(String masterReplOffset) {
        this.masterReplOffset = masterReplOffset;
        return this;
    }

    public String getReplBacklogActive() {
        return replBacklogActive;
    }

    public Replication setReplBacklogActive(String replBacklogActive) {
        this.replBacklogActive = replBacklogActive;
        return this;
    }

    public String getReplBacklogSize() {
        return replBacklogSize;
    }

    public Replication setReplBacklogSize(String replBacklogSize) {
        this.replBacklogSize = replBacklogSize;
        return this;
    }

    public String getReplBacklogFirstByteOffset() {
        return replBacklogFirstByteOffset;
    }

    public Replication setReplBacklogFirstByteOffset(String replBacklogFirstByteOffset) {
        this.replBacklogFirstByteOffset = replBacklogFirstByteOffset;
        return this;
    }

    public String getReplBacklogHistlen() {
        return replBacklogHistlen;
    }

    public Replication setReplBacklogHistlen(String replBacklogHistlen) {
        this.replBacklogHistlen = replBacklogHistlen;
        return this;
    }
}
