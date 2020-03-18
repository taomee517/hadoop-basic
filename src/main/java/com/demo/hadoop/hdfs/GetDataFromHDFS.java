package com.demo.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

public class GetDataFromHDFS {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name", "local");
        String uri = "hdfs://localhost:19000/hdfs/files/demo.txt";
        //拿到HDFS文件系统中的URI
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            //返回一个FSDatalnputStream
            //in表示拷贝源
            //System.out 表示拷贝目的地（也就是要拷贝到标准输出中去）
            //4096表示用来拷贝的buffer大小
            //false表明拷贝完后不关闭拷贝源和拷贝目的地（因为System.out不需要关闭，in可以在finally语句中关闭）
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
