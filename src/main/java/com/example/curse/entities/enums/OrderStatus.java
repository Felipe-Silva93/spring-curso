package com.example.curse.entities.enums;

public enum OrderStatus {
	
//o java por padrão atribui um valor a cada enumerado então cada um tem um valor porem a atribuição automatica pode acontecer poblemas de manutenção se algum outro dev alterar os valores
//então atribui manualmente o valor para cada anum	ai  a ide vai pedir cricriamos um construtor
		WAITING_PAYMENT(1),
		PAID(2),
		SHIPPED(3),
		DELIVERED(4),
		CANCELED(5);

		private int code;
		
		private OrderStatus(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}

		//metodo estatico para converter um valor numerico para um tipo enumerado
		public static OrderStatus valueOf(int code) {
			for(OrderStatus value: OrderStatus.values()) {//forma de percorrer os valores do OrderStatus que são os atrubutos WAITING_PAYMENT(1), e etc e para cada um deles vai ser testado se o code é correspondente se for retorn o code
				if(value.getCode()==code) {
					return value;
				}
			}
			throw new IllegalArgumentException("codigo de status invalido");
		}
}

