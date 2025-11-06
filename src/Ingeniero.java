public class Ingeniero {
    private int id_ing;
    private String nombre;
    
    // Constructor vacío
    public Ingeniero() {
    }
    
    // Constructor con parámetros
    public Ingeniero(String nombre) {
        this.nombre = nombre;
    }
    
    // Constructor completo
    public Ingeniero(int id_ing, String nombre) {
        this.id_ing = id_ing;
        this.nombre = nombre;
    }
    
    // Getters y Setters
    public int getId_ing() {
        return id_ing;
    }
    
    public void setId_ing(int id_ing) {
        this.id_ing = id_ing;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "Ingeniero{" +
                "id_ing=" + id_ing +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}