import shutil
import cv2
import pyautogui._pyautogui_win

out = cv2.VideoWriter('Screen-Recording\\recording.mp4',cv2.VideoWriter_fourcc(*'mp4v'), 15, pyautogui.size())

for i in range(int(open("Screen-Shots\\frame_data.txt").readline())):
    out.write(cv2.imread('Screen-Shots\\capture' + str(i) + '.jpeg'))

out.release()
shutil.rmtree('Screen-Shots')
