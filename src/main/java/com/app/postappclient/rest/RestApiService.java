package com.app.postappclient.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.postappclient.ApiService;
import com.app.postappclient.model.MailMessage;
import com.app.postappclient.model.ModelID;
import com.app.postappclient.security.User;

import reactor.core.publisher.Flux;

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

}
