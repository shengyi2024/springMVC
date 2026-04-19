package com.atguigu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现文件下载功能 文件复制到浏览器
 * ResponseEntity 用于控制器方法的返回值类型 该控制器方法的返回值类就是响应到浏览器的响应报文
 * 现在使用ResponseEntity实现文件下载的功能
 * 创建RespnseEntity对象，有三个参数:
 *      1.下载文件的字节数组
 *      2.响应头信息
 *      3.响应状态码
 */
@Controller
public class testFileDownController {


    // 如果控制器方法没有设置返回值，Thymeleaf解析器默认把RequestMapping注解中参数的值作为逻辑视图进行跳转
    // ResonseEntity对象的泛型表示的是响应到浏览器中的数据的类型
    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testDown(HttpSession session) throws IOException {
        // 获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        // 获取服务器中文件的真实路径
        // 如果getRealPath()方法参数为""，则返回的是当前项目的真实路径
        // 如果getRealPath()方法参数为具体路径，则返回的是当前项目的指定的资源路径
        // getRealPath()方法的参数参数会直接拼接到当前项目的真实路径后面
        String realPath = servletContext.getRealPath("img");
        // 不确定分隔符，使用File.separator 会自动根据系统自动选择分隔符
        realPath = realPath + File.separator + "target.jpg";
        // 获取需要下载文件的字节 创建输入流
        InputStream is = new FileInputStream(realPath);
        // 创建字符数组 大小设置为输入流中文件的大小 也就是说创建的字符数组大小为文件大小
        // is.available() 获取输入流中的文件的字节数
        // TODO 如果文件特别大，怎么处理？
        byte[] bytes = new byte[is.available()];
        // 将流读到字符数组中
        is.read(bytes);
        // 关闭输入流
        is.close();
        // 创建响应头信息 本质是map集合
        MultiValueMap<String, String> headers = new HttpHeaders();
        // 设置下载方式以及下载文件的名字
        // Content-Disposition:设置下载方式 固定格式
        // attachment;filename=:attachment表示以附件方式下载 filename=:后接文件默认名
        headers.add("Content-Disposition", "attachment;filename=target.jpg");
        // 设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        // 创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes,headers,statusCode);
        // 返回ResponseEntity对象
        return responseEntity;
    }
}
