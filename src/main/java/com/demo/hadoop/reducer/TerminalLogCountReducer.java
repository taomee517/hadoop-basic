package com.demo.hadoop.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TerminalLogCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    Text outKey = new Text();
    IntWritable outValue = new IntWritable();
    private static int index = 0;

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("TerminalLogCountReducer被调用次数：" + index);
        index++;
        int count = 0;

        for(IntWritable value : values){
            count += value.get();
        }

        outKey = key;
        outValue.set(count);
        context.write(outKey,outValue);
    }
}
