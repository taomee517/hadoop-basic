package com.demo.hadoop.driver;

import com.demo.hadoop.wordcount.MapHandler;
import com.demo.hadoop.wordcount.ReduceHandler;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HadoopDemoDriver {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        //放到线上执行需要，本地运行则改yarn为local
        conf.set("mapreduce.framework.name", "yarn");
//        //指定resourcemanager的主机名
        conf.set("yarn.resourcemanager.hostname", "localhost");
        Job job = Job.getInstance(conf);

        //设置驱动类
        job.setJarByClass(HadoopDemoDriver.class);

        //指定map reduce处理类
        job.setMapperClass(MapHandler.class);
        job.setReducerClass(ReduceHandler.class);

        //指定Mapper输出数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //计算结果数据分区
        job.setNumReduceTasks(1);

        //指定数据源和数据处理后的结果路径
        FileInputFormat.setInputPaths(job, new Path("/mr/test/in"));
        FileOutputFormat.setOutputPath(job, new Path("/mr/test/out"));

//        job.submit();
        //监控并打印处理流程
        boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);
    }
}
