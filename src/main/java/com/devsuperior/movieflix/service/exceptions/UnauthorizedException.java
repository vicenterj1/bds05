package com.devsuperior.movieflix.service.exceptions;

//retornar mensagens customizadas
// 403 - token válido mas por causa de regra negócio não tem acesso ao recurso
// 401 - token inválido / não reconhecido
public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
}
