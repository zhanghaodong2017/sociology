package com.zhd.ultimate.sociology.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-26 16:37
 */
public class FileUtils {

    /**
     * <p>Title: toByteArrayNIO</p>
     * <p>Description: NIO way</p>
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayNIO(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while (channel.read(byteBuffer) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
