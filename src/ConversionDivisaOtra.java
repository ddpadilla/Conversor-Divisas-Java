import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//Clase para obtener la conversion de Otras monedas a USD
public class ConversionDivisaOtra {
    public Divisa ConversionDivisaOther(String tipoCambio, double cantidad){

        HttpClient client = HttpClient.newHttpClient();
        // Cambia el orden de los parámetros en la URL para convertir de otra moneda a USD
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/2ffd4b4b3673cbd78ec7ccb8/pair/"+tipoCambio+"/USD/"+cantidad);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error al obtener el tipo de cambio. Código de estado: " + response.statusCode());
            }
            return new Gson().fromJson(response.body(), Divisa.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al enviar la solicitud: " + e.getMessage());
        }
    }
}
