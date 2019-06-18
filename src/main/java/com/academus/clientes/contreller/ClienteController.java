package com.academus.clientes.contreller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academus.clientes.exception.BadRequestException;
import com.academus.clientes.model.dto.ClienteDto;
import com.academus.clientes.model.entity.Cliente;
import com.academus.clientes.service.ClienteService;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("clientes")
	public List<ClienteDto> getClientes () {
		return this.clienteService.findAll();
	}
	
	@PostMapping("clientes")
	public String postClientes(@RequestBody ClienteDto cliente) {
		// Validar que el nomre de los clientes empiensen por la letra b
		this.clienteService.saveCliente(cliente);
		return "Ok";
	}
	
	@PutMapping("clientes/{idCliente}")
	public void putClientes(@PathVariable("idCliente") Long idCliente, @RequestBody Cliente cliente) {		
		if (idCliente == cliente.getId()) {
			this.clienteService.updateCliente(cliente);			
		} else {
			throw new BadRequestException("Error de datos");
		}
	}
	
	@DeleteMapping("clientes/{idCliente}")
	public void deleteClientes(@PathVariable("idCliente") Long idCliente) {
		this.clienteService.deleteCliente(idCliente);
	}
}
