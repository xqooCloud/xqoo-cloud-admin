@echo off
  java -Dserver.port=8718 ^
  -Dspring.config.location=./config/application.properties ^
  -Dcsp.sentinel.dashboard.server=127.0.0.1:8718 ^
  -Dproject.name=sentinel-dashboard ^
  -Dsentinel.dashboard.auth.username=sentinel ^
  -Dsentinel.dashboard.auth.password=!@#123qwe ^
  -jar sentinel-dashboard.jar
  pause
