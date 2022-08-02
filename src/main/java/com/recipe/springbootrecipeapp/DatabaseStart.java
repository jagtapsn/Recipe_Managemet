package com.recipe.springbootrecipeapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.recipe.springbootrecipeapp.model.Ingredients;
import com.recipe.springbootrecipeapp.repository.IngredientsRepository;

@Service
public class DatabaseStart implements CommandLineRunner{

	private final IngredientsRepository ingredientsRepo;
	
	public DatabaseStart(IngredientsRepository ingredientsRepo) {
		this.ingredientsRepo = ingredientsRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		Ingredients cebola = new Ingredients("Cebola");
		Ingredients chicoria = new Ingredients("Chicoria");
		Ingredients banana = new Ingredients("Banana");
		Ingredients uva = new Ingredients("Uva");
		Ingredients leite = new Ingredients("Leite");
		Ingredients chocolate = new Ingredients("Chocolate");
		Ingredients abobora = new Ingredients("Abóbora");
		Ingredients batata = new Ingredients("Batata");
		Ingredients couveflor = new Ingredients("Couve-Flor");
		Ingredients beterraba = new Ingredients("Beterraba");
		Ingredients pimentao = new Ingredients("Pimentão");
		Ingredients feijao = new Ingredients("Feijão");
		Ingredients quiabo = new Ingredients("Quiabo");
		Ingredients lentilha = new Ingredients("Lentilha");
		Ingredients mandioca = new Ingredients("Mandioca");
		Ingredients pepino = new Ingredients("Pepino");
		Ingredients batatadoce = new Ingredients("Batata Doce");
		Ingredients chuchu = new Ingredients("Chuchu");
		Ingredients inhame = new Ingredients("Inhame");	
		
		ingredientsRepo.save(cebola);
		ingredientsRepo.save(chicoria);
		ingredientsRepo.save(banana);
		ingredientsRepo.save(uva);
		ingredientsRepo.save(leite);
		ingredientsRepo.save(chocolate);
		ingredientsRepo.save(abobora);
		ingredientsRepo.save(batata);
		ingredientsRepo.save(couveflor);
		ingredientsRepo.save(beterraba);
		ingredientsRepo.save(pimentao);
		ingredientsRepo.save(feijao);
		ingredientsRepo.save(quiabo);
		ingredientsRepo.save(lentilha);
		ingredientsRepo.save(mandioca);
		ingredientsRepo.save(pepino);
		ingredientsRepo.save(batatadoce);
		ingredientsRepo.save(chuchu);
		ingredientsRepo.save(inhame);
		ingredientsRepo.save(abobora);
	}
}
