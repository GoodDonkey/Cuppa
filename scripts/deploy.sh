#!/bin/bash


cd /home/ubuntu/cuppa &&
sudo docker-compose up -d rabbitmq && sleep 15 &&
sudo docker-compose up --build -d application
