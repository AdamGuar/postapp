package com.app.postappclient.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.postappclient.ApiService;
import com.app.postappclient.model.MailMessage;
import com.app.postappclient.model.ModelID;
import com.app.postappclient.model.ModelRequest;
import com.app.postappclient.model.SubRequest;
import com.app.postappclient.model.Subscription;
import com.app.postappclient.security.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RestApiService implements ApiService{
	
	@Value("${api.host}")
	private String apiHost;
	
	
	@Override
	public List<String> getAllModelIds() {
		
		WebClient client = WebClient.create(apiHost + "/model/getids");
		
		Flux<ModelID> repsonse = client.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(ModelID.class);
		
		List<ModelID> result = repsonse.collectList().block();
		
		return result.stream().map(e->e.getName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllUsernames() {
		WebClient client = WebClient.create(apiHost + "/user/list");
		
		Flux<User> repsonse = client.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToFlux(User.class);
		
		List<User> result = repsonse.collectList().block();
		
		return result.stream().map(e->e.getUsername()).collect(Collectors.toList());
	}

	@Override
	public void sendMail(MailMessage msg) {
		WebClient client = WebClient.create(apiHost+"mail/send");
		
		 	     	     
	     BodyInserter<Object, ReactiveHttpOutputMessage> inserter
	     = BodyInserters.fromObject(msg);
	     
	     client.post()
					.body(inserter)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(Boolean.class).block();

	}

	@Override
	public List<String> getAllSubsForModel(String modelName) {
		WebClient client = WebClient.create(apiHost + "/sub/get/"+modelName);
		
		Mono<Subscription> repsonse = client.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(Subscription.class);
		
		Subscription result = repsonse.block();
		if(result!=null)
			return result.getSbuscribents();
		else 
			return new ArrayList<String>();
	}
	
	@Override
	public void subscribeForModel(String modelName,String username) {
		WebClient client = WebClient.create(apiHost + "/sub/subscribe");
		
		SubRequest request = new SubRequest(modelName, username);
		
		BodyInserter<Object, ReactiveHttpOutputMessage> inserter
	     = BodyInserters.fromObject(request);
	     
	     client.post()
					.body(inserter)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(Subscription.class).block();
		
	}
	
	@Override
	public void unsubscribeForModel(String modelName,String username) {
		WebClient client = WebClient.create(apiHost + "/sub/unsubscribe");
		
		SubRequest request = new SubRequest(modelName, username);
		
		BodyInserter<Object, ReactiveHttpOutputMessage> inserter
	     = BodyInserters.fromObject(request);
	     
	     client.post()
					.body(inserter)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(Subscription.class).block();
		
	}
	
	
/*	@Override
	public void saveModel(String modelName,MultipartFile file) {
		WebClient client = WebClient.create(apiHost + "/save");
		
				
		MultiValueMap<String, Object> multipartData = new LinkedMultiValueMap<>();
		multipartData.add("file", file);
		multipartData.add("name", modelName);
		
		BodyInserter<MultiValueMap<String, Object>, ClientHttpRequest> inserter
	     = BodyInserters.from(multipartData);
	     
	     client.post()
					.body(inserter)
					.retrieve().bodyToMono(Subscription.class).block();
		
	}*/
	
	public void saveModel(String modelName,MultipartFile file) {
		WebClient client = WebClient.create(apiHost + "/model/savemodel");
		
		ModelRequest request;
		
		try {
			request = new ModelRequest(modelName, new String(file.getBytes()));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		
		BodyInserter<Object, ReactiveHttpOutputMessage> inserter
	     = BodyInserters.fromObject(request);
	     
	     client.post()
					.body(inserter)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(Subscription.class).block();
		
	}
	

}
