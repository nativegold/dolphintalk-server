# DolphinTalk: 실시간 웹 채팅 애플리케이션 🐬
## 개요 📝
DolphinTalk은 사용자가 닉네임을 설정하여 다양한 채팅방에 참여할 수 있는 실시간 웹 채팅 애플리케이션입니다. 사용자는 텍스트 메시지와 이미지를 실시간으로 전송할 수 있으며, 웹소켓을 이용하여 빠르고 안정적인 채팅 환경을 제공합니다.

## 기술 스택 💻
- **프론트엔드**: jQuery, Ajax, HTML, CSS
- **백엔드**: Spring Boot, Java 17
- **통신**: STOMP 프로토콜 (웹소켓), REST API
- **이미지 저장**: AWS S3
- **테스트 프레임워크**: JUnit, MockMvc
- **배포**: AWS EC2, Docker

## 구현 기능 💬
- **메시지 및 이미지 전송**
- **채팅방 목록, 생성, 삭제**
- **채팅방 사용자 접속 및 퇴장 알림**

## 문제 해결 전략 🤔
- **웹소켓 연결**: SockJS와 STOMP 프로토콜을 사용하여 웹소켓 연결과 실시간 채팅을 가능하게 하였습니다.
- **이미지 전송**: FormData 객체를 사용하여 이미지를 서버에 전송하면, AWS S3에 저장하고 해당 URL을 웹소켓으로 전송해 채팅창에 표시하도록 하였습니다.
- **중복 닉네임 구별**: 클라이언트 IPv4 주소를 확인하여 채팅방에 같은 닉네임의 사용자가 존재할 경우 구별할 수 있도록 구현하였습니다. 
- **REST API 구현**: 채팅방 생성 및 이미지 업로드를 위해 RESTful API를 설계하고 구현하였습니다.
- **실시간 렌더링**: 웹소켓 메시지를 받을 때마다 jQuery를 이용하여 DOM을 업데이트하고, 실시간으로 메시지 렌더링할 수 있도록 구현하였습니다.

## 프로젝트 실행 방법 👨‍💻
프로젝트를 실행하기 위해 아래의 단계를 따라주세요.

### 사전 요구사항
- Java 17
- IntelliJ (추천)
- Gradle
- AWS 계정

### 로컬에서 실행
1. **Repository clone**
    ```bash
    git clone https://github.com/nativegold/dolphintalk-server.git
    ```
2. **프로젝트 내 환경 변수 설정**
   - `src/main/resources/application.yml` 파일에서 AWS S3와 관련된 환경 변수를 설정합니다.
    ```yml
    aws:
    s3:
      bucket: (Enter your AWS S3 bucket)
    credentials:
      access-key: (Enter your access key)
      secret-key: (Enter your secret key)
    region:
      static: (Enter your aws region)
    ```
2. **애플리케이션 실행 후 브라우저에서 확인**
   - 애플리케이션을 실행한 후 브라우저에서 `http://localhost:8080/home.html` 으로 접속하여 결과를 확인합니다.

## 추후 개선 사항 🛠️
- **값 검증 코드 추가**: 현재는 입력 값의 유효성 검사를 제한적으로 처리하고 있어, 엄격한 값 검증 및 에러 핸들링 로직을 추가하여 애플리케이션의 안정성을 높일 생각입니다.
- **CI/CD 배포**: 지속적인 통합 (CI) 및 지속적인 배포 (CD) 파이프라인을 구축하여 자동화된 테스트와 배포 프로세스를 구축할 생각입니다.
