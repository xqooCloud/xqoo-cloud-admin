@echo off
  java -jar zipkin-server-2.21.7-exec.jar ^
  --STORAGE_TYPE=mysql ^
  --MYSQL_HOST=127.0.0.1 ^
  --MYSQL_TCP_PORT=3306 ^
  --MYSQL_DB=xqoo_system_zipkin ^
  --MYSQL_USER=root ^
  --MYSQL_PASS=Zyj123456.
  pause
