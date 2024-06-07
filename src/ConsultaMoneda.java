import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {


    private static final String API_KEY = "2ff1228ddcf48a85df7934ad"; // Reemplaza con tu clave de API

    public Moneda buscarMoneda(String baseCurrency) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se pudo obtener las tasas de cambio.", e);
        }
    }


}
