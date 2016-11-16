package com.acumen.redis.cluster.monitor.model;

import java.io.Serializable;

/**
 * Created by vladimir akummail@gmail.com on 11/15/16.
 */
public class RedisClusterInfo implements Serializable {
    private String state;
    private Long slotsAssigned;
    private Long slotsOk;
    private Long slotsPfail;
    private Long slotsFail;
    private Long knownNodes;
    private Long clusterSize;
    private Long currentEpoch;
    private Long myEpoch;
    private Long messagesSent;
    private Long messagesReceived;

    public String getState() {
        return state;
    }

    public RedisClusterInfo setState(String state) {
        this.state = state;
        return this;
    }

    public Long getSlotsAssigned() {
        return slotsAssigned;
    }

    public RedisClusterInfo setSlotsAssigned(Long slotsAssigned) {
        this.slotsAssigned = slotsAssigned;
        return this;
    }

    public Long getSlotsOk() {
        return slotsOk;
    }

    public RedisClusterInfo setSlotsOk(Long slotsOk) {
        this.slotsOk = slotsOk;
        return this;
    }

    public Long getSlotsPfail() {
        return slotsPfail;
    }

    public RedisClusterInfo setSlotsPfail(Long slotsPfail) {
        this.slotsPfail = slotsPfail;
        return this;
    }

    public Long getSlotsFail() {
        return slotsFail;
    }

    public RedisClusterInfo setSlotsFail(Long slotsFail) {
        this.slotsFail = slotsFail;
        return this;
    }

    public Long getKnownNodes() {
        return knownNodes;
    }

    public RedisClusterInfo setKnownNodes(Long knownNodes) {
        this.knownNodes = knownNodes;
        return this;
    }

    public Long getClusterSize() {
        return clusterSize;
    }

    public RedisClusterInfo setClusterSize(Long clusterSize) {
        this.clusterSize = clusterSize;
        return this;
    }

    public Long getCurrentEpoch() {
        return currentEpoch;
    }

    public RedisClusterInfo setCurrentEpoch(Long currentEpoch) {
        this.currentEpoch = currentEpoch;
        return this;
    }

    public Long getMyEpoch() {
        return myEpoch;
    }

    public RedisClusterInfo setMyEpoch(Long myEpoch) {
        this.myEpoch = myEpoch;
        return this;
    }

    public Long getMessagesSent() {
        return messagesSent;
    }

    public RedisClusterInfo setMessagesSent(Long messagesSent) {
        this.messagesSent = messagesSent;
        return this;
    }

    public Long getMessagesReceived() {
        return messagesReceived;
    }

    public RedisClusterInfo setMessagesReceived(Long messagesReceived) {
        this.messagesReceived = messagesReceived;
        return this;
    }
}
