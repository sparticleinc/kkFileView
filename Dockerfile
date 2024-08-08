FROM keking/kkfileview-jdk:latest

# 复制并自动解压缩
ADD server/target/kkFileView-*.tar.gz /opt/
ENV KKFILEVIEW_BIN_FOLDER /opt/kkFileView-4.4.0-beta/bin

ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/opt/kkFileView-4.4.0-beta/bin/kkFileView-4.4.0-beta.jar"]
