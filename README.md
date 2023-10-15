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
- **중복 닉네임 구별**: 클라이언트 IPv4 주소를 확인하여 채팅방에 같은 닉네임의 사용자가 존재할 경우 구별할 수 있도록 구현하였습니다. 
- **REST API 구현**: 채팅방 생성 및 이미지 업로드를 위해 RESTful API를 설계하고 구현하였습니다.
- **이미지 전송**: FormData 객체를 사용하여 이미지를 서버에 전송하면, AWS S3에 저장하고 해당 URL을 웹소켓으로 전송해 채팅창에 표시하도록 하였습니다.
- **실시간 렌더링**: 웹소켓 메시지를 받을 때마다 jQuery를 이용하여 DOM을 업데이트하고, 실시간으로 메시지 렌더링할 수 있도록 구현하였습니다.

## 프로젝트 실행 방법 👨‍💻
1. **설계**: 웹 채팅 애플리케이션에 필요한 기능을 파악하고 설계하였습니다.
2. **개발**: 채팅방 기본 구조 및 웹소켓 연결, 텍스트와 이미지 전송 기능 및 REST API 등을 포함하여 순차적으로 개발을 진행하였습니다.
3. **테스트**: JUnit과 MockMvc를 사용하여 각 Controller마다 단위 테스트를 진행하였습니다.
4. **배포 및 모니터링**: AWS EC2와 Docker를 이용하여 배포하였으며, 애플리케이션의 상태를 지속적으로 모니터링하고 있습니다.

## 추후 개선 사항 🛠️
- **값 검증 코드 추가**: 현재는 입력 값의 유효성 검사를 제한적으로 처리하고 있어, 엄격한 값 검증 및 에러 핸들링 로직을 추가하여 애플리케이션의 안정성을 높일 생각입니다.
- **CI/CD 배포**: 지속적인 통합 (CI) 및 지속적인 배포 (CD) 파이프라인을 구축하여 자동화된 테스트와 배포 프로세스를 구축할 생각입니다.
