import{_ as n,o as s,c as a,e as p}from"./app-08222125.js";const t={},e=p(`<h1 id="level" tabindex="-1"><a class="header-anchor" href="#level" aria-hidden="true">#</a> Level</h1><p>Esta clase del paquete util nos permitirá definir en un primer instante los niveles de juego para poder observar la creación dinámica de las cosas y luego poder almacenar, mediante otras clases, su contenido en archivos que pueda leer nuestro Juego.</p><p>Esta clase cuenta con tres atributos:</p><ul><li><strong>brickPad</strong><ul><li>Matriz de bloques representan el área de juego.</li></ul></li><li><strong>background</strong><ul><li>El fondo de pantalla de juego.</li></ul></li><li><strong>audio</strong><ul><li>El audio de fondo para el nivel.</li></ul></li></ul><p>Por lo que nuestra clase se ve así:</p><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token keyword">package</span> <span class="token namespace">util</span><span class="token punctuation">;</span>

<span class="token keyword">import</span> <span class="token import"><span class="token namespace">graphics<span class="token punctuation">.</span>brick<span class="token punctuation">.</span></span><span class="token class-name">Brick</span></span><span class="token punctuation">;</span>
<span class="token keyword">import</span> <span class="token import"><span class="token namespace">graphics<span class="token punctuation">.</span>brick<span class="token punctuation">.</span></span><span class="token class-name">BrickType</span></span><span class="token punctuation">;</span>

<span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Level</span> <span class="token punctuation">{</span>

  <span class="token keyword">private</span> <span class="token class-name">Brick</span><span class="token punctuation">[</span><span class="token punctuation">]</span><span class="token punctuation">[</span><span class="token punctuation">]</span> brickPad<span class="token punctuation">;</span>
  <span class="token keyword">private</span> <span class="token class-name">String</span> background<span class="token punctuation">;</span>
  <span class="token keyword">private</span> <span class="token class-name">String</span> audio<span class="token punctuation">;</span>

  <span class="token keyword">public</span> <span class="token class-name">Level</span><span class="token punctuation">(</span><span class="token class-name">String</span> background<span class="token punctuation">,</span> <span class="token class-name">String</span> audio<span class="token punctuation">)</span> <span class="token punctuation">{</span>

    <span class="token keyword">this</span><span class="token punctuation">.</span>background <span class="token operator">=</span> background<span class="token punctuation">;</span>
    <span class="token keyword">this</span><span class="token punctuation">.</span>audio <span class="token operator">=</span> audio<span class="token punctuation">;</span>
    <span class="token comment">//Esto lo que hace es tomar las constantes de Globals para determinar el tamaño de la matriz</span>
    <span class="token keyword">this</span><span class="token punctuation">.</span>brickPad <span class="token operator">=</span> <span class="token keyword">new</span> <span class="token class-name">Brick</span><span class="token punctuation">[</span><span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICKS_ROWS</span><span class="token punctuation">]</span><span class="token punctuation">[</span><span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICK_COLUMNS</span><span class="token punctuation">]</span><span class="token punctuation">;</span>
    <span class="token comment">//Esta variable permitirá guardar un número que usaremos para cada tipo de bloque</span>
    <span class="token keyword">int</span> numero<span class="token punctuation">;</span>
    <span class="token keyword">int</span> x<span class="token punctuation">;</span>
    <span class="token keyword">int</span> y<span class="token punctuation">;</span>
    <span class="token comment">//El primer for es para recorrer las filas</span>
    <span class="token keyword">for</span> <span class="token punctuation">(</span><span class="token keyword">int</span> row <span class="token operator">=</span> <span class="token number">0</span><span class="token punctuation">;</span> row <span class="token operator">&lt;</span> <span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICKS_ROWS</span><span class="token punctuation">;</span> row<span class="token operator">++</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>
      <span class="token comment">//El segundo for es para recorrer las columnas</span>
      <span class="token keyword">for</span> <span class="token punctuation">(</span><span class="token keyword">int</span> column <span class="token operator">=</span> <span class="token number">0</span><span class="token punctuation">;</span> column <span class="token operator">&lt;</span> <span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICK_COLUMNS</span><span class="token punctuation">;</span> column<span class="token operator">++</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>
        numero <span class="token operator">=</span> <span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token function">random</span><span class="token punctuation">(</span><span class="token number">1</span><span class="token punctuation">,</span> <span class="token number">2</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        x <span class="token operator">=</span> <span class="token punctuation">(</span>row <span class="token operator">*</span> <span class="token punctuation">(</span><span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICK_WIDTH</span><span class="token punctuation">)</span><span class="token punctuation">)</span> <span class="token operator">+</span> <span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICK_MARGIN</span><span class="token punctuation">;</span>
        y <span class="token operator">=</span> <span class="token punctuation">(</span>column <span class="token operator">*</span> <span class="token punctuation">(</span><span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BRICK_HEIGHT</span><span class="token punctuation">)</span><span class="token punctuation">)</span> <span class="token operator">+</span> <span class="token class-name">Globals</span><span class="token punctuation">.</span><span class="token constant">BORDER_HEIGHT</span><span class="token punctuation">;</span>
        <span class="token keyword">switch</span> <span class="token punctuation">(</span>numero<span class="token punctuation">)</span> <span class="token punctuation">{</span>
          <span class="token keyword">case</span> <span class="token number">1</span> <span class="token operator">-&gt;</span> brickPad<span class="token punctuation">[</span>row<span class="token punctuation">]</span><span class="token punctuation">[</span>column<span class="token punctuation">]</span> <span class="token operator">=</span> <span class="token keyword">new</span> <span class="token class-name">Brick</span><span class="token punctuation">(</span><span class="token class-name">BrickType</span><span class="token punctuation">.</span><span class="token constant">BLUE</span><span class="token punctuation">,</span> x<span class="token punctuation">,</span> y<span class="token punctuation">)</span><span class="token punctuation">;</span>
          <span class="token keyword">case</span> <span class="token number">2</span> <span class="token operator">-&gt;</span> brickPad<span class="token punctuation">[</span>row<span class="token punctuation">]</span><span class="token punctuation">[</span>column<span class="token punctuation">]</span> <span class="token operator">=</span> <span class="token keyword">new</span> <span class="token class-name">Brick</span><span class="token punctuation">(</span><span class="token class-name">BrickType</span><span class="token punctuation">.</span><span class="token constant">RED</span><span class="token punctuation">,</span> x<span class="token punctuation">,</span> y<span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
      <span class="token punctuation">}</span>
    <span class="token punctuation">}</span>
  <span class="token punctuation">}</span>
<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,6),o=[e];function c(l,i){return s(),a("div",null,o)}const k=n(t,[["render",c],["__file","level.html.vue"]]);export{k as default};