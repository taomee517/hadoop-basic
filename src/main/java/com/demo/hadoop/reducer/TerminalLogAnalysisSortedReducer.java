package com.demo.hadoop.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TerminalLogAnalysisSortedReducer extends Reducer<Text, IntWritable, IntWritable, Text> {
    IntWritable outKey = new IntWritable();
    Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for(IntWritable value:values){
            count += value.get();
        }

        outKey.set(count);
        outValue = key;
        context.write(outKey,outValue);
    }
}
