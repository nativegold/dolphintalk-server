$(document).ready(function(){

    // 채팅방 목록을 가져오는 AJAX 요청
    $.ajax({
        url: '/api/rooms',
        type: 'GET',
        success: function(response) {
            const chatRooms = response.chatRooms;

            // HTML 리스트에 채팅방 이름을 추가
            chatRooms.forEach(function(chatRoom) {
                $('#chat-room-list').append('<li><span>' + chatRoom.roomName + '</span><button>입장</button></li>');
            });
        },
        error: function(error) {
            console.error('Error fetching chat rooms:', error);
        }
    });

    let nickname = sessionStorage.getItem("nickname");

    // 페이지 로딩 시 닉네임 표시
    $('#nickname-display').text(nickname);

    // 닉네임 변경 버튼 클릭 이벤트
    $('#change-nickname-btn').click(function() {
        const newNickname = prompt("변경할 닉네임을 입력하세요:"); // 새 닉네임을 입력받음
        if (newNickname) { // 입력값이 있는 경우에만 닉네임 업데이트
            nickname = newNickname;
            $('#nickname-display').text(nickname);
        }
    });

    // 채팅방 생성 버튼 클릭 이벤트
    $('#create-chatroom').click(function() {
        if (nickname) {
            const newChatRoomName = prompt("생성할 채팅방 이름을 입력하세요:");

            if (newChatRoomName) {
                $.ajax({
                    url: '/api/room',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'chatRoomName': newChatRoomName
                    }),
                    success: function(response) {
                        const chatRoomId = response.roomId;

                        sessionStorage.setItem("nickname", nickname);
                        window.location.href = `/chatroom.html?chatRoomId=${chatRoomId}`;
                    },
                    error: function(error) {
                        console.error('Error: 채팅방 생성 실패', error);
                    }
                });
            }
        }
    });
});