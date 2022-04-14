#!/bin/bash

install_docker()
{
  echo "preparing to install docker..." >> /home/ubuntu/cuppa/deploy.log
  sudo apt update
  sudo apt-get install -y \
      ca-certificates \
      curl \
      software-properties-common \
      apt-transport-https \
      gnupg \
      lsb-release

  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

  sudo add-apt-repository \
     "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
     $(lsb_release -cs) \
     stable"

  echo \
    "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

  sudo apt-get update

  echo "installing docker..." >> /home/ubuntu/cuppa/deploy.log
  sudo apt-get -y install docker-ce docker-ce-cli containerd.io docker-compose
}

install_docker_compose()
{
  echo "installing docker-compose..." >> /home/ubuntu/cuppa/deploy.log

  sudo apt-get update
  sudo apt-get -y docker-compose

}

echo "> sudo docker ps" >> /home/ubuntu/cuppa/deploy.log
sudo docker ps
echo "> sudo docker-compose down" >> /home/ubuntu/cuppa/deploy.log
sudo docker-compose down

CONTAINER_ID=$(sudo docker container ls -f "name=cuppa-backend" -q)

echo "> 컨테이너 ID: ${CONTAINER_ID}"

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

# docker 설치 확인 및 설치
sudo docker info && echo "> docker already installed" >> /home/ubuntu/cuppa/deploy.log || install_docker
sudo docker-compose version && echo "> docker-compose already installed" >> /home/ubuntu/cuppa/deploy.log || install_docker_compose
