package uz.pdp.hrmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String message;
    private Object data;

    public Response(String message) {
        this.message = message;
    }
}
