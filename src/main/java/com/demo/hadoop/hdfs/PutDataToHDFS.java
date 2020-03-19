package com.demo.hadoop.hdfs;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

import static com.demo.hadoop.config.HadoopEnv.HDFS_PREFIX;

public class PutDataToHDFS {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("dfs.replication","1");
        conf.set("fs.defaultFS", HDFS_PREFIX);

//        String localSrc = "E:\\ghost\\mrtest\\files\\localfile.txt";
        String localSrc = "E:\\private\\test\\words.txt";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        //拿到HDFS文件系统中的URI
        String dest = "/hdfs/create/demo.txt";
        FileSystem fs = FileSystem.get(URI.create(dest), conf);
        //create方法能够为需要写入且当前不存在的文件创建父目录
        FSDataOutputStream fos = fs.create(new Path(dest));
        IOUtils.copyBytes(in, fos, 4096, true);

    }
}
