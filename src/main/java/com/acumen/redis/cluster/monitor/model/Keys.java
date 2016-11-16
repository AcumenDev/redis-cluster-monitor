package com.acumen.redis.cluster.monitor.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vstalmakov on 16.11.16.
 */
public class Keys implements Serializable {
    private List<NodeKeys> keyses;

    public List<NodeKeys> getKeyses() {
        return keyses;
    }

    public Keys setKeyses(List<NodeKeys> keyses) {
        this.keyses = keyses;
        return this;
    }
}
