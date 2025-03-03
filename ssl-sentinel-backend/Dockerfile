
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-amd64

# 设置维护者信息
LABEL maintainer="Denchouka"

# 设置环境变量
ENV JAR_NAME=ssl-sentinel-backend-2.1.0.jar

# 设置工作目录
WORKDIR /ssl

# 将项目的jar文件复制到容器中
COPY target/ssl-sentinel-backend-2.1.0.jar /ssl/ssl-sentinel-backend-2.1.0.jar

# 暴露应用程序使用的端口
EXPOSE 8882

# 运行应用程序
ENTRYPOINT ["sh", "-c", "java -DMYSQL_URL=${MYSQL_URL} -DMYSQL_PASSWORD=${MYSQL_PASSWORD} -jar ssl-sentinel-backend-2.1.0.jar"]

