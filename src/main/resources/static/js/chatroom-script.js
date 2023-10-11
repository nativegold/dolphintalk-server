let nickname = sessionStorage.getItem("nickname");
const urlParams = new URL(location.href).searchParams;
const chatRoomId = urlParams.get('chatRoomId');

if(!nickname || !chatRoomId) {
    alert('잘못된 접근입니다. 다시 접속해주세요.');
    window.location.href = `/home.html`;
}

$(document).ready(function(){

    // 채팅방 나가기 버튼 클릭 이벤트
    $('#exit-button').click(function() {
        window.location.href = `/chatrooms.html`;
    });

    // SockJS, stomp 클라이언트 생성
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    // 서버 웹소켓 연결
    stompClient.connect({}, function(frame) {
        // 채팅방 웹소켓 구독
        stompClient.subscribe(`/topic/chat/room/${chatRoomId}`, function(response) {
            console.log(response.body);
            var data = JSON.parse(response.body);
            // 새로운 메시지 HTML 요소 생성
            var newMessage = $("<div>").addClass("message");

            // 닉네임, IP, 메시지 내용을 요소에 추가
            $("<span>").addClass("nickname").text(data.sender).appendTo(newMessage);
            $("<span>").addClass("ip").text(data.senderIp).appendTo(newMessage);
            $("<p>").addClass("content").text(data.message).appendTo(newMessage);

            // 생성한 메시지 요소를 chat-box에 추가
            $("#chat-box").append(newMessage);

            // 스크롤을 최신 메시지 위치로 이동
            $("#chat-box").scrollTop($("#chat-box")[0].scrollHeight);
        });
    });

    // 메시지 전송 이벤트
    $("#send-button").click(function() {
        var message = $("#message-input").val();
        var messageRequestDTO = {
            "roomId": chatRoomId,
            "sender": nickname,
            "message": message,
        };

        console.log(messageRequestDTO);

        // 메시지 전송
        stompClient.send("/app/chat/message", {}, JSON.stringify(messageRequestDTO));

        // 메시지 입력창 초기화
        $("#message-input").val("");
    });
});