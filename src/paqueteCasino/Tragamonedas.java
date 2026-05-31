package paqueteCasino;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tragamonedas {

	private int[][] tablero;

    private final int FILAS = 3;
    private final int COLUMNAS = 3;

    private Random random;

    public Tragamonedas() {
        tablero = new int[FILAS][COLUMNAS];
        random = new Random();
    }

    public Integer jugar(Integer apuesta) {

        generarTablero();

        Integer premio = calcularPremio(apuesta);

        mostrarTableroVisual(apuesta, premio);

        return premio;
    }

    private void generarTablero() {

        for (int i = 0; i < FILAS; i++) {

            for (int j = 0; j < COLUMNAS; j++) {

        	 tablero[i][j] = random.nextInt(6);
            }
        }
    }

    private Integer calcularPremio(
        Integer apuesta
    ) {

        Integer premio = 0;

        if (verificarFilas()) {
            premio += apuesta * 2;
        }

        if (verificarColumnas()) {
            premio += apuesta * 2;
        }

        if (verificarDiagonales()) {
            premio += apuesta * 3;
        }

        if (tableroCompletoIgual()) {
            premio += apuesta * 10;
        }

        return premio;
    }

    private boolean verificarFilas() {

    	for (int i = 0; i < FILAS; i++) {

            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }

        return false;
    }

    private boolean verificarColumnas() {

    	 for (int j = 0; j < COLUMNAS; j++) {

    	        if (tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
    	            return true;
    	        }
    	    }

    	    return false;
    }

    private boolean verificarDiagonales() {

    	 boolean d1 = tablero[0][0]  == tablero[1][1] && tablero[1][1] == tablero[2][2];

    	 boolean d2 = tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0];
    		    
    	 return d1 || d2;
    }

    private boolean tableroCompletoIgual() {

    	int simbolo = tablero[0][0];

    	    for (int i = 0; i < FILAS; i++) {

    	        for (int j = 0; j < COLUMNAS; j++) {

    	            if (tablero[i][j] != simbolo) {
    	                return false;
    	            }
    	        }
    	    }

    	    return true;
    }

    private void mostrarTableroVisual(
    	    Integer apuesta,
    	    Integer premio
    	) {

    	    JPanel panel =
    	        new JPanel(
    	            new GridLayout(4,3)
    	        );

    	    for (int i = 0; i < FILAS; i++) {

    	        for (int j = 0; j < COLUMNAS; j++) {

    	            String archivo =
    	                obtenerImagen(
    	                    tablero[i][j]
    	                );
    	            
    	         
    	            ImageIcon originalIcon =
    	                new ImageIcon(
    	                    getClass()
    	                    .getResource(
    	                        archivo
    	                    )
    	                );
    	            
    	            Image scaledImage =
    	            	    originalIcon
    	            	        .getImage()
    	            	        .getScaledInstance(
    	            	            80,     
    	            	            80,     
    	            	            java.awt.Image.SCALE_SMOOTH
    	            	        );
    	            
    	            ImageIcon icon =
    	            	    new ImageIcon(scaledImage);

    	            JLabel label =
    	                new JLabel(icon);

    	            panel.add(label);
    	        }
    	    }

    	    JOptionPane.showMessageDialog(
    	        null,
    	        panel,
    	        "Apuesta: $"
    	        + apuesta
    	        + " | Premio: $"
    	        + premio,
    	        JOptionPane.PLAIN_MESSAGE
    	    );
    	}
    
    
    private String obtenerImagen(
    	    int valor
    	) {

    	    switch (valor) {

    	        case 0:
    	            return "/img/cherry.png";

    	        case 1:
    	            return "/img/lemon.png";

    	        case 2:
    	            return "/img/bell.png";

    	        case 3:
    	            return "/img/diamond.png";

    	        case 4:
    	            return "/img/leaf.png";

    	        default:
    	            return "/img/seven.png";
    	    }
    	}
}
