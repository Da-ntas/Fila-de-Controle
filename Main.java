import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Filas adcfila = new Filas(101);
    System.out.println("Tamanho máximo da fila: 100\n");
    Scanner sc = new Scanner(System.in);
    char sair = 'a';
    while(sair != '.'){
     System.out.println("Tipo de cliente: ('.' para sair)");
     char tipo = sc.next().charAt(0);
     adcfila.enqueue(Character.toUpperCase(tipo)); 
     sair = tipo;
    }

    //System.out.println(adcfila.isEmpty());
    System.out.println(adcfila.imprime());

    char fim = 'a';
    while(fim != '.'){
      System.out.println("Atendimento realizado?('S' para mostrar o proximo da fila ou '.' para sair)");
      char tipo = sc.next().charAt(0);
      if(Character.toUpperCase(tipo) == 'S'){
        adcfila.dequeue();
      }
      else{
        fim = tipo;
      }
    }
    sc.close();
  }
}