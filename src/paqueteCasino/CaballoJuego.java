package paqueteCasino;

import javax.swing.JOptionPane;
import java.util.Set;
import java.util.HashSet;

public class CaballoJuego {
	private String[] caballos = { "Rosa", "Tomas" };
	private Integer[] wins = { 0, 0 };
	private Integer juegasCount = 0;
	
	public Integer jugar(Integer apuesta) {
		mostrarCaballos();
		Integer caballoId = preguntarCaballo();
		
		if (caballoId == null) {
			return apuesta;
		}
		Integer[] arrayDeCaballosIds = arrayDeCaballosIds();
		
		Integer random = (int)(Math.random()*(arrayDeCaballosIds.length));
		
		JOptionPane.showMessageDialog(null, "Win caballo: " + caballos[arrayDeCaballosIds[random]]);
		
		Double coef = getCoef(arrayDeCaballosIds[random]);
		
		wins[arrayDeCaballosIds[random]] += 1;
		juegasCount += 1;
		
		if (arrayDeCaballosIds[random] == caballoId) {
			JOptionPane.showMessageDialog(null, "Tu dinero: " + apuesta * 2);
			JOptionPane.showMessageDialog(null, coef + "  " + (apuesta * coef));
			return (int) ((double) apuesta * coef);
		} else {
			JOptionPane.showMessageDialog(null, "Tu dinero: " + 0);
			return 0;
		}
	}
	
	private void mostrarCaballos() {
		StringBuilder sb = new StringBuilder();
		 sb.append("Caballos \n");
		 
		 for (int i = 0; i < caballos.length; i++) {
			Double percent = getCoef(i);
			sb.append(caballos[i] + ", wins: " + percent + "%" + "\n");
		}
		
		JOptionPane.showMessageDialog(null, sb);
	}
	
	private Double getCoef(Integer index) {
		if (juegasCount != 0) {
			Double percent = (double) ((double)(wins[index])/(double) (juegasCount));
			return 2.0 - ((2.0 * percent) - 0.1);
		} else {
			return 2.0;
		}
	}
	

	public Integer preguntarCaballo() {
        Object[] opciones = caballos;
        String caballo = (String) JOptionPane.showInputDialog(null, "¿Qué caballo te gusta?", "Casino",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (caballo == null) {
        	return null;
        }
        
        for (int i = 0; i < opciones.length; i++) {
			if (opciones[i] == caballo) {
				return i;
			}
		}
        return null;
    }
    
    public Integer[] arrayDeCaballosIds() {
    	Integer count = 0;
    	for (int i = 0; i < wins.length; i++) {
			count += wins[i] + 1;
			
		}
   
    	Integer[] caballos = new Integer[count];
    	Integer current = 0;
    	
    	for (int i = 0; i < this.caballos.length; i++) {
    		for (int j = 0; j < this.wins[i] + 1; j++) {
    			caballos[current] = i;
    			current++;
			}
		}
    	return caballos;
    }
}
