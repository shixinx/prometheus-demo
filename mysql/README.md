mysql -h localhost -P 3333 -u root -p
rootroot
## 创建用户
CREATE USER 'exporter'@'%' IDENTIFIED BY 'password' WITH MAX_USER_CONNECTIONS 3;
GRANT PROCESS, REPLICATION CLIENT, SELECT ON *.* TO 'exporter'@'%';

docker exec -it mysql mysql -uexporter -p

