# 使用OpenJDK 8作为基础镜像
FROM openjdk:8-jdk-alpine
# 设置工作目录，后续操作将在该目录下进行
WORKDIR /app
# 将项目打包生成的JAR文件复制到容器内
COPY target/prometheus-demo-1.0-SNAPSHOT.jar app.jar
RUN chmod +x app.jar
# 暴露容器内部应用的端口，与Spring Boot项目中配置的server.port一致
EXPOSE 8080
# 设置容器启动时执行的命令，运行JAR文件
ENTRYPOINT ["java", "-jar", "app.jar", "-Xms256m", "-Xmx512m"]
# # 健康检查，每30秒检查一次，超时5秒，重试3次
# # 假设应用使用Spring Boot Actuator提供健康检查接口，路径为/actuator/health
# HEALTHCHECK --interval=30s --timeout=5s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1