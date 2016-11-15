package com.acumen.redis.cluster.monitor.cluster;


import com.acumen.redis.cluster.monitor.model.Nodes;
import com.acumen.redis.cluster.monitor.model.RedisClusterInfo;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import com.acumen.redis.cluster.monitor.util.JsonUtils;
import com.acumen.redis.cluster.monitor.util.convert.AppConverters;
import com.acumen.redis.cluster.monitor.util.convert.ClusterInfoToRedisClusterInfoConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
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
    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    static {
        System.setProperty("line.separator", "\n");
    }


    RedisTemplate redisTemplate;
    JedisClusterConnection clusterConnection;
    ClusterInfoToRedisClusterInfoConvert redisClusterInfoConvert;


    public ClusterServiceImpl(RedisTemplate redisTemplate, JedisClusterConnection clusterConnection, ClusterInfoToRedisClusterInfoConvert redisClusterInfoConvert) {
        this.redisTemplate = redisTemplate;
        this.clusterConnection = clusterConnection;
        this.redisClusterInfoConvert = redisClusterInfoConvert;
    }

    @Override
    @Cacheable(value = "info", unless = "#result == null")
    public RedisClusterInfo info() {
        RedisClusterInfo info = redisClusterInfoConvert.convert(clusterConnection.clusterGetClusterInfo());
        logger.debug("Получение общей информации о кластере: {}", JsonUtils.dump(info));
        return info;
    }

    @Override
    @Cacheable(value = "slots", unless = "#result == null")
    public Set<Slot> slots() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        logger.debug(JsonUtils.dump(clusterNodes));
        Set<Slot> slots = AppConverters.toSetOfSlot().convert(clusterNodes);
        return slots;
    }

    @Override
    @Cacheable(value = "nodes", unless = "#result == null")
    public Nodes nodes() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        logger.debug("Получение нодов: {}", JsonUtils.dump(sortNodes(clusterNodes)));
        return new Nodes().setNodes(sortNodes(clusterNodes));
    }

    @Override
    @Cacheable(value = "nodesInfo", unless = "#result == null")
    public Map<String, Info> nodesInfo() {
        Map<String, Info> infos = new HashMap<String, Info>();
        Properties prop = clusterConnection.info();
        logger.debug("Получение информации по нодам : {}", JsonUtils.dump(prop));

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
    @Cacheable(value = "nodeInfo", unless = "#result == null")
    public Info nodeInfo(String node) {
        Properties prop = clusterConnection.info(buildFromHostAndPort(node));
        return AppConverters.toInfo().convert(prop);
    }

    @Override
    public String executeCommand(String command) {
        //  redisTemplate.keys()       //  redisTemplate.keys()
//clusterConnection.sScan()
        //   clusterConnection.execute(command);
        return "";  //getKeys(command);

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
        logger.debug("Получение мастеров: {}", JsonUtils.dump(nodes));
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
