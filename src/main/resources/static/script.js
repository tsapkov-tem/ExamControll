"use strict";

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
  $(document).ready(function () {
    $('#search-btn').click(function () {
      $.ajax({
        url: '/request',
        type: 'get',
        dataType: 'html',
        success : function(data) {
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
