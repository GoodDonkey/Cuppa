# 💫 프로젝트 개요

Spring WebSocket 을 활용한 채팅 웹사이트 입니다.



# 🛠️ 시스템 아키텍처

![cuppa-cicd2.png](https://github.com/GoodDonkey/Cuppa/blob/b316a97d36be1dc8e90c2ea3fa6f20901c79c19b/etc/cuppa-cicd2.png)

# 🧙 문서
[WebSocket](https://hungrydonkey.notion.site/WebSocket-8d97faedd282461697fa3e066e477a86)

[Stomp](https://hungrydonkey.notion.site/Stomp-9549a8b166e1467791ee5f1d1fa1d534)

[SockJS](https://hungrydonkey.notion.site/SockJS-d2b76184c3ab456f92be6ca5d41c4deb)

[Route53 + ACM + ELB로 DNS 설정, HTTPS 설정, 로드밸런서 설정하기](https://hungrydonkey.notion.site/Route53-ACM-ELB-DNS-HTTPS-f4ee5d273d3a4360b975a7a358592cc2)

[Github Actions + CodeDeploy 로 CI/CD 구축하기](https://hungrydonkey.notion.site/Github-Actions-CodeDeploy-CI-CD-571337fe0ca94912a1ecc6d51822195f)

[Message Queue 개념 + RabbitMQ 프로젝트에 적용해보기](https://hungrydonkey.notion.site/Message-Queue-RabbitMQ-34841b5d26fd4dfaaf71175f91cb37bd)

[Spring Security 비동기 요청 검증에 대한 예외 처리하기](https://hungrydonkey.notion.site/Spring-Security-a971ba8dbeb54fdf89370b59b6114c39)


# 🪣 DB 설계

![cuppa-ERD.png](https://raw.githubusercontent.com/GoodDonkey/Cuppa/e5f29426d94c7af30148c61dc167af12f396d902/etc/cuppa-ERD.png)

# 🙉 프로젝트 설정

## 1. 환경

### BackEnd Framework

- Java 11
- Spring Boot
- Spring Data JPA
- Spring Websocket
- Spring Validation
- Spring Security

### DB

- MySQL

### FrontEnd

- HTML/CSS
- Javascript / Jquery
- Bootstrap

### WebSocket

- SockJS
- Stomp

### MessageBroker

- RabbitMQ

### Misc.

- Docker
- Swagger
- Github Actions

# 🤠 구현할 기능

### 단계 1

- [x]  1:1 메시지 주고 받기
- [x]  회원 기능과 통합
- [x]  메시지 저장 및 꺼내오기

### 단계 2

- [x]  프로젝트 패키징
- [x]  CI/CD — GitHub Action

### 단계 3

- [ ]  채팅방 구현하기
- [ ]  접속 상태 표시
- [ ]  메시지 확인
