package com.juangaravito.forohub.dominio.Validaciones;

import com.juangaravito.forohub.dominio.DTO.TopicoDatosPeticion;
import com.juangaravito.forohub.dominio.repository.TopicoRepository;
import com.juangaravito.forohub.infraestrucutra.Errores.TopicoDuplicadoExecption;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoDuplicado implements ValidacionesTopico{

    private TopicoRepository topicoRepository;

    public ValidarTopicoDuplicado(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @Override
    public void validar(TopicoDatosPeticion topico) {
        Boolean tituloExist = topicoRepository.existsContaninTitulo(topico.titulo());
        Boolean mensajeExist = topicoRepository.existsContainMensaje(topico.mensaje());

        if(tituloExist && mensajeExist){
            throw new TopicoDuplicadoExecption("Este topico ya existe. Tiene titulo y mensaje duplicado");
        }
    }
}
