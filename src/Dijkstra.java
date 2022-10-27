import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

  // Atributos usados na funcao encontrarMenorCaminho

  // Lista que guarda os vertices pertencentes ao menor caminho encontrado
  List<Vertice> menorCaminho = new ArrayList<Vertice>();

  // Variavel que recebe os vertices pertencentes ao menor caminho
  Vertice verticeCaminho = new Vertice();

  // Variavel que guarda o vertice que esta sendo visitado
  Vertice atual = new Vertice();

  // Variavel que marca o vizinho do vertice atualmente visitado
  Vertice vizinho = new Vertice();

  // Lista dos vertices que ainda nao foram visitados
  List<Vertice> naoVisitados = new ArrayList<Vertice>();

  Double distancia = 0d;

  // Algoritmo de Dijkstra
  public void encontrarMenorCaminhoDijkstra(
    Grafo grafo,
    Vertice origem,
    Vertice destino
  ) {
    // Adiciona a origem na lista do menor caminho
    menorCaminho.add(origem);

    // Colocando a distancias iniciais
    for (int i = 0; i < grafo.getVertices().size(); i++) {
      // Vertice atual tem distancia zero, e todos os outros,
      // 9999("infinita")
      if (
        grafo.getVertices().get(i).getDescricao().equals(origem.getDescricao())
      ) {
        grafo.getVertices().get(i).setDistancia(0);
      } else {
        grafo.getVertices().get(i).setDistancia(9999);
      }
      // Insere o vertice na lista de vertices nao visitados
      this.naoVisitados.add(grafo.getVertices().get(i));
    }

    // MUDAR PARA QUICK SORT
    Collections.sort(naoVisitados);

    // O algoritmo continua ate que todos os vertices sejam visitados
    while (!this.naoVisitados.isEmpty()) {
      // Toma-se sempre o vertice com menor distancia, que eh o primeiro
      // da
      // lista

      atual = this.naoVisitados.get(0);
      //System.out.println("Pegou esse vertice: " + atual);
      //System.out.println("Menor caminho " + menorCaminho);
      /*
       * Para cada vizinho (cada aresta), calcula-se a sua possivel
       * distancia, somando a distancia do vertice atual com a da aresta
       * correspondente. Se essa distancia for menor que a distancia do
       * vizinho, esta eh atualizada.
       */
      for (int i = 0; i < atual.getArestas().size(); i++) {
        vizinho = atual.getArestas().get(i).getDestino();
        //System.out.println("Olhando o vizinho de " + atual + ": " + vizinho);

        if (!vizinho.verificarVisita()) {
          // Comparando a distância do vizinho com a possível
          // distância
          if (
            vizinho.getDistancia() == 0 ||
            vizinho.getDistancia() >
            (atual.getDistancia() + atual.getArestas().get(i).getPeso())
          ) {
            vizinho.setDistancia(
              atual.getDistancia() + atual.getArestas().get(i).getPeso()
            );
            vizinho.setPai(atual);
            /*
             * Se o vizinho eh o vertice procurado, e foi feita uma
             * mudanca na distancia, a lista com o menor caminho
             * anterior eh apagada, pois existe um caminho menor
             * vertices pais, ateh o vertice origem.
             */
            if (vizinho != destino) {
              menorCaminho.clear();
              verticeCaminho = vizinho;
              menorCaminho.add(vizinho);
              distancia = +vizinho.getDistancia();
              while (verticeCaminho.getPai() != null) {
                menorCaminho.add(verticeCaminho.getPai());
                verticeCaminho = verticeCaminho.getPai();
              }
              // Ordena a lista do menor caminho, para que ele
              // seja exibido da origem ao destino.
              // MUDAR PARA QUICK SORT
              Collections.sort(menorCaminho);
            }

            if (vizinho == destino) {
              menorCaminho.add(vizinho);
              distancia += vizinho.getDistancia();
            }
          }
        }
      }
      // Marca o vertice atual como visitado e o retira da lista de nao
      // visitados
      atual.visitar();
      this.naoVisitados.remove(atual);
      /*
       * Ordena a lista, para que o vertice com menor distancia fique na
       * primeira posicao
       */
      // MUDAR PARA QUICK SORT
      Collections.sort(naoVisitados);
      // System.out.println("Nao foram visitados ainda:" + naoVisitados);
    }

    // System.out.println("Rota: " + menorCaminho);
    // System.out.println("Quantidade de saltos: " + (menorCaminho.size() - 1));
    // System.out.println("Distância: " + distancia);
    // System.out.println(
    //   "Custo da viagem: $" + String.format("%.2f", distancia / 10 * 6.95)
    // );

    StringBuilder texto = new StringBuilder();

    texto.append("Menor rota encontrada: ");
    texto.append("\n\n");
    texto.append("Rota: ");
    texto.append(menorCaminho);
    texto.append("\n");
    texto.append("Quantidade de saltos: ");
    texto.append(menorCaminho.size() - 1);
    texto.append("\n");
    texto.append("Distância: ");
    texto.append(distancia);
    texto.append("Km");
    texto.append("\n");
    texto.append("Custo da viagem: $");
    texto.append(String.format("%.2f", distancia / 10 * 6.95));

    GravarNoArquivo.gravar(texto.toString());

    System.out.println(
      "Análise de menor rota executado com sucesso, resultado gravado no arquivo rota.txt"
    );
  }
}
