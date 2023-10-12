let nickname = sessionStorage.getItem("nickname");

if(!nickname) {
    window.location.href = `/home.html`;
}

function fetchChatRooms() {
    // 채팅방 목록을 가져오는 AJAX 요청
    $.ajax({
        url: '/api/rooms',
        type: 'GET',
        success: function(response) {
            const chatRooms = response.chatRooms;
            $('#chat-room-list').empty(); // 기존 채팅방 목록을 비움

            // HTML 리스트에 채팅방 이름을 추가
            chatRooms.forEach(function(chatRoom) {
                $('#chat-room-list').append(`
                    <li>
                        <span>${chatRoom.roomName}</span>
                        <button class="chatroom-join-button" id="${chatRoom.roomId}">입장</button>
                    </li>
                `);
            });
        },
        error: function(error) {
            console.error('Error fetching chat rooms:', error);
        }
    });
}

$(document).ready(function(){

    fetchChatRooms();

    $(document).on('click', '.chatroom-join-button', function() {
        const chatRoomId = $(this).attr('id');
        window.location.href = `/chatroom.html?chatRoomId=${chatRoomId}`;
    });

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
                        'roomName': newChatRoomName
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

    // 채팅방 목록 새로고침 버튼 클릭 이벤트
    $('#refresh-chatrooms').click(function() {
        fetchChatRooms(); // 채팅방 목록을 다시 가져옴
    });
});