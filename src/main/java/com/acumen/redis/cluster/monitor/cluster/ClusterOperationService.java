package com.acumen.redis.cluster.monitor.cluster;

import com.acumen.redis.cluster.monitor.model.NodeKeys;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.util.RedisUtils;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by vstalmakov on 16.11.16.
 */
@Service
public class ClusterOperationService {

    private JedisClusterConnection clusterConnection;
    private ClusterInformationService clusterInformationService;

    public ClusterOperationService(JedisClusterConnection clusterConnection,
                                   ClusterInformationService clusterInformationService) {
        this.clusterConnection = clusterConnection;
        this.clusterInformationService = clusterInformationService;
    }

    public List<NodeKeys> getKeys(String rawNode) {
        CopyOnWriteArrayList<NodeKeys> resultKeys = new CopyOnWriteArrayList<>();

        if (StringUtils.isEmpty(rawNode)) {
            Set<Node> masters = clusterInformationService.activeMasters();
            masters.parallelStream().forEach(node -> {
                RedisClusterNode redisClusterNode = new RedisClusterNode(node.getHost(), node.getPort(), null);

                NodeKeys nodeKeys = new NodeKeys().setName(node.getHost() + ":" + node.getPort()).setKeys(
                        clusterConnection.keys(redisClusterNode, "*".getBytes())
                                .stream()
                                .map(RedisUtils::decode)
                                .collect(Collectors.toList()));
                if (!nodeKeys.getKeys().isEmpty()) {
                    resultKeys.add(nodeKeys);
                }
            });
        } else {
            RedisClusterNode clusterNode = RedisUtils.buildFromHostAndPort(rawNode);
            NodeKeys nodeKeys = new NodeKeys().setName(rawNode).setKeys(
                    clusterConnection.keys(clusterNode, "*".getBytes())
                            .stream()
                            .map(RedisUtils::decode)
                            .collect(Collectors.toList()));
            if (!nodeKeys.getKeys().isEmpty()) {
                resultKeys.add(nodeKeys);
            }
        }
        return resultKeys;
    }
}
