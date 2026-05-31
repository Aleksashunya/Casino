package paqueteCasino;
import javax.swing.JOptionPane;

public class Administrador extends Usuario {
	public Administrador(Integer id, String login, String password) {
		super(id, login, password, "Administrador");
	}
	
	public void ingresarDineroCasino(CasinoData casinoData) {
        String input = JOptionPane.showInputDialog(
                null,
                "Monto para ingresar:"
        );

        if (input == null) {
            return;
        }

        try {

            Integer monto = Integer.parseInt(input);

            if (monto <= 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Monto invalido"
                );

                return;
            }

            casinoData.ingresarDinero(monto);

            JOptionPane.showMessageDialog(
                    null,
                    "Dinero agregado correctamente\n"
                    + "Saldo Casino: $"
                    + casinoData.getSaldoCasino()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Valor invalido"
            );
        }
    }
	
	 public void retirarDineroCasino(CasinoData casinoData) {

        String input = JOptionPane.showInputDialog(
            null,
            "Monto para retirar:"
        );

        if (input == null) {
            return;
        }

        try {

            Integer monto = Integer.parseInt(input);
            if (monto <= 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Monto invalido"
                );
                return;
            }

            boolean success = casinoData.retirarDinero(monto);
            if (success) {
                JOptionPane.showMessageDialog(
                    null,
                    "Dinero retirado\n"
                    + "Saldo Casino: $"
                    + casinoData.getSaldoCasino()
                );
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Saldo insuficiente"
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Valor invalido"
            );
        }
    }
	 
	 public void verSaldoCasino(CasinoData casinoData) {
        JOptionPane.showMessageDialog(
            null,
            "Saldo Casino: $"
            + casinoData.getSaldoCasino()
        );
    }
	 
	 public void verMaxPrestamo(CasinoData casinoData) {
		 JOptionPane.showMessageDialog(null, "Max prestamo: $" + casinoData.mostrarMaxValorPrestamo());
	 }
	 
	 public void cambiarMaxPrestamo (CasinoData casinoData) {
		 
		 String input = JOptionPane.showInputDialog(null, "Valor de préstamo máximo: ");

        if (input == null) {
            return;
        }
        
        try {

            Integer valor = Integer.parseInt(input);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor invalido");
                return;
            }

            casinoData.setMaxValorPrestamo(valor);
            JOptionPane.showMessageDialog(
                null,
                "Nuevo valor de préstamo máximo agregado con éxito \n"
                + "Préstamo máximo: $"
                + casinoData.getMaxValorPrestamo()
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valor invalido");
        }
	 }
	 
	 public void verPrestamos(CasinoData casinoData) {
	    JOptionPane.showMessageDialog(null, casinoData.mostrarPrestamos());
	}
	 
	 public void verEstadisticas(CasinoData casinoData) {
	    JOptionPane.showMessageDialog(null, casinoData.mostrarEstadisticas());
	}
	 
	 public void verHistorial(CasinoData casinoData) {
	    JOptionPane.showMessageDialog(null, casinoData.mostrarHistorial());
	}
}
