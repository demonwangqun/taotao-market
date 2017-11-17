package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception {
		// 创建FTPClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建ftp连接。默认是21
		ftpClient.connect("192.168.75.128", 21);
		// 登录ftp服务器，使用用户名密码登录
		ftpClient.login("ftpuser", "ftpuser");
		// 上传文件
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File(
				"C:\\Users\\home\\Desktop\\img2-lg.jpg"));
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改上传文件格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一参数服务器端文件名
		// 第二参数 上传文件的inputStream

		ftpClient.storeFile("hello1.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}

	public void testFtpUtil() throws Exception {
		FileInputStream inputStream = new FileInputStream(new File(
				"C:\\Users\\home\\Desktop\\img2-lg.jpg"));
		FtpUtil.uploadFile("192.168.75.128", 21, "ftpuser", "ftpuser",
				"/home/ftpuser/www/images", "/2017/10/26", "hello1.jpg",
				inputStream);

	}
}
