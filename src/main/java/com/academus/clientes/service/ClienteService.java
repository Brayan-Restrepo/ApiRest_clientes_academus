/**
 * 
 */
package com.academus.clientes.service;

import java.util.List;

import com.academus.clientes.model.dto.ClienteDto;
import com.academus.clientes.model.entity.Cliente;

/**
 * @author Brayan
 *
 */
public interface ClienteService {
	
	public List<ClienteDto> findAll();
	public void saveCliente(ClienteDto cliente);
	public void updateCliente(Cliente cliente);
	public void deleteCliente(Long id);
}
