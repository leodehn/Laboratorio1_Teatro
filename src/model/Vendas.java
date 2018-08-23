package model;
public class Vendas {
    public static final int INTEIRA = 1;
    public static final int MEIA = 2;
    public static final int ISENTO = 3;
    
    private Teatro teatro;
    private Ingresso[] ingressos;
    private int ultimaVenda;

    
    public Vendas() {
        this.teatro = new Teatro();
        ultimaVenda = -1;
        ingressos = new Ingresso[teatro.tamanhoTeatro()];
    }
    
    public boolean novaVenda(int fila, int assento, int tipoIngresso) {
    	/*
    	 * Nunca inserir interação com usuário na lógica de negócio.
    	 */
                    System.out.println(tipoIngresso);
        
        boolean deuCerto = false;
        Ingresso ingresso;
        Cadeira cadeira;
        
        /*
         * Apenas a verificar se a cadeira é nula resolver todo o problema, dispensando
         * o if complexo e ineficiente abaixo.
         */
        if(fila<=teatro.getFileiras()&&fila>0&&assento<=teatro.getCadeiras()&&assento>0){
            if(teatro.vender(fila, assento)) {
                cadeira = teatro.buscaCadeira(fila, assento);

                if(tipoIngresso == INTEIRA)
                    ingresso = new Normal(cadeira);
                else if(tipoIngresso == MEIA)
                    ingresso = new Meia(cadeira);
                else 
                    ingresso = new Isento(cadeira);

                ultimaVenda += 1;
                ingressos[ultimaVenda] = ingresso;
                deuCerto = true;
            }
        } return deuCerto;
    }
    
    public double valorArrecadado() {
        int i=0;
        double soma = 0;
        for(i=0; i<=ultimaVenda; i++) {
            soma += ingressos[i].getValor();
        }
        return soma;
    }
    
    private int cadeirasOcupadas(){
        return ultimaVenda+1;
    }
    
    public int cadeirasRestantes(){
        return teatro.tamanhoTeatro()-cadeirasOcupadas();
    }
    
    
}