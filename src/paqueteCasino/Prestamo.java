package paqueteCasino;

public class Prestamo {
	private Jugador jugador;
    private Integer saldo;
    private Boolean pagado;
    
    public Prestamo(Jugador jugador, Integer saldoInitial) {
	    this.jugador = jugador;
	    this.saldo = saldoInitial;
	    this.pagado = false;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public Integer getSaldo() {
        return saldo;
    }
    
    public Boolean aumentarPrestamo(Integer monto, Integer maxValorPrestamo) {

        Integer nuevoMonto = this.saldo + monto;

        if (nuevoMonto > maxValorPrestamo) {
            return false;
        }

        this.saldo = nuevoMonto;
        jugador.setSaldo(jugador.getSaldo() + monto);
        return true;
    }
    
    public Boolean disminuirPrestamo(Integer monto) {

        if (monto > saldo) {
            return false;
        }

        if (jugador.getSaldo() < monto) {
            return false;
        }

        this.saldo -= monto;

        jugador.setSaldo(jugador.getSaldo() - monto);

        if (this.saldo == 0) {
            this.pagado = true;
        }
        
        return true;
    }
    
    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }
    
    @Override
    public String toString() {

        return
                "Jugador: "
                + jugador.getNombre()

                + " | Deuda Actual: $"
                + saldo

                + " | Pagado: "
                + pagado;
    }
}
