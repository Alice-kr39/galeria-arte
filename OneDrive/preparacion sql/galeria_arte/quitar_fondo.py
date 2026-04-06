#quitar fondo al marco 

import cv2
import numpy as np

img = cv2.imread(r"marcodorado.jpg")
img_rgba = cv2.cvtColor(img, cv2.COLOR_BGR2BGRA)

# Quitar TODO lo que no sea dorado
hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

# Rango del color dorado en HSV
lower_dorado = np.array([15, 80, 80])
upper_dorado = np.array([40, 255, 255])

# Máscara del dorado — lo que SÍ queremos conservar
mascara_dorado = cv2.inRange(hsv, lower_dorado, upper_dorado)

# Todo lo que NO es dorado se vuelve transparente
img_rgba[:, :, 3] = mascara_dorado

cv2.imwrite("marco_transparente.png", img_rgba)
print("Listo")