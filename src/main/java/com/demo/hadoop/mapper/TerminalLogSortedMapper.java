package com.demo.hadoop.mapper;

import com.demo.hadoop.beans.DeviceMsgCount;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TerminalLogSortedMapper extends Mapper<LongWritable, Text, DeviceMsgCount, Text> {
    DeviceMsgCount outKey = new DeviceMsgCount();
    Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] lineDatas = StringUtils.split(line,"\t");
        String tag = lineDatas[0];
        String count = lineDatas[1];
        outKey.init(Long.valueOf(count));
        outValue.set(tag);
        context.write(outKey,outValue);
    }
}
