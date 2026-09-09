package com.AEP.DoaLivros.repositories;

import com.AEP.DoaLivros.models.PedidoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoRepository extends MongoRepository<PedidoModel, String> {

    List<PedidoModel> findByIdUsuario(String idUsuario);
    List<PedidoModel> findByStatus(PedidoModel.StatusPedido status);
}
