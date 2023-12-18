package br.org.recode.tarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class Index {
@GetMapping("/home")
public String home() {
	return "index";
}
@Controller
public class Contato {
@GetMapping("/contato")
public String contato() {
	return "contato";
}

@Controller
public class Destinos {
@GetMapping("/destinos")
public String destinos() {
	return "destinos";
}

@Controller
public class Login{
@GetMapping("/login")
public String login() {
	return "login";
}

@Controller
public class Crud{
@GetMapping("/crud")
public String crud() {
	return "crud";
}

@Controller
public class Promocoes{
@GetMapping("/promocoes")
public String promocoes() {
	return "promocoes";
}

@Controller
public class Registrese{
@GetMapping("/registrese")
public String registrese() {
	return "registrese";
}




}
}
}
}
}
}
}


