public class App {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.setVertices(LerDoArquivo.lerGrafo("cidades.txt"));
        Vertice origem = new Vertice();
        Vertice destino = new Vertice();
        origem = grafo.encontrarVertice("Porto Alegre");
        destino = grafo.encontrarVertice("Gramado");

        Dijkstra algoritmo = new Dijkstra();
        algoritmo.encontrarMenorCaminhoDijkstra(grafo, origem, destino);

    }
}
