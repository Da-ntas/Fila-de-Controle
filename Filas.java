
class Filas {

  // atributos
  private String[] FilaGeral;
  private String[] FilaNormal;
  private String[] FilaPrio;
  private String[] FilaOrganizada;
  private int tamMax;
  private int posFinal;
  private int posFinalN;
  private int posFinalP;
  private int posFinalO;

  //construtor das Filas
  public Filas(int tamFila) {
    this.FilaGeral = new String[tamFila];
    this.FilaNormal = new String[tamFila];
    this.FilaPrio = new String[tamFila];
    this.FilaOrganizada = new String[tamFila];
    this.tamMax = tamFila;
    this.posFinal = -1;
    this.posFinalN = -1;
    this.posFinalP = -1;
    this.posFinalO = -1;
  }

  // limpa a fila
  public void delete() {
    this.FilaGeral = null;
    System.gc();

  }

  // verificar se tem alguém na fila
  public boolean isEmpty() {
    if (this.posFinal == -1) {
      return true;
    } else {
      return false;
    }
  }

  // adicionar pessoas a fila
  public void enqueue(char tipo) {
    if (isFull()) {
      System.out.println("Não inseriu elemento - fila cheia");
    } else if (tipo == 'N') {
      this.FilaGeral[this.posFinal + 1] = "Normal";
      // this.FilaNormal[this.posFinalN + 1] = "Normal";
      this.posFinal++;
      // this.posFinalN++;

    } else if (tipo == 'P') {
      this.FilaGeral[this.posFinal + 1] = "Prioritario";
      this.posFinal++;
    }

  }

  // tirar pessoas da fila
  public String dequeue(){
    if (isEmpty()) {
      System.out.println("Não existe dado - fila vazia");
      System.out.println("\n\n");
      System.exit(0);
    } else {
      String elemento = this.FilaGeral[0];
      String elemento2 = this.FilaOrganizada[0];
      for (int i = 0; i < this.posFinal; i++) {
        this.FilaGeral[i] = this.FilaGeral[i + 1];
        this.FilaOrganizada[i] = this.FilaOrganizada[i + 1];
      }
      this.posFinal--;
      this.posFinalO--;
      System.out.println("\n----------------------------");
      System.out.println("Próximo Fila Geral: " + elemento);
      System.out.println("----------------------------");
      System.out.println("Próximo Fila Organizada: " + elemento2);
      System.out.println("----------------------------\n\n");
    }
    return " ";
  }

  //dequeue nas filas Normais e Prio, enquanto adicionando na Organizada
  public String dequeueNP(char tipo){
    if (isEmpty()) {
      System.out.println("Não existe dado - fila vazia");
    } else if (tipo == 'N') {
      for (int i = 0; i < 1; i++) {
        this.FilaNormal[i] = this.FilaNormal[i + 1];
      }
      this.posFinalN--;
    } else if (tipo == 'P') {
      for (int i = 0; i < 1; i++) {
        this.FilaPrio[i] = this.FilaPrio[i + 1];
      }
      this.posFinalP--;
  }
  return " ";
}
  // adiciona em uma fila para cada
  public void filasNormPrio(){
    for (int i = 0; i <= posFinal; i++) {
      if (this.FilaGeral[i].equals("Normal")) {
        this.FilaNormal[this.posFinalN + 1] = "Normal";
        this.posFinalN++;
      }
      if (this.FilaGeral[i].equals("Prioritario")) {
        this.FilaPrio[this.posFinalP + 1] = "Prioritario";
        this.posFinalP++;
      }
    }
  }

  public String organizaFila() {

    filasNormPrio();

    // adiciona em uma fila organizada: 1 normal a cada 2 prioritarios ->> em
    // tentativa de producao
    for (int i = 0; i <= posFinal; i++) {
      //quando i for igual a 0, verificar se tem como subir incrementar na fila, tendo a FilaPrio como prioridade
      if (i == 0) {
        if (this.FilaPrio[0] != null && this.FilaPrio[0].equals("Prioritario")) {
          this.FilaOrganizada[this.posFinalO + 1] = "Prioritario";
          posFinalO++;
          dequeueNP('P');
        } else if (this.FilaNormal[0] != null && this.FilaNormal[0].equals("Normal")) {
          this.FilaOrganizada[this.posFinalO + 1] = "Normal";
          posFinalO++;
          dequeueNP('N');
        }
      }

      //quando i for igual a 1, verificar se o i == 0, é prioritario, se for, aumenta 1 prioritario, se não, continua com normal
      if (i == 1) {
        if (this.FilaPrio[0] != null && this.FilaOrganizada[i - 1] == this.FilaPrio[0]) {
          this.FilaOrganizada[this.posFinalO + 1] = "Prioritario";
          posFinalO++;
          dequeueNP('P');
        } else if (this.FilaPrio[0] != null && this.FilaOrganizada[i - 1] == this.FilaNormal[0]) {
          this.FilaOrganizada[this.posFinalO + 1] = "Prioritario";
          posFinalO++;
          dequeueNP('P');
        } else {
          this.FilaOrganizada[this.posFinalO + 1] = "Normal";
          posFinalO++;
          dequeueNP('N');
        }
      }

      //montagem para a cada 2 prioritarios, ser 1 normal, ou para caso não tenha normal, ou prio na fila
      if (i > 1) {
       if(this.posFinalP != -1 && this.posFinalN != -1){
        if (this.FilaOrganizada[i-1] == this.FilaPrio[0] && this.FilaOrganizada[i-2] == this.FilaPrio[0]) {
          this.FilaOrganizada[this.posFinalO + 1] = "Normal";
          posFinalO++;
          dequeueNP('N');
        }
        else{
          this.FilaOrganizada[this.posFinalO + 1] = "Prioritario";
          posFinalO++;
          dequeueNP('P');
        }
       }

       if(this.posFinalP == -1){
        this.FilaOrganizada[this.posFinalO + 1] = "Normal";
         this.posFinalO++;
         dequeueNP('N');
       }
       if(this.posFinalN == -1){
         this.FilaOrganizada[this.posFinalO + 1] = "Prioritario";
         this.posFinalO++;
         dequeueNP('P');
       }
      }
    }
    return " ";
  }

  // verificar se tem disponibilidade para entrar mais alguém
  public boolean isFull() {
    if (this.posFinal == (this.tamMax - 1)) {
      return true;
    } else {
      return false;
    }
  }

  // peek
  public String peek() {
    return this.FilaGeral[0];
  }

  // tamanho
  public int length() {
    return this.posFinal + 1;
  }

  // imprimir

  public String imprime() {
    organizaFila();
    System.out.println("--------------------------");
    if (isEmpty()) {
      System.out.println("Nada a imprimir - fila vazia");
    } else {
      System.out.println("\n-------Fila Geral-------");

      for (int i = 0; i <= this.posFinal; i++) {
        System.out.println("Senha " + (i + 1) + ": " + this.FilaGeral[i]);
      }
      System.out.println("\n--------------------------\n");
      System.out.println("-------Fila Organizada-------");
      for (int i = 0; i <= this.posFinal; i++) {
        System.out.println("Senha " + (i + 1) + ": " + this.FilaOrganizada[i]);
      }
      System.out.println("\n--------------------------\n");
    }
    return "";
  }
}