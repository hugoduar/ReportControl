package modelo;

/**
 *
 * @author Hugo
 */
public class TipoFalla {
    int idTfa;
    String desTfa;
    int tipFal;

    public TipoFalla(int idTfa, String desTfa, int tipFal) {
        this.idTfa = idTfa;
        this.desTfa = desTfa;
        this.tipFal = tipFal;
    }

    public TipoFalla() {
    }

    public int getIdTfa() {
        return idTfa;
    }

    public void setIdTfa(int idTfa) {
        this.idTfa = idTfa;
    }

    public String getDesTfa() {
        return desTfa;
    }

    public void setDesTfa(String desTfa) {
        this.desTfa = desTfa;
    }

    public int getTipFal() {
        return tipFal;
    }

    public void setTipFal(int tipFal) {
        this.tipFal = tipFal;
    }
    
}
