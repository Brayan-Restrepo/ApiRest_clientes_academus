package com.academus.clientes.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academus.clientes.exception.NotFoundException;
import com.academus.clientes.model.dto.ClienteDto;
import com.academus.clientes.model.entity.Cliente;
import com.academus.clientes.repository.ClienteRepository;
import com.academus.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	private ModelMapper modelMapper;
	
	public ClienteServiceImpl() {
		this.modelMapper = new ModelMapper();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ClienteDto> findAll() {
		List<Cliente> clientes = (List<Cliente>) this.clienteRepository.findAll();
		return clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void saveCliente(ClienteDto clienteDto) {
		if (Objects.nonNull(clienteDto)) {
			Cliente cliente = this.modelMapper.map(clienteDto, Cliente.class);
			this.clienteRepository.save(cliente);
		} else {
			throw new NotFoundException("Cliente no Valido");
		}
	}

	@Override
	@Transactional
	public void updateCliente(Cliente cliente) {
		if (this.clienteRepository.existsById(cliente.getId())) {
			this.clienteRepository.save(cliente);
		} else {
			throw new NotFoundException("Cliente ("+cliente.getId()+") no existe");
		}
	}

	@Override
	@Transactional
	public void deleteCliente(Long id) {
		if (this.clienteRepository.existsById(id)) {
			this.clienteRepository.deleteById(id);
		} else {
			throw new NotFoundException("Cliente ("+id+") no existe");
		}
	}

}
