import{_ as i,r as e,o as p,c as r,a as n,b as s,d as a,w as t}from"./app-08222125.js";const u={},k={id:"actualizando-laser",tabindex:"-1"},d=n("a",{class:"header-anchor",href:"#actualizando-laser","aria-hidden":"true"},"#",-1),m=n("p",null,"La clase Laser recibirá una pequeña lavadita de cara para poder agregar el GamePanel y también definir algunas cosas sobre su movimiento.",-1),b=n("div",{class:"language-java line-numbers-mode","data-ext":"java"},[n("pre",{class:"language-java"},[n("code",null,[n("span",{class:"token keyword"},"package"),s(),n("span",{class:"token namespace"},"graphics"),n("span",{class:"token punctuation"},";"),s(`

`),n("span",{class:"token keyword"},"import"),s(),n("span",{class:"token import"},[n("span",{class:"token namespace"},[s("util"),n("span",{class:"token punctuation"},".")]),n("span",{class:"token class-name"},"Globals")]),n("span",{class:"token punctuation"},";"),s(`
`),n("span",{class:"token keyword"},"import"),s(),n("span",{class:"token import"},[n("span",{class:"token namespace"},[s("util"),n("span",{class:"token punctuation"},".")]),n("span",{class:"token class-name"},"Moveable")]),n("span",{class:"token punctuation"},";"),s(`

`),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token keyword"},"class"),s(),n("span",{class:"token class-name"},"Laser"),s(),n("span",{class:"token keyword"},"extends"),s(),n("span",{class:"token class-name"},"Sprite"),s(),n("span",{class:"token keyword"},"implements"),s(),n("span",{class:"token class-name"},"Moveable"),s(),n("span",{class:"token punctuation"},"{"),s(`
    `),n("span",{class:"token keyword"},"private"),s(),n("span",{class:"token keyword"},"boolean"),s(" destroy "),n("span",{class:"token operator"},"="),s(),n("span",{class:"token boolean"},"false"),n("span",{class:"token punctuation"},";"),s(`

    `),n("span",{class:"token doc-comment comment"},[s(`/**
     * Constructor de los objetos que recibe como parámetros, las coordenadas (x, y)
     * de origen de dibujo y el nombre del archivo a dibujar.
     *
     * `),n("span",{class:"token keyword"},"@param"),s(),n("span",{class:"token parameter"},"x"),s(` El valor de la coordenada x
     * `),n("span",{class:"token keyword"},"@param"),s(),n("span",{class:"token parameter"},"y"),s(` El valor de la coordenada y
     */`)]),s(`
    `),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token class-name"},"Laser"),n("span",{class:"token punctuation"},"("),n("span",{class:"token keyword"},"int"),s(" x"),n("span",{class:"token punctuation"},","),s(),n("span",{class:"token keyword"},"int"),s(" y"),n("span",{class:"token punctuation"},")"),s(),n("span",{class:"token punctuation"},"{"),s(`

        `),n("span",{class:"token keyword"},"super"),n("span",{class:"token punctuation"},"("),n("span",{class:"token class-name"},"Globals"),n("span",{class:"token punctuation"},"."),n("span",{class:"token constant"},"LASER_SPRITE"),n("span",{class:"token punctuation"},","),s(" x"),n("span",{class:"token punctuation"},","),s(" y"),n("span",{class:"token punctuation"},")"),n("span",{class:"token punctuation"},";"),s(`
    `),n("span",{class:"token punctuation"},"}"),s(`

    `),n("span",{class:"token annotation punctuation"},"@Override"),s(`
    `),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token keyword"},"void"),s(),n("span",{class:"token function"},"move"),n("span",{class:"token punctuation"},"("),n("span",{class:"token punctuation"},")"),s(),n("span",{class:"token punctuation"},"{"),s(`

        `),n("span",{class:"token keyword"},"this"),n("span",{class:"token punctuation"},"."),n("span",{class:"token function"},"setY"),n("span",{class:"token punctuation"},"("),n("span",{class:"token keyword"},"this"),n("span",{class:"token punctuation"},"."),n("span",{class:"token function"},"getY"),n("span",{class:"token punctuation"},"("),n("span",{class:"token punctuation"},")"),s(),n("span",{class:"token operator"},"-"),s(),n("span",{class:"token number"},"5"),n("span",{class:"token punctuation"},")"),n("span",{class:"token punctuation"},";"),s(`
    `),n("span",{class:"token punctuation"},"}"),s(`
`),n("span",{class:"token punctuation"},"}"),s(`
`)])]),n("div",{class:"line-numbers","aria-hidden":"true"},[n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"})])],-1),v=n("div",{class:"language-java line-numbers-mode","data-ext":"java"},[n("pre",{class:"language-java"},[n("code",null,[n("span",{class:"token keyword"},"package"),s(),n("span",{class:"token namespace"},"graphics"),n("span",{class:"token punctuation"},";"),s(`

`),n("span",{class:"token keyword"},"import"),s(),n("span",{class:"token import"},[n("span",{class:"token namespace"},[s("ui"),n("span",{class:"token punctuation"},".")]),n("span",{class:"token class-name"},"GamePanel")]),n("span",{class:"token punctuation"},";"),s(`
`),n("span",{class:"token keyword"},"import"),s(),n("span",{class:"token import"},[n("span",{class:"token namespace"},[s("util"),n("span",{class:"token punctuation"},".")]),n("span",{class:"token class-name"},"Globals")]),n("span",{class:"token punctuation"},";"),s(`
`),n("span",{class:"token keyword"},"import"),s(),n("span",{class:"token import"},[n("span",{class:"token namespace"},[s("util"),n("span",{class:"token punctuation"},".")]),n("span",{class:"token class-name"},"Moveable")]),n("span",{class:"token punctuation"},";"),s(`

`),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token keyword"},"class"),s(),n("span",{class:"token class-name"},"Laser"),s(),n("span",{class:"token keyword"},"extends"),s(),n("span",{class:"token class-name"},"Sprite"),s(),n("span",{class:"token keyword"},"implements"),s(),n("span",{class:"token class-name"},"Moveable"),s(),n("span",{class:"token punctuation"},"{"),s(`
    `),n("span",{class:"token keyword"},"private"),s(),n("span",{class:"token keyword"},"boolean"),s(" destroy "),n("span",{class:"token operator"},"="),s(),n("span",{class:"token boolean"},"false"),n("span",{class:"token punctuation"},";"),s(`
    `),n("span",{class:"token keyword"},"private"),s(),n("span",{class:"token class-name"},"GamePanel"),s(" gamePanel"),n("span",{class:"token punctuation"},";"),s(`

    `),n("span",{class:"token doc-comment comment"},[s(`/**
     * Constructor de los objetos que recibe como parámetros, las coordenadas (x, y)
     * de origen de dibujo y el nombre del archivo a dibujar.
     *
     * `),n("span",{class:"token keyword"},"@param"),s(),n("span",{class:"token parameter"},"x"),s(` El valor de la coordenada x
     * `),n("span",{class:"token keyword"},"@param"),s(),n("span",{class:"token parameter"},"y"),s(` El valor de la coordenada y
     */`)]),s(`
    `),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token class-name"},"Laser"),n("span",{class:"token punctuation"},"("),n("span",{class:"token keyword"},"int"),s(" x"),n("span",{class:"token punctuation"},","),s(),n("span",{class:"token keyword"},"int"),s(" y"),n("span",{class:"token punctuation"},","),s(),n("span",{class:"token class-name"},"GamePanel"),s(" gamePanel"),n("span",{class:"token punctuation"},")"),s(),n("span",{class:"token punctuation"},"{"),s(`

        `),n("span",{class:"token keyword"},"super"),n("span",{class:"token punctuation"},"("),n("span",{class:"token class-name"},"Globals"),n("span",{class:"token punctuation"},"."),n("span",{class:"token constant"},"LASER_SPRITE"),n("span",{class:"token punctuation"},","),s(" x"),n("span",{class:"token punctuation"},","),s(" y"),n("span",{class:"token punctuation"},")"),n("span",{class:"token punctuation"},";"),s(`
        `),n("span",{class:"token keyword"},"this"),n("span",{class:"token punctuation"},"."),s("gamePanel "),n("span",{class:"token operator"},"="),s(" gamePanel"),n("span",{class:"token punctuation"},";"),s(`
    `),n("span",{class:"token punctuation"},"}"),s(`

    `),n("span",{class:"token annotation punctuation"},"@Override"),s(`
    `),n("span",{class:"token keyword"},"public"),s(),n("span",{class:"token keyword"},"void"),s(),n("span",{class:"token function"},"move"),n("span",{class:"token punctuation"},"("),n("span",{class:"token punctuation"},")"),s(),n("span",{class:"token punctuation"},"{"),s(`

        y `),n("span",{class:"token operator"},"-="),s(),n("span",{class:"token number"},"5"),n("span",{class:"token punctuation"},";"),s(`
    `),n("span",{class:"token punctuation"},"}"),s(`
`),n("span",{class:"token punctuation"},"}"),s(`
`)])]),n("div",{class:"highlight-lines"},[n("br"),n("br"),n("div",{class:"highlight-line"}," "),n("br"),n("br"),n("br"),n("br"),n("br"),n("div",{class:"highlight-line"}," "),n("br"),n("br"),n("br"),n("br"),n("br"),n("br"),n("br"),n("br"),n("div",{class:"highlight-line"}," "),n("br"),n("br"),n("div",{class:"highlight-line"}," "),n("br"),n("br"),n("br"),n("br"),n("br"),n("div",{class:"highlight-line"}," "),n("br"),n("br")]),n("div",{class:"line-numbers","aria-hidden":"true"},[n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"}),n("div",{class:"line-number"})])],-1);function y(w,h){const l=e("Badge"),o=e("CodeGroupItem"),c=e("CodeGroup");return p(),r("div",null,[n("h1",k,[d,s(" Actualizando Laser "),a(l,{type:"warning",text:"Modificada",vertical:"middle"})]),m,a(c,null,{default:t(()=>[a(o,{title:"Anterior"},{default:t(()=>[b]),_:1}),a(o,{title:"ACTUALIZADO"},{default:t(()=>[v]),_:1})]),_:1})])}const _=i(u,[["render",y],["__file","laser.html.vue"]]);export{_ as default};
