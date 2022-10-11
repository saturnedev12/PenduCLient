import java.io.Serializable;

public class ResponseServer implements Serializable {
     String trustWord;
     ResponseStatus rps;

     ResponseServer(String trustWord, ResponseStatus rps) {
          this.trustWord = trustWord;
          this.rps = rps;
     }

}