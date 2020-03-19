package com.demo.hadoop.partitioner;

import com.demo.hadoop.beans.DeviceMsgCount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TagPartioner extends Partitioner<Text, IntWritable> {

    private static Map<String,Integer> partitionMap = new HashMap<>();

    static {
        partitionMap.put("30f", 0);
        partitionMap.put("331", 1);
        partitionMap.put("30c", 2);
        partitionMap.put("217", 3);
        partitionMap.put("4f1", 3);
        partitionMap.put("614", 3);
    }


    @Override
    public int getPartition(Text text, IntWritable value, int i) {
        String tag = text.toString();
        Integer partiton = partitionMap.get(tag.toLowerCase());
        if(Objects.nonNull(partiton)){
            return partiton;
        }else {
            return 4;
        }
    }
}
