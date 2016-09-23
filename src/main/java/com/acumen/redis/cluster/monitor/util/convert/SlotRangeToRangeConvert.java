package com.acumen.redis.cluster.monitor.util.convert;


import com.acumen.redis.cluster.monitor.model.cluster.node.Range;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisClusterNode.SlotRange;

public class SlotRangeToRangeConvert implements Converter<SlotRange, Range> {

    @Override
    public Range convert(SlotRange source) {
        String rowStr = source.toString();
        int lowerIndex = rowStr.indexOf(",");
        int upperIndex = rowStr.lastIndexOf(", ");

        Range range = new Range();
        range.setLowerBound(Integer.parseInt(rowStr.substring(1, lowerIndex)));
        range.setUpperBound(Integer.parseInt(rowStr.substring(upperIndex + 2, rowStr.length() - 1)));
        return range;
    }

}
