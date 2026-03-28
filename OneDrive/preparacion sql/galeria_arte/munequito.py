

import pygame
import sys

pygame.init()

ancho, alto = 800, 500
pantalla = pygame.display.set_mode((ancho, alto))
pygame.display.set_caption("Galeria de Arte - Paintbrush Blue")

reloj = pygame.time.Clock()

munequito_x = 400
munequito_y = 350
velocidad = 3

NEGRO         = (10, 10, 10)
PARED         = (26, 26, 26)
PISO          = (20, 20, 20)
AZUL_MAYA     = (26, 107, 138)
DORADO        = (201, 169, 110)
CAFE          = (74, 55, 40)
VERDE_OSCURO  = (42, 74, 40)
BLANCO_CALIDO = (232, 224, 213)

# Primer frame antes del loop
pantalla.fill(NEGRO)
pygame.display.flip()

while True:
    for evento in pygame.event.get():
        if evento.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

    teclas = pygame.key.get_pressed()
    if teclas[pygame.K_LEFT]:  munequito_x -= velocidad
    if teclas[pygame.K_RIGHT]: munequito_x += velocidad
    if teclas[pygame.K_UP]:    munequito_y -= velocidad
    if teclas[pygame.K_DOWN]:  munequito_y += velocidad

    pantalla.fill(NEGRO)
    pygame.draw.rect(pantalla, PARED,        (0, 0, ancho, 300))
    pygame.draw.rect(pantalla, PISO,         (0, 300, ancho, 200))
    pintura = pygame.image.load("cristo.jpeg")
    pintura = pygame.transform.scale(pintura, (120, 150))
    pantalla.blit(pintura, (100, 80))
    pygame.draw.rect(pantalla, DORADO, (100, 80, 120, 150), 3)
    totoro = pygame.image.load("totoro.jpeg")
    totoro = pygame.transform.scale(totoro, (120, 150))
    pantalla.blit(totoro, (340, 80))
    pygame.draw.rect(pantalla, DORADO, (340, 80, 120, 150), 3)
    vango = pygame.image.load("vango.jpeg")
    vango = pygame.transform.scale(vango, (120, 150))
    pantalla.blit(vango, (580, 80))
    pygame.draw.rect(pantalla, DORADO, (580, 80, 120, 150), 3)
    pygame.draw.circle(pantalla, BLANCO_CALIDO, (munequito_x, munequito_y - 40), 12)
    pygame.draw.rect(pantalla,   BLANCO_CALIDO, (munequito_x - 8, munequito_y - 28, 16, 30))
    pygame.draw.rect(pantalla,   BLANCO_CALIDO, (munequito_x - 8, munequito_y + 2, 6, 20))
    pygame.draw.rect(pantalla,   BLANCO_CALIDO, (munequito_x + 2, munequito_y + 2, 6, 20))

    pygame.display.flip()
    reloj.tick(60)