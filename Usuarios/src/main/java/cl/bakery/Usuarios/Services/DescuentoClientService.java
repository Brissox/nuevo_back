package cl.bakery.Usuarios.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.bakery.Usuarios.DTO.UsuarioRegistroDescuentoDTO;

@Service
public class DescuentoClientService {

    private final WebClient webClient;

    public DescuentoClientService(@Qualifier("descuentoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void asignarDescuentosAlUsuario(Long idUsuario, String codigoRegistro, String correo,
            LocalDate fechaNacimiento) {
        UsuarioRegistroDescuentoDTO dto = new UsuarioRegistroDescuentoDTO();
        dto.setIdUsuario(idUsuario);
        dto.setCodigoRegistro(codigoRegistro);
        dto.setCorreo(correo);
        dto.setFechaNacimiento(fechaNacimiento);

        try {
            webClient.post()
                    .uri("/descuentos/asignar")
                    .bodyValue(dto)
                    .retrieve()
                    .toBodilessEntity()
                    .block(); 
        } catch (Exception e) {


        }
    }
}
