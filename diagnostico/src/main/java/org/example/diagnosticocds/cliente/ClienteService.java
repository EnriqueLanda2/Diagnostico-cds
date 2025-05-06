package org.example.diagnosticocds.cliente;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;
            public ClienteService(ClienteRepository clienteRepository) {
                this.clienteRepository = clienteRepository;
            }


    @Transactional(readOnly = true)
    public List<ClienteEntity> ConsultaGeneral (){
                try{
                    return clienteRepository.findAll();
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
    }


    @Transactional
    public ResponseEntity<ClienteEntity> Registrar (ClienteEntity cliente){

        try{
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }





    @Transactional
    public ClienteEntity eliminar(Long id){
                try{
                    clienteRepository.deleteById(id);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            return null;
    }

    @Transactional
    public ClienteEntity actualizar (Long id, ClienteEntity cliente){

                ClienteEntity clientee = clienteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


                clientee.setNombre(cliente.getNombre());
                clientee.setApellido(cliente.getApellido());
                clientee.setId(cliente.getId());
                clientee.setCurp(cliente.getCurp());
                cliente.setFecha(cliente.getFecha());

                ClienteEntity clientes = clienteRepository.save(clientee);

                return clientes;
    }






}
