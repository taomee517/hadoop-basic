package com.demo.hadoop.reducer;

import com.demo.hadoop.beans.DeviceMsgCount;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TerminalLogSortReducer extends Reducer<DeviceMsgCount, Text, Text, DeviceMsgCount> {
    DeviceMsgCount outKey = new DeviceMsgCount();
    Text outValue = new Text();

    @Override
    protected void reduce(DeviceMsgCount key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(values.iterator().next(),key);
    }
}
