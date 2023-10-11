// 웹소켓 객체 생성 (실제 서버 URL을 사용해주세요)
const webSocket = new WebSocket('/chat/message');
let nickname = sessionStorage.getItem("nickname");

if(!nickname) {
    alert('잘못된 접근입니다. 다시 접속해주세요.');
    window.location.href = `/chat/home`;
}

// 서버로부터 메시지가 도착했을 때의 처리
webSocket.onmessage = function(event) {
    const receivedData = JSON.parse(event.data);
    $("#chat-box").append('<p class="m-2">' + receivedData.sender + ': ' + receivedData.message + '</p>');
};

// 웹소켓 연결이 열렸을 때의 처리
webSocket.onopen = function(event) {
    $("#send-button").click(function(){
        const message = $("#message-input").val();
        if (message) {
            const messageRequestDTO = {
                'roomId': roomId,
                'sender': nickname,
                'message': message,
            };

            // 서버에 메시지 전송
            webSocket.send(JSON.stringify(messageRequestDTO));

            $("#message-input").val("");
        }
    });
};

// 웹소켓 연결이 닫혔을 때의 처리
webSocket.onclose = function(event) {
    alert('WebSocket connection closed');
};