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

# docker 설치 확인 및 설치
docker info && echo "> docker already installed" >> /home/ubuntu/cuppa/deploy.log || install_docker
docker-compose version && echo "> docker-compose already installed" >> /home/ubuntu/cuppa/deploy.log || install_docker_compose
