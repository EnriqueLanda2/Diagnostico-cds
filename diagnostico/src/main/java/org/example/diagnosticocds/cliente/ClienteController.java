package org.example.diagnosticocds.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente/")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("Consulta")
    public ResponseEntity<List<ClienteEntity>> consulta() {
        try{
            return ResponseEntity.ok(clienteService.ConsultaGeneral());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @PostMapping("registrar")
    public ResponseEntity<ClienteEntity> registrar ( @RequestBody ClienteEntity cliente) {
        try{

            return  ResponseEntity.ok(clienteService.Registrar(cliente).getBody());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("{id}")
    public ClienteEntity eliminar(@PathVariable Long id) {
        try{

            return  clienteService.eliminar(id);

        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    @PutMapping("actualizar/{id}")
    public ClienteEntity actualizar (@PathVariable Long id, @RequestBody ClienteEntity cliente) {
        cliente.setId(id);
        return clienteService.actualizar(id,cliente);
    }


}
