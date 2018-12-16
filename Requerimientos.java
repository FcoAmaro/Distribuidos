import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;

public class Requerimientos {
    private int id;
    private String Cargo;
    private List<Pair> localesrequerimientos;


    public Requerimientos(String cargo, int id,List<Pair> listado) {
        this.Cargo = cargo;
        this.id=id;
        this.localesrequerimientos=listado;
        

    }

    public String getCargo(){
    	return this.Cargo;



    }


     public int getId(){
    	return this.id;



    }

    public List<Pair> getListado(){
        return this.localesrequerimientos;



    }



}
