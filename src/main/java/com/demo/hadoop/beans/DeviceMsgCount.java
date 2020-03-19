package com.demo.hadoop.beans;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DeviceMsgCount implements WritableComparable {
    private Long count;

    public DeviceMsgCount() {
    }

    public DeviceMsgCount(Long count) {

        this.count = count;
    }


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void init(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "\t" + count;
    }

    @Override
    public int compareTo(Object o) {
        long nextCount = ((DeviceMsgCount) o).count;
        return this.count>nextCount?-1:1;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.count = dataInput.readLong();
    }
}
