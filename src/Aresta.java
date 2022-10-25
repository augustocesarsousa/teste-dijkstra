public class Aresta {

  private double peso;
  private Vertice origem;
  private Vertice destino;

  public Aresta(double peso, Vertice origem, Vertice destino) {
    this.peso = peso;
    this.origem = origem;
    this.destino = destino;
  }

  public Aresta(Vertice v1, Vertice v2) {
    this.peso = 1;
    this.origem = v1;
    this.destino = v2;
  }

  public void setPeso(double novoPeso) {
    this.peso = novoPeso;
  }

  public double getPeso() {
    return peso;
  }

  public void setDestino(Vertice destino) {
    this.destino = destino;
  }

  public Vertice getDestino() {
    return destino;
  }

  public void setOrigem(Vertice origem) {
    this.origem = origem;
  }

  public Vertice getOrigem() {
    return origem;
  }

  @Override
  public String toString() {
    return "Aresta [peso=" + peso + ", origem=" + origem + ", destino=" + destino + "]";
  }

}
