import os
from datetime import datetime

import cv2
import numpy as np
from py4j.java_gateway import JavaGateway


def detection(entry_point):
    net = cv2.dnn.readNet("yolov3.weights", "yolov3.cfg")
    with open("coco.names", "r") as f:
        classes = [line.strip() for line in f.readlines()]
    layer_names = net.getLayerNames()
    output_layers = [layer_names[i - 1] for i in net.getUnconnectedOutLayers()]

    cap = cv2.VideoCapture(0)
    font = cv2.FONT_HERSHEY_PLAIN

    class_ids = []
    confidences = []
    boxes = []

    count_phone_detect = 0
    current_frame = 0

    try:

        # creating a folder named data
        if not os.path.exists('data'):
            os.makedirs('data')

        # if not created then raise error
    except OSError:
        print('Error: Creating directory of data')

    while True:
        ret, frame = cap.read()
        ret, frame2 = cap.read()

        diff = cv2.absdiff(frame, frame2)
        gray = cv2.cvtColor(diff, cv2.COLOR_BGR2GRAY)
        blur = cv2.GaussianBlur(gray, (5, 5), 0)
        _, thresh = cv2.threshold(blur, 20, 255, cv2.THRESH_BINARY)
        dilated = cv2.dilate(thresh, None, iterations=3)
        contours, _ = cv2.findContours(dilated, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
        for contour in contours:
            (x, y, w, h) = cv2.boundingRect(contour)
            if cv2.contourArea(contour) < 4500:
                continue
            cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)
            print("Detect Move")

            # entry_point.moveDetected()

            move_detected(entry_point)

        height, width, channels = frame.shape
        blob = cv2.dnn.blobFromImage(frame, 0.00392, (320, 320), (0, 0, 0), True, crop=False)
        net.setInput(blob)
        outs = net.forward(output_layers)

        for out in outs:
            for detection in out:
                scores = detection[5:]
                class_id = np.argmax(scores)
                confidence = scores[class_id]
                if (confidence > 0.8 and class_id == 67):
                    center_x = int(detection[0] * width)
                    center_y = int(detection[1] * height)
                    w = int(detection[2] * width)
                    h = int(detection[3] * height)
                    x = int(center_x - w / 2)
                    y = int(center_y - h / 2)
                    boxes.append([x, y, w, h])
                    confidences.append(float(confidence))
                    class_ids.append(class_id)

        indexes = cv2.dnn.NMSBoxes(boxes, confidences, 0.4, 0.3)

        for i in range(len(boxes)):
            if i in indexes:
                x, y, w, h = boxes[i]
                label = classes[class_ids[i]]
                count_phone_detect+=1
                if(count_phone_detect % 5 == 0):
                    print("Cell phone detected")
                    current_frame +=1
                    path = './data/frame' + str(current_frame) + '.jpg'
                    cv2.imwrite(path, frame)

                    # entry_point.phoneDetected()

                    phone_detected(entry_point, path)

                cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 0, 255), 2)
                cv2.putText(frame, label, (x, y + 30), font, 1, (0, 0, 0), 3)

        cv2.imshow("Image", frame)
        key = cv2.waitKey(1)
        if key == 27:
            break

    cap.release()
    cv2.destroyAllWindows()


def phone_detected(point, path):
    # Передать время, камеру
    id_cam = "636b4cfe943dff06e99cbc88"
    time = str(datetime.now().time())
    point.phoneDetected(id_cam, str(time), path)


def move_detected(point):
    # Передать id камеры
    id_cam = "636b4cfe943dff06e99cbc88"
    point.moveDetected(id_cam)

def main():
    gateway = JavaGateway()
    entry_point = gateway.entry_point
    detection(entry_point)


if __name__ == '__main__':
    main()
