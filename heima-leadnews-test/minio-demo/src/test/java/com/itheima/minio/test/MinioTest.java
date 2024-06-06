package com.itheima.minio.test;


import com.itheima.file.service.FileStorageService;
import com.itheima.minio.MinIOApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinioTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testUpdateImgFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\list.html");
            String filePath = fileStorageService.uploadImgFile("", "list.html", fileInputStream);
            System.out.println(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args)  {
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream("D:\\list.html");
//
//            //1.获取minio的连接信息 创建一个minio客户端
//
//            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio123").endpoint("http://192.168.200.130:9000").build();
//
//            //2.上传
//            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
//                    .object("list.html") //文件名
//                    .contentType("text/html")//文件类型
//                    .bucket("leadnews") //桶名称 与minio管理界面的名称一致就行
//                    .stream(fileInputStream, fileInputStream.available(), -1).build();
//            minioClient.putObject(putObjectArgs);
//
//            //访问路径
//            System.out.println("http://192.168.200.130:9000/leadnews/list.html");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

}
