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

    // 채팅방 정보를 가져오는 AJAX 요청
    $.ajax({
        url: '/api/room',
        type: 'GET',
        data: { 'roomId': chatRoomId },
        success: function(response) {
            const roomName = response.roomName;

            if (!roomName) {
                alert('존재하지 않는 채팅방입니다. 다시 접속해주세요.');
                window.location.href = `/chatrooms.html`;
            }

            $('#chatroom-name').text(roomName);

        },
        error: function(error) {
            console.error('Error fetching chat room:', error);
        }
    });

    // SockJS, stomp 클라이언트 생성
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    // 서버 웹소켓 연결
    stompClient.connect({}, function(frame) {
        // 채팅방 웹소켓 구독
        stompClient.subscribe(`/topic/chat/room/${chatRoomId}`, function(response) {
            console.log(`/topic/chat/room/${chatRoomId}`);
            console.log(response.body);
            var data = JSON.parse(response.body);

            addMessage(data.sender, data.senderIp, data.message);

            // 스크롤을 최신 메시지 위치로 이동
            $("#chat-box").scrollTop($("#chat-box")[0].scrollHeight);
        });
    });

    // 메시지 전송 버튼 이벤트
    $("#send-button").click(function() {
        sendMessage();
    });

    // 메시지 전송 엔터키 이벤트
    $("#message-input").on("keyup",function(key){
        if(key.keyCode===13) {
            sendMessage();
        }
    });

    function addMessage(sender, ip, message) {
        const chatMessage = `
            <div class="chat-message mb-3">
                <div class="d-flex justify-content-between">
                    <span class="chat-sender">${sender}</span>
                    <span class="chat-sender-ip">${ip}</span>
                </div>
                <div class="chat-content">
                    ${message}
                </div>
            </div>
        `;

        $('#chat-box').append(chatMessage);
    }

    function sendMessage() {
        var message = $("#message-input").val();

        if(message) {
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
        }
    }
});