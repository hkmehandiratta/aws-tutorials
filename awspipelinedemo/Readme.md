
# User Data for EC2 instance. 
# Source for Tomcat may change, that's for apache tomcat. You only need to make changes for download link. There wouldn't be any change required in application code 
# wget https://aws-codedeploy-$region.s3.amazonaws.com/latest/install , make changes in this link based on your region. Here, it's North Virginia.
 
 #!/bin/bash

sudo yum -y update

sudo yum -y install ruby

wget https://aws-codedeploy-us-east-1.s3.amazonaws.com/latest/install

sudo chmod +x ./install

sudo ./install auto

wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.78/bin/apache-tomcat-8.5.78.tar.gz

sudo mkdir /opt

tar xf apache-tomcat-8.5.78.tar.gz

sudo mv apache-tomcat-8.5.78 tomcat

sudo mv tomcat /opt

cd /opt

sudo chown ec2-user:ec2-user tomcat -R

sudo chmod +x /opt/tomcat/bin/./startup.sh

sudo chmod +x /opt/tomcat/bin/./shutdown.sh

sudo /opt/tomcat/bin/./startup.sh

## Test Changes 1

