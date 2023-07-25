import { defineUserConfig } from 'vuepress'
import { defaultTheme } from 'vuepress'

export default defineUserConfig({
  base: '/arkanoid/',
  lang: 'es-ES',
  title: 'Arkanoid :: UNITEC ',
  description: 'Sitio para el proyecto Arkanoid Java de la asignatura, Programación Orientada a Objetos de la UNITEC',
  head: [['link', { rel: 'icon', href: '/img/educacion.png' }]],
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
        link: '/guia/inicio'
      },
    ],
    sidebar: [
      {
        text: 'Parte 1: Demos Inicio',
        link: '/guia/inicio/README.md',
        children: [
          '/guia/inicio/sprite.md', '/guia/inicio/interfaces.md', '/guia/inicio/graphics.md'
        ]
      },
      {
        text: 'Parte 2: Bordes, Tipos Enumerados y Niveles',
        link: '/guia/levels/README.md',
        children: [
          '/guia/levels/borders.md', '/guia/levels/bonus.md', '/guia/levels/brick.md', '/guia/levels/paddle.md',
          '/guia/levels/level.md'
        ]
      }
    ]
  }),
  markdown: {
    extendMarkdown: md => {
      md.use(require("markdown-it-footnote"));
    }
  },
})
