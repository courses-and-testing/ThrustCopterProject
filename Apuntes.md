# LibGDX Game Development Essentials

## Chapter 2. Let There Be Graphics

### The ThrustCopter game

* El ciclo de un videojuego se puede utilizar implementando `ApplicationListener` o extendiendo `ApplicationAdapter`

* La lógica del juego se añade en el método `render`

* La memoria se limpia en el método `dispose`

* `FPSLogger` permite mostrar los FPS llamando a su método `log`

* `OrtographicCamera` es la mejor en los juegos 2D donde no hay puntos de fuga

### Displaying the graphics

* `SpriteBatch` recolecta las posiciones donde una textura va a ser dibujada y lo hace en una sola pasada

* No es tan útil cuando la textura cambia. Para esto es mejor crear un *pack* de texturas

* `TextureRegion` es una porción rectangular de una textura más grande, pero puede ser también usada para contener a una sola textura

* La clase `Sprite` contiene la textura y la posición donde tiene que ser dibujada

  * El método `draw` se llama desde la instancia y no desde el `batch`

* Teniendo en cuenta estas cosas, el fondo puede ser una `Texture`, puesto que no se mueve, los objetos *Sprites* y las partes de arriba y abajo pueden ser `TextureRegion`

* *Blending* es la técnica para añadir pixeles translúcidos cuando una textura se dibuja sobre otra en la escena.

* `TextureRegion.flip(boolean x, boolean y)` sirve para dar la vuelta a una textura, en el eje y, x o en ambos

* `Gdx.graphics.getDeltaTime()` para conseguir el delta

* `Animation.setPlayMode` para el modo de reproducción

* Para las animaciones se necesita un `float` que lleve el tiempo de animación e irlo actualizando con `DeltaTime` y así poder obtener la `KeyFrame` en los momentos determinados

### Handling multiple screen sizes and aspect ratios

* *Viewports*

  * `StrechViewport`: Estira el *viewport* para que quepa en la pantalla. Se puede escalar
  * `FitViewport`: Pone barras negras para que quepa en la pantalla. No hay distorsión al poner el *viewport* en su tamaño
  * `FillViewport`: Mantiene el ratio de la imagen pero y lo mete en la pantalla pero no crea barras. Por lo que puede cortar partes de la imagen
  * `ScreenViewport`: Siempre del mismo tamaño de la pantalla, pero esto beneficia pantallas más grandes

* Los *viewports* se tienen que actualizar en el método `resize`

## Chapter 3. Thou Shall Not Pass!

### Navigating using touch input

* `touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0)` es un `Vector3` y sirve para conseguir la posición x e y del toque

* `camera.unproject(Vector3)` para convertir las coordenadas de la pantalla en las de la cámara 
