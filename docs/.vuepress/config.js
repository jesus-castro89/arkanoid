import { defineUserConfig } from 'vuepress'
import { defaultTheme } from 'vuepress'

export default defineUserConfig({
  base: '/arkanoid/',
  lang: 'es-ES',
  title: 'Arkanoid :: UNITEC',
  description: 'Sitio para el proyecto Arkanoid Java de la asignatura, Programación Orientada a Objetos de la UNITEC',
  theme: defaultTheme({
      log: './img/educacion.png',
      navbar: [
        {
          text: 'Inicio',
          link: '/',
        },
        {
          text: 'Guía del Proyecto',
          link: '/guia',
        },
      ],
    }),
})
