$(function(){
    console.log("=======================페이지 시작합니다=======================")
    getChatRooms();
});

function getChatRooms(){
    $.ajax({
        type: "GET",
        url: "/chatRoom/get",
        success: function (response) {
            console.log(response)
            appendChatRoom(response);

        },error: function (error){
            console.log(error)
        }
    });
}
function appendChatRoom(chatRoomData){
    let chatSection = $('#chatRoomList');
    for(let i =0; i<chatRoomData.length;i++) {
        let chatRoomId   = chatRoomData[i]["chatRoomId"];
        let chatRoomName = chatRoomData[i]["chatRoomName"];
        let chatRoom = `<tr id="${chatRoomId}" onclick="location.href='/chat/${chatRoomId}'">
                            <td>${chatRoomName}</td>
                        </tr>`;
        chatSection.append(chatRoom);
    }
}