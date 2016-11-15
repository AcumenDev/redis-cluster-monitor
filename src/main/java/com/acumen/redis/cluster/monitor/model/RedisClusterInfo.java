package com.acumen.redis.cluster.monitor.model;

import java.io.Serializable;

/**
 * Created by vladimir akummail@gmail.com on 11/15/16.
 */
public class RedisClusterInfo implements Serializable {
   // private static final long serialVersionUID = -1696488776637339227L;
   // @JsonProperty("cluster_state")
    private String state;
  //  @JsonProperty("cluster_slots_assigned")
    private Long slotsAssigned;
   // @JsonProperty("cluster_slots_ok")
    private Long slotsOk;
   /// @JsonProperty("cluster_slots_pfail")
    private Long slotsPfail;
   // @JsonProperty("cluster_slots_fail")
    private Long slotsFail;
   /// @JsonProperty("cluster_known_nodes")
    private Long knownNodes;
   /// @JsonProperty("cluster_size")
    private Long clusterSize;
   /// @JsonProperty("cluster_current_epoch")
    private Long currentEpoch;
   /// @JsonProperty("cluster_my_epoch")
    private Long myEpoch;
   // @JsonProperty("cluster_stats_messages_sent")
    private Long messagesSent;
   /// @JsonProperty("cluster_stats_messages_received")
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
