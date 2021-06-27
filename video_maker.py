import cv2
import numpy as np
import glob
import pyautogui._pyautogui_win

file = open("frame_data.txt")

img_array = []
for i in range(0, eval(file.readline())):
    img = cv2.imread('img\\capture' + str(i) + '.jpeg')
    img_array.append(img)

#replace 'mp4v' with 'DIVX' if the ap fails...
out = cv2.VideoWriter('ScreenRecording\\ScreenRecording.mp4',cv2.VideoWriter_fourcc(*'mp4v'), 15, pyautogui.size())

for i in range(len(img_array)):
    out.write(img_array[i])

out.release()
