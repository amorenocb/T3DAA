# T3DAA
---
CC4102 Diseño y Analisis de Algoritmos: Tarea III 

## Hipotesis

Mas que nada se asume un cierto formato para cargar graficos. Este debe contener en la primera linea el numero de nodos, seguido de un espacio y la probabilidad p de que se encuentre una arista entre dos nodos u y v cualesquiera (La señalada en el enunciado).
Luego de esto en cada linea siguiente se encuentra dos numeros separados por un espacio. Donde cada numero representa el identificador de un nodo del grafo. Esto nos lleva a nuestra segunda suposicion: todos los graficos representan sus nodos con identificadores numericos, por lo cual si se desea probar con otro input sera necesario pre procesar para mapear cada nodo a un identificador unico.

## Diseño

Se opto por utilizar **listas de adyacencia**. Estas se representaron por un ArrayList que contiene instancias de la clase **Vertex**. Esta representa cada nodo del grafo y desde ella se pueden obtener distintas caracteristicas del nodo, como por ejemplo su grado e identificador. Cada instancia de esta clase contiene ademas un campo llamado **adyacencyList** que contiene una lista de enteros, los cuales representan todos los nodos conectados a el. Asi si por ejemplo se desea tomar una arista de manera aleatoria del grafo el proceso el siguiente:

1. Tomar Vertex v en indice aleatorio de lista de adyacencia del grafo.
2. Tomar identificador de vecino de v, digamos u, de manera aleatoria de la lista de adyacencia (de vecinos de v).
3. Retornar el par v,u.

## Uso

La idea es entonces generar inputs con el script **InputGenerator**. Luego guardar estos en una carpeta con un nombre M. Luego al correr el script **Main** se le entrega M como argumento. Este recorre la carpeta con los inputs y para cada uno utiliza la clase **GraphGenerator** para cargar un grafo desde un archivo .txt hacia una instancia de la clase **AdyacencyMatrix**, la cual representa al grafo. Luego de esto se le asigna este grafo a uno de los dos algoritmos implementados : **TwoApproximation** o **NotApproximation** con el metodo **.setGraph(AdyacencyMatrix aGraph)** y por ultimo se obtiene el vertex cover como un ArrayList de enteros, los cuales representan los identificadores de los vertices que pertenecen al vertex cover.

## Generacion de Pruebas

Utilizando el script **InputGenerator** se logro generar grafos de prueba de hasta 2^15 nodos 5 valores de probabilidades distinto para cada uno: 0.005, 0.008, 0.01, 0.025, 0.05. Se opto por estos valores pues si se utilizan valores muy elevados la maquina virtual de Java se queda sin memoria para almacenar tal cantidad de aristas.

Link a Inputs generados:
https://mega.nz/#!tpByXbpI!yO9HAPYw2BFNiLsDG2Xyk6Cmwja-avSlAtlY6aOwz0I
