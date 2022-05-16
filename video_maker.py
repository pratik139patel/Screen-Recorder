import cv2
import pyautogui._pyautogui_win

out = cv2.VideoWriter('ScreenRecording\\ScreenRecording.mp4',cv2.VideoWriter_fourcc(*'mp4v'), 15, pyautogui.size())

for i in range(int(open("img\\frame_data.txt").readline())):
    out.write(cv2.imread('img\\capture' + str(i) + '.jpeg'))

out.release()
