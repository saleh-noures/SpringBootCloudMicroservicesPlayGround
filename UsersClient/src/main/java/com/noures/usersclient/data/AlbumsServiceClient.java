package com.noures.usersclient.data;

import java.util.ArrayList;
import java.util.List;
import com.noures.usersclient.ui.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {

	/** You create the interface and Spring Framework will provide the implementation **/
	/** To Test FeignErrorDecoder class under shared package change to /users/{id}/albumsss or stop albums Microservice **/
	/** If you use Retry alone with the Circuit Breaker then you don't need to add fallbackMethod attribute to Retry **/
	@GetMapping("/users/{id}/albums")
	@Retry(name = "albums-ws") // See the retry properties in application.properties
	@CircuitBreaker(name = "albums-ws", fallbackMethod = "getAlbumsFallback") // See the CircuitBreaker properties in application.properties
	public List<AlbumResponseModel> getAlbums(@PathVariable String id);

	/*
	Some rules when defining fallback methods
	1- Should have the same signature (return type and parameters) like the original method (getAlbums)
	2- Should be placed in the same interface/class as the original method
	3- if you have more than one fallback Methods for the same method (getAlbums) with different exceptions, the one with the more specific exception one will be executed.
	   in the example below the getAlbumsFallback will be called for all exceptions as 'exception' is a Throwable object.
	*/
	default List<AlbumResponseModel> getAlbumsFallback(String id, Throwable exception){
		System.out.println("Param = " + id);
		System.out.println("Exception took place: " + exception.getMessage());
		return new ArrayList<>();
	}

}

