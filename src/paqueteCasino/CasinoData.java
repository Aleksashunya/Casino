package paqueteCasino;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CasinoData {

	private Integer saldoCasino;
	
	private Integer maxValorPrestamo;
	private List<Prestamo> prestamos;
	
    private List<String> historiaList;
	private Integer totalJugadas;
    private Integer totalApostado;
    private Integer totalPremiosPagados;
    private Integer gananciasCasino;
    private Integer perdidasCasino;
    
    public CasinoData (Integer saldo, Integer maxValorPrestamo) {
    	this.saldoCasino = saldo;
    	
    	this.maxValorPrestamo = maxValorPrestamo;
    	this.prestamos = new ArrayList<>();
    	
    	this.historiaList = new ArrayList<>();
    	this.totalJugadas = 0;
        this.totalApostado = 0;
        this.totalPremiosPagados = 0;
        this.gananciasCasino = 0;
        this.perdidasCasino = 0;
    }
    
    public Integer getSaldoCasino() {
        return saldoCasino;
    }
    
    public void ingresarDinero(Integer monto) {
        saldoCasino += monto;
    }
    
    public boolean retirarDinero(Integer monto) {
        if (monto > saldoCasino) {
            return false;
        }

        saldoCasino -= monto;
        return true;
    }
    
    public Integer getMaxValorPrestamo () {
    	return maxValorPrestamo;
    }
    
    public void setMaxValorPrestamo (Integer valor) {
    	maxValorPrestamo = valor;
    }
    
    public String mostrarMaxValorPrestamo () {
    	return "Préstamo máximo: " + maxValorPrestamo;
    }
    
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    
    public String mostrarPrestamos() {
        if (historiaList.isEmpty()) {
            return "No hay préstamos";
        }
        
		 StringBuilder prestamosSb = new StringBuilder();

        for (Prestamo prestamo: prestamos) {
       	 prestamosSb.append(
                "Jugador: "
                + prestamo.getJugador()
                .getNombre()

                + "\nSaldo Jugador: $"
                + prestamo.getJugador()
                .getSaldo()

                + "\nDeuda Actual: $"
                + prestamo.getSaldo()

                + "\nPagado: "
                + prestamo.getPagado()
            );
        }
        
//        JOptionPane.showMessageDialog(null, prestamosSb.toString());
        return prestamosSb.toString();
	 }
    
    public void crearPrestamo(Jugador jugador, Integer monto) {

        if (saldoCasino < monto * 2) {
            JOptionPane.showMessageDialog(null,"Casino sin dinero");
            return;
        }

        Prestamo prestamo = new Prestamo(jugador, monto);
        prestamos.add(prestamo);
        saldoCasino -= monto;

        historiaList.add("Prestamo nuevo: " + prestamo);

        JOptionPane.showMessageDialog(null, "Prestamo aprobado");
    }
    
    public Prestamo getPrestamo(Jugador jugador) {
    	for (Prestamo prestamo : prestamos) {
    		if (prestamo.getJugador().getId() == jugador.getId()) {
    			return prestamo;
    		}
    	}
    	
    	return null;
    }
    
    public void pagarPrestamo(Jugador jugador, Integer montoPago) {
    	
    	Prestamo prestamo = getPrestamo(jugador);
    	
    	if (prestamo == null) {
    		JOptionPane.showMessageDialog(null, "El jugador "  + jugador.getNombre()  + " no tiene prestamos");
            return;
    	}

        if (prestamo.getPagado()) {
            JOptionPane.showMessageDialog(null, "Prestamo ya pagado");
            return;
        }
        
        
        if (jugador.getSaldo() < montoPago) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            return;
        }
        
        if (montoPago > prestamo.getSaldo()) {
            JOptionPane.showMessageDialog(null, "Monto mayor que deuda");
            return;
        }
        
        prestamo.disminuirPrestamo(montoPago);
        saldoCasino += montoPago;
        
        if (prestamo.getSaldo() == 0) {
        	 JOptionPane.showMessageDialog(null, "Prestamo pagado completo");
        } else {
        	JOptionPane.showMessageDialog(
                null,
                "Pago realizado"
                + "\nDeuda restante: $"
                + prestamo.getSaldo()
            );
        }
        
        historiaList.add(
            "Pago prestamo -> "
            + jugador.getNombre()
            + " | Pago: $"
            + montoPago
            + " | Deuda restante: $"
            + prestamo.getSaldo()
        );
      
    }
    
    public void registrarJuego(
	    String jugador,
	    String juego,
	    Integer apuesta,
	    Integer premio
    ) {

        totalJugadas++;
        totalApostado += apuesta;

        String resultado;

        if (premio > 0) {
            totalPremiosPagados += premio;
            Integer perdida = premio - apuesta;
            perdidasCasino += perdida;
            saldoCasino -= perdida;
            resultado = "GANO";
        } else {
            gananciasCasino += apuesta;
            saldoCasino += apuesta;
            resultado = "PERDIO";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(
            "Jugador: " + jugador
            + " | Juego: " + juego
            + " | Apuesta: $" + apuesta
            + " | Premio: $" + premio
            + " | Resultado: " + resultado
        );

        historiaList.add(sb.toString());
    }
    
    public String mostrarEstadisticas() {
    	
    	StringBuilder sb = new StringBuilder();  	
    	 sb.append(
		    "=== ESTADISTICAS CASINO ===\n\n"
            + "Saldo Casino: $" + saldoCasino
            + "\n\nTotal Jugadas: " + totalJugadas
            + "\n\nTotal Apostado: $" + totalApostado
            + "\n\nPremios Pagados: $" + totalPremiosPagados
            + "\n\nGanancias Casino: $" + gananciasCasino
            + "\n\nPerdidas Casino: $" + perdidasCasino
		 );

        return sb.toString();
    }
    
    public String mostrarHistorial() {

        if (historiaList.isEmpty()) {
            return "No hay historial";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(
            "=== HISTORIAL CASINO ===\n\n"
        );

        for (String item : historiaList) {

            sb.append(item + "\n");
        }

        return sb.toString();
    }
}