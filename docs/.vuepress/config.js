import {defineUserConfig} from 'vuepress'
import {defaultTheme} from 'vuepress'

export default defineUserConfig({
  base: '/arkanoid/',
  lang: 'es-ES',
  title: 'Arkanoid :: UNITEC ',
  description: 'Sitio para el proyecto Arkanoid Java de la asignatura, Programación Orientada a Objetos de la UNITEC',
  head: [['link', {rel: 'icon', href: '/img/educacion.png'}]],
  editLink: false,
  theme: defaultTheme({
    logo: '/img/educacion.png',
    repo: 'https://github.com/jesus-castro89/arkanoid',
    navbar: [
      {
        text: 'Inicio',
        link: '/',
      },
      {
        text: 'Guía del Proyecto',
        link: '/guia/inicio/'
      },
    ],
    sidebar: [
      {
        text: 'Parte 1: Demos Inicio',
        collapsible: true,
        link: '/guia/inicio/index.md',
        children: [
          '/guia/inicio/index.md', '/guia/inicio/sprite.md', '/guia/inicio/interfaces.md', '/guia/inicio/graphics.md'
        ]
      },
      {
        text: 'Parte 2: Bordes, Tipos Enumerados y Niveles',
        collapsible: true,
        link: '/guia/levels/README.md',
        children: [
          '/guia/levels/README.md', '/guia/levels/borders.md', '/guia/levels/bonus.md', '/guia/levels/brick.md',
          '/guia/levels/paddle.md', '/guia/levels/level.md'
        ]
      },
      {
        text: 'Parte 3: Ventanas, Sprites y Niveles',
        collapsible: true,
        link: '/guia/gui/README.md',
        children: [
          '/guia/gui/README.md', '/guia/gui/main-window.md', '/guia/gui/game-panel.md', '/guia/gui/game-cycle.md',
          '/guia/gui/level.md', '/guia/gui/file-manager.md'
        ]
      },
      {
        text: 'Parte 4: Movimientos y Colisiones',
        collapsible: true,
        link: '/guia/move/README.md',
        children: [
          '/guia/move/README.md', '/guia/move/ball.md', '/guia/move/paddle.md', '/guia/move/laser.md',
          '/guia/move/bonus.md', '/guia/move/keyboard.md'
        ]
      },
      {
        text: 'Parte 5: Reproductor y acciones finales',
        collapsible: true,
        link: '/guia/sonido/README.md',
        children: [
          '/guia/sonido/README.md', '/guia/sonido/audio_player.md', '/guia/sonido/main_window.md',
          '/guia/sonido/game_panel.md', '/guia/sonido/brick.md'
        ]
      }
    ]
  }),
  markdown: {},
})
