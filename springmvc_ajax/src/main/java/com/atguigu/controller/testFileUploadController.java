package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class testFileUploadController {

    @RequestMapping("/test/upload")
    public String testUpload(HttpSession session, MultipartFile photo) throws IOException{
        // 获取上传文件对象 在SpringMVC中上传文件使用MultipartFile对象封装 可以引入参数直接使用
        // 参数名称必须与表单中上传文件控件的name属性一致
        // tranferTo()方法 是SpringMVC的封装文件复制功能 可以把上传的文件复制到指定目录下
        // getName()方法 获取的是文件上传控件的name属性值
        // getOriginalFilename()方法 获取上传文件的原始文件名
        String fileName = photo.getOriginalFilename();
        // 在原生的java中只有file类型，是无法解析MultipartFile对象，需要在springmvc.xml中配置文件上传解析器
        // 在使用UUID作为文件名之前 我们需要获取上传文件的后缀名
        // 这里使用的不是indexOf()方法，而是使用lastIndexOf()方法
        // 目的是为了防止出现文件名中有“.”的文件 例如 1.1.1.jpg
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 获取UUID
        String uuid = UUID.randomUUID().toString();
        // 拼接一个新的文件名 UUID + 原始文件后缀名
        fileName = uuid + suffixName;
        // 设置上传文件的在服务器的位置
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("photo");
        // 服务器中没有该目录 创建photo目录
        File file = new File(realPath);
        // 判断file对应的目录是否存在 如果不存在就创建目录
        if(!file.exists()){
            file.mkdir();
        }
        String uploadFileName = realPath + File.separator + fileName;
        // 上传文件
        photo.transferTo(new File(uploadFileName));
        return "success";

        // 现在出现一个问题 当我们上传两个同名的文件的时候，上传的文件“内容”会被覆盖
        // 注意这里是文件内容被覆盖了 文件可以和它的副本同时存在
        // 然而并不需要它覆盖原来的文件内容 我们需要同时保留这两个文件内容 那怎么处理
        // 可以给这些文件取绝对不会重复的名字就可以解决这个问题 例如时间戳 UUID
    }


}
