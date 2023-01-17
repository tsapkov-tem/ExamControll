"use strict";

let stompClient;
let username;
let separate;
let isLoadMessage;

const onCamera = () => {
  if (navigator.webkitGetUserMedia != null) {
    var options = {
      video: true,
    };

    // Запрашиваем доступ к веб-камере
    navigator.webkitGetUserMedia(
      options,
      function (stream) {
        // Получаем тег video
        var video = document.getElementById("video-player");
        // Включаем поток в тег video
        video.srcObject = stream;
      },
      function (e) {
        console.log("произошла ошибка");
      }
    );
  }
};

const connect = (event) => {
  onCamera();
  const socket = new SockJS("/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, online);
  separate = 0;
  event.preventDefault();

  document.querySelector("#chp").onclick = () => {};
};

const online = () => {
  stompClient.subscribe("/topic/public", onMessageReceived);
};

const onMessageReceived = (payload) => {
  const message = JSON.parse(payload.body);

  let chip = document.createElement("div");
  chip.classList.add("chips__chip");
  chip.innerHTML =
    "<label class=" + "chips__info" + ">" + message.info + "</label>";
  document.querySelector("#chips").appendChild(chip);
  setTimeout(() => {
    chip.remove();
  }, 3000);
};

document.addEventListener("DOMContentLoaded", connect);
