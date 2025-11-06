import java.util.ArrayList;
import java.util.List;

public class DynamicPlanner {
    private int[] informatell;
    private int[] sistecom;
    private int[] technologic;
    private int totalDays;

    public DynamicPlanner(int[] informatell, int[] sistecom, int[] technologic, int totalDays) {
        this.informatell = informatell;
        this.sistecom = sistecom;
        this.technologic = technologic;
        this.totalDays = totalDays;
    }

    // Estructura para guardar una combinación (camino)
    public static class Camino {
        int infoDays;
        int sisteDays;
        int technoDays;
        int ganancia;

        public Camino(int i, int s, int t, int g) {
            infoDays = i;
            sisteDays = s;
            technoDays = t;
            ganancia = g;
        }
    }

    public List<Camino> calcularCaminos() {
        List<Camino> caminos = new ArrayList<>();
        int maxGanancia = Integer.MIN_VALUE;

        // Recorrer todas las combinaciones posibles de días (i + s + t = totalDays)
        for (int i = 0; i <= totalDays; i++) {
            for (int s = 0; s <= totalDays - i; s++) {
                int t = totalDays - i - s;
                int ganancia = informatell[i] + sistecom[s] + technologic[t];

                if (ganancia > maxGanancia) {
                    maxGanancia = ganancia;
                    caminos.clear(); // Nuevo máximo → limpiar lista
                    caminos.add(new Camino(i, s, t, ganancia));
                } else if (ganancia == maxGanancia) {
                    caminos.add(new Camino(i, s, t, ganancia));
                }
            }
        }

        return caminos;
    }
}
