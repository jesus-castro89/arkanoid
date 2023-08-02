import{_ as s,r as e,o as t,c as p,a as n,b as c,d as o,e as l}from"./app-d733c1e2.js";const i={},u={id:"gamecycle",tabindex:"-1"},d=n("a",{class:"header-anchor",href:"#gamecycle","aria-hidden":"true"},"#",-1),r=l(`<p>Esta pequeña clase del paquete <code>events</code>, es usada para definir el evento del juego y es de suma importancia, ya que sin ella las imágenes animadas y el concepto de movimiento dentro del juego no tienen sentido.</p><p>Como tal, la clase solamente define la manera en la cual se realizarán ciertas tareas de forma repetitiva, siendo estas tareas las de move y repaint de <code>GamePanel</code>.</p><p>Por lo que la clase se verá de la siguiente forma:</p><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token keyword">package</span> <span class="token namespace">events</span><span class="token punctuation">;</span>

<span class="token keyword">import</span> <span class="token import"><span class="token namespace">ui<span class="token punctuation">.</span></span><span class="token class-name">GamePanel</span></span><span class="token punctuation">;</span>

<span class="token keyword">import</span> <span class="token import"><span class="token namespace">java<span class="token punctuation">.</span>awt<span class="token punctuation">.</span>event<span class="token punctuation">.</span></span><span class="token class-name">ActionEvent</span></span><span class="token punctuation">;</span>
<span class="token keyword">import</span> <span class="token import"><span class="token namespace">java<span class="token punctuation">.</span>awt<span class="token punctuation">.</span>event<span class="token punctuation">.</span></span><span class="token class-name">ActionListener</span></span><span class="token punctuation">;</span>

<span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">GameCycle</span> <span class="token keyword">implements</span> <span class="token class-name">ActionListener</span> <span class="token punctuation">{</span>

    <span class="token keyword">private</span> <span class="token keyword">final</span> <span class="token class-name">GamePanel</span> panel<span class="token punctuation">;</span>

    <span class="token keyword">public</span> <span class="token class-name">GameCycle</span><span class="token punctuation">(</span><span class="token class-name">GamePanel</span> panel<span class="token punctuation">)</span> <span class="token punctuation">{</span>

        <span class="token keyword">this</span><span class="token punctuation">.</span>panel <span class="token operator">=</span> panel<span class="token punctuation">;</span>
    <span class="token punctuation">}</span>

    <span class="token annotation punctuation">@Override</span>
    <span class="token keyword">public</span> <span class="token keyword">void</span> <span class="token function">actionPerformed</span><span class="token punctuation">(</span><span class="token class-name">ActionEvent</span> e<span class="token punctuation">)</span> <span class="token punctuation">{</span>

        <span class="token function">doGameCycle</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token punctuation">}</span>

    <span class="token keyword">private</span> <span class="token keyword">void</span> <span class="token function">doGameCycle</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>

        panel<span class="token punctuation">.</span><span class="token function">move</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        panel<span class="token punctuation">.</span><span class="token function">repaint</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token punctuation">}</span>
<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,4);function k(m,v){const a=e("Badge");return t(),p("div",null,[n("h1",u,[d,c(" GameCycle "),o(a,{type:"tip",text:"Nuevo",vertical:"middle"})]),r])}const y=s(i,[["render",k],["__file","game-cycle.html.vue"]]);export{y as default};
