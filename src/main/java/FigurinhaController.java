import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FigurinhaController {

    public void criar(InputStream inputStream, String nomeArquivo) throws Exception {
        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme_maior.jpg"));
        //InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);
        //escrever uma frase na nova imagem
        graphics.drawString("           TOPZERA", 0, novaAltura - 100);

        //escrever a nova imagem em um arquivo
        String saida = "saida";
        Path pathSaida = Path.of(saida);
        String pathFigurinha = saida + "/" + nomeArquivo;
        if (Files.notExists(pathSaida)) {
            Files.createDirectories(pathSaida);
        }
        ImageIO.write(novaImagem, "png", new File(pathFigurinha));
    }
}
