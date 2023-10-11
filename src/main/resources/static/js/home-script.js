$(document).ready(function(){
    $("#nickname-form").submit(function(event) {
        event.preventDefault();
        var nickname = $("#nickname").val();

        // 닉네임 값을 Local Storage에 저장
        sessionStorage.setItem("nickname", nickname);

        // 다음 페이지로 이동
        window.location.href = "/chat/rooms";
    });
});