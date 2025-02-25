package it.unicam.cs.ids2425.filieraagricolalocale.exceptions;

public class NonAutorizzatoException extends RuntimeException{

    String message = "";

    public NonAutorizzatoException(){
        super();
    }

    public NonAutorizzatoException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return message;
    }

}
