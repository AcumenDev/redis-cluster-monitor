package com.acumen.redis.cluster.monitor.cluster;

import com.acumen.redis.cluster.monitor.model.Nodes;
import com.acumen.redis.cluster.monitor.model.RedisClusterInfo;
import com.acumen.redis.cluster.monitor.model.cluster.node.Node;
import com.acumen.redis.cluster.monitor.model.cluster.slot.Slot;
import com.acumen.redis.cluster.monitor.model.info.Info;
import com.acumen.redis.cluster.monitor.util.JsonUtils;
import com.acumen.redis.cluster.monitor.util.RedisUtils;
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

@Service
public class ClusterInformationService {
    private static final Logger logger = LoggerFactory.getLogger(ClusterInformationService.class);

    static {
        System.setProperty("line.separator", "\n");
    }

    RedisTemplate redisTemplate;
    JedisClusterConnection clusterConnection;
    ClusterInfoToRedisClusterInfoConvert redisClusterInfoConvert;

    public ClusterInformationService(RedisTemplate redisTemplate, JedisClusterConnection clusterConnection, ClusterInfoToRedisClusterInfoConvert redisClusterInfoConvert) {
        this.redisTemplate = redisTemplate;
        this.clusterConnection = clusterConnection;
        this.redisClusterInfoConvert = redisClusterInfoConvert;
    }

    @Cacheable(value = "info", unless = "#result == null")
    public RedisClusterInfo info() {
        RedisClusterInfo info = redisClusterInfoConvert.convert(clusterConnection.clusterGetClusterInfo());
        logger.debug("Получение общей информации о кластере: {}", JsonUtils.dump(info));
        return info;
    }

    @Cacheable(value = "slots", unless = "#result == null")
    public Set<Slot> slots() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        logger.debug(JsonUtils.dump(clusterNodes));
        Set<Slot> slots = AppConverters.toSetOfSlot().convert(clusterNodes);
        return slots;
    }

    @Cacheable(value = "nodes", unless = "#result == null")
    public Nodes nodes() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();

        Nodes nodes = new Nodes().setNodes(sortNodes(clusterNodes));

        logger.debug("Получение нодов: {}", JsonUtils.dump(nodes));
        return nodes;
    }

    @Cacheable(value = "nodesInfo", unless = "#result == null")
    public Map<String, Info> nodesInfo() {
        Map<String, Info> infos = new HashMap<String, Info>();
        Properties prop = clusterConnection.info();

        Enumeration<Object> keys = prop.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Properties subProp = (Properties) prop.get(key);
            Info info = AppConverters.toInfo().convert(subProp);
            infos.put(String.valueOf(key), info);
        }
        logger.debug("Получение информации по нодам : {}", JsonUtils.dump(infos));
        return infos;
    }


    @Cacheable(value = "nodeInfo", key = "#node", unless = "#result == null")
    public Info nodeInfo(String node) {
        Info info = AppConverters.toInfo().convert(clusterConnection.info(RedisUtils.buildFromHostAndPort(node)));
        logger.debug("Получение информации по ноду {}: {}", node, JsonUtils.dump(info));
        return info;
    }


    public String executeCommand(String command) {
        //  redisTemplate.keys()       //  redisTemplate.keys()
//clusterConnection.sScan()
        //   clusterConnection.execute(command);
        return "";  //getKeys(command);

    }




    public Set<Node> activeMasters() {
        Set<RedisClusterNode> clusterNodes = clusterConnection.clusterGetNodes();
        Set<Node> nodes = AppConverters.toSetOfNode().convert(getActiveMasterNodes(clusterNodes));
        logger.debug("Получение мастеров: {}", JsonUtils.dump(nodes));
        return nodes;
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
