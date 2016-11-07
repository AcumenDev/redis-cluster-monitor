package com.acumen.redis.cluster.monitor.cluster;


import com.acumen.redis.cluster.monitor.model.Nodes;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import com.acumen.redis.cluster.monitor.util.convert.AppConverters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ClusterInfo;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fczheng
 */
@Service
public class ClusterServiceImpl implements ClusterService {
    private static final Log logger = LogFactory.getLog(ClusterServiceImpl.class);

    static {
        System.setProperty("line.separator", "\n");
    }

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JedisClusterConnection clusterConnection;

    @Override
    public ClusterInfo info() {
        ClusterInfo info = clusterConnection.clusterGetClusterInfo();
        logger.info(info);
        return info;
    }

    @Override
    public Set<Slot> slots() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        logger.info(clusterNodes);
        Set<Slot> slots = AppConverters.toSetOfSlot().convert(clusterNodes);
        return slots;
    }

    @Override
    public Nodes nodes() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        logger.info(sortNodes(clusterNodes));
        return new Nodes().setNodes(sortNodes(clusterNodes));
    }

    @Override
    public Map<String, Info> nodesInfo() {
        Map<String, Info> infos = new HashMap<String, Info>();
        Properties prop = clusterConnection.info();
        logger.info(prop);

        Enumeration<Object> keys = prop.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Properties subProp = (Properties) prop.get(key);
            Info info = AppConverters.toInfo().convert(subProp);
            infos.put(String.valueOf(key), info);
        }

        return infos;
    }

    @Override
    public Info nodeInfo(String node) {
        Properties prop = clusterConnection.info(buildFromHostAndPort(node));
        return AppConverters.toInfo().convert(prop);
    }

    @Override
    public String executeCommand(String command) {
        //  redisTemplate.keys()       //  redisTemplate.keys()
clusterConnection.sScan()
     //   clusterConnection.execute(command);
        return   getKeys(command);

    }

    private List<String> getKeys(String node) {
        List<String> keys;
        if (StringUtils.isEmpty(node)) {
            keys = clusterConnection.keys("*".getBytes()).stream().map(bytes -> new String(bytes, Charset.defaultCharset())).collect(Collectors.toList());
        } else {
            RedisClusterNode clusterNode = buildFromHostAndPort(node);
            keys = clusterConnection.keys(clusterNode, "*".getBytes()).stream().map(bytes -> new String(bytes, Charset.defaultCharset())).collect(Collectors.toList());
        }
        return keys;
    }

    @Override
    public Set<Node> activeMasters() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        Set<Node> nodes = AppConverters.toSetOfNode().convert(getActiveMasterNodes(clusterNodes));
        logger.info(nodes);
        return nodes;
    }

    private RedisClusterNode buildFromHostAndPort(String node) {
        String[] hostAndPort = node.split(":");
        return new RedisClusterNode(hostAndPort[0], Integer.parseInt(hostAndPort[1]), null);
    }

    private Set<RedisClusterNode> getActiveMasterNodes(Set<RedisClusterNode> nodes) {

        Set<RedisClusterNode> activeMasterNodes = new LinkedHashSet<RedisClusterNode>(nodes.size());
        for (RedisClusterNode node : nodes) {
            if (node.isMaster() && node.isConnected() && !node.isMarkedAsFail()) {
                activeMasterNodes.add(node);
            }
        }
        return activeMasterNodes;
    }

    private List<Node> sortNodes(Set<RedisClusterNode> clusterNodes) {
        Set<RedisClusterNode> activeNodes = getActiveMasterNodes(clusterNodes);
        List<Node> sortedNodes = new ArrayList<Node>(clusterNodes.size());

        for (RedisClusterNode node : activeNodes) {
            sortedNodes.add(AppConverters.convertMaster(node));
            for (RedisClusterNode clusterNode : clusterNodes) {
                if (clusterNode.isSlave() && clusterNode.getMasterId().equals(node.getId())) {
                    sortedNodes.add(AppConverters.convertSlave(clusterNode, node.asString()));
                }
            }
        }
        return sortedNodes;
    }
}
