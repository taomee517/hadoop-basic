package com.demo.hadoop.wordcount;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Map阶段的实现逻辑
 *  必须继承父类Mapper<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
 *  KEYIN: 表示mapper输入key的数据类型，在默认的数据读取组件下，叫InputFormat,它的行为是一行一行的读取待处理数据
 *         读取一行，返回一行给M-R程序，这种情况下，返回的数据类型就是每一行的数据偏移量，因此数据类型是Long
 *  VALUEIN: 表示mapper输入的时候value的数据类型，在默认的读取组件下，返回的数据就是那一行的内容，数据类型为String
 *  KEYOUT: 表示mapper输出时的key的数据类型，本案为了统计单词出现的次数，key为String类型
 *  VALUEOUT: 表示mapper输出时的value的数据类型，本案为了统计单词出现的次数，value为Integer类型
 *
 * Hadoop框架为提升序列化效率，封装了对应的数据类型
 *  Long---LongWritable
 *  String---Text
 *  Integer---IntWritable
 *  null---NullWritable
 */
public class MapHandler extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = StringUtils.split(line," ");
        for(String word : words){
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
