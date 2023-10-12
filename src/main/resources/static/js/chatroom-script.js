let nickname = sessionStorage.getItem("nickname");
const urlParams = new URL(location.href).searchParams;
const chatRoomId = urlParams.get('chatRoomId');

if(!nickname || !chatRoomId) {
    window.location.href = `/home.html`;
}

$(document).ready(function(){

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

    // 이미지 버튼을 누르면 이미지 업로드 창 나오도록 설정
    $('#image-button').click(function() {
        $('#image-upload').click();
    });

    // 이미지 업로드 이벤트
    $('#image-upload').change(function(e) {
        var file = e.target.files[0];

        // 이미지 파일 크기가 10MB를 초과하면 경고 메시지를 출력하고 리턴
        if (file.size > 10000000) {
            alert('이미지 파일 크기는 최대 10MB를 초과할 수 없습니다.');
            return;
        }

        if (file && file.type.match('image.*')) {
            const formData = new FormData();
            formData.append('multipartFile', file);

            $.ajax({
                url: '/api/chat/image',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(data) {
                    console.log('Image upload successful:', data);

                    var chatImageRequestDTO = {
                        "roomId": chatRoomId,
                        "sender": nickname,
                        "imageUrl": data.imageUrl,
                    };

                    stompClient.send("/app/chat/image", {}, JSON.stringify(chatImageRequestDTO));

                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Image upload failed:', errorThrown);
                }
            });
        }
    });

    // SockJS, stomp 클라이언트 생성
    const socket = new SockJS(`/ws?roomId=${chatRoomId}`);
    const stompClient = Stomp.over(socket);

    // 서버 웹소켓 연결
    stompClient.connect({}, function(frame) {
        // 채팅방 웹소켓 구독
        stompClient.subscribe(`/topic/chat/room/${chatRoomId}`, function(response) {
            const data = JSON.parse(response.body);

            addMessage(data);

            // 스크롤을 최신 메시지 위치로 이동
            $("#chat-box").scrollTop($("#chat-box")[0].scrollHeight);
        });

        // 사용자가 채팅방에 입장하여 'JOIN' 메시지 전송
        const joinMessageRequestDTO = {
            "roomId": chatRoomId,
            "sender": nickname,
            "type": "JOIN"
        };

        stompClient.send("/app/chat/join", {}, JSON.stringify(joinMessageRequestDTO));
    });

    // 채팅방 나가기 버튼 클릭 이벤트
    $('#exit-button').click(function() {
        const leaveMessageRequestDTO = {
            "roomId": chatRoomId,
            "sender": nickname
        };

        // 메시지 전송
        stompClient.send("/app/chat/leave", {}, JSON.stringify(leaveMessageRequestDTO));

        // 채팅방 목록 페이지로 이동
        window.location.href = `/chatrooms.html`;
    });

    // 메시지 전송 버튼 이벤트
    $("#send-button").click(function() {
        sendTextMessage();
    });

    // 메시지 전송 엔터키 이벤트
    $("#message-input").on("keyup",function(key){
        if(key.keyCode===13) {
            sendTextMessage();
        }
    });

    function sendTextMessage() {
        var content = $("#message-input").val();

        if(content) {
            var messageRequestDTO = {
                "roomId": chatRoomId,
                "sender": nickname,
                "content": content,
            };

            // 메시지 전송
            stompClient.send("/app/chat/text", {}, JSON.stringify(messageRequestDTO));

            // 메시지 입력창 초기화
            $("#message-input").val("");
        }
    }

    // 메시지 내용 View에 추가
    function addMessage(data) {
        let chatMessage;
        if (data.type === 'TEXT') {     // 텍스트 메시지
            chatMessage = `
                <div class="chat-message mb-3">
                    <div class="d-flex justify-content-between">
                        <span class="chat-sender">${data.sender}</span>
                        <span class="chat-sender-ip">${data.senderIp}</span>
                    </div>
                    <div class="chat-content">
                        ${data.content}
                    </div>
                </div>
            `;
        } else if (data.type === 'IMAGE') {     // 이미지
            chatMessage = `
                <div class="chat-message mb-3">
                    <div class="d-flex justify-content-between">
                        <span class="chat-sender">${data.sender}</span>
                        <span class="chat-sender-ip">${data.senderIp}</span>
                    </div>
                    <div class="chat-content">
                        <img src="${data.imageUrl}" alt="image" width="200">
                    </div>
                </div>
            `;
        } else if (data.type === 'JOIN') {      // 입장
            chatMessage = `
                <div class="chat-message mb-3">
                    <div class="chat-content text-center">
                        <em>${data.sender}(${data.senderIp})님이 참가하셨습니다.</em>
                    </div>
                </div>
            `;
        } else if (data.type === 'LEAVE') {     // 퇴장
            chatMessage = `
                <div class="chat-message mb-3">
                    <div class="chat-content text-center">
                        <em>${data.sender}(${data.senderIp})님이 나가셨습니다.</em>
                    </div>
                </div>
            `;
        }

        $('#chat-box').append(chatMessage);
    }
});