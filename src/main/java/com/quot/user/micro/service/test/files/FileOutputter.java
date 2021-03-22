package com.quot.user.micro.service.test.files;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Component
public class FileOutputter {

    public void write(String content, String filepath) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(content);
        writer.close();
    }
}
