"use strict";
let cams = "";

const onCamera = () => {
    if (navigator.webkitGetUserMedia != null) {
        let options = {
            video: true,
        };

        // Запрашиваем доступ к веб-камере
        navigator.webkitGetUserMedia(
            options,
            function (stream) {
                // Получаем тег video
                let video = document.getElementById("video-player");
                // Включаем поток в тег video
                video.srcObject = stream;
            },
            function () {
                console.log("произошла ошибка");
            }
        );
    }
};

const online = () => {
    $( "#main > video").each( function( index, element) {
        let id = $( element).attr( "id")
        cams = cams + "," + id;
    });
    $(document).ready(function () {
        $('#get__сhips').click(function () {
            console.log(cams)
            $.ajax({
                url: '/request',
                type: 'get',
                dataType: 'html',
                data: {ids : cams},
                success : function(data) {
                    console.log(cams);
                    onMessageReceived(data);
                },
            });
        });
    });
}
const onMessageReceived = (info) => {
    let chip = document.createElement("div");
    chip.classList.add("chips__chip");
    chip.innerHTML =
        "<label class=" + "chips__info" + ">" + info + "</label>";
    document.querySelector("#chips").appendChild(chip);
    setTimeout(() => {
        chip.remove();
    }, 3000);
};

document.addEventListener("DOMContentLoaded", online);