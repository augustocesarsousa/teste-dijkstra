import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class GravarNoArquivo {

  public static void gravar(String texto) {
    try {
      // criando o caminho e o nome do arquivo
      Path filePath = Paths.get("", "rota.txt");

      // verificando se o arquivo já existe
      if (Files.notExists(filePath)) {
        Files.createFile(filePath);
      }

      // escrevendo os dados no arquivo
      Files.writeString(filePath, texto);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
