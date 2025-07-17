package bcd.lobinho.exception;

public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constrói uma nova exceção com a mensagem padrão.
     */
    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }

    /**
     * Constrói uma nova exceção com a mensagem especificada.
     * @param message a mensagem detalhando a exceção
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constrói uma nova exceção com a mensagem e causa especificadas.
     * @param message a mensagem detalhando a exceção
     * @param cause a causa da exceção
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constrói uma nova exceção para um recurso específico.
     * @param resourceName o nome do recurso (ex: "Pessoa", "Distintivo")
     * @param fieldName o nome do campo usado na busca (ex: "id", "cpf")
     * @param fieldValue o valor do campo usado na busca
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s não encontrado com %s: '%s'", resourceName, fieldName, fieldValue));
    }
}