#!/bin/bash
CONTAINER_ID=$(docker container ls -f "name=cuppa" -q)

echo "> 컨테이너 ID는 무엇?? ${CONTAINER_ID}"

if [ -z ${CONTAINER_ID} ] # 검색된 컨테이너 id가 없으면
then
  echo "> 현재 구동중인 컨테이너가 없습니다. 서버를 실행합니다." >> /home/ubuntu/cuppa/deploy.log
else
  echo "> docker stop ${CONTAINER_ID}"
  sudo docker stop ${CONTAINER_ID}
  echo "> docker rm ${CONTAINER_ID}"
  sudo docker rm ${CONTAINER_ID}
  sleep 5
fi

cd /home/ubuntu/cuppa &&
sudo docker-compose up -d rabbitmq && sleep 15 &&
sudo docker-compose up --build -d application
