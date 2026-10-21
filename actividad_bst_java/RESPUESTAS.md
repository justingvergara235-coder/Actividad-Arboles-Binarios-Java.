# Respuestas escritas — Árboles Binarios de Búsqueda

## Reflexión previa sobre los errores del código base

1. En `setLeft` y `setRight`, asignar el atributo a sí mismo no enlazaba el hijo recibido. Por eso los nodos nuevos no quedaban conectados al árbol y sus ramas no crecían.
2. Si `insertRecursive` devolvía la raíz nueva al insertar en un árbol vacío, pero `insertNode` no guardaba ese resultado en `root`, la raíz seguía siendo `null` y el árbol parecía vacío.

## Parte B — Elección del recorrido

| Requerimiento | Recorrido elegido | Justificación | ¿Poda o detención anticipada? |
|---|---|---|---|
| R1 | In-orden: izquierda, nodo, derecha | En un BST visita las claves de menor a mayor. | No; visita todos los nodos para listar todos los clientes. |
| R2 | In-orden inverso: derecha, nodo, izquierda | Visita primero las claves mayores, así obtiene los compradores principales sin ordenar toda la colección y luego invertirla. | Sí; se detiene cuando reúne n clientes. |
| R3 | Recorrido en orden con poda por límites | Solo visita la izquierda si la clave actual es mayor que el mínimo y solo visita la derecha si la clave actual es menor que el máximo. | Sí; descarta ramas que no pueden contener edades dentro del rango. |
| R5 | Buscar el extremo izquierdo para el mínimo y el extremo derecho para el máximo | Por la propiedad del BST, la menor clave está en el nodo más a la izquierda y la mayor en el más a la derecha. | Sí; no necesita recorrer el árbol completo. |

## Parte C — Claves duplicadas

Con el código base, las claves iguales no se insertaban: las condiciones solo enlazaban nodos cuando la clave era menor o mayor, y para igualdad no ocurría nada. En `arbolEdad`, Luis Prado y Camilo Vélez comparten 28 años, y Ana Torres y Paola Gil comparten 34 años. Por tanto, se perdían 2 clientes y el árbol de edades tenía 7 nodos en vez de 9.

La implementación corregida inserta las claves iguales en la rama derecha. Así se conservan los 9 clientes. El recorrido in-orden y la consulta por rango siguen devolviendo las claves en orden, incluyendo repeticiones. `buscarNodo` devuelve el primer nodo coincidente que encuentra, no necesariamente todos los clientes con esa edad. Para recuperar todos, se podría recorrer el árbol buscando la clave y guardar cada cliente coincidente, o usar una estructura por clave que almacene una lista de clientes.

## Parte D — Forma y complejidad

- Insertando `10, 20, 30, 40, 50, 60` en ese orden, el árbol queda sesgado hacia la derecha, como una lista. Su altura es 5.
- Insertando `40, 20, 60, 10, 30, 50`, se obtiene un árbol más equilibrado: 40 como raíz; 20 y 60 como hijos; 10 y 30 bajo 20; 50 como hijo izquierdo de 60. Su altura es 2.
- En el árbol sesgado, buscar puede costar O(n) en el peor caso. En el árbol equilibrado, la búsqueda cuesta O(log n) aproximadamente.
- Para reducir el peor caso, conviene evitar insertar datos ya ordenados en un BST simple o mantener el árbol balanceado. Un árbol AVL o un árbol rojo-negro mantiene la altura en O(log n).

## Complejidad de las operaciones

Sea `n` el número de nodos y `h` la altura del árbol:

- Inserción y búsqueda: O(h); promedio esperado O(log n) si el árbol está razonablemente equilibrado y peor caso O(n).
- Mínimo y máximo: O(h); peor caso O(n), porque se sigue una sola rama.
- Altura: O(n), porque debe visitar todos los nodos.
- Listado in-orden: O(n).
- Top n: O(h + n) en un caso favorable; puede visitar más nodos por la forma del árbol, con peor caso O(n).
- Consulta por rango: depende de la altura y de la cantidad de resultados; la poda evita explorar ramas que están fuera del rango, pero en el peor caso puede ser O(n).
