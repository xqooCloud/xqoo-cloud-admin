#指定基础镜像，在其上进行定制
FROM frolvlad/alpine-oraclejdk8:slim

#维护者信息
MAINTAINER xqoo

#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp

#复制上下文目录下的生成的jar 到容器里
COPY target/xqoo-third-pay.jar xqoo-third-pay.jar


#声明运行时容器提供服务端口 可以暴露出来的端口 这里不暴露
EXPOSE 9045
#指定默认环境变量
ENV JAVA_OPTS="-Xms256m -Xmx512m"
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT java $JAVA_OPTS -jar xqoo-third-pay.jar
