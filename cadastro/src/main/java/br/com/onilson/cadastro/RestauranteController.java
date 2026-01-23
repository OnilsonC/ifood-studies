package br.com.onilson.cadastro;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping
    public List<Restaurante> listAll() {
        return restauranteRepository.findAll();
    }

    @PostMapping
    @Transactional
    public void salvar(Restaurante restaurante) {
        restauranteRepository.save(restaurante);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable("id") Long id, Restaurante rest) {
        Optional<Restaurante> restauranteOp = restauranteRepository.findById(id);
        if(restauranteOp.isEmpty()) {
            throw new NotFoundException();
        }
        Restaurante restaurante = restauranteOp.get();

        restaurante.setNome(rest.getNome());
        restauranteRepository.save(restaurante);
    }
}
