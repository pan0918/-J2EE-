
#说明
此文档为大连理工大学软件学院J2EE上机作业

#使用方法
#1.第一、二、五、六次作业，只需要在IDEA中打开，配置一下tomcat即可运行。
#2.第三次作业，需要在IDEA中的终端中输入如下两个命令来启动前端：
  1.cd vue-ui
  2.npm run dev
接着启动后端，在浏览器中输入路径就可以了
#3.第四次作业没有前端文件。利用postman进行接口测试，
  第四五次作业中需要配置百度人脸识别API的key application.properties APP_ID，API_KEY，SECRET_KEY 请求地址：https://ai.baidu.com/tech/face/compare  
  配置完成后需在人脸库中添加组person
  http://localhost:8000/user/login POST请求 form-data: id:学号/教工号,password:密码 返回值：用户token
  http://localhost:8000/user/uploadPhoto form-data: token:用户token,file:人脸照片
  http://localhost:8000/search/face form-data: file:人脸照片
