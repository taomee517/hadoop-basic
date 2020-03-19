package com.demo.hadoop.driver;

import com.demo.hadoop.beans.DeviceMsgCount;
import com.demo.hadoop.mapper.TerminalLogSortMapper;
import com.demo.hadoop.reducer.TerminalLogSortReducer;
import com.demo.hadoop.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TerminalLogSortDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //放到线上执行需要，本地运行则改yarn为local
        conf.set("mapreduce.framework.name", "local");

        Job job = Job.getInstance(conf);

        //设置驱动类
        job.setJarByClass(TerminalLogSortDriver.class);


        //指定map reduce处理类
        job.setMapperClass(TerminalLogSortMapper.class);
        job.setReducerClass(TerminalLogSortReducer.class);

        //指定Mapper输出数据类型
        job.setMapOutputKeyClass(DeviceMsgCount.class);
        job.setMapOutputValueClass(Text.class);

        //指定最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DeviceMsgCount.class);


        //指定数据源和数据处理后的结果路径
        FileInputFormat.setInputPaths(job, new Path("E:\\private\\test\\logs\\count\\part-r-00000"));
        String outDir = "E:\\private\\test\\logs\\sort";

        //如果文件或者文件夹已经存在，先将其删除
        FileUtils.outputFileDelete(outDir);
        FileOutputFormat.setOutputPath(job, new Path(outDir));

//        job.submit();
        //监控并打印处理流程
        boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }

}
