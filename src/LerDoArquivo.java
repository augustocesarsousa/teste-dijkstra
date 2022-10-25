import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LerDoArquivo {

  public static List<Vertice> lerGrafo(String nomeArquivo) {
    Grafo g = new Grafo();
    Vertice v;
    File f = new File(nomeArquivo);
    String vertices[];
    String linha;
    ArrayList<String[]> s1 = new ArrayList<String[]>();

    try {
      try (BufferedReader br = new BufferedReader(new FileReader(f))) {
        Map<String, Vertice> mapa = new HashMap<String, Vertice>();

        while ((linha = br.readLine()) != null) {
          if (linha.contains(",")) {
            s1.add(linha.split("/"));
            vertices = s1.get(0)[0].split(",");

            v = (Vertice) mapa.get(vertices[0]);
            if (v == null)
              v = new Vertice();

            List<Vertice> vizinhosAtual = new ArrayList<Vertice>();
            List<Aresta> arestasAtual = new ArrayList<Aresta>();
            v.setDescricao(vertices[0]);
            mapa.put(vertices[0], v);

            if (linha.contains("/")) {
              String pesoArestas[] = s1.get(0)[1].split(",");

              for (int i = 1; i < vertices.length; i++) {
                Vertice vit;
                // vit = g.encontrarVertice(vertices[i]);
                vit = mapa.get(vertices[i]);
                if (vit == null)
                  vit = new Vertice();
                vit.setDescricao(vertices[i]);
                vizinhosAtual.add(vit);
                mapa.put(vertices[i], vit);

                Aresta ait = new Aresta(v, vit);
                ait.setPeso(Double.parseDouble(pesoArestas[i - 1]));
                arestasAtual.add(ait);
              }
              v.setVizinhos(vizinhosAtual);
              v.setArestas(arestasAtual);
            }
          }
          // Vertices finais
          else {
            // v = g.encontrarVertice(linha);
            v = (Vertice) mapa.get(linha);
            if (v == null)
              v = new Vertice();
            v.setDescricao(linha);
            mapa.put(linha, v);
          }

          g.adicionarVertice(v);
          s1.clear();
        }
        // catch do BufferedReader
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Nao encontrou o arquivo");
      e.printStackTrace();
    } catch (IOException e) { // catch do readLine
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Retornando os vertices
    return g.getVertices();
  }

  public static void main(String args[]) {
    Grafo teste = new Grafo();

    teste.setVertices(lerGrafo("cidades.txt"));
    Vertice origem = new Vertice();
    Vertice destino = new Vertice();
    origem = teste.encontrarVertice("Porto Alegre");
    destino = teste.encontrarVertice("Gramado");

    Dijkstra algoritmo = new Dijkstra();
    algoritmo.encontrarMenorCaminhoDijkstra(teste, origem, destino);

  }
}
