package ar.edu.unlam.pb2.excepciones;

public class ClienteDuplicadoException extends Exception {
    public ClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
