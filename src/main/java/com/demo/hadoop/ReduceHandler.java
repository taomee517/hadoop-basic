package com.demo.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReduceHandler extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * reduce处理方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     * Reducer在收到Mapper处理后的数据后，会按照中key的字典顺序排序，
     * <hello 1><haddop 1><spark 1><haddop 1><linux 1>
     *
     * <hadoop 1><haddop 1><hello 1><linux 1><spark 1>
     * 相同的key作为一组数据调用reduce方法，因此
     * 传入的key即word
     * value为计数集合
     *
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;

        for(IntWritable index : values){
            count += index.get();
        }

        context.write(key, new IntWritable(count));
    }
}
