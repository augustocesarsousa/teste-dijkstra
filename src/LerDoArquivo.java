import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LerDoArquivo {

  public static List<Vertice> lerGrafo(String nomeArquivo) {
    Grafo grafo = new Grafo();
    Vertice vertice;
    String vertices[];
    ArrayList<String[]> cidadeRota = new ArrayList<String[]>();

    Path filePath = Path.of(nomeArquivo);

    List<String> linhas = new ArrayList<>();

    Map<String, Vertice> mapa = new HashMap<String, Vertice>();

    try {
      linhas = Files.readAllLines(filePath);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    for (String linha : linhas) {
      if (linha.contains(",")) {
        cidadeRota.add(linha.split("/"));
        vertices = cidadeRota.get(0)[0].split(",");

        vertice = (Vertice) mapa.get(vertices[0]);
        if (vertice == null)
          vertice = new Vertice();

        List<Vertice> vizinhosAtual = new ArrayList<Vertice>();
        List<Aresta> arestasAtual = new ArrayList<Aresta>();
        vertice.setDescricao(vertices[0]);
        mapa.put(vertices[0], vertice);

        if (linha.contains("/")) {
          String pesoArestas[] = cidadeRota.get(0)[1].split(",");

          for (int i = 1; i < vertices.length; i++) {
            Vertice vtc;
            vtc = mapa.get(vertices[i]);
            if (vtc == null)
              vtc = new Vertice();
            vtc.setDescricao(vertices[i]);
            vizinhosAtual.add(vtc);
            mapa.put(vertices[i], vtc);

            Aresta aresta = new Aresta(vertice, vtc);
            aresta.setPeso(Double.parseDouble(pesoArestas[i - 1]));
            arestasAtual.add(aresta);
          }
          vertice.setVizinhos(vizinhosAtual);
          vertice.setArestas(arestasAtual);
        }
      }
      // Vertices finais
      else {
        // v = g.encontrarVertice(linha);
        vertice = (Vertice) mapa.get(linha);
        if (vertice == null)
          vertice = new Vertice();
        vertice.setDescricao(linha);
        mapa.put(linha, vertice);
      }

      grafo.adicionarVertice(vertice);
      cidadeRota.clear();
    }
    // Retornando os vertices
    return grafo.getVertices();
  }
}
