#We need below user data for EC2 instance. Please note that your EC2 instance 

# should have AmazonSQSFullAccess, AmazonS3FullAccess, CloudWatchFullAccess, AmazonDynamoDBFullAccess, AWSCodeDeployFullAccess 

#!/bin/bash

sudo yum -y update

sudo yum -y install ruby

wget https://aws-codedeploy-us-east-1.s3.amazonaws.com/latest/install

sudo chmod +x ./install

sudo ./install auto

sudo mkdir /home/ec2-user/catalog-service

sudo chown ec2-user:ec2-user /home -R
