#!/bin/bash
cd /home/ec2-user/catalog-service
sudo pkill -f 'java.*CatalogService.jar' || true
#Stop the service on default port 8080 if already running.
java -Dspring.profiles.active=prod -jar CatalogService.jar  > /home/ec2-user/catalog-service/mylogfile.txt 2>&1 &
# java -Dspring.profiles.active=prod  -jar CatalogService.jar &
