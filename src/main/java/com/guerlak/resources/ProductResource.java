package com.guerlak.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guerlak.model.Product;
import com.guerlak.model.dto.ProductDTO;
import com.guerlak.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		Product u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	@GetMapping("/")
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="0") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="direction", defaultValue="ASC")String direction,
			@RequestParam(value="orderBy", defaultValue="name")String orderBy
			) {
		
		
		List<Long> idList = Arrays.asList(categories.split(","))
				.stream()
				.map(x -> Long.parseLong(x))
				.collect(Collectors.toList());
		
		String decodedName;
		try {
			decodedName = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			decodedName =  "";
		}
		
		Page<Product> list = service.search(decodedName, idList, page, linesPerPage, orderBy, direction);
		
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
	}
}
