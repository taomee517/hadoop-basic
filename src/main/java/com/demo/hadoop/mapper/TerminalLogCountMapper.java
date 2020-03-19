package com.demo.hadoop.mapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TerminalLogCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text outKey = new Text();
    IntWritable outValue = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] lineDatas = StringUtils.split(line,"\t");
        String msg = lineDatas[3];
        String tag = StringUtils.split(StringUtils.split(msg,"|")[2],",")[0];
        outKey.set(tag);
        outValue.set(1);
        context.write(outKey,outValue);
    }
}
