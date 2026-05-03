package adopet.api.exception;

public record ResponseError(String type,
                            String title,
                            int status,
                            String detail,
                            String instance) {
}
