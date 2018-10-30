package professorangoti.com.interaocomousuario;

import java.util.ArrayList;

public class Produto extends ArrayList<Produto> {
    private String produto;
    private String valor;

    public Produto() {
    }

    public Produto(String produto, String valor) {
        this.produto = produto;
        this.valor = valor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
