package com.acumen.redis.cluster.monitor.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vstalmakov on 16.11.16.
 */
public class NodeKeys implements Serializable {
    private String name;
    private List<String> keys;

    public String getName() {
        return name;
    }

    public NodeKeys setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getKeys() {
        return keys;
    }

    public NodeKeys setKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }
}
