import java.sql.Timestamp;

public class Resultado {
    private int id_resultado;
    private int id_ing;
    private int numero_camino;
    private int informatell;
    private int sistecom;
    private int technologic;
    private int ganancia;
    private Timestamp fecha_registro;
    
    // Constructor vacío
    public Resultado() {
    }
    
    // Constructor con parámetros principales
    public Resultado(int id_ing, int numero_camino, int informatell, int sistecom, int technologic, int ganancia) {
        this.id_ing = id_ing;
        this.numero_camino = numero_camino;
        this.informatell = informatell;
        this.sistecom = sistecom;
        this.technologic = technologic;
        this.ganancia = ganancia;
    }
    
    // Constructor completo
    public Resultado(int id_resultado, int id_ing, int numero_camino, int informatell, int sistecom, int technologic, int ganancia, Timestamp fecha_registro) {
        this.id_resultado = id_resultado;
        this.id_ing = id_ing;
        this.numero_camino = numero_camino;
        this.informatell = informatell;
        this.sistecom = sistecom;
        this.technologic = technologic;
        this.ganancia = ganancia;
        this.fecha_registro = fecha_registro;
    }
    
    // Getters y Setters
    public int getId_resultado() {
        return id_resultado;
    }
    
    public void setId_resultado(int id_resultado) {
        this.id_resultado = id_resultado;
    }
    
    public int getId_ing() {
        return id_ing;
    }
    
    public void setId_ing(int id_ing) {
        this.id_ing = id_ing;
    }
    
    public int getNumero_camino() {
        return numero_camino;
    }
    
    public void setNumero_camino(int numero_camino) {
        this.numero_camino = numero_camino;
    }
    
    public int getInformatell() {
        return informatell;
    }
    
    public void setInformatell(int informatell) {
        this.informatell = informatell;
    }
    
    public int getSistecom() {
        return sistecom;
    }
    
    public void setSistecom(int sistecom) {
        this.sistecom = sistecom;
    }
    
    public int getTechnologic() {
        return technologic;
    }
    
    public void setTechnologic(int technologic) {
        this.technologic = technologic;
    }
    
    public int getGanancia() {
        return ganancia;
    }
    
    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }
    
    public Timestamp getFecha_registro() {
        return fecha_registro;
    }
    
    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    @Override
    public String toString() {
        return "Resultado{" +
                "id_resultado=" + id_resultado +
                ", id_ing=" + id_ing +
                ", numero_camino=" + numero_camino +
                ", informatell=" + informatell +
                ", sistecom=" + sistecom +
                ", technologic=" + technologic +
                ", ganancia=" + ganancia +
                ", fecha_registro=" + fecha_registro +
                '}';
    }
}